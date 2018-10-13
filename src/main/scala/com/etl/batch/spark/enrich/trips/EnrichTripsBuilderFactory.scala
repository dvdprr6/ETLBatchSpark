package com.etl.batch.spark.enrich.trips

object EnrichTripsBuilderFactory {
  def getTripsBuilder(clazz: Class[_ <: EnrichTripsBuilder]): EnrichTripsBuilder = {
    var enrichTripsBuilder: EnrichTripsBuilder = clazz.newInstance()
    return enrichTripsBuilder
  }
}
