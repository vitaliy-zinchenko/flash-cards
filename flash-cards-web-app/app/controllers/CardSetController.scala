package controllers

import java.sql.Date
import java.util.Calendar
import javax.inject._

import auth.DefaultEnv
import com.mohiva.play.silhouette.api.Silhouette
import dao.{CardDao, CardSetDao}
import model.{Card, CardSet}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class CardSetController @Inject()
(cardSetDao: CardSetDao, cardDao: CardDao, silhouette: Silhouette[DefaultEnv]) extends Controller {

  implicit val cardSetWriters = Json.writes[CardSet]
  implicit val cardSetReads = Json.reads[CardSet]
  implicit val cardWriters = Json.writes[Card]

  case class CardSetRequest(id: Option[Long], title: String)
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

  def update(cardSetId: Long) = silhouette.SecuredAction.async(parse.json) { request =>
    val requestBody = request.body.as[CardSetRequest]
    val cardSet = CardSet(requestBody.id, requestBody.title, request.identity.id.get)
    cardSetDao.update(cardSet).map { cardSet =>
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

  def cardsToLearn(cardSetId: Long) = silhouette.SecuredAction.async {
//    val cards = cardDao.listToLearn(cardSetId).flatMap { cards =>
//      if(cards.isEmpty) {
//        cardDao.list(cardSetId, 0, 9999); //TODO remove 9999
//      } else {
//        Future.successful(cards)
//      }
//    }
//    cards.map(cards => Ok(Json.toJson(cards)))
    cardDao.listToLearn(cardSetId)
      .map(cards => Ok(Json.toJson(cards)))
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
    val now = new Date(Calendar.getInstance().getTimeInMillis)
    val card = Card(None, requestBody.word, requestBody.translation, cardSetId, 0, now, now)
    cardDao.save(card).map { card =>
      Ok(Json.toJson(card))
    }
  }

  def updateCard(cardSetId: Long) = silhouette.SecuredAction.async(parse.json) { request =>
    val requestBody = request.body.as[CardRequest]
    cardDao.get(requestBody.id.get) //TODO remove .get
      .map(_.get.copy(word = requestBody.word, translation = requestBody.translation)) // TODO remove .get
      .map(cardDao.update)
      .map(_ => Ok)
  }

  def removeCardById(cardSetId: Long, cardId: Long) = silhouette.SecuredAction.async {
    cardDao.remove(cardId).map(_ => Ok)
  }

  case class CardProgress(cardId: Long, wrong: Option[Int], right: Option[Int])
  implicit val cardProgressReads = Json.reads[CardProgress]

  def progressCards() = silhouette.SecuredAction.async(parse.json) { request =>
    val progresses = request.body.as[List[CardProgress]]
    println(progresses)
    progresses.foreach( progress => //TODO refactor, optimize
      cardDao.get(progress.cardId).map{ card =>
        val c = card.get
        val d = Calendar.getInstance()
        d.setTimeInMillis(c.guessDate.getTime)
        d.add(Calendar.DATE, levelToDays(c.level))
        if(c.level < 5 && progress.wrong.isEmpty && c.trainDate.getTime <= Calendar.getInstance().getTimeInMillis ) {
          val newLevel = c.level + 1
          val newTrainDate = Calendar.getInstance()
          newTrainDate.add(Calendar.DATE, levelToDays(newLevel))
          val newC = c.copy(
            level = newLevel,
            guessDate = new Date(Calendar.getInstance().getTimeInMillis),
            trainDate = new Date(newTrainDate.getTimeInMillis))
          cardDao.update(newC)
        }
      }
    )
    Future.successful(Ok)
  }

  private def levelToDays(level: Int): Int = level match {
    case l if(l == 0) => 0
    case l if(l == 1) => 2
    case l if(l == 2) => 4
    case l if(l == 3) => 6
    case l if(l == 4) => 15
  }

}

