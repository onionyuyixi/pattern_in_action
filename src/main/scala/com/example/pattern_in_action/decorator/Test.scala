package com.example.pattern_in_action.decorator

object Test extends App {

  val base = new ScalaBaseAccumulator

  val first = new ScalaFirstAccumulator(base, 1.0)

  val second = new ScalaSecondAccumulator(first, 2.0, 3)

  val third = new ScalaThirdAccumulator(second, 2.0, 3, 0.667)

  val result = third.compute

  print(result)
}
