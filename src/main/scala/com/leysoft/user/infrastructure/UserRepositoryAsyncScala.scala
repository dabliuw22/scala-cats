package com.leysoft.user.infrastructure

import com.leysoft.user.domain.{User, UserRepository}

import scala.concurrent.{ExecutionContext, Future}

final case class UserRepositoryAsyncScala()(implicit context: ExecutionContext) extends UserRepository[Future] {

  private val users = collection.mutable.Map[Int, User](
    1 -> User(1, "User1"),
    1 -> User(2, "User2"),
    3 -> User(3, "User3")
  )

  override def save(user: User): Future[User] = Future {
    users.put(user.id, user) match {
      case Some(value) => value
      case _ => user
    }
  }

  override def findBy(id: Int): Future[User] = Future {
    users.get(id) match {
      case Some(value) => value
      case _ => throw new RuntimeException
    }
  }

  override def findAll: Future[Seq[User]] = Future { users.values.toList }
}
