package com.etl.batch.spark.enrich.trips
import com.etl.batch.spark.enrich.Enrich
import org.apache.spark.sql.DataFrame

class Trips extends EnrichTripsBuilder {
  override var trips: DataFrame = _
  override var calendarDates: DataFrame = _
  override var frequencies: DataFrame = _

  override def setTrips(trips: DataFrame): EnrichTripsBuilder = {
    this.trips = trips
    return this
  }

  override def setCalendarDates(calendarDate: DataFrame): EnrichTripsBuilder = {
    this.calendarDates = calendarDate
    return this
  }

  override def setFrequencies(frequencies: DataFrame): EnrichTripsBuilder = {
    this.frequencies = frequencies
    return this
  }

  override def build: Enrich = {
    return new EnrichTrips(this)
  }
}
