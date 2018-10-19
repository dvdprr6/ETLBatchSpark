package com.etl.batch.spark.util

import org.apache.spark.sql.types.{StringType, StructField, StructType}

object SchemaGeneration {
  //def getSchema(schemaString: String): StructType = {
  //  return StructType(schemaString.split(",").map(fieldName => StructField(fieldName, StringType, true)))
  //}
  var getSchema = (schemaString: String) => {
    StructType(schemaString.split(",").map(fieldName => StructField(fieldName, StringType, true)))
  }

}
