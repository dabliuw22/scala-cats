package com.leysoft.user.infrastructure.scala.option

import com.leysoft.user.domain.{User, UserRepository}

final case class UserRepositoryOption() extends UserRepository[Option] {

  private val users = collection.mutable.Map[Int, User](
    1 -> User(1, "User1"),
    1 -> User(2, "User2"),
    3 -> User(3, "User3")
  )

  override def save(user: User): Option[User] = Option {
    users.put(user.id, user) match {
      case Some(value) => value
      case _ => user
    }
  }

  override def findBy(id: Int): Option[User] = users.get(id)

  override def findAll: Option[Seq[User]] = Option { users.values.toList }
}
