package com.leysoft.user.domain

trait UserPublisher[T[_]] {

  def publish(user: User): T[User]
}
