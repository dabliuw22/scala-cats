package com.leysoft.user

import akka.actor.ActorSystem
import com.leysoft.user.application.UserServiceFlatMap
import com.leysoft.user.domain.User
import com.leysoft.user.infrastructure.{UserPublisherAsyncCats, UserRepositoryAsyncCats}

object MainCats extends App {
  implicit val system = ActorSystem("cats-system")
  val userRepository = UserRepositoryAsyncCats()
  val userPublisher = UserPublisherAsyncCats()
  val userService = UserServiceFlatMap(userRepository, userPublisher)
  userService.create(User(4, "User4"))
}
