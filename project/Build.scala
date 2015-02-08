import sbt._
import sbt.Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

object Build extends Build {
  val defaultAssemblySettings =
    assemblySettings ++
  Seq(
    test in assembly := {},
    mergeStrategy in assembly <<= (mergeStrategy in assembly) {
      (old) => {
        case "reference.conf" => MergeStrategy.concat
        case "application.conf" => MergeStrategy.concat
        case "META-INF/MANIFEST.MF" => MergeStrategy.discard
        case "META-INF\\MANIFEST.MF" => MergeStrategy.discard
        case _ => MergeStrategy.first
      }
    }
  )

  override lazy val settings =
    super.settings ++ Seq(shellPrompt := { s => Project.extract(s).currentProject.id + " > " })

  lazy val root =
    Project("curve", file("."))
      .aggregate(core)

  lazy val core = 
    Project("core", file("core"))
      .settings(coreSettings: _*)

  lazy val coreSettings =
    Seq(
      name := "curve",
      organization := "org.locationtech",
      version := "0.1.0",
      scalaVersion := "2.11.1",
      scalacOptions ++= Seq(
        "-deprecation",
        "-unchecked",
        "-Yinline-warnings",
        "-language:implicitConversions",
        "-language:reflectiveCalls",
        "-language:postfixOps",
        "-language:existentials",
        "-feature"),
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "2.2.4" % "test"
      )
    ) ++ defaultAssemblySettings
}
