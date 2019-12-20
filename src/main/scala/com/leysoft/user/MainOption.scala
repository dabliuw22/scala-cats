package com.leysoft.user

import akka.actor.ActorSystem
import com.leysoft.user.application.UserServiceFunctional
import com.leysoft.user.domain.User
import com.leysoft.user.infrastructure.scala.option.{UserPublisherOption, UserRepositoryOption}

object MainOption extends App {
  implicit val system = ActorSystem("option-system")
  val userRepository = UserRepositoryOption()
  val userPublisher = UserPublisherOption()
  import cats.instances.option._ // or import cats.instances._
  val userService = UserServiceFunctional(userRepository, userPublisher)
  userService.create(User(4, "User4")) match {
    case Some(value) => println(s"User: $value")
    case None => println("Error")
  }

  userService.getAll match {
    case Some(value) => println(s"Users: $value")
    case None => println("Error")
  }
}
