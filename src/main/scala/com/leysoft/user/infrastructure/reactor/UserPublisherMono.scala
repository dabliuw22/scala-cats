package com.leysoft.user.infrastructure.reactor

import com.leysoft.user.domain.{User, UserPublisher}
import reactor.core.publisher.Mono

final case class UserPublisherMono() extends UserPublisher[Mono] {

  override def publish(user: User): Mono[User] = Mono.just { user }
}
