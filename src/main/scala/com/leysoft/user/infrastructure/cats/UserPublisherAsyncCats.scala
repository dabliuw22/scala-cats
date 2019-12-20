package com.leysoft.user.infrastructure.cats

import cats.effect.IO
import com.leysoft.user.domain.{User, UserPublisher}

final case class UserPublisherAsyncCats() extends UserPublisher[IO] {

  override def publish(user: User): IO[User] = IO { user }
}
