package dao.impl.auth.slick

import javax.inject.Inject

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.persistence.daos.DelegableAuthInfoDAO
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Vitalii Zinchenko
  */
class PasswordAuthInfoDao @Inject()(dbConfigProvider: DatabaseConfigProvider) extends DelegableAuthInfoDAO[PasswordInfo] {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  class PasswordInfoTable(tag:Tag)
    extends Table[Password](tag, "password_info") {
    def providerId = column[String]("provider_id", O.PrimaryKey)
    def providerKey = column[String]("provider_key", O.PrimaryKey)
    def hasher = column[String]("hasher")
    def password = column[String]("password")
    def salt = column[Option[String]]("salt")


    override def * =
      (providerId, providerKey, hasher, password, salt) <> (Password.tupled, Password.unapply)
  }

  case class Password(providerId: String, providerKey: String, hasher: String, password: String, salt: Option[String])

  implicit val passwordInfo = TableQuery[PasswordInfoTable]

  override def find(loginInfo: LoginInfo): Future[Option[PasswordInfo]] = {
    val action = passwordInfo.filter(_.providerId === loginInfo.providerID).filter(_.providerKey === loginInfo.providerKey).result.headOption
    db.run(action).map(convert)
  }

  override def add(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] = {
    db.run((passwordInfo returning passwordInfo) += convert(loginInfo, authInfo)).map(convert)
  }

  override def update(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] = ???

  override def save(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] = ???

  override def remove(loginInfo: LoginInfo): Future[Unit] = ???

  private def convert(password: Option[Password]): Option[PasswordInfo] = password.map(convert)

  private def convert(password: Password): PasswordInfo = PasswordInfo(password.hasher, password.password, password.salt)

  private def convert(loginInfo: LoginInfo, authInfo: PasswordInfo): Password = {
    Password(loginInfo.providerID, loginInfo.providerKey, authInfo.hasher, authInfo.password, authInfo.salt)
  }
}
