package com.etl.batch.spark

import com.etl.batch.spark.dao.{CalendarsDateDao, DaoFactory, FrequenciesDao, TripsDao}

object Main extends App{

  val trips = DaoFactory.getDao(classOf[TripsDao]).get()
  val calendar_dates = DaoFactory.getDao(classOf[CalendarsDateDao]).get()
  val frequencies = DaoFactory.getDao(classOf[FrequenciesDao]).get()

}
