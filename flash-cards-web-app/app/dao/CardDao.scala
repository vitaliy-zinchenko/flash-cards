package dao

import com.google.inject.ImplementedBy
import dao.impl.CardDaoImpl
import model.{Card, CardSet}

import scala.concurrent.Future

@ImplementedBy(classOf[CardDaoImpl])
trait CardDao {

  def list(cardSetId: Long, page: Int, size: Int) : Future[Seq[Card]]
  def save(card: Card) : Future[Card]

}
