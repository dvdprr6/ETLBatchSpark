package com.etl.batch.spark.util

object Constants {
  val APP_NAME: String = "ETLBatchSpark"
  val FORMAT_CSV: String = "cvs"

  val TRIPS: String = "hdfs://localhost:9000/user/vagrant/stm/gtfs/trips/trips.txt"
  val CALENDAR_DATES: String = "hdfs://localhost:9000/user/vagrant/stm/gtfs/calendar_dates/calendar_dates.txt"
  val FREQUENCIES: String = "hdfs://localhost:9000/user/vagrant/stm/gtfs/frequencies/frequencies.txt"
}
