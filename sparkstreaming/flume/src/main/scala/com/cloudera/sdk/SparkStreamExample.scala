package com.cloudera.sdk

import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{ FileSystem, Path }
import org.apache.spark.rdd.RDD
import org.apache.log4j.{ Level, Logger }
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.flume.FlumeUtils

object SparkStreamExample {
  lazy val logger = Logger.getLogger(this.getClass.getName)

  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      System.err.println("Usage: SparkStreamExample <host> <port>")
      System.exit(1)
    }

    val host = args(0)
    val port = args(1).toInt

    val conf = new SparkConf()
      .setAppName("Spark Stream Example")
      //          .setMaster("local[2]")
      .set("spark.io.compression.codec", "lz4")

    val sc = new SparkContext(conf)

    Logger.getRootLogger.setLevel(Level.WARN)

    /* READ STREAM FROM FLUME AND PRINT COUNTS */
    /* PUSH MODEL */
    val ssc = new StreamingContext(sc, Seconds(5))
    val flumeStream = FlumeUtils.createStream(ssc, host, port)

    val mappedlines = flumeStream.map { sparkFlumeEvent => { (new String(sparkFlumeEvent.event.getBody().array())) } }
    val wordcount = mappedlines.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
    wordcount.print()

    /* DON'T FORGET TO START THE STREAM SESSION */
    ssc.start()
    ssc.awaitTermination()

  }
}
