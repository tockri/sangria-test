lazy val commonSettings = Seq(
  scalaVersion := "2.12.4",
  version := "1.0.0",
  libraryDependencies ++= Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
    "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )
)

lazy val domain = (project in file("domain"))
  .settings(commonSettings:_*)
  .settings(
    name := "groups-core",
    libraryDependencies ++= Seq(
      "javax.inject" % "javax.inject" % "1",
    )
  )

lazy val infra = (project in file("infra"))
  .settings(commonSettings:_*)
  .settings(
    name := "groups-infra",
    libraryDependencies ++= Seq(
      "javax.inject" % "javax.inject" % "1",
      "mysql" % "mysql-connector-java" % "6.0.6",
      "com.typesafe.play" %% "play-slick" % "3.0.3",
      "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3",
    )
  ).dependsOn(domain)

lazy val application = (project in file("application"))
  .settings(commonSettings:_*)
  .settings(
    name := "groups-core",
    libraryDependencies ++= Seq(
      "javax.inject" % "javax.inject" % "1",
    )
  ).dependsOn(domain)

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(commonSettings:_*)
  .settings(
    name := "nu-groups",
    version := "0.1.0-SNAPSHOT",

    description := "GraphQL server written with Play Framework.",

    libraryDependencies ++= Seq(
      guice,
      "org.sangria-graphql" %% "sangria" % "1.4.0",
      "org.sangria-graphql" %% "sangria-play-json" % "1.0.4",
      "net.codingwell" %% "scala-guice" % "4.1.0",
    )
  ).dependsOn(application, domain % "test->test;compile->compile", infra)

Test / fork := true
Test / javaOptions += "-Dconfig.resource=test.conf"
Test / parallelExecution := false
