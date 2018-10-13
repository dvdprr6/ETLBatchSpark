package com.etl.batch.spark.dao.dataframe

object DataFrameDaoFactory {
  def getDao(clazz: Class[_ <: DataFrameDao]): DataFrameDao = {
    var dao: DataFrameDao = clazz.newInstance()
    return dao
  }
}
