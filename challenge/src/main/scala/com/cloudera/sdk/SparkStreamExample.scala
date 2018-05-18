package com.cloudera.sdk

import org.apache.spark.streaming.{StreamingContext, Seconds}
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.log4j.{Level, Logger}
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.io.Text
import org.apache.hadoop.hbase.mapreduce.{TableInputFormat, TableOutputFormat}
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.client.HTable

object SparkStreamExample {

  val stopWords = List("a", "is", "the", "this")

  lazy val logger = Logger.getLogger(this.getClass.getName)

  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      System.err.println("Usage: SparkStreamExample <host> <port>")
      System.exit(1)
    }

    /** Variable definition **/

    /** Flume connection **/
    val host = args(0)
    val port = args(1).toInt

    /** Hbase connection **/
    val tableName = "wordcount_sofia"
    val columnFamilyName = "wordcount_data"
    val columnNameWord = "word"
    val columnNameCount = "count"

    /** For generate a random number between [0,r] **/
    val r = 10;
    


    val conf = new SparkConf()
      .setAppName("Spark Stream Example")
      //          .setMaster("local[2]")
      .set("spark.io.compression.codec", "lz4")

    val sc = new SparkContext(conf)

    Logger.getRootLogger.setLevel(Level.WARN)

    /** Read stream from flume and save to hdfs **/
    /** Using PUSH MODEL **/
    val ssc = new StreamingContext(sc, Seconds(5))
    val flumeStream = FlumeUtils.createStream(ssc, host, port)

    val mappedlines = flumeStream.map {
      sparkFlumeEvent => { (new String(sparkFlumeEvent.event.getBody().array())) }
    }
    val wordcount = mappedlines.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .filter { case (palabra, cant) => !stopWords.exists(palabra.equals) == true }

    wordcount.foreachRDD {
      rdd =>
        /** Save word count result in hbase **/
        rdd.foreachPartition { iter =>

          /* Hbase connection per worker */
          val hbaseConf = HBaseConfiguration.create()
          // hbaseConf.set(TableOutputFormat.OUTPUT_TABLE, tableName)
          hbaseConf.set(TableInputFormat.INPUT_TABLE, tableName)
          val hTable = new HTable(hbaseConf, tableName)
          iter.foreach {
            case (word, count) =>
              val key = word + "_" + count + "_" + scala.util.Random.nextInt(r)
              println(">>> key:" + key)
              val putdata = new Put(key.getBytes)
              putdata.addColumn(columnFamilyName.getBytes, columnNameWord.getBytes, word.getBytes)
              putdata.addColumn(columnFamilyName.getBytes, columnNameCount.getBytes, Bytes.toBytes(count))
              hTable.put(putdata)
              hTable.flushCommits()
          }
        }
    }

    wordcount.print()

    /* DON'T FORGET TO START THE STREAM SESSION */
    ssc.start()
    ssc.awaitTermination()

  }
}
