package com.example.pattern_in_action.monads


class IntMonad extends Monad[List] {
  override def unit[A](a: => A): List[A] = Nil
}

object IntMonadTest extends App {

  val monad = new IntMonad

  val datas = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val filter: List[List[Int]] = monad.filterM(datas)(a => List(a > 5))

  println(filter)

}
