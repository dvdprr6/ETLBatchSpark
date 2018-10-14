package com.etl.batch.spark.dao

import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.streaming.dstream.DStream

trait Dao {
  def getDataFrame(): DataFrame
  def getDStream(): DStream[Row]
}
