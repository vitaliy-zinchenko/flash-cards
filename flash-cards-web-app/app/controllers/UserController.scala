package controllers

import javax.inject._

import dao.UserDAO
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class UserController @Inject()(dao: UserDAO) extends Controller {

  def test = Action.async {
    println("--->")

    dao.listAll.map{us=>
      println("!!")
      us.foreach{u=>
        println(u)
      }
      Ok("ok")
    }

//    Future.successful(Ok("OK"))
  }

}
