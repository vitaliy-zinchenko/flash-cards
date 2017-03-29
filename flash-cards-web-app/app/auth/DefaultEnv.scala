package auth

import com.mohiva.play.silhouette.api.Env
import com.mohiva.play.silhouette.impl.authenticators.JWTAuthenticator
import model.User

/**
  * Created by vitaliizinchenko on 2/16/17.
  */
class DefaultEnv extends Env {
  type I = User
  type A = JWTAuthenticator //TODO investigate more about JWT
}
