package com.njb44.brainflux_entry_test;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class InfluxDBConfiguration {
	
	@Bean
	public InfluxDB initializeConnection() {
	
		final String serverURL = "http://127.0.0.1:8086", username = "root", password = "root";
		final InfluxDB influxDB = InfluxDBFactory.connect(serverURL, username, password);
		
		String databaseName = "AirQuality";
		influxDB.query(new Query("CREATE DATABASE " + databaseName));
		influxDB.setDatabase(databaseName);
		
		influxDB.enableBatch(BatchOptions.DEFAULTS);	
		
		return influxDB;

	}

}
