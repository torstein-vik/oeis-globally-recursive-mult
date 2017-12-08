scalaVersion := "2.12.3"

// Show warnings
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

// Fork into another JVM process
fork := true

// Dependencies

// Scala parser combinators
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6"
