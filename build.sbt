name := "scala_test"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature", "-Xelide-below", "ALL")

resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.1.1",
  "com.atilika.kuromoji" % "kuromoji-ipadic" % "0.9.0",
  "org.apache.spark" %% "spark-mllib" % "2.1.1"
)