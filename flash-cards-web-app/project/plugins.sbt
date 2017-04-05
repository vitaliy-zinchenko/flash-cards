// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.10")

// web plugins

addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.1.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.4")

addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.8")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.1.0")

addSbtPlugin("org.irundaia.sbt" % "sbt-sassify" % "1.4.6")

//addSbtPlugin("stejskal" % "sbt-webpack" % "0.4")

resolvers += Resolver.url("bintray-kipsigman-sbt-plugins", url("http://dl.bintray.com/kipsigman/sbt-plugins"))(Resolver.ivyStylePatterns)
addSbtPlugin("kipsigman" % "sbt-elastic-beanstalk" % "0.1.4")

resolvers += "Play2war plugins release" at "http://repository-play-war.forge.cloudbees.com/release/"
addSbtPlugin("com.github.play2war" % "play2-war-plugin_2.9.2_0.12" % "0.9-RC1")
addSbtPlugin("com.joescii" % "sbt-elasticbeanstalk-plugin" % "0.0.7")
