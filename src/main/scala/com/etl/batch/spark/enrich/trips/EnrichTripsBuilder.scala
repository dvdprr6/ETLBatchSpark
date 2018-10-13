package com.etl.batch.spark.enrich.trips

import com.etl.batch.spark.enrich.Enrich
import org.apache.spark.sql.DataFrame

abstract class EnrichTripsBuilder {
  var trips: DataFrame
  var calendarDates: DataFrame
  var frequencies: DataFrame

  def setTrips(trips: DataFrame): EnrichTripsBuilder
  def setCalendarDates(calendarDate: DataFrame): EnrichTripsBuilder
  def setFrequencies(frequencies: DataFrame): EnrichTripsBuilder
  def build: Enrich
}
