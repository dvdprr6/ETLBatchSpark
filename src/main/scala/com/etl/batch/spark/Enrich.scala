package com.etl.batch.spark

import com.etl.batch.spark.util.Constants
import org.apache.spark.sql.DataFrame

object Enrich {
  val enrichTrips = (trips: DataFrame, calendarDates: DataFrame, frequencies: DataFrame) => {
    trips.join(frequencies, Seq(Constants.TRIPS_TRIP_ID), "inner").join(calendarDates, Seq(Constants.TRIPS_SERVICE_ID), "inner")
      .select(
        trips(Constants.TRIPS_ROUTE_ID),
        trips(Constants.TRIPS_SERVICE_ID),
        trips(Constants.TRIPS_TRIP_ID),
        trips(Constants.TRIPS_TRIP_HEADSIGN),
        trips(Constants.TRIPS_DIRECTION_ID),
        trips(Constants.TRIPS_SHAPE_ID),
        trips(Constants.TRIPS_WHEELCHAIR_ACCESSIBLE),
        trips(Constants.TRIPS_NOTE_FR),
        trips(Constants.TRIPS_NOTE_EN),
        calendarDates(Constants.CALENDAR_DATES_DATE),
        calendarDates(Constants.CALENDAR_DATES_EXCEPTION_TYPE),
        frequencies(Constants.FREQUENCIES_START_TIME),
        frequencies(Constants.FREQUENCIES_END_TIME),
        frequencies(Constants.FREQUENCIES_HEADWAY_SECS)
      )
  }

  val enrichStoptimes = (enrichedTrips: DataFrame, stoptimes: DataFrame) => {
    enrichedTrips.join(stoptimes, Seq(Constants.TRIPS_TRIP_ID), "inner")
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
  }
}
