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
    .toDF(
      Constants.TRIPS_ROUTE_ID,
      Constants.TRIPS_SERVICE_ID,
      Constants.TRIPS_TRIP_ID,
      Constants.TRIPS_TRIP_HEADSIGN,
      Constants.TRIPS_DIRECTION_ID,
      Constants.TRIPS_SHAPE_ID,
      Constants.TRIPS_WHEELCHAIR_ACCESSIBLE,
      Constants.TRIPS_NOTE_FR,
      Constants.TRIPS_NOTE_EN,
      Constants.CALENDAR_DATES_DATE,
      Constants.CALENDAR_DATES_EXCEPTION_TYPE,
      Constants.FREQUENCIES_START_TIME,
      Constants.FREQUENCIES_END_TIME,
      Constants.FREQUENCIES_HEADWAY_SECS
    )

  enrichedTripsDF.rdd.map(x => x.mkString(",")).saveAsTextFile(Constants.ENRICHED_TRIPS)

  var stopTimesDS = DaoFactory.getDao(classOf[StoptimesDao]).getDStream()
  var schema = SchemaGeneration.getSchema(Constants.STOP_TIMES_SCHEMA)

  stopTimesDS.foreachRDD{ rdd =>
    var stopTimesDF = SparkConnection.getSparkSession.createDataFrame(rdd, schema)
      .toDF(
        Constants.STOP_TIMES_TRIP_ID,
        Constants.STOP_TIMES_ARRIVAL_TIME,
        Constants.STOP_TIMES_DEPARTURE_TIME,
        Constants.STOP_TIMES_STOP_ID,
        Constants.STOP_TIMES_STOP_SEQUENCIES
      )

    var enrichedStoptimes = EnrichStoptimesBuilderFactory.getStoptimesBuilder()
      .setEnrichedTrips(enrichedTripsDF).setStoptimes(stopTimesDF).build.enrich()

    enrichedStoptimes.write.mode(SaveMode.Append).csv(Constants.ENRICHED_STOP_TIMES)
  }

  SparkConnection.getSparkStreaming.start()
  SparkConnection.getSparkStreaming.awaitTermination()

}
