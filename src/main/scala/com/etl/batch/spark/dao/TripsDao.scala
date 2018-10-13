package com.etl.batch.spark.dao {

  import com.etl.batch.spark.connection.SparkConnection
  import com.etl.batch.spark.util.Constants
  import org.apache.spark.sql.DataFrame

  class TripsDao extends Dao {
    override def get(): DataFrame = {
      return SparkConnection.getConnection().read.format(Constants.FORMAT_CSV).option("header", "true").load(Constants.TRIPS)
    }
  }

}
