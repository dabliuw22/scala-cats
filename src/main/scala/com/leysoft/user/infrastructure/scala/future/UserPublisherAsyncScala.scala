package com.leysoft.user.infrastructure.scala.future

import com.leysoft.user.domain.{User, UserPublisher}

import scala.concurrent.{ExecutionContext, Future}

final case class UserPublisherAsyncScala()(implicit context: ExecutionContext) extends UserPublisher[Future] {

  override def publish(user: User): Future[User] = Future { user }
}
