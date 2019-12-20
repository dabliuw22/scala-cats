package com.leysoft.user

import akka.actor.ActorSystem
import com.leysoft.user.application.UserServiceFunctional
import com.leysoft.user.domain.User
import com.leysoft.user.infrastructure.cats.{UserPublisherAsyncCats, UserRepositoryAsyncCats}

object MainCats extends App {
  implicit val system = ActorSystem("cats-system")
  val userRepository = UserRepositoryAsyncCats()
  val userPublisher = UserPublisherAsyncCats()
  val userService = UserServiceFunctional(userRepository, userPublisher)
  userService.create(User(4, "User4"))
}
