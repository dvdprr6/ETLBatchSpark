package com.etl.batch.spark.dao

import org.apache.spark.sql.DataFrame

trait Dao {
  def get(): DataFrame
}
