package com.etl.batch.spark.dao.dataframe

import org.apache.spark.sql.DataFrame

trait DataFrameDao {
  def get(): DataFrame
}
