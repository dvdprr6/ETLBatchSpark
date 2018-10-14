package com.etl.batch.spark.dao.dataframe

import com.etl.batch.spark.connection.SparkConnection
import com.etl.batch.spark.util.Constants
import org.apache.spark.sql.DataFrame

class TripsDataFrameDao extends DataFrameDao {
  override def get(): DataFrame = {
    return SparkConnection.getSparkSession.read.format(Constants.FORMAT_CSV).option("header", "true").load(Constants.TRIPS)
  }
}

