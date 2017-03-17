package controllers

import javax.inject._

import auth.DefaultEnv
import com.mohiva.play.silhouette.api.Silhouette
import dao.{CardDao, CardSetDao}
import model.{Card, CardSet}
import play.api.libs.json.{Json, Writes}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class CardSetController @Inject()
(cardSetDao: CardSetDao, cardDao: CardDao, silhouette: Silhouette[DefaultEnv]) extends Controller {

  implicit val cardSetWriters = Json.writes[CardSet]
  implicit val cardSetReads = Json.reads[CardSet]
  implicit val cardWriters = Json.writes[Card]

  case class CardSetRequest(title: String)
  implicit val cardSetRequestReads = Json.reads[CardSetRequest]

  case class CardRequest(id: Option[Long], word: String, translation: String)
  implicit val cardRequestReads = Json.reads[CardRequest]

  def listCardSets(page: Int, size: Int) = silhouette.SecuredAction.async { request =>
    cardSetDao.listAll(request.identity.id.get, page, size).map { cardSets =>
      Ok(Json.toJson(cardSets))
    }
  }

  def get(cardSetId: Long) = silhouette.SecuredAction.async {
    cardSetDao.find(cardSetId).map { cardSet =>
      Ok(Json.toJson(cardSet))
    }
  }

  def create() = silhouette.SecuredAction.async(parse.json) { request =>
    val requestBody = request.body.as[CardSetRequest]
    val cardSet = CardSet(id = None, requestBody.title, request.identity.id.get)
    cardSetDao.save(cardSet).map { cardSet =>
      Ok(Json.toJson(cardSet))
    }
  }

  def removeCardSetById(cardSetId: Long) = silhouette.SecuredAction.async {
    cardSetDao.remove(cardSetId).map(_ => Ok)
  }

  def listCards(cardSetId: Long, page: Int, size: Int) = silhouette.SecuredAction.async {
    cardDao.list(cardSetId, page, size).map { cards =>
      Ok(Json.toJson(cards).toString()) //TODO fix toString()
    }
  }

  def listCardsByIds(cardSetId: Long, cardIds: String) = silhouette.SecuredAction.async {
    println(s"$cardSetId - $cardIds")
    val cardIdsList = cardIds.split(",").map(_.toLong).toList

    cardDao.list(cardSetId, cardIdsList).map { cards =>
      Ok(Json.toJson(cards).toString()) //TODO fix toString()
    }
  }

  def createCard(cardSetId: Long) = silhouette.SecuredAction.async(parse.json) { request =>
    val requestBody = request.body.as[CardRequest]
    println(requestBody)
    val card = Card(None, requestBody.word, requestBody.translation, cardSetId)
    cardDao.save(card).map { card =>
      Ok(Json.toJson(card))
    }
  }

  def updateCard(cardSetId: Long) = silhouette.SecuredAction.async(parse.json) { request =>
    val requestBody = request.body.as[CardRequest]
    println(requestBody)
    val card = Card(requestBody.id, requestBody.word, requestBody.translation, cardSetId)
    cardDao.update(card).map(_ => Ok)
  }

  def removeCardById(cardSetId: Long, cardId: Long) = silhouette.SecuredAction.async {
    cardDao.remove(cardId).map(_ => Ok)
  }

}

