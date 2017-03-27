package dao.impl

import javax.inject.Inject

import com.google.inject.Singleton
import dao.CardSetDao
import model.{CardSet, User}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * @author Vitaliy_Zinchenko
  */
@Singleton
class CardSetDaoImpl @Inject()(dbConfigProvider: DatabaseConfigProvider) extends CardSetDao {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  class CardSetTable(tag: Tag)
    extends Table[CardSet](tag, "card_set") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

    def title = column[String]("title")

    def userId = column[Long]("user_id")

    override def * =
      (id, title, userId) <> (CardSet.tupled, CardSet.unapply)
  }

  implicit val cardSets = TableQuery[CardSetTable]

  override def listAll(userId: Long, page: Int, size: Int): Future[Seq[CardSet]] = {
    db.run(
      cardSets
        .filter(_.userId === userId)
        .drop(page * size)
        .take(size)
        .result
    )
  }

  override def find(cardSetId: Long): Future[Option[CardSet]] = {
    db.run(cardSets.filter(_.id === cardSetId).result.headOption)

  }

  override def save(cardSet: CardSet): Future[CardSet] = {
    db.run((cardSets returning cardSets) += cardSet)
  }

  override def update(cardSet: CardSet): Future[Int] = {
    db.run(cardSets.filter(_.id === cardSet.id).update(cardSet))
  }

  override def remove(cardSetId: Long) : Future[Int] = {
    db.run(cardSets.filter(_.id === cardSetId).delete)
  }

}
