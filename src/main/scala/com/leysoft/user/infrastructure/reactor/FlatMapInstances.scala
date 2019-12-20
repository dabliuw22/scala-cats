package com.leysoft.user.infrastructure.reactor

import reactor.core.publisher.Mono
import scala.compat.java8.FunctionConverters._

object FlatMapInstances {
  import cats.FlatMap

  implicit val monoFlatMap = new FlatMap[Mono] {

    override def flatMap[A, B](fa: Mono[A])(f: A => Mono[B]): Mono[B] = fa.flatMap(asJavaFunction(f))

    override def map[A, B](fa: Mono[A])(f: A => B): Mono[B] = fa.map(asJavaFunction(f))

    override def tailRecM[A, B](a: A)(f: A => Mono[Either[A, B]]): Mono[B] = ???
  }
}
