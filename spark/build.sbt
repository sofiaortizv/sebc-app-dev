name := "SparkBatchExample"
version := "1.0"
scalaVersion := "2.11.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

// ADD CLOUDERA REPO AND DEPENDENCIES FOR SPARK APPS

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.0.cloudera1"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.0.cloudera1"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.0.cloudera1"

libraryDependencies += "org.apache.spark" %% "spark-streaming-flume" % "2.3.0.cloudera1"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.3.0.cloudera1"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11"

resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/"
