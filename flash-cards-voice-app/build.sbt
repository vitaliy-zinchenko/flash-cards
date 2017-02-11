name := """flash-cards-voice-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.73",
  "com.amazonaws" % "aws-java-sdk-polly" % "1.11.73",
  "com.googlecode.soundlibs" % "jlayer" % "1.0.1.4",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

