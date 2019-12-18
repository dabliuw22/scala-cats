package com.leysoft.user.domain

trait UserRepository[T[_]] {

  def save(user: User): T[User]

  def findBy(id: Int): T[User]

  def findAll: T[Seq[User]]
}
