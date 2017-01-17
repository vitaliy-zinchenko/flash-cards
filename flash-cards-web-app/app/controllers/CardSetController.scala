package controllers

import javax.inject._

import dao.{CardDao, CardSetDao}
import model.{Card, CardSet}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class CardSetController @Inject()
(cardSetDao: CardSetDao, cardDao: CardDao) extends Controller {

  implicit val cardSetWriters = Json.writes[CardSet]
  implicit val cardSetReads = Json.reads[CardSet]
  implicit val cardWriters = Json.writes[Card]

  case class CardSetRequest(title: String)
  implicit val cardSetRequestReads = Json.reads[CardSetRequest]

  case class CardRequest(word: String, translation: String)
  implicit val cardRequestReads = Json.reads[CardRequest]

  def listCardSets(page: Int, size: Int) = Action.async {
    cardSetDao.listAll(page, size).map { cardSets =>
      Ok(Json.toJson(cardSets))
    }
  }

  def get(cardSetId: Long) = Action.async {
    cardSetDao.find(cardSetId).map { cardSet =>
      Ok(Json.toJson(cardSet))
    }
  }

  def create() = Action.async(parse.json) { request =>
    val requestBody = request.body.as[CardSetRequest]
    val cardSet = CardSet(id = None, requestBody.title, temp.userId)
    cardSetDao.save(cardSet).map { cardSet =>
      Ok(Json.toJson(cardSet))
    }
  }

  def listCards(cardSetId: Long, page: Int, size: Int) = Action.async {
    cardDao.list(cardSetId, page, size).map { cards =>
      Ok(Json.toJson(cards))
    }
  }

  def createCard(cardSetId: Long) = Action.async(parse.json) { request =>
    val requestBody = request.body.as[List[CardRequest]]
    val card = Card(id = None, requestBody.head.word, requestBody.head.translation, cardSetId) //TODO fix head
    cardDao.save(card).map { card =>
      Ok(Json.toJson(card))
    }
  }

}

