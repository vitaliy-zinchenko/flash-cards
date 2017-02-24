package services

import java.util.UUID

import com.google.inject.Inject
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.providers.CommonSocialProfile
import dao.UserDAO
import model.User

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by vitaliizinchenko on 2/16/17.
  */
class UserServiceImpl @Inject()(userDAO: UserDAO) extends UserService {

  override def retrieve(loginInfo: LoginInfo): Future[Option[User]] = userDAO.find(loginInfo.providerID, loginInfo.providerKey)

  override def save(user: User): Future[User] = userDAO.add(user)

  override def save(profile: CommonSocialProfile): Future[User] = {
    userDAO.find(profile.email.get).flatMap { //TODO remove '.get'
      case Some(user) => // Update user with profile
//        userDAO.update(//user.copy(
//          firstName = profile.firstName,
//          lastName = profile.lastName,
//          fullName = profile.fullName,
//          email = profile.email,
//          avatarURL = profile.avatarURL
//        User(None, "", "", ""))
        Future.successful(user)
      case None => // Insert a new user
        userDAO.add(User(
          None,
          profile.firstName,
          profile.lastName,
          profile.email,
          profile.loginInfo.providerID,
          profile.loginInfo.providerKey
        ))




          //User(
//          userID = UUID.randomUUID(),
//          loginInfo = profile.loginInfo,
//          firstName = profile.firstName,
//          lastName = profile.lastName,
//          fullName = profile.fullName,
//          email = profile.email,
//          avatarURL = profile.avatarURL

    }
  }
}
