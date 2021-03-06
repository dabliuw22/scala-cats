package com.leysoft.user

import akka.actor.ActorSystem
import com.leysoft.user.domain.User
import com.leysoft.user.infrastructure.reactor.{UserPublisherMono, UserRepositoryMono}

object MainReactor extends App {
  implicit val system = ActorSystem("reactor-system")
  val userRepository = UserRepositoryMono()
  val userPublisher = UserPublisherMono()
  import infrastructure.reactor.MonadInstances._
  val userService = application.UserServiceFunctional(userRepository, userPublisher)
  userService.create(User(4, "User4")).subscribe()
  userService.get(5).subscribe()
}
