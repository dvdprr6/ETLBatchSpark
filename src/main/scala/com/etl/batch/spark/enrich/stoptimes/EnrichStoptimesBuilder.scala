package com.etl.batch.spark.enrich.stoptimes

import com.etl.batch.spark.enrich.Enrich
import org.apache.spark.sql.DataFrame

abstract class EnrichStoptimesBuilder {
  var enrichedTrips: DataFrame
  var stoptimes: DataFrame

  def setEnrichedTrips(enrichedTrips: DataFrame): EnrichStoptimesBuilder
  def setStoptimes(stoptimes: DataFrame): EnrichStoptimesBuilder
  def build: Enrich
}
