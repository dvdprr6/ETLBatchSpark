# ETLBatchSpark

## Introduction

Using Spark Streaming and STM GTFS data, which can be found [here](http://www.stm.info/en/about/developers). This application will read the trips, calendar dates, and frequencies dataset from the HDFS and enrich them to create a enriched trips data set. Spark Streaming will stream the stop times on the HDFS and enrich it with enriched trips to create a new data set called enriched stop times. Enrichment is preformed by joining the trip_id between frequencies and trips, joining service_id between trips and calendar dates to create the enriched trips dataset. Finally, enriched trips will be joined with stop times and the trip_id to create enriched stoptimes.

## Scripts

The ```start.sh``` script to submit the application to Spark

```
#!/bin/bash

export SPARK_SUBMIT_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5000

spark-submit --class com.etl.batch.spark.Main --master local[*] etlbatchspark_2.11-0.1.jar
```

The ```copyStopTimesToHDFS.sh``` script to copy the chunks of stop times data to the HDFS
```
#!/bin/bash

HDFS_STOP_TIMES_TEMP_DIR=/user/vagrant/stm/gtfs/staging/stop_times_temp
HDFS_STOP_TIMES_DIR=/user/vagrant/stm/gtfs/staging/stop_times
FS_GTFS_STM_STOP_TIMES_DIR=/home/vagrant/projects/ETLBatchSpark/gtfs_stm/stop_times

for entry in $FS_GTFS_STM_STOP_TIMES_DIR/*
do
filename=$(basename $entry)
hadoop fs -put $entry $HDFS_STOP_TIMES_TEMP_DIR
hadoop fs -mv $HDFS_STOP_TIMES_TEMP_DIR/$filename $HDFS_STOP_TIMES_DIR
echo $filename
done
```

The ```cleanHDFS.sh``` script to clear the data from the HDFS
```
#!/bin/bash

HDFS_STOP_TIMES_TEMP_DIR=/user/vagrant/stm/gtfs/staging/stop_times_temp
HDFS_STOP_TIMES_DIR=/user/vagrant/stm/gtfs/staging/stop_times
HDFS_STOP_TIMES_TEST_DIR=/user/vagrant/stm/gtfs/staging/stop_times_test
HDFS_ENRICHED_STOP_TIMES_DIR=/user/vagrant/stm/gtfs/staging/enriched_stop_times
HDFS_ENRICHED_TRIPS_DIR=/user/vagrant/stm/gtfs/staging/enriched_trips

hadoop fs -rm -r $HDFS_STOP_TIMES_TEMP_DIR/*
hadoop fs -rm -r $HDFS_STOP_TIMES_DIR/*
hadoop fs -rm -r $HDFS_ENRICHED_STOP_TIMES_DIR/*
hadoop fs -rm -r $HDFS_ENRICHED_TRIPS_DIR
hadoop fs -rm -r $HDFS_STOP_TIMES_TEST_DIR
```



## Installation Directory Setup

Create a installation directory, in this installation this will include all the necessary scripts.
