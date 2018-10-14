package com.etl.batch.spark.enrich.trips

object EnrichTripsBuilderFactory {
  def getTripsBuilder(): EnrichTripsBuilder = {
    var enrichTripsBuilder: EnrichTripsBuilder = new Trips()
    return enrichTripsBuilder
  }
}
