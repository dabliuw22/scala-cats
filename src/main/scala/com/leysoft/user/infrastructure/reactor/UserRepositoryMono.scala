package com.leysoft.user.infrastructure.reactor

import com.leysoft.user.domain.{User, UserRepository}
import reactor.core.publisher.Mono

final case class UserRepositoryMono() extends UserRepository[Mono] {

  private val users = collection.mutable.Map[Int, User](
    1 -> User(1, "User1"),
    1 -> User(2, "User2"),
    3 -> User(3, "User3")
  )

  override def save(user: User): Mono[User] = Mono.just {
    users.put(user.id, user) match {
      case Some(value) => value
      case _ => user
    }
  }

  override def findBy(id: Int): Mono[User] = users.get(id) match {
    case Some(value) => Mono.just(value)
    case _ => Mono.error(new RuntimeException)
  }

  override def findAll: Mono[Seq[User]] = Mono.just(users.values.toList)
}
