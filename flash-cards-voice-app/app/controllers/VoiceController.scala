package controllers

import java.io.InputStream
import javax.inject.{Inject, Singleton}

import akka.stream.IOResult
import akka.stream.scaladsl.{Source, StreamConverters}
import akka.util.ByteString
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.polly.AmazonPollyClient
import com.amazonaws.services.polly.model.{SynthesizeSpeechRequest, SynthesizeSpeechResult}
import com.amazonaws.services.s3.model.{ObjectMetadata, PutObjectRequest}
import com.amazonaws.services.s3.{AmazonS3, AmazonS3Client}
import org.bson.types.ObjectId
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future
import org.mongodb.scala.model.Filters._

import scala.concurrent.ExecutionContext.Implicits.global


/**
  * @author Vitaliy_Zinchenko
  */
@Singleton
class VoiceController @Inject() extends Controller {

  val client: MongoClient = MongoClient()
  val database: MongoDatabase = client.getDatabase("db")
  val collection: MongoCollection[Document] = database.getCollection("voice")

  val credentials: AWSCredentials = new ProfileCredentialsProvider("zinjvi").getCredentials
  val s3client: AmazonS3 = new AmazonS3Client(credentials)
  val pollyClient: AmazonPollyClient = new AmazonPollyClient(credentials)

  def voice(word: String, lang: String) = Action.async {
    println(s"voice request word=$word lang=$lang")
    getId(word, lang).map {
      case None => generate(word, lang)
      case Some(id) => Future.successful(id)
    }.flatMap(identity)
      .map { id => loadVoice(id) }
      .map { source => Ok.chunked(source) }
  }

  private def getId(word: String, lang: String): Future[Option[String]] = {
    collection.find(and(equal("word", word), equal("lang", lang))).toFuture().map { docs =>
      docs.headOption.map(doc => doc("_id").asObjectId().getValue.toString)
    }
  }

  private def generate(word: String, lang: String): Future[String] = {
    val id = ObjectId.get()
    println("!!!!!!!!!!!!!!!! -> generating with id = " + id)
    s3client.putObject(new PutObjectRequest("zinjvi-voice", id + ".mp3", generateVoice(word, lang), new ObjectMetadata))
    collection.insertOne(Document("_id" -> id, "word" -> word, "lang" -> lang))
      .toFuture()
      .map { _ => id.toString }
  }

  private def loadVoice(id: String): Source[ByteString, Future[IOResult]] = {
    val voiceStream = s3client.getObject("zinjvi-voice", id + ".mp3").getObjectContent
    StreamConverters.fromInputStream(() => voiceStream)
  }

  private def generateVoice(word: String, lang: String): InputStream = {
    println("lang: " + lang)
    val request: SynthesizeSpeechRequest = new SynthesizeSpeechRequest
    request.setText(word + " ...")
    request.setOutputFormat("mp3")
    request.setVoiceId(getVoiceId(lang))

    val result: SynthesizeSpeechResult = pollyClient.synthesizeSpeech(request)
    result.getAudioStream
  }

  private def getVoiceId(language: String): String = language match {
    case "ru" => "Tatyana"
    case "en" => "Joanna"
  }

}
