package com.leysoft.user.infrastructure

import com.leysoft.user.domain.{User, UserPublisher}
import monix.reactive.Observable

final case class UserPublisherMonix() extends UserPublisher[Observable] {

  override def publish(user: User): Observable[User] = Observable.apply { user }
}
