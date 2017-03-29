package dao.impl.auth

import com.mohiva.play.silhouette.api.{AuthInfo, LoginInfo}
import com.mohiva.play.silhouette.persistence.daos.DelegableAuthInfoDAO

import scala.concurrent.Future
import scala.reflect.ClassTag

/**
  * Created by Vitalii Zinchenko
  */
class NotImplementedAuthInfoDao[T <: AuthInfo: ClassTag] extends DelegableAuthInfoDAO[T] {
  override def find(loginInfo: LoginInfo): Future[Option[T]] = ???

  override def add(loginInfo: LoginInfo, authInfo: T): Future[T] = ???

  override def update(loginInfo: LoginInfo, authInfo: T): Future[T] = ???

  override def save(loginInfo: LoginInfo, authInfo: T): Future[T] = ???

  override def remove(loginInfo: LoginInfo): Future[Unit] = ???
}
