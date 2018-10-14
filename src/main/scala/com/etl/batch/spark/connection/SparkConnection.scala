package com.etl.batch.spark.connection

import com.etl.batch.spark.util.Constants
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkSessionConnection{
  private lazy val session: SparkSession = SparkSession.builder().appName(Constants.APP_NAME).enableHiveSupport().getOrCreate()
  private lazy val context: SparkContext = session.sparkContext
  private lazy val streaming: StreamingContext = new StreamingContext(context, Seconds(Constants.STREAMING_INTERVAL))

  lazy val getSparkSession: SparkSession = session
  lazy val getSparkStreaming: StreamingContext = streaming

  lazy val closeSparkSession: Unit = session.close()
}