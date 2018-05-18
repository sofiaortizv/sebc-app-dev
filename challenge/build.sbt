name := "challenge"
version := "1.0"
scalaVersion := "2.11.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

// ADD CLOUDERA REPO AND DEPENDENCIES FOR SPARK APPS
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.0.cloudera1"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.0.cloudera1"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.0.cloudera1"

libraryDependencies += "org.apache.spark" %% "spark-streaming-flume" % "2.3.0.cloudera1"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.3.0.cloudera1"

libraryDependencies += "org.apache.hbase" % "hbase-client" % "1.2.0-cdh5.14.2"

libraryDependencies += "org.apache.hbase" % "hbase-server" % "1.2.0-cdh5.14.2"

libraryDependencies += "org.apache.hbase" % "hbase-common" % "1.2.0-cdh5.14.2"

libraryDependencies += "org.apache.hbase" % "hbase-spark" % "1.2.0-cdh5.14.2"

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/"

// ALSO ADD A MERGE STRATEGY & BUILD A FAT JAR HERE
