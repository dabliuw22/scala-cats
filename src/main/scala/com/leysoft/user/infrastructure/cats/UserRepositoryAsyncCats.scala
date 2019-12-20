package com.leysoft.user.infrastructure.cats

import cats.effect.IO
import com.leysoft.user.domain.{User, UserRepository}

final case class UserRepositoryAsyncCats() extends UserRepository[IO] {

  private val users = collection.mutable.Map[Int, User](
    1 -> User(1, "User1"),
    1 -> User(2, "User2"),
    3 -> User(3, "User3")
  )

  override def save(user: User): IO[User] = IO {
    users.put(user.id, user) match {
      case Some(value) => value
      case _ => user
    }
  }

  override def findBy(id: Int): IO[User] = IO {
    users.get(id) match {
      case Some(value) => value
      case _ => throw new RuntimeException
    }
  }

  override def findAll: IO[Seq[User]] = IO { users.values.toList }
}
