package com.etl.batch.spark.enrich.stoptimes

import com.etl.batch.spark.connection.SparkConnection
import com.etl.batch.spark.enrich.Enrich
import com.etl.batch.spark.util.Constants
import org.apache.spark.sql.DataFrame

class EnrichStoptimes(builder: EnrichStoptimesBuilder) extends Enrich{
  var enrichedTrips: DataFrame = builder.enrichedTrips
  var stoptimes: DataFrame = builder.stoptimes

  override def enrich(): DataFrame = {

    /*
    var enrichedStoptimes = enrichedTrips.join(stoptimes, Seq(Constants.TRIPS_TRIP_ID), "inner")
      .select(
        enrichedTrips(Constants.TRIPS_ROUTE_ID),
        enrichedTrips(Constants.TRIPS_SERVICE_ID),
        enrichedTrips(Constants.TRIPS_TRIP_ID),
        enrichedTrips(Constants.TRIPS_TRIP_HEADSIGN),
        enrichedTrips(Constants.TRIPS_DIRECTION_ID),
        enrichedTrips(Constants.TRIPS_SHAPE_ID),
        enrichedTrips(Constants.TRIPS_WHEELCHAIR_ACCESSIBLE),
        enrichedTrips(Constants.TRIPS_NOTE_FR),
        enrichedTrips(Constants.TRIPS_NOTE_EN),
        enrichedTrips(Constants.CALENDAR_DATES_DATE),
        enrichedTrips(Constants.CALENDAR_DATES_EXCEPTION_TYPE),
        enrichedTrips(Constants.FREQUENCIES_START_TIME),
        enrichedTrips(Constants.FREQUENCIES_END_TIME),
        enrichedTrips(Constants.FREQUENCIES_HEADWAY_SECS),
        stoptimes(Constants.STOP_TIMES_ARRIVAL_TIME),
        stoptimes(Constants.STOP_TIMES_DEPARTURE_TIME),
        stoptimes(Constants.STOP_TIMES_STOP_ID),
        stoptimes(Constants.STOP_TIMES_STOP_SEQUENCIES)
      )
    */
    /*
    var enrichedStoptimes = stoptimes.join(enrichedTrips, enrichedTrips.col(Constants.TRIPS_TRIP_ID) === stoptimes.col(Constants.STOP_TIMES_TRIP_ID))
      .select(
        enrichedTrips(Constants.TRIPS_ROUTE_ID),
        enrichedTrips(Constants.TRIPS_SERVICE_ID),
        enrichedTrips(Constants.TRIPS_TRIP_ID),
        enrichedTrips(Constants.TRIPS_TRIP_HEADSIGN),
        enrichedTrips(Constants.TRIPS_DIRECTION_ID),
        enrichedTrips(Constants.TRIPS_SHAPE_ID),
        enrichedTrips(Constants.TRIPS_WHEELCHAIR_ACCESSIBLE),
        enrichedTrips(Constants.TRIPS_NOTE_FR),
        enrichedTrips(Constants.TRIPS_NOTE_EN),
        enrichedTrips(Constants.CALENDAR_DATES_DATE),
        enrichedTrips(Constants.CALENDAR_DATES_EXCEPTION_TYPE),
        enrichedTrips(Constants.FREQUENCIES_START_TIME),
        enrichedTrips(Constants.FREQUENCIES_END_TIME),
        enrichedTrips(Constants.FREQUENCIES_HEADWAY_SECS),
        stoptimes(Constants.STOP_TIMES_ARRIVAL_TIME),
        stoptimes(Constants.STOP_TIMES_DEPARTURE_TIME),
        stoptimes(Constants.STOP_TIMES_STOP_ID),
        stoptimes(Constants.STOP_TIMES_STOP_SEQUENCIES)
      )
    */

    enrichedTrips.createOrReplaceTempView("enrichedTrips")
    stoptimes.createOrReplaceTempView("stoptimes")

    var enrichedStoptimes = SparkConnection.getSparkSession.sql(
      "SELECT " +
        "et." + Constants.TRIPS_ROUTE_ID +
        ",et." + Constants.TRIPS_SERVICE_ID +
        ",et." + Constants.TRIPS_TRIP_ID +
        ",et." + Constants.TRIPS_TRIP_HEADSIGN +
        ",et." + Constants.TRIPS_DIRECTION_ID +
        ",et." + Constants.TRIPS_SHAPE_ID +
        ",et." + Constants.TRIPS_WHEELCHAIR_ACCESSIBLE +
        ",et." + Constants.TRIPS_NOTE_FR +
        ",et." + Constants.TRIPS_NOTE_EN +
        ",et." + Constants.CALENDAR_DATES_DATE +
        ",et." + Constants.CALENDAR_DATES_EXCEPTION_TYPE +
        ",et." + Constants.FREQUENCIES_START_TIME +
        ",et." + Constants.FREQUENCIES_END_TIME +
        ",et." + Constants.FREQUENCIES_HEADWAY_SECS +
        ",s." + Constants.STOP_TIMES_ARRIVAL_TIME +
        ",s." + Constants.STOP_TIMES_DEPARTURE_TIME +
        ",s." + Constants.STOP_TIMES_STOP_ID +
        ",s." + Constants.STOP_TIMES_STOP_SEQUENCIES + " from enrichedTrips et join stoptimes s on et.trip_id = s.trip_id")

    return enrichedStoptimes
  }
}
