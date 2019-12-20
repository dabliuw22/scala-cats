package com.leysoft.user

import akka.actor.ActorSystem
import com.leysoft.user.application.UserServiceFunctional
import com.leysoft.user.domain.User
import com.leysoft.user.infrastructure.scala.future.{UserPublisherAsyncScala, UserRepositoryAsyncScala}

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object MainFuture extends App {
  implicit val system = ActorSystem("future-system")
  implicit val context: ExecutionContext = scala.concurrent.ExecutionContext.global
  val userRepository = UserRepositoryAsyncScala()
  val userPublisher = UserPublisherAsyncScala()
  import cats.instances.future._ // or import cats.instances._
  val userService = UserServiceFunctional(userRepository, userPublisher)
  userService.create(User(4, "User4")).onComplete {
    case Success(value) => println(s"User: $value")
    case Failure(error) => println(s"Error: $error")
  }

  userService.getAll.onComplete {
    case Success(value) => println(s"Users: $value")
    case Failure(error) => println(s"Error: $error")
  }
}
