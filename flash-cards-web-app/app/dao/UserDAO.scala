package dao

import com.google.inject.ImplementedBy
import dao.impl.UserDAOImpl
import model.User

import scala.concurrent.Future

@ImplementedBy(classOf[UserDAOImpl])
trait UserDAO {

  def add(user:User) : Future[User]
  def update(user: User) : Future[Int]
  def get(id : Long) : Future[Option[User]]
  def find(email : String) : Future[Option[User]]
  def find(providerId: String, providerKey: String) : Future[Option[User]]
  def delete(id : Long) : Future[Int]
  def listAll : Future[Seq[User]]

}
