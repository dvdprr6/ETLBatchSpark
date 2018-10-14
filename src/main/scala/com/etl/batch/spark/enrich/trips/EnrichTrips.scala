package com.etl.batch.spark.enrich.trips

import com.etl.batch.spark.enrich.Enrich
import com.etl.batch.spark.util.Constants
import org.apache.spark.sql.DataFrame

// REFERENCE: https://gist.github.com/kencoba/1874015
class EnrichTrips(builder: EnrichTripsBuilder) extends Enrich {
  var trips: DataFrame = builder.trips
  var calendarDates: DataFrame = builder.calendarDates
  var frequencies: DataFrame = builder.frequencies

  override def enrich(): DataFrame = {

    var enrichedTrips = trips.join(frequencies, Seq(Constants.TRIPS_TRIP_ID), "inner").join(calendarDates, Seq(Constants.TRIPS_SERVICE_ID), "inner")
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

    return enrichedTrips
  }
}
