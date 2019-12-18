package com.leysoft.user.application

import com.leysoft.user.domain.User

trait UserService[T[_]] {

  def create(user: User): T[User]

  def get(id: Int): T[User]

  def getAll: T[Seq[User]]
}
