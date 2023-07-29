ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

val akkaVersion = "2.8.0"
val akkaHttpVersion = "10.5.0"
val akkaHttpJsonVersion = "1.39.2"
val circeVersion = "0.14.5"
val scalaLoggingVersion = "3.9.5"

lazy val root = (project in file("."))
  .settings(
    name := "gcp-integration",
    idePackagePrefix := Some("gcptest"),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
      "de.heikoseeberger" %% "akka-http-circe" % akkaHttpJsonVersion,
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "ch.qos.logback" % "logback-classic" % "1.4.7",
    )
  )
