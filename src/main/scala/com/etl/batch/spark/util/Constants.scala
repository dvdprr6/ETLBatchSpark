package com.etl.batch.spark.util

object Constants {
  val APP_NAME = "ETLBatchSpark"
  val FORMAT_CSV = "csv"

  val TRIPS = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/trips/trips.txt"
  val CALENDAR_DATES = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/calendar_dates/calendar_dates.txt"
  val FREQUENCIES = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/frequencies/frequencies.txt"
  val ENRICHED_TRIPS = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/enriched_trips/"
  val ENRICHED_STOP_TIMES = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/enriched_stop_times/"
  val STOP_TIMES = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/stop_times/"
  val STOP_TIMES_TEST = "hdfs://localhost:9000/user/vagrant/stm/gtfs/staging/stop_times_test/"

  val TRIPS_ROUTE_ID = "route_id"
  val TRIPS_SERVICE_ID = "service_id"
  val TRIPS_TRIP_ID = "trip_id"
  val TRIPS_TRIP_HEADSIGN = "trip_headsign"
  val TRIPS_DIRECTION_ID = "direction_id"
  val TRIPS_SHAPE_ID = "shape_id"
  val TRIPS_WHEELCHAIR_ACCESSIBLE = "wheelchair_accessible"
  val TRIPS_NOTE_FR = "note_fr"
  val TRIPS_NOTE_EN = "note_en"
  val CALENDAR_DATES_DATE = "date"
  val CALENDAR_DATES_EXCEPTION_TYPE = "exception_type"
  val FREQUENCIES_START_TIME = "start_time"
  val FREQUENCIES_END_TIME = "end_time"
  val FREQUENCIES_HEADWAY_SECS = "headway_secs"
  val STOP_TIMES_TRIP_ID = "trip_id"
  val STOP_TIMES_ARRIVAL_TIME = "arrival_time"
  val STOP_TIMES_DEPARTURE_TIME = "departure_time"
  val STOP_TIMES_STOP_ID = "stop_id"
  val STOP_TIMES_STOP_SEQUENCIES = "stop_sequence"

  val STREAMING_INTERVAL = 5

  val STOP_TIMES_SCHEMA = "trip_id,arrival_time,departure_time,stop_id,stop_sequence"
}
