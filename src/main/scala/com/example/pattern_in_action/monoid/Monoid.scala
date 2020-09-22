package com.example.pattern_in_action.monoid

trait Monoid[T] {

  def unit: T;

  def op(a: T, b: T): T;

}


class EndoFunction[A] extends Monoid[A => A] {
  override def unit: A => A = a => a

  override def op(a: A => A, b: A => A): A => A = a.andThen(b)
}

object TestMonoid extends App {
  val stringMonoid = new Monoid[String] {
    override def unit: String = ""

    override def op(a: String, b: String): String = a + b
  }

  val strs = List("1", "----", "2")
  val result = strs.foldRight(stringMonoid.unit)(stringMonoid.op)
  val leftResult = strs.foldLeft(stringMonoid.unit)(stringMonoid.op)
  val leftResult1 = strs.foldLeft("")((a: String, b: String) => a + b)

  println(result == leftResult)

  val intEndo = new EndoFunction[Int]

  def concatenate[A](as: List[A], m: Monoid[A]): A = {
    as match {
      case Nil => m.unit
      case ::(head, next) => as.foldRight(m.unit)(m.op)
      case _ => m.unit
    }
  }

  def foldMap[A, B](as: List[A], m: Monoid[B])(f: A => B): B = {
    as match {
      case Nil => m.unit
      case ::(head, next) => as.foldRight(m.unit)((a, b) => m.op(f.apply(a), b))
      case _ => m.unit
    }
  }


}