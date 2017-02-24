package services

import com.mohiva.play.silhouette.api.services.IdentityService
import com.mohiva.play.silhouette.impl.providers.CommonSocialProfile
import model.User

import scala.concurrent.Future

/**
  * Created by vitaliizinchenko on 2/16/17.
  */
trait UserService extends IdentityService[User] {

  def save(user: User): Future[User]

  def save(profile: CommonSocialProfile): Future[User]

}
