val spark = "org.apache.spark" % "spark-core_2.10" % "1.2.1"
val sparkSQL = "org.apache.spark" % "spark-hive_2.10" % "1.2.1"
//val finatra = "com.twitter" % "finatra" % "1.6.0"

lazy val commonSettings = Seq(
  organization := "com.thl",
  version := "0.1.0",
  scalaVersion := "2.10.4"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "table-jdbcrdd",
    libraryDependencies += spark,
    libraryDependencies += sparkSQL//,
    //libraryDependencies += finatra
  )