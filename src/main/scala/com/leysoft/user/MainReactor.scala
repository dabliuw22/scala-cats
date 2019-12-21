package com.leysoft.user

import akka.actor.ActorSystem
import com.leysoft.user.application.UserServiceFunctional
import com.leysoft.user.domain.User
import com.leysoft.user.infrastructure.reactor.{UserPublisherMono, UserRepositoryMono}

import scala.compat.java8.FunctionConverters._

object MainReactor extends App {
  implicit val system = ActorSystem("reactor-system")
  val userRepository = UserRepositoryMono()
  val userPublisher = UserPublisherMono()
  import infrastructure.reactor.MonadInstances._
  val userService = UserServiceFunctional(userRepository, userPublisher)
  userService.create(User(4, "User4")).subscribe(asJavaConsumer({ element => println(s"User: $element") }),
      asJavaConsumer({ error => println(s"Error: $error") }))

  userService.get(5)
    .subscribe(asJavaConsumer({ element => println(s"User: $element") }),
      asJavaConsumer({ error => println(s"Error: $error") }))
}
