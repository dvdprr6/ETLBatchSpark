package com.etl.batch.spark.dao.dataframe

import com.etl.batch.spark.connection.SparkSessionConnection
import com.etl.batch.spark.util.Constants
import org.apache.spark.sql.DataFrame

class CalendarsDateDao extends Dao {
  override def get(): DataFrame = {
    return SparkSessionConnection.getSparkConnection.read.format(Constants.FORMAT_CSV).option("header", "true").load(Constants.CALENDAR_DATES)
  }
}

