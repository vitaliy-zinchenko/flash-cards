package dao

import com.google.inject.ImplementedBy
import dao.impl.CardSetDaoImpl
import model.CardSet

import scala.concurrent.Future

@ImplementedBy(classOf[CardSetDaoImpl])
trait CardSetDao {

  def listAll(userId: Long, page: Int, size: Int) : Future[Seq[CardSet]]
  def find(cardSetId: Long) : Future[Option[CardSet]]
  def save(cardSet: CardSet) : Future[CardSet]

}
