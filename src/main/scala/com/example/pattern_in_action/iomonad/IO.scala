package com.example.pattern_in_action.iomonad


import scala.io.StdIn.readLine

object IO0 extends App {

  /*
    这里self对应的函数run()返回结果是Unit 这样将一个action(区别于transform) wrapper到IO中 delegate他的子类
   */

  trait IO {
    self =>
    def run(): Unit

    def ++(io: IO): IO = new IO {
      override def run(): Unit = {
        self.run()
        io.run()
      }
    }
  }

  object IO {
    val empty: IO = new IO {
      override def run(): Unit = () //要重写self可以引用的方法 这不就颇像抽象方法
    }
  }

  def dataConvert(f: Double): Double = (f - 32) * 5.0 / 9.0

  def convert: Unit = {
    println("输入数据:")
    val data = readLine.toDouble
    println(dataConvert(data))
  }


  convert

  def convertByIO: IO = {
    println("IO式输入数据:")
    val data = readLine.toDouble
    new IO {
      override def run(): Unit = println(dataConvert(data))
    }
  }

  convertByIO.run()

  //convertByIO 与 convert 相比算是有FP的特征了  返回的是一个对象了 而不是直接取执行某个action
}


object IO1 extends App {

  /*
  添加type parameter 可以实现多种action的处理 并结合Monad 可以进行数据的不停转换处理
  */

  sealed trait IO[A] {
    self =>
    def run: A

    def map[B](f: A => B): IO[B] = new IO[B] {
      override def run: B = f(self.run)
    }

    def flatMap[B](f: A => IO[B]): IO[B] = new IO[B] {
      override def run: B = f(self.run).run
    }
  }

  object IO extends Monad[IO] {
    def unit[A](a: => A): IO[A] = new IO[A] {
      def run: A = a
    }

    def flatMap[A, B](fa: IO[A])(f: A => IO[B]): IO[B] = fa flatMap f

    def apply[A](a: => A): IO[A] = unit(a)

    def ref[A](a: A): IO[IORef[A]] = IO {
      new IORef[A](a)
    }

    //妙啊
    sealed class IORef[A](var value: A) {
      def set(a: A): IO[A] = IO {
        value = a
        a
      }

      def get: IO[A] = IO {
        value
      }

      //此处有整合之神 正是monad之用 用同一个category 则相近相习
      def modify(f: A => A): IO[A] = get flatMap (a => set(f(a)))

    }

  }

  def ReadLine: IO[String] = IO {
    println("输入内容:")
    readLine
  }

  def PrintLine(msg: String): IO[Unit] = IO {
    println(msg)
  }

  import IO0.dataConvert

  //采用for yield语法糖果优化
  def convert: IO[Unit] = for {
    _ <- PrintLine("输入数据")
    d <- ReadLine.map(_.toDouble)
    _ <- PrintLine(dataConvert(d).toString)
  } yield ()

  import IO._

  val echo: IO[Unit] = ReadLine.flatMap(PrintLine)
  echo.run

  private val readInt: IO[Int] = ReadLine.map(_.toInt)

  private val readInts: IO[(Int, Int)] = readInt ** readInt

  println(readInts.run)


}


object IO2a {

  /*
  IO1 由于递归的调用run可能会导致栈溢出错误
  一般性的解决办法
  设定IO是一种数据类型 然后采用匹配 尾递归来进行优化
   */

  sealed trait IO[A] {
    def flatMap[B](f: A => IO[B]): IO[B] = FlatMap(this, f)

    def map[B](f: A => B): IO[B] = flatMap(f andThen (Return(_)))
  }

  case class Return[A](a: A) extends IO[A]

  case class Suspend[A](resume: () => A) extends IO[A]

  case class FlatMap[A, B](sub: IO[A], k: A => IO[B]) extends IO[B]

  object IO extends Monad[IO] {
    def unit[A](a: => A): IO[A] = Return(a)

    def flatMap[A, B](a: IO[A])(f: A => IO[B]): IO[B] = a flatMap f

    def suspend[A](a: => IO[A]): IO[A] = Suspend(() => ()).flatMap(_ => a)
  }

  def printLine(s: String): IO[Unit] = Suspend(() => Return(println(s)))


  def run[A](io: IO[A]): A = io match {
    case Return(a) => a
    case Suspend(r) => r()
    case FlatMap(x, f) => x match {
      case Return(a) => run(f(a))
      case Suspend(r) => run(f(r()))
      case FlatMap(y, g) => run(y flatMap (a => g(a) flatMap f))
    }
  }
}


object IO2aTest extends App {

  import IO2a._

  val f: Int => IO[Int] = (i: Int) => Return(i)

  val g: Int => IO[Int] = List.fill(1000)(f).foldLeft(f) {
    (a: (Int) => IO[Int], b: (Int) => IO[Int]) => {
      (x: Int) => IO.suspend(a(x).flatMap(b))
    }
  }

  val gFortyTwo = g(42)

  println(gFortyTwo)

  println(run(gFortyTwo))


}


object IO2b {

  /*
  IO2b IO2a 除了用名不同 实质是一
   */
  /*
  * As it turns out, there's nothing about this data type that is specific
  * to I/O, it's just a general purpose data type for optimizing tail calls.
  * Here it is, renamed to `TailRec`. This type is also sometimes called
  * `Trampoline`, because of the way interpreting it bounces back and forth
  * between the main `run` loop and the functions contained in the `TailRec`.
  */
  //蹦床模式的讲解 可以参详Java Trampoline pattern
  sealed trait TailRec[A] {
    def flatMap[B](f: A => TailRec[B]): TailRec[B] = FlatMap(this, f)

    def map[B](f: A => B): TailRec[B] = flatMap(f andThen (Return(_)))
  }

  case class Return[A](a: A) extends TailRec[A]

  case class Suspend[A](resume: () => A) extends TailRec[A]

  case class FlatMap[A, B](sub: TailRec[A], k: A => TailRec[B]) extends TailRec[B]

  object TailRec extends Monad[TailRec] {
    override def unit[A](a: => A): TailRec[A] = Return(a)

    override def flatMap[A, B](a: TailRec[A])(f: A => TailRec[B]): TailRec[B] = a flatMap f

    def suspend[A](a: => TailRec[A]) = Suspend(() => ()).flatMap(_ => a)
  }

  //每一个状态 都由run来完成  形如蹦床 来回往返的意思
  @annotation.tailrec def run[A](t: TailRec[A]): A = t match {
    case Return(a) => a
    case Suspend(r) => r()
    case FlatMap(x, f) => x match {
      case Return(a) => run(f(a))
      case Suspend(r) => run(f(r()))
      case FlatMap(y, g) => run(y flatMap (a => g(a) flatMap f))
    }
  }
}

object IO2bTests extends App {


  import IO2b._

  val f: Int => TailRec[Int] = (i: Int) => Return(i)

  val g: Int => TailRec[Int] = List.fill(1000)(f).foldLeft(f) {
    (a: (Int => TailRec[Int]), b: (Int => TailRec[Int])) =>
      x => TailRec.suspend(a(x).flatMap(b))
  }

  val gFortyTwo = g(42)
  println(gFortyTwo)
  println(run(gFortyTwo))


}


object IO3 {

  sealed trait Free[F[_], A] {
    def flatMap[B](f: A => Free[F, B]): Free[F, B] =
      FlatMap(this, f)

    def map[B](f: A => B): Free[F, B] =
      flatMap(f andThen (Return(_)))
  }


  case class Return[F[_], A](a: A) extends Free[F, A]

  case class Suspend[F[_], A](s: F[A]) extends Free[F, A]

  case class FlatMap[F[_], A, B](s: Free[F, A], f: A => Free[F, B]) extends Free[F, B]


  def freeMonad[F[_]]: Monad[({type f[a] = Free[F, a]})#f] =
    new Monad[({
      type f[a] = Free[F, a]
    })#f] {
      def unit[A](a: => A): Free[F, A] = Return(a)

      def flatMap[A, B](a: Free[F, A])(f: A => Free[F, B]): Free[F, B] =
        a flatMap f
    }

  @annotation.tailrec
  def runTrampoline[A](a: Free[Function0, A]): A = a match {
    case Return(a) => a
    case Suspend(r) => r()
    case FlatMap(x, f) => x match {
      case Return(a) => runTrampoline(f(a))
      case Suspend(r) => runTrampoline(f(r()))
      case FlatMap(a0, g) => runTrampoline {
        a0 flatMap (a0 => g(a0) flatMap f
      }
    }
  }

  @annotation.tailrec
  def step[F[_], A](a: Free[F, A]): Free[F, A] = a match {
    case FlatMap(FlatMap(x, f), g) => step(x flatMap (a => f(a) flatMap (g)))
    case FlatMap(Return(x), f) => step(f(x))
  }

  def run[F[_], A](a: Free[F, A])(implicit F: Monad[F]): F[A] = step(a) match {
    case Return(a) => F.unit(a)
    case Suspend(r) => r
    case FlatMap(Suspend(r), f) => F.flatMap(r)(a => run(f(a)))
    case _ => sys.error("Impossible, since `step` eliminates these cases")
  }
}




