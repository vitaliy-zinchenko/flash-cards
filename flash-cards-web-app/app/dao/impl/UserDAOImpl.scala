package dao.impl

import javax.inject.Inject

import com.google.inject.Singleton
import dao.UserDAO
import model.{Card, User}
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits._
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * @author Vitaliy_Zinchenko
  */
@Singleton
class UserDAOImpl @Inject()(dbConfigProvider: DatabaseConfigProvider) extends UserDAO {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  class UserTable(tag: Tag)
    extends Table[User](tag, "users") {

    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

    def firstName = column[Option[String]]("first_name")

    def lastName = column[Option[String]]("last_name")

    def email = column[Option[String]]("email")

    def providerId = column[String]("provider_id")

    def providerKey = column[String]("provider_key")

    override def * =
      (id, firstName, lastName, email, providerId, providerKey) <> (User.tupled, User.unapply)
  }

  implicit val users = TableQuery[UserTable]

  def add(user: User): Future[User] = {
    db.run((users returning users) += user)
  }

  override def update(user: User): Future[Int] = {
    db.run(users.filter(_.id === user.id).update(user))
  }

  def delete(id: Long): Future[Int] = {
    db.run(users.filter(_.id === id).delete)
  }

  def get(id: Long): Future[Option[User]] = {
    db.run(users.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[User]] = {
    db.run(users.result)
  }

  override def find(email: String): Future[Option[User]] = {
    db.run(users.filter(_.email === email).result.headOption)
  }

  override def find(providerId: String, providerKey: String): Future[Option[User]] = {
    db.run(
      users
        .filter(_.providerId === providerId)
        .filter(_.providerKey === providerKey)
        .result.headOption
    )
  }
}
