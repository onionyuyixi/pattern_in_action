package com.example.pattern_in_action.monoid.parallel

sealed trait WC {

}


case class Stub(chars: String) extends WC

case class Part(lStub: String, words: Int, rStub: String) extends WC

