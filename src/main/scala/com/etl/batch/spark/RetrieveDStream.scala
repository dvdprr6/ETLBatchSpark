package com.etl.batch.spark

import org.apache.spark.sql.Row
import org.apache.spark.streaming.dstream.DStream

object RetrieveDStream {
  val getStopTimes = (stream: DStream[String]) => {
    stream.map(_.split(",")).map(stoptimes => Row(stoptimes(0), stoptimes(1), stoptimes(2), stoptimes(3), stoptimes(4)))
  }
}
