#!/bin/sh

#export SPARK_DIST_CLASSPATH=$(hadoop classpath)
export SPARK_DIST_CLASSPATH=$(hadoop classpath):$(hbase classpath)

SPARK_CLASS=com.cloudera.sdk.SparkStreamExample
SPARK_BUILD_DIR=${PWD}
SPARK_JAR=challenge_2.11-1.0.jar
HOST=localhost
PORT=41415

DEPLOY_MODE=client
MASTER=yarn

spark2-submit --deploy-mode ${DEPLOY_MODE} --master ${MASTER} --executor-memory 1G --num-executors 2 --executor-cores 2 --class ${SPARK_CLASS} ${SPARK_BUILD_DIR}/${SPARK_JAR} ${HOST} ${PORT}


 export SPARK_DIST_CLASSPATH=$(hadoop classpath):$(hbase classpath):/home/sofia/challenge/hbase-0.90.0.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar


----
spark2-submit --deploy-mode client --master yarn --executor-memory 1G --num-executors 2 --executor-cores 2 --conf "spark.driver.extraClassPath=/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-client-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-server-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar" --conf "spark.executor.extraClassPath=/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-client-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-server-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar" --class com.cloudera.sdk.SparkStreamExample challenge_2.11-1.0.jar localhost 41415



 ..bueno .. actual
spark2-submit --deploy-mode client --master yarn --executor-memory 1G --num-executors 4 --executor-cores 2 --conf "spark.driver.extraClassPath=/home/sofia/challenge/hbase-0.90.0.jar:/home/sofia/challenge/hbase-client-1.2.0-cdh5.14.2.jar" --class com.cloudera.sdk.SparkStreamExample challenge_2.11-1.0.jar 127.0.0.1 41415

spark2-submit --deploy-mode client --master yarn --executor-memory 1G --num-executors 4 --executor-cores 2 --conf "spark.driver.extraClassPath=/home/sofia/challenge/hbase-0.90.0.jar:/home/sofia/challenge/hbase-client-1.2.0-cdh5.14.2.jar" --conf "--conf spark.executor.extraClassPath=/home/sofia/challenge/hbase-0.90.0.jar:/home/sofia/challenge/hbase-client-1.2.0-cdh5.14.2.jar" --class com.cloudera.sdk.SparkStreamExample challenge_2.11-1.0.jar 127.0.0.1 41415

-- Bueno
spark2-submit --deploy-mode client --master yarn --executor-memory 1G --num-executors 2 --executor-cores 2 --conf "spark.driver.extraClassPath=/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar" --class com.cloudera.sdk.SparkStreamExample challenge_2.11-1.0.jar 127.0.0.1 41415

%%% Bueno
spark2-submit --deploy-mode client --master yarn --executor-memory 1G --num-executors 2 --executor-cores 2 --conf "spark.driver.extraClassPath=/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar:" --class com.cloudera.sdk.SparkStreamExample challenge_2.11-1.0.jar 127.0.0.1 41415-


-- bueno
spark2-submit --deploy-mode client --master yarn --executor-memory 1G --num-executors 2 --executor-cores 2 --conf "spark.driver.extraClassPath=/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-client-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-common-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-server-1.2.0-cdh5.14.2.jar:/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/hbase-spark-1.2.0-cdh5.14.2.jar" --class com.cloudera.sdk.SparkStreamExample challenge_2.11-1.0.jar 127.0.0.1 41415


spark2-submit --deploy-mode client --master yarn --executor-memory 1G --num-executors 2 --executor-cores 2 --conf "spark.driver.extraClassPath=/opt/cloudera/parcels/CDH-5.14.2-1.cdh5.14.2.p0.3/lib/hbase/*" --class com.cloudera.sdk.SparkStreamExample challenge_2.11-1.0.jar localhost 41415








