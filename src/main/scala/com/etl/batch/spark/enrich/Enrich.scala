package com.etl.batch.spark.enrich

import org.apache.spark.sql.DataFrame

trait Enrich {
  def enrich(): DataFrame
}
