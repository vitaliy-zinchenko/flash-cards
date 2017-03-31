package dao.impl

import java.sql.Date
import java.util.Calendar
import javax.inject.Inject

import com.google.inject.Singleton
import dao.CardDao
import model.Card
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * @author Vitaliy_Zinchenko
  */
@Singleton
class CardDaoImpl @Inject()(dbConfigProvider: DatabaseConfigProvider) extends CardDao {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  class CardTable(tag:Tag)
    extends Table[Card](tag, "card") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def word = column[String]("word")
    def translation = column[String]("translation")
    def cardSetId = column[Long]("card_set_id")
    def level = column[Int]("level")
    def guessDate = column[Date]("guess_date")
    def trainDate = column[Date]("train_date")

    override def * =
      (id, word, translation, cardSetId, level, guessDate, trainDate) <> (Card.tupled, Card.unapply)
  }

  implicit val cards = TableQuery[CardTable]

  override def get(cardId: Long): Future[Option[Card]] = {
    db.run(cards.filter(_.id === cardId).result.headOption)
  }

  override def list(cardSetId: Long, page: Int, size: Int): Future[Seq[Card]] = {
    db.run(cards
      .filter(_.cardSetId === cardSetId)
      .drop(page * size)
      .take(size)
      .result)
  }

  override def save(card: Card) : Future[Card] = {
    db.run((cards returning cards) += card)
  }

  override def update(card: Card) : Future[Int] = {
    db.run(cards.filter(_.id === card.id).update(card))
  }

  override def remove(cardId: Long) : Future[Int] = {
    db.run(cards.filter(_.id === cardId).delete)
  }

  override def list(cardSetId: Long, cardIds: List[Long]): Future[Seq[Card]] = {
    val query = for {
      c <- cards
      if c.id inSetBind cardIds
    } yield c

    db.run(query.result)
  }

  override def listToLearn(cardSetId: Long): Future[Seq[Card]] = {
    val q = cards.filter(_.cardSetId === cardSetId)
      .filter(_.level < 5)
      .filter(_.trainDate <= new Date(Calendar.getInstance().getTimeInMillis))
    db.run(q.result)
  }
}
