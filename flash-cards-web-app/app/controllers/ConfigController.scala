package controllers

import javax.inject._

import auth.DefaultEnv
import com.mohiva.play.silhouette.api.Silhouette
import config.AppConfig
import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class ConfigController @Inject()(silhouette: Silhouette[DefaultEnv],
                                 config: AppConfig) extends Controller {

  implicit val appConfigWrites = Json.writes[AppConfig]

  def appConfig() = silhouette.UnsecuredAction {
    Ok(Json.toJson(config))
  }

}
