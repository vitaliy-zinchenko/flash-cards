package controllers


import javax.inject._

import _root_.services.UserService
import auth.DefaultEnv
import com.mohiva.play.silhouette.api.Authenticator.Implicits._
import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.api.exceptions.ProviderException
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.api.util.{Clock, Credentials, PasswordHasher}
import com.mohiva.play.silhouette.impl.exceptions.IdentityNotFoundException
import com.mohiva.play.silhouette.impl.providers.{CommonSocialProfileBuilder, CredentialsProvider, SocialProvider, SocialProviderRegistry}
import model.User
import net.ceedubs.ficus.Ficus._
import play.api.Configuration
import play.api.cache.CacheApi
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc.{Request, _}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.{FiniteDuration, _}

@Singleton
class AuthController @Inject()(
                                userService: UserService,
                                silhouette: Silhouette[DefaultEnv],
                                credentialsProvider: CredentialsProvider,
                                configuration: Configuration,
                                clock: Clock,
                                passwordHasher: PasswordHasher,
                                authInfoRepository: AuthInfoRepository,
                                socialProviderRegistry: SocialProviderRegistry,
                                cache: CacheApi,
                                val messagesApi: MessagesApi)
  extends Controller with Logger with I18nSupport {

  implicit val reqRe = Json.reads[LoginRequest]
  implicit val signUpReads = Json.reads[SignUpRequest]

  def signUp() = Action.async(parse.json) { implicit request =>
    val req = request.body.as[SignUpRequest]
    val login = LoginInfo(CredentialsProvider.ID, req.email)
    userService.retrieve(login).flatMap {
      case None => createNewUser(login, req)
      case Some(user) => Future.successful(BadRequest(Json.obj("message" -> Messages("user.exists"))))
    }
  }

  def createNewUser(loginInfo: LoginInfo, req: SignUpRequest)(implicit request: RequestHeader): Future[Result] = {
    val authInfo = passwordHasher.hash(req.password)
    for {
      user <- userService.save(User(None, Some(req.firstName), Some(req.lastName), Some(req.email), loginInfo.providerID, loginInfo.providerKey))
      authInfo <- authInfoRepository.add(loginInfo, authInfo)
      authenticator <- silhouette.env.authenticatorService.create(loginInfo)
      token <- silhouette.env.authenticatorService.init(authenticator)
    } yield {
      silhouette.env.eventBus.publish(SignUpEvent(user, request))
      silhouette.env.eventBus.publish(LoginEvent(user, request))
      println("token -> " + token)
      Ok(Json.obj("token" -> token))
    }
  }

  def signIn() = Action.async(parse.json) { implicit request =>
    val data = request.body.as[LoginRequest]
    credentialsProvider.authenticate(Credentials(data.email, data.password)).flatMap { loginInfo =>
      userService.retrieve(loginInfo).flatMap {
        case Some(user) => silhouette.env.authenticatorService.create(loginInfo).map {
          case authenticator if data.rememberMe =>
            val c = configuration.underlying
            authenticator.copy(
              expirationDateTime = clock.now + c.as[FiniteDuration]("silhouette.authenticator.rememberMe.authenticatorExpiry"),
              idleTimeout = c.getAs[FiniteDuration]("silhouette.authenticator.rememberMe.authenticatorIdleTimeout")
            )
          case authenticator => authenticator
        }.flatMap { authenticator =>
          silhouette.env.eventBus.publish(LoginEvent(user, request))
          silhouette.env.authenticatorService.init(authenticator).map { token =>
            Ok(Json.obj("token" -> token))
          }
        }
        case None => Future.failed(new IdentityNotFoundException("Couldn't find user"))
      }
    }.recover {
      case e: ProviderException =>
        Unauthorized(Json.obj("message" -> "invalid.credentials"))
    }
  }

  def authenticate(provider: String) = Action.async { r =>
    cacheAuthTokenForOauth1(r) { implicit request =>
      (socialProviderRegistry.get[SocialProvider](provider) match {
        case Some(p: SocialProvider with CommonSocialProfileBuilder) =>
          p.authenticate().flatMap {
            case Left(result) => Future.successful(result)
            case Right(authInfo) => for {
              profile <- p.retrieveProfile(authInfo)
              user <- userService.save(profile)
              authInfo <- authInfoRepository.save(profile.loginInfo, authInfo)
              authenticator <- silhouette.env.authenticatorService.create(profile.loginInfo)
              token <- silhouette.env.authenticatorService.init(authenticator)
            } yield {
              silhouette.env.eventBus.publish(LoginEvent(user, request))
              Ok(Json.obj("token" -> token))
            }
          }
        case _ => Future.failed(new ProviderException(s"Cannot authenticate with unexpected social provider $provider"))
      }).recover {
        case e: ProviderException =>
          logger.error("Unexpected provider error", e)
          Unauthorized(Json.obj("message" -> Messages("could.not.authenticate")))
      }
    }
  }

  /**
    * Satellizer executes multiple requests to the same application endpoints for OAuth1.
    *
    * So this function caches the response from the OAuth provider and returns it on the second
    * request. Not nice, but it works as a temporary workaround until the bug is fixed.
    *
    * @param request The current request.
    * @param f       The action to execute.
    * @return A result.
    * @see https://github.com/sahat/satellizer/issues/287
    */
  private def cacheAuthTokenForOauth1(request: Request[AnyContent])(f: Request[AnyContent] => Future[Result]): Future[Result] = {
    request.getQueryString("oauth_token") -> request.getQueryString("oauth_verifier") match {
      case (Some(token), Some(verifier)) => cache.get[Result](token + "-" + verifier) match {
        case Some(result) => Future.successful(result)
        case None => f(request).map { result =>
          cache.set(token + "-" + verifier, result, 1 minute)
          result
        }
      }
      case _ => f(request)
    }
  }
}

case class LoginRequest(email: String, password: String, rememberMe: Boolean)

case class SignUpRequest(firstName: String, lastName: String, email: String, password: String)



