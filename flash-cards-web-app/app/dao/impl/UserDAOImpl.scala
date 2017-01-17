package dao.impl

import javax.inject.Inject

import com.google.inject.Singleton
import dao.UserDAO
import model.User
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

  class UserTable(tag:Tag)
    extends Table[User](tag, "users") {
    def id = column[Long]("id", O.PrimaryKey,O.AutoInc)
    def firstName = column[String]("first_name")
    def lastName = column[String]("last_name")
    def email = column[String]("email")

    override def * =
      (id, firstName,lastName,email) <> (User.tupled, User.unapply)
  }

  implicit val users = TableQuery[UserTable]

  def add(user: User): Future[String] = {
    db.run(users += user).map(res => "User successfully added").recover {
      case ex : Exception => ex.getCause.getMessage
    }
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
}
