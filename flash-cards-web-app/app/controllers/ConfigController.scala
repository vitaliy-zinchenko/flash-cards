package controllers

import javax.inject._

import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ConfigController @Inject()() extends Controller {

  case class AppConfig(googleClientId: String)
  implicit val appConfigWrites = Json.writes[AppConfig]

  def appConfig() = Action {
    Ok(Json.toJson(AppConfig("563875364656-v4t1v5t4agj9p57m35q9t6f9rgrn0r02.apps.googleusercontent.com")))
  }

}
