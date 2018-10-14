package com.etl.batch.spark.util

object Constants {
  val APP_NAME: String = "ETLBatchSpark"
  val FORMAT_CSV: String = "csv"

  val TRIPS: String = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/trips/trips.txt"
  val CALENDAR_DATES: String = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/calendar_dates/calendar_dates.txt"
  val FREQUENCIES: String = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/frequencies/frequencies.txt"
  val ENRICHED_TRIPS: String = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/enriched_trips/"
  val ENRICHED_STOP_TIMES: String = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/enriched_stop_times/"
  val STOP_TIMES: String = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/stop_times/"

  val TRIPS_ROUTE_ID: String = "route_id"
  val TRIPS_SERVICE_ID: String = "service_id"
  val TRIPS_TRIP_ID: String = "trip_id"
  val TRIPS_TRIP_HEADSIGN: String = "trip_headsign"
  val TRIPS_DIRECTION_ID: String = "direction_id"
  val TRIPS_SHAPE_ID: String = "shape_id"
  val TRIPS_WHEELCHAIR_ACCESSIBLE: String = "wheelchair_accessible"
  val TRIPS_NOTE_FR: String = "note_fr"
  val TRIPS_NOTE_EN: String = "note_en"
  val CALENDAR_DATES_DATE: String = "date"
  val CALENDAR_DATES_EXCEPTION_TYPE: String = "exception_type"
  val FREQUENCIES_START_TIME: String = "start_time"
  val FREQUENCIES_END_TIME: String = "end_time"
  val FREQUENCIES_HEADWAY_SECS: String = "headway_secs"
  val STOP_TIMES_TRIP_ID: String = "trip_id"
  val STOP_TIMES_ARRIVAL_TIME: String = "arrival_time"
  val STOP_TIMES_DEPARTURE_TIME: String = "departure_time"
  val STOP_TIMES_STOP_ID: String = "stop_id"
  val STOP_TIMES_STOP_SEQUENCIES: String = "stop_sequence"

  val STREAMING_INTERVAL: Int = 10

  val STOP_TIMES_SCHEMA: String = "trip_id,arrival_time,departure_time,stop_id,stop_sequence"
}
