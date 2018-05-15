package com.cloudera.sdk

import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{ FileSystem, Path }
import org.apache.spark.rdd.RDD

object SparkBatchExample {
  def main(args: Array[String]): Unit = {
    if (args.length < 1) {
      System.err.println("Usage: SparkBatchExample <path>")
      System.exit(1)
    }

    val pathInput = args(0)
    val pathOutput = args(1)
    val conf = new SparkConf()
      .setAppName("Spark Batch Example")
      .set("spark.io.compression.codec", "lz4")
    val sc = new SparkContext(conf)
    conf.set("spark.cleaner.ttl", "120000")

    
    /* CLEAN DESTINATION FOLDER */
    val fs=FileSystem.get(sc.hadoopConfiguration)
    fs.delete(new Path(pathOutput),true)
    
    /* READ DATA FROM HDFS, SORT BY KEY, SAVE TO HDFS */
    val words = sc.textFile(pathInput).repartition(1).map {
      line =>
        val token = line.split(",")
        (token(0), token(1))
    }.sortByKey()

    words.saveAsTextFile(pathOutput)
    
    sc.stop

  }
}

