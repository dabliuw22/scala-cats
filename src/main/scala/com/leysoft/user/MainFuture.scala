package com.leysoft.user

import akka.actor.ActorSystem
import cats.implicits._
import com.leysoft.user.application.UserServiceFlatMap
import com.leysoft.user.domain.User
import com.leysoft.user.infrastructure.{UserPublisherAsyncScala, UserRepositoryAsyncScala}

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object MainFuture extends App {
  implicit val system = ActorSystem("future-system")
  implicit val context: ExecutionContext = scala.concurrent.ExecutionContext.global
  val userRepository = UserRepositoryAsyncScala()
  val userPublisher = UserPublisherAsyncScala()
  val userService = UserServiceFlatMap(userRepository, userPublisher)
  userService.create(User(4, "User4")).onComplete {
    case Success(value) => println(s"User: $value")
    case Failure(error) => println(s"Error: $error")
  }

  userService.getAll.onComplete {
    case Success(value) => println(s"Users: $value")
    case Failure(error) => println(s"Error: $error")
  }
}
