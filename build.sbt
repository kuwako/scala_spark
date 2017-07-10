name := "scala_test"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature", "-Xelide-below", "ALL")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.1.1",
  "org.apache.spark" %% "spark-mllib" % "2.1.1"
)