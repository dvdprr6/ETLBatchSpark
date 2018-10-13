package com.etl.batch.spark

import com.etl.batch.spark.dao.dataframe.{CalendarsDateDataFrameDao, DataFrameDaoFactory, FrequenciesDataFrameDao, TripsDataFrameDao}
import com.etl.batch.spark.enrich.trips.{EnrichTripsBuilderFactory, Trips}
import com.etl.batch.spark.util.Constants

object Main extends App{

  Thread.sleep(5000)

  val trips = DataFrameDaoFactory.getDao(classOf[TripsDataFrameDao]).get()
  val calendar_dates = DataFrameDaoFactory.getDao(classOf[CalendarsDateDataFrameDao]).get()
  val frequencies = DataFrameDaoFactory.getDao(classOf[FrequenciesDataFrameDao]).get()

  val enrichedTrips = EnrichTripsBuilderFactory.getTripsBuilder(classOf[Trips])
    .setTrips(trips).setCalendarDates(calendar_dates).setFrequencies(frequencies).build.enrich()

  // the map removes the brackets around the record
  //enrichedTrips.rdd.map(x => x.mkString(",")).saveAsTextFile(Constants.ENRICHED_TRIPS)



}
