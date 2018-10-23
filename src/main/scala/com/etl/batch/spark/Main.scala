package com.etl.batch.spark

import com.etl.batch.spark.connection.SparkConnection
import com.etl.batch.spark.util.{Constants, SchemaGeneration}

object Main extends App{

  val tripsDF = RetrieveDataFrame.getDataFrame(Constants.TRIPS, Constants.FORMAT_CSV)
  val calendarDatesDF = RetrieveDataFrame.getDataFrame(Constants.CALENDAR_DATES, Constants.FORMAT_CSV)
  val frequenciesDF = RetrieveDataFrame.getDataFrame(Constants.FREQUENCIES, Constants.FORMAT_CSV)

  val enrichedTripsDF = Enrich.enrichTrips(tripsDF, calendarDatesDF, frequenciesDF)

  //enrichedTripsDF.rdd.map(x => x.mkString(",")).saveAsTextFile(Constants.ENRICHED_TRIPS)


  val file = SparkConnection.getSparkStreaming.textFileStream(Constants.STOP_TIMES)

  val stoptimesDS = RetrieveDStream.getStopTimes(file)

  val schema = SchemaGeneration.getSchema(Constants.STOP_TIMES_SCHEMA)

  stoptimesDS.foreachRDD{ rdd =>
    val stopTimesDF = SparkConnection.getSparkSession.createDataFrame(rdd, schema)

    //stopTimesDF.rdd.map(x => x.mkString(","))saveAsTextFile(Constants.STOP_TIMES_TEST)

    val enrichedStoptimes = Enrich.enrichStoptimes(enrichedTripsDF, stopTimesDF)

    if(!enrichedStoptimes.take(1).isEmpty){
      enrichedStoptimes.rdd.map(x => x.mkString(",")).saveAsTextFile(Constants.ENRICHED_STOP_TIMES)
    }
  }

  SparkConnection.getSparkStreaming.start()
  SparkConnection.getSparkStreaming.awaitTermination()

}
