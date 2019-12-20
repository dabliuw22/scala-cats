package com.leysoft.user.infrastructure

import com.leysoft.user.domain.{User, UserPublisher}

final case class UserPublisherOption() extends UserPublisher[Option] {

  override def publish(user: User): Option[User] = Option { user }
}
