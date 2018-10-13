package com.etl.batch.spark.connection

import com.etl.batch.spark.util.Constants
import org.apache.spark.sql.SparkSession

object SparkConnection{
  private var connection: SparkSession = null

  def getConnection() : SparkSession = {
    if(connection == null){
      connection = SparkSession.builder().appName(Constants.APP_NAME).enableHiveSupport().getOrCreate()
    }

    return connection
  }

  def close(): Unit = {
    connection.close()
    connection = null
  }
}
