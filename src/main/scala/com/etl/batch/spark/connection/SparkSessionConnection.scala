package com.etl.batch.spark.connection

import com.etl.batch.spark.util.Constants
import org.apache.spark.sql.SparkSession

object SparkConnection{
  private lazy val connection: SparkSession = SparkSession.builder().appName(Constants.APP_NAME).enableHiveSupport().getOrCreate()
  lazy val getSparkConnection: SparkSession = connection
  lazy val closeSparkConnection: Unit = connection.close()
}