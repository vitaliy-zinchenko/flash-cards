package dao

import com.google.inject.ImplementedBy
import dao.impl.CardDaoImpl
import model.{Card, CardSet}

import scala.concurrent.Future

@ImplementedBy(classOf[CardDaoImpl])
trait CardDao {

  def get(cardId: Long) : Future[Option[Card]]
  def list(cardSetId: Long, page: Int, size: Int) : Future[Seq[Card]]
  def list(cardSetId: Long, cardIds: List[Long]) : Future[Seq[Card]]
  def listToLearn(cardSetId: Long) : Future[Seq[Card]]
  def save(card: Card) : Future[Card]
  def update(card: Card) : Future[Int]
  def remove(cardId: Long) : Future[Int]

}
