package com.example.pattern_in_action.decorator

class Accumulators {}

class ScalaFirstAccumulator(accumulator: ScalaAccumulator, first: Double)
  extends ScalaAccumulatorDecorator(accumulator) {
  override def compute(): Double = {
    val upperCompute = super.compute
    upperCompute + first
  }
}


class ScalaSecondAccumulator(accumulator: ScalaAccumulator, second: Double, factor: Int)
  extends ScalaAccumulatorDecorator(accumulator) {
  override def compute(): Double = {
    val upperCompute = super.compute
    upperCompute + second * factor
  }
}

class ScalaThirdAccumulator(accumulator: ScalaAccumulator, third: Double, factor: Int, influence: Double)
  extends ScalaAccumulatorDecorator(accumulator) {
  override def compute(): Double = {
    val upperCompute = super.compute
    upperCompute + third * factor - influence
  }
}