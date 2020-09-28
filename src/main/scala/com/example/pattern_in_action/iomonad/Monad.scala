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

  def replicateM_[A](n: Int)(f: F[A]): F[Unit] = foreachM(LazyList.fill(n)(f))(skip)


  def foreachM[A](l: LazyList[A])(f: A => F[Unit]): F[Unit] =
    foldM_(l)(())((u, a) => skip(f(a)))

  def foldM_[A, B](l: LazyList[A])(z: B)(f: (B, A) => F[B]): F[Unit] =
    skip {
      foldM(l)(z)(f)
    }

  def foldM[A, B](l: LazyList[A])(z: B)(f: (B, A) => F[B]): F[B] =
    l match {
      case h #:: t => f(z, h)
      case _ => unit(z)
    }


  implicit def toMonadic[A](a:F[A]):Monadic[F,A] = new Monadic[F,A] {
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

  def replicateM_(n: Int) = F.replicateM_(n)(a)
}







