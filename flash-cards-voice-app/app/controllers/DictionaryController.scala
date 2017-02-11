//package controllers
//
//import javax.inject.{Inject, Singleton}
//
//import org.mongodb.scala.bson.collection.immutable.Document
//import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}
//import play.api.libs.json.{Format, Json, Reads, Writes}
//import play.api.mvc.{Action, Controller}
//
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.concurrent.Future
//
//import org.mongodb.scala.model.Filters._
//
//
///**
//  * @author Vitaliy_Zinchenko
//  */
//@Singleton
//class DictionaryController @Inject() extends Controller {
//
//  val client: MongoClient = MongoClient()
//  val database: MongoDatabase = client.getDatabase("db")
//  val collection: MongoCollection[Document] = database.getCollection("dictionary")
//
//  case class Word(word: String, translation: String)
//
//  implicit val formatter: Format[Word] = Json.format[Word]
//  implicit val writes: Writes[Word] = Json.writes[Word]
//  implicit val reads: Reads[Word] = Json.reads[Word]
//
//  def save() = Action.async(parse.json) { request =>
//    val docs = request.body.as[Seq[Word]]
//      .map{ word => Document(Json.toJson(word).toString()) }
//
//    collection.deleteMany(notEqual("word", "!")).toFuture().map{_ =>
//      collection.insertMany(docs)
//        .toFuture().map(_ => Ok("saved"))
//    }.flatMap(identity)
//  }
//
//  def get() = Action.async {
//    collection.find().toFuture().map { docs =>
//      docs.map(doc => Json.parse(doc.toJson()).as[Word])
//    }.map { words =>
//      println(words)
//      Ok(Json.toJson(words).toString())
//    }
//  }
//
//  def test() = Action {
//    Ok("""{"word":"привет!!"}""")
//  }
//
//}
