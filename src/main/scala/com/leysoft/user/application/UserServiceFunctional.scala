package com.leysoft.user.application

import cats.{Functor, Monad}
import cats.syntax.flatMap._
import cats.syntax.functor._
import com.leysoft.user.domain.{User, UserPublisher, UserRepository}

final case class UserServiceFunctional[T[_]](repository: UserRepository[T], publisher: UserPublisher[T])
                                            (implicit monad: Monad[T], functor: Functor[T]) extends UserService[T] {

  override def create(user: User): T[User] = repository.save(user)
    .flatMap { publisher.publish }
    .map { user => user }

  override def get(id: Int): T[User] = repository.findBy(id)

  override def getAll: T[Seq[User]] = repository.findAll


}
