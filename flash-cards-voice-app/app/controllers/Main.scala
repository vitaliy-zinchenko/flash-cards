package controllers

import java.io.File
import java.nio.file.{Files, Paths}
import java.util.Date

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.polly.AmazonPollyClient
import com.amazonaws.services.polly.model.{SynthesizeSpeechRequest, SynthesizeSpeechResult}
import com.amazonaws.services.s3.{AmazonS3, AmazonS3Client}
import javax.sound.sampled._

/**
  * @author Vitaliy_Zinchenko
  */
object Main {

  def main(args: Array[String]): Unit = {
    val credentials: AWSCredentials = new ProfileCredentialsProvider("zinjvi").getCredentials
    val s3client: AmazonS3 = new AmazonS3Client(credentials)
    val pollyClient: AmazonPollyClient = new AmazonPollyClient(credentials)

    val request: SynthesizeSpeechRequest = new SynthesizeSpeechRequest
    val word: String = "fuel"
    request.setText(word)
    request.setOutputFormat("mp3")
    request.setVoiceId("Joanna")

    val result: SynthesizeSpeechResult = pollyClient.synthesizeSpeech(request)
    val stream = result.getAudioStream

    val path = "/home/zinjvi/Documents/test/" + word + "_" + new Date()
    val pathWithExt = path + ".mp3"
    Files.copy(stream, Paths.get(pathWithExt))



    val audioInputStream = AudioSystem.getAudioInputStream(new File(pathWithExt))
    val clip = AudioSystem.getClip()
    clip.open(audioInputStream)
    val gainControl = clip.getControl(FloatControl.Type.MASTER_GAIN).asInstanceOf[FloatControl]
    gainControl.setValue(10.0f); // Reduce volume by 10 decibels.
    clip.start()

    Files.copy(audioInputStream, Paths.get(path + "_changed.mp3"))

  }

}
