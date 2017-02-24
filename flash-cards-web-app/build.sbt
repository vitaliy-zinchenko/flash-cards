name := """flash-cards-web-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  cache,
  ws,
  "com.mohiva" % "play-silhouette_2.11" % "4.0.0",
  "com.mohiva" % "play-silhouette-persistence_2.11" % "4.0.0",
  "com.mohiva" % "play-silhouette-password-bcrypt_2.11" % "4.0.0",
  "com.mohiva" % "play-silhouette-crypto-jca_2.11" % "4.0.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "mysql" % "mysql-connector-java" % "5.1.34",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "com.typesafe.play" %% "play-slick" % "1.1.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.0",
  "net.codingwell" %% "scala-guice" % "4.0.1",
  "com.iheart" %% "ficus" % "1.4.0"
)

//pipelineStages := Seq(webpack)

//webpackConfig in webpack := Some("path_to_config_file")

//https://github.com/stejskal/sbt-webpack

//resolvers += Resolver.jcenterRepo
//resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/"

