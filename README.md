# Brainflux_InfluxDB_Test

* an influxdb instance must be running on port 8086 prior to loading or displaying the query result
* The first page is mapped to localhost:8080/page_one
** This contains a single button labeled 'load' which, when pressed, parses the csv contents and loads them into an active local influxdb server
* The second page is mapped to localhost:8080/page_two
** This presents the query at the top and a table of average CO and NO2 for each day between 2004 and 2005. 
*** The csv begins to have data starting on March 10 2004, so rows before that point will have NULL averages.
**** Per the query I kept rows for all days in that year, even if no data was collected 
