name := """angularjs-play2-postgresql"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  cache
)

libraryDependencies += "com.typesafe.slick" %% "slick" % "2.1.0"

libraryDependencies += "com.typesafe.play" %% "play-slick" % "0.8.0"

libraryDependencies += "org.postgresql" % "postgresql" % "9.2-1002-jdbc4"


