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
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

/**
  * @author Vitaliy_Zinchenko
  */
@Singleton
class VoiceController @Inject() extends Controller {

  val credentials: AWSCredentials = new ProfileCredentialsProvider("zinjvi").getCredentials
  val s3client: AmazonS3 = new AmazonS3Client(credentials)
  val pollyClient: AmazonPollyClient = new AmazonPollyClient(credentials)

  def voice(word: String, lang: String) = Action {
    val fileName: String = getFileName(word, lang)
    if(!s3client.doesObjectExist("zinjvi-voice", fileName)) {
      s3client.putObject(new PutObjectRequest("zinjvi-voice", fileName, generateVoice(word, lang), new ObjectMetadata))
    }
    Ok.chunked(loadVoice(fileName))
  }

  private def loadVoice(fileName: String): Source[ByteString, Future[IOResult]] = {
    val voiceStream = s3client.getObject("zinjvi-voice", fileName).getObjectContent
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

  private def getFileName(word: String, lang: String): String = {
    s"$word-${getVoiceId(lang)}.mp3"
  }

  private def getVoiceId(language: String): String = language match {
    case "ru" => "Tatyana"
    case "en" => "Joanna"
  }

}
