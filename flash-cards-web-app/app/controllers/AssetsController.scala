package controllers

import javax.inject._

import auth.DefaultEnv
import com.mohiva.play.silhouette.api.Silhouette
import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class AssetsController @Inject()() extends Controller {

  def reRoteToRoot(path: String, file: String, f: String) = Action.async { request =>
    Assets.at(path, file).apply(request)
  }

}
