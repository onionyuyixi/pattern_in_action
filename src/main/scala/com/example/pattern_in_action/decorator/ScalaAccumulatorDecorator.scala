package com.example.pattern_in_action.decorator

class ScalaAccumulatorDecorator(accumulator: ScalaAccumulator) extends ScalaAccumulator {
  override def compute: Double = accumulator.compute
}
