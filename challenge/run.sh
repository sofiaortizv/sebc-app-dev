#!/bin/sh

#export SPARK_DIST_CLASSPATH=$(hadoop classpath)
export SPARK_DIST_CLASSPATH=$(hadoop classpath):$(hbase classpath)
spark2-submit --deploy-mode client --master yarn --executor-memory 1G --num-executors 2 --executor-cores 2 --conf "spark.driver.extraClassPath=/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-client-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-server-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar" --conf "spark.executor.extraClassPath=/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-client-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-server-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar" --class com.cloudera.sdk.SparkStreamExample challenge_2.11-1.0.jar localhost 41415

