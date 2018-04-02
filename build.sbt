name := "sangria-test"
version := "0.1.0-SNAPSHOT"

description := "GraphQL server written with Play Framework."

scalaVersion := "2.12.4"
scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
  guice,
  "org.sangria-graphql" %% "sangria" % "1.4.0",
  "org.sangria-graphql" %% "sangria-play-json" % "1.0.4",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
