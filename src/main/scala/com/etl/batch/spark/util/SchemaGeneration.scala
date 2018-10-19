package com.etl.batch.spark.util

import org.apache.spark.sql.types.{StringType, StructField, StructType}

object SchemaGeneration {
  var getSchema = (schemaString: String) => {
    StructType(schemaString.split(",").map(fieldName => StructField(fieldName, StringType, true)))
  }

}
