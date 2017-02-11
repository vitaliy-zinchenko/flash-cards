package controllers

import javax.inject._

import dao.{CardDao, CardSetDao}
import model.{Card, CardSet}
import play.api.libs.json.{Json, Writes}
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

  case class CardRequest(id: Option[Long], word: String, translation: String)
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
      Ok(Json.toJson(cards).toString()) //TODO fix toString()
    }
  }

  def listCardsByIds(cardSetId: Long, cardIds: String) = Action.async {
    println(s"$cardSetId - $cardIds")
    val cardIdsList = cardIds.split(",").map(_.toLong).toList

    cardDao.list(cardSetId, cardIdsList).map { cards =>
      Ok(Json.toJson(cards).toString()) //TODO fix toString()
    }
  }

  def createCard(cardSetId: Long) = Action.async(parse.json) { request =>
    val requestBody = request.body.as[CardRequest]
    println(requestBody)
    val card = Card(None, requestBody.word, requestBody.translation, cardSetId)
    cardDao.save(card).map { card =>
      Ok(Json.toJson(card))
    }
  }

  def updateCard(cardSetId: Long) = Action.async(parse.json) { request =>
    val requestBody = request.body.as[CardRequest]
    println(requestBody)
    val card = Card(requestBody.id, requestBody.word, requestBody.translation, cardSetId)
    cardDao.update(card).map { _ =>
      Ok
    }
  }

//  def okJsonResult[T](result: T)(implicit writes: Writes[T]): Result = {
//    Ok(Json.toJson(result).toString())
//  }

}

