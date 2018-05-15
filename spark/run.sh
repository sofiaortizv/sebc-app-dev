#!/bin/sh

SPARK_CLASS=com.cloudera.sdk.SparkBatchExample
SPARK_BUILD_DIR=${PWD}
SPARK_JAR=sparkbatchexample_2.11-1.0.jar
DIR_INPUT=spark/input/file*.csv
DIR_OUTPUT=spark/output/

DEPLOY_MODE=cluster
MASTER=yarn

spark-submit --deploy-mode ${DEPLOY_MODE} --master ${MASTER} --executor-memory 128M --num-executors 2 --executor-cores 1 --class ${SPARK_CLASS} ${SPARK_BUILD_DIR}/${SPARK_JAR} ${DIR_INPUT} ${DIR_OUTPUT}
