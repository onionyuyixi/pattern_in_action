package fpinscala
package monads


trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  def distribute[A, B](fab: F[(A, B)]): (F[A], F[B]) =
    (map(fab)(_._1), map(fab)(_._2))

  def codistribute[A, B](e: Either[F[A], F[B]]): F[Either[A, B]] = e match {
    case Left(fa) => map(fa)(Left(_))
    case Right(fb) => map(fb)(Right(_))
  }
}

object Functor {
  val listFunctor = new Functor[List] {
    def map[A, B](as: List[A])(f: A => B): List[B] = as map f
  }
}


trait Monad[F[_]] extends Functor[F] {
  def unit[A](a: => A): F[A]

  //以下三個方法 flatMap compose join 之間都是可以互相轉化的
  //同時可以發現 join一定會跟map搭載在一起使用 才能完成轉換
  //故而我們monad的三種形式就清楚了
  //a----->unit flatMap
  //b----->unit compose
  //c----->unit map join
  def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B] = {
    val flatMapByJoin: F[B] = join(map(ma)(f))
    val flatMapByCompose: F[B] = compose((_: Unit) => ma, f)()
    flatMapByCompose
  }

  def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C] = {
    val composeByFlatMap: A => F[C] = (a: A) => flatMap(f(a))(g)
    val composeByJoin: A => F[C] = a => join(map(f(a))(g))
    composeByFlatMap
  }

  def join[A](mma: F[F[A]]): F[A] = {
    val joinByFlatMap: F[A] = flatMap(mma)(ma => ma)
    val joinByCompose: F[A] = compose((_: Unit) => mma, (ma: F[A]) => ma)()
    joinByCompose
  }


  def map[A, B](ma: F[A])(f: A => B): F[B] =
    flatMap(ma)(a => unit(f(a)))

  def map2[A, B, C](ma: F[A], mb: F[B])(f: (A, B) => C): F[C] =
    flatMap(ma)(a => map(mb)(b => f(a, b)))

  def sequence[A](lma: List[F[A]]): F[List[A]] =
    lma.foldRight(unit(List[A]()))((ma, mla) => map2(ma, mla)(_ :: _))

  def traverse[A, B](la: List[A])(f: A => F[B]): F[List[B]] =
    la.foldRight(unit(List[B]()))((a, mlb) => map2(f(a), mlb)(_ :: _))

  // For `List`, the `replicateM` function will generate a list of lists.
  // It will contain all the lists of length `n` with elements selected from the
  // input list.
  // For `Option`, it will generate either `Some` or `None` based on whether the
  // input is `Some` or `None`. The `Some` case will contain a list of length `n`
  // that repeats the element in the input `Option`.
  // The general meaning of `replicateM` is described very well by the
  // implementation `sequence(List.fill(n)(ma))`. It repeats the `ma` monadic value
  // `n` times and gathers the results in a single value, where the monad `M`
  // determines how values are actually combined.

  // Recursive version:
  def _replicateM[A](n: Int, ma: F[A]): F[List[A]] =
    if (n <= 0) unit(List[A]()) else map2(ma, _replicateM(n - 1, ma))(_ :: _)

  // Using `sequence` and the `List.fill` function of the standard library:
  def replicateM[A](n: Int, ma: F[A]): F[List[A]] =
    sequence(List.fill(n)(ma))


  def filterM[A](ms: List[A])(f: A => F[Boolean]): F[List[A]] =
    ms.foldRight(unit(List[A]()))((x, y) =>
      compose(f, (b: Boolean) => if (b) map2(unit(x), y)(_ :: _) else y)(x))
}

case class Reader[R, A](run: R => A)


case class Id[A](value: A) {
  def map[B](f: A => B): Id[B] = Id(f(value))

  def flatMap[B](f: A => Id[B]): Id[B] = f(value)
}

object Reader {
  def ask[R]: Reader[R, R] = Reader(r => r)
}

