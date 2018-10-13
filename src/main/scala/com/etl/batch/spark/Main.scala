package com.etl.batch.spark

import com.etl.batch.spark.dao.{CalendarsDateDao, DaoFactory, FrequenciesDao, TripsDao}
import com.etl.batch.spark.enrich.trips.{EnrichTripsBuilderFactory, Trips}
import com.etl.batch.spark.util.Constants

object Main extends App{

  Thread.sleep(5000)

  val trips = DaoFactory.getDao(classOf[TripsDao]).get()
  val calendar_dates = DaoFactory.getDao(classOf[CalendarsDateDao]).get()
  val frequencies = DaoFactory.getDao(classOf[FrequenciesDao]).get()

  val enrichedTrips = EnrichTripsBuilderFactory.getTripsBuilder(classOf[Trips])
    .setTrips(trips).setCalendarDates(calendar_dates).setFrequencies(frequencies).build.enrich()

  // the map removes the brackets around the record
  //enrichedTrips.rdd.map(x => x.mkString(",")).saveAsTextFile(Constants.ENRICHED_TRIPS)



}
