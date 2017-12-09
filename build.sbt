scalaVersion := "2.12.3"

// Show warnings
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

// Stack size
javaOptions += "-Xss4m";


// Fork into another JVM process
fork := true

// Dependencies

// Scala parser combinators
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6"

// Jackson
libraryDependencies += "org.json4s" %% "json4s-native" % "3.6.0-M1"
