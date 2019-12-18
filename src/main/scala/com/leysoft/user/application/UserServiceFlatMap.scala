package com.leysoft.user.application

import cats.FlatMap
import cats.syntax.flatMap._
import com.leysoft.user.domain.{User, UserPublisher, UserRepository}

final case class UserServiceFlatMap[T[_]](repository: UserRepository[T], publisher: UserPublisher[T])
                                         (implicit flatMap: FlatMap[T]) extends UserService[T] {

  override def create(user: User): T[User] = repository.save(user)
    .flatMap { publisher.publish }

  override def get(id: Int): T[User] = repository.findBy(id)

  override def getAll: T[Seq[User]] = repository.findAll
}
