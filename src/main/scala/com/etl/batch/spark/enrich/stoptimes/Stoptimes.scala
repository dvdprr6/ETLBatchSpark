package com.etl.batch.spark.enrich.stoptimes
import com.etl.batch.spark.enrich.Enrich
import org.apache.spark.sql.DataFrame

class Stoptimes extends EnrichStoptimesBuilder {
  override var enrichedTrips: DataFrame = _
  override var stoptimes: DataFrame = _

  override def setEnrichedTrips(enrichedTrips: DataFrame): EnrichStoptimesBuilder = {
    this.enrichedTrips = enrichedTrips
    return this
  }

  override def setStoptimes(stoptimes: DataFrame): EnrichStoptimesBuilder = {
    this.stoptimes = stoptimes
    return this
  }

  override def build: Enrich = {
    return new EnrichStoptimes(this)
  }
}
