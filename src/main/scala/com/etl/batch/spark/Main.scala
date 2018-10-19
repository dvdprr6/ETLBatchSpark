package com.etl.batch.spark

import com.etl.batch.spark.connection.SparkConnection
import com.etl.batch.spark.util.{Constants, SchemaGeneration}

object Main extends App{

  var tripsDF = RetrieveDataFrame.getDataFrame(Constants.TRIPS, Constants.FORMAT_CSV)
  var calendarDatesDF = RetrieveDataFrame.getDataFrame(Constants.CALENDAR_DATES, Constants.FORMAT_CSV)
  var frequenciesDF = RetrieveDataFrame.getDataFrame(Constants.FREQUENCIES, Constants.FORMAT_CSV)

  var enrichedTripsDF = Enrich.enrichTrips(tripsDF, calendarDatesDF, frequenciesDF)

  //enrichedTripsDF.rdd.map(x => x.mkString(",")).saveAsTextFile(Constants.ENRICHED_TRIPS)


  var file = SparkConnection.getSparkStreaming.textFileStream(Constants.STOP_TIMES)

  var stoptimesDS = RetrieveDStream.getStopTimes(file)

  var schema = SchemaGeneration.getSchema(Constants.STOP_TIMES_SCHEMA)

  stoptimesDS.foreachRDD{ rdd =>
    var stopTimesDF = SparkConnection.getSparkSession.createDataFrame(rdd, schema)

    //stopTimesDF.rdd.map(x => x.mkString(","))saveAsTextFile(Constants.STOP_TIMES_TEST)

    var enrichedStoptimes = Enrich.enrichStoptimes(enrichedTripsDF, stopTimesDF)

    if(!enrichedStoptimes.take(1).isEmpty){
      enrichedStoptimes.rdd.map(x => x.mkString(",")).saveAsTextFile(Constants.ENRICHED_STOP_TIMES)
    }
  }

  SparkConnection.getSparkStreaming.start()
  SparkConnection.getSparkStreaming.awaitTermination()

}
