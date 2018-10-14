package com.etl.batch.spark.dao
import com.etl.batch.spark.connection.SparkConnection
import com.etl.batch.spark.util.Constants
import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.streaming.dstream.DStream

class StoptimesDao extends Dao {
  override def getDataFrame(): DataFrame = throw new UnsupportedOperationException

  override def getDStream(): DStream[Row] = {
    var file = SparkConnection.getSparkStreaming.textFileStream(Constants.STOP_TIMES)
    var stoptimesDS = file.map(_.split(",")).map(stoptimes => Row(stoptimes(0), stoptimes(1), stoptimes(2), stoptimes(3), stoptimes(4)))
    return stoptimesDS
  }
}
