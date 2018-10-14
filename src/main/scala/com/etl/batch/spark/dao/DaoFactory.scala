package com.etl.batch.spark.dao

object DaoFactory {
  def getDao(clazz: Class[_ <: Dao]): Dao = {
    var dao: Dao = clazz.newInstance()
    return dao
  }
}
