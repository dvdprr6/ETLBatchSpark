package com.etl.batch.spark

import com.etl.batch.spark.connection.SparkConnection
import com.etl.batch.spark.dao._
import com.etl.batch.spark.enrich.stoptimes.EnrichStoptimesBuilderFactory
import com.etl.batch.spark.enrich.trips.EnrichTripsBuilderFactory
import com.etl.batch.spark.util.{Constants, SchemaGeneration}
import org.apache.spark.sql.SaveMode

object Main extends App{

  Thread.sleep(5000)

  var tripsDF = DaoFactory.getDao(classOf[TripsDao]).getDataFrame()
  var calendarDatesDF = DaoFactory.getDao(classOf[CalendarsDateDao]).getDataFrame()
  var frequenciesDF = DaoFactory.getDao(classOf[FrequenciesDao]).getDataFrame()

  var enrichedTripsDF = EnrichTripsBuilderFactory.getTripsBuilder()
    .setTrips(tripsDF).setCalendarDates(calendarDatesDF).setFrequencies(frequenciesDF).build.enrich()

  enrichedTripsDF.rdd.map(x => x.mkString(",")).saveAsTextFile(Constants.ENRICHED_TRIPS)

  var stopTimesDS = DaoFactory.getDao(classOf[StoptimesDao]).getDStream()
  var schema = SchemaGeneration.getSchema(Constants.STOP_TIMES_SCHEMA)

  stopTimesDS.foreachRDD{ rdd =>
    var stopTimesDF = SparkConnection.getSparkSession.createDataFrame(rdd, schema)

    stopTimesDF.rdd.map(x => x.mkString(","))saveAsTextFile(Constants.STOP_TIMES_TEST)

    var enrichedStoptimes = EnrichStoptimesBuilderFactory.getStoptimesBuilder()
      .setEnrichedTrips(enrichedTripsDF).setStoptimes(stopTimesDF).build.enrich()

    if(!enrichedStoptimes.take(1).isEmpty){
      enrichedStoptimes.rdd.map(x => x.mkString(",")).saveAsTextFile(Constants.ENRICHED_STOP_TIMES)
      //enrichedStoptimes.write.mode(SaveMode.Append).csv(Constants.ENRICHED_STOP_TIMES)
    }
  }

  SparkConnection.getSparkStreaming.start()
  SparkConnection.getSparkStreaming.awaitTermination()

}
