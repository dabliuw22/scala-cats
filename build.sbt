name := "scala-cats"

version := "0.1"

scalaVersion := "2.13.0"
val akkaParent = "com.typesafe.akka"
val akkaVersion = "2.5.23"
val typeLevelParent = "org.typelevel"
val catsVersion = "2.0.0"

libraryDependencies ++= Seq(
  akkaParent %% "akka-actor" % akkaVersion,
  typeLevelParent %% "cats-macros" % catsVersion,
  typeLevelParent %% "cats-kernel" % catsVersion,
  typeLevelParent %% "cats-core" % catsVersion,
  typeLevelParent %% "cats-effect" % catsVersion
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:postfixOps",
  "-language:higherKinds"
)