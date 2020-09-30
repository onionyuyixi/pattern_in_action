package com.example.pattern_in_action.iomonad

trait Functor[F[_]] {
  def map[A, B](a: F[A])(f: A => B): F[B]
}


trait Monad[F[_]] extends Functor[F] {
  def unit[A](a: => A): F[A]

  def flatMap[A, B](a: F[A])(f: A => F[B]): F[B]

  def map[A, B](a: F[A])(f: A => B): F[B] = {
    flatMap(a)(a => unit(f(a)))
  }

  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] =
    flatMap(fa)(a => map(fb)(b => f(a, b)))

  //      = {
  //      val afc: A => F[C] = (a: A) => map(fb)(b => f.curried.apply(a).apply(b))
  //      val result: F[C] = flatMap(fa)(afc)
  //      result
  //    }

  def as[A, B](a: F[A])(b: B): F[B] = map(a)(_ => b)

  def skip[A](a: F[A]): F[Unit] = as(a)(())

  def replicateM[A](n: Int)(f: F[A]): F[List[A]] =
    LazyList.fill(n)(f).foldRight(unit(List[A]()))(map2(_, _)(_ :: _))

//  def replicateM_[A](n: Int)(f: F[A]): F[Unit] = foreachM(LazyList.fill(n)(f))(skip)


  def foreachM[A](l: LazyList[A])(f: A => F[Unit]): F[Unit] =
    foldM_(l)(())((u, a) => skip(f(a)))

  def foldM_[A, B](l: LazyList[A])(z: B)(f: (B, A) => F[B]): F[Unit] =
    skip {
      foldM(l)(z)(f)
    }

  def foldM[A, B](l: LazyList[A])(z: B)(f: (B, A) => F[B]): F[B] =
    l match {
      case h #:: t => f(z, h) flatMap (z2 => foldM(t)(z2)(f))
      case _ => unit(z)
    }

//  def sequence_[A](fs: LazyList[F[A]]): F[Unit] = foreachM(fs)(skip)

//  def when[A](b: Boolean)(fa: => F[A]): F[Boolean] = if (b) as(fa)(true) else unit(fa)

  def forever[A, B](a: F[A]): F[B] = {
    lazy val t: F[B] = a flatMap (_ => t)
    t
  }

//  def while_(a: F[Boolean])(b: F[Unit]): F[Unit] = {
//    lazy val t: F[Unit] = while_(a)(b)
//    a flatMap (c => skip(when(c)(t)))
//  }

  def doWhile[A](a: F[A])(cond: A => F[Boolean]): F[Unit] = for {
    a1 <- a
    ok <- cond(a1)
    _ <- if (ok) doWhile(a)(cond) else unit(())
  } yield ()


//def foreachM[A](l:LazyList[A])(f:A=>F[Unit]):F[Unit] = foldM_(l)(())((u,a)=>skip(f(a)))


  implicit def toMonadic[A](a: F[A]): Monadic[F, A] = new Monadic[F, A] {
    override val F: Monad[F] = Monad.this

    override def get: F[A] = a
  }

}


//这里神似代理模式
trait Monadic[F[_], A] {

  val F: Monad[F]

  import F._

  def get: F[A]

  private val a = get

  def map[B](f: A => B): F[B] = F.map(a)(f)

  def flatMap[B](f: A => F[B]) = F.flatMap(a)(f)

  def **[B](b: F[B]): F[(A, B)] = F.map2(a, b)((_, _))

  def *>[B](b: F[B]): F[B] = F.map2(a, b)((_, b) => b)

  def map2[B, C](b: F[B])(f: (A, B) => C): F[C] = F.map2(a, b)(f)

  def as[B](b: B): F[B] = F.as(a)(b)

  def skip: F[Unit] = F.skip(a)

  def replicateM(n: Int) = F.replicateM(n)(a)

//  def replicateM_(n: Int) = F.replicateM_(n)(a)

  //  def foreachM


}


object Test extends App {

  val intMonad = new Monad[List] {
    override def unit[Int](a: => Int): List[Int] = List(a)

    override def flatMap[Int, B](a: List[Int])(f: Int => List[B]): List[B] = a flatMap f
  }

  val datas: LazyList[Int] = LazyList.range(1, 10)

  val result: List[Int] = intMonad.foldM(datas)(0)((b, a) => intMonad.unit(b + a))

  println(result)

  intMonad.skip(List(1))
}




