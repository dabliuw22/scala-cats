name := "scala-cats"

version := "0.1"

scalaVersion := "2.13.0"
val akkaParent = "com.typesafe.akka"
val akkaVersion = "2.5.23"
val typeLevelParent = "org.typelevel"
val catsVersion = "2.0.0"
val monixParent = "io.monix"
val monixVersion = "3.1.0"
val reactorProjectParent = "io.projectreactor"
val reactorProjectVersion = "0.5.0"

libraryDependencies ++= Seq(
  akkaParent %% "akka-actor" % akkaVersion,
  typeLevelParent %% "cats-macros" % catsVersion,
  typeLevelParent %% "cats-kernel" % catsVersion,
  typeLevelParent %% "cats-core" % catsVersion,
  typeLevelParent %% "cats-effect" % catsVersion,
  monixParent %% "monix-reactive" % monixVersion,
  reactorProjectParent %% "reactor-scala-extensions" % reactorProjectVersion,
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:postfixOps",
  "-language:higherKinds" // or import scala.language.higherKinds
)