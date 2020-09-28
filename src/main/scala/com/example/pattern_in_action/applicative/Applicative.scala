//package com.example.pattern_in_action.applicative
//
//import fpinscala.monads.Functor
//
//
//
//trait Applicative[F[_]] extends Functor[F] {
//
//  //整合两次apply即可
//  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] = {
//    val curried: A => B => C = f.curried
//    val fUnit: F[A => B => C] = unit(curried)
//    val function: F[B => C] = apply(fUnit)(fa)
//    val result: F[C] = apply(function)(fb)
//    apply(apply(unit(f.curried))(fa))(fb)
//  }
//
//  def apply[A,B](fa:)
