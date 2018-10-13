package com.etl.batch.spark.dao

object DaoFactory {
  def getDao[DAO <: Dao](clazz: Class[DAO]): DAO = {
    val dao: DAO = clazz.newInstance()
    return dao
  }
}
