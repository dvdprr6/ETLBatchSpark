package com.etl.batch.spark

import com.etl.batch.spark.connection.SparkConnection

object RetrieveDataFrame {
  val getDataFrame = (fileSource: String, fileFormat: String) => {
    SparkConnection.getSparkSession.read.format(fileFormat).option("header", "true").load(fileSource)
  }
}
