package com.etl.batch.spark.enrich.stoptimes

object EnrichStoptimesBuilderFactory {
  def getStoptimesBuilder(): EnrichStoptimesBuilder = {
    var enrichStoptimesBuilder: EnrichStoptimesBuilder = new Stoptimes()
    return enrichStoptimesBuilder
  }
}
