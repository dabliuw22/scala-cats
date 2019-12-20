package com.leysoft.user

import akka.actor.ActorSystem
import com.leysoft.user.application.UserServiceFunctional
import com.leysoft.user.domain.User
import com.leysoft.user.infrastructure.{UserPublisherMonix, UserRepositoryMonix}
import monix.execution.{Ack, Scheduler}

import scala.concurrent.Future

object MainMonix extends App {
  implicit val system = ActorSystem("monix-system")
  implicit val scheduler = Scheduler.global
  val userRepository = UserRepositoryMonix()
  val userPublisher = UserPublisherMonix()
  val userService = UserServiceFunctional(userRepository, userPublisher)
  userService.create(User(4, "User4"))
    .subscribe({ element => println(s"User: $element"); Future { Ack.Continue } },
      { error => println(s"Error: $error") })
  userService.get(5).subscribe({ element => println(s"User: $element"); Future { Ack.Continue } },
    { error => println(s"Error: $error") })
}
