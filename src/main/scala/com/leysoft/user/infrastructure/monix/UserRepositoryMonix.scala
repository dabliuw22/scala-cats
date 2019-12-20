package com.leysoft.user.infrastructure.monix

import com.leysoft.user.domain.{User, UserRepository}
import monix.reactive.Observable

final case class UserRepositoryMonix() extends UserRepository[Observable] {

  private val users = collection.mutable.Map[Int, User](
    1 -> User(1, "User1"),
    1 -> User(2, "User2"),
    3 -> User(3, "User3")
  )

  override def save(user: User): Observable[User] = Observable.apply {
    users.put(user.id, user) match {
      case Some(value) => value
      case _ => user
    }
  }

  override def findBy(id: Int): Observable[User] = users.get(id) match {
    case Some(value) => Observable.apply(value)
    case _ => Observable.raiseError(new RuntimeException)
  }

  override def findAll: Observable[Seq[User]] = Observable.apply(users.values.toList)
}
