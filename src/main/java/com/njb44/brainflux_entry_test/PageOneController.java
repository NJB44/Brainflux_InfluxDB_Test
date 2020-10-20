package com.njb44.brainflux_entry_test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageOneController {
	
	//connect to initialized database connection
	InfluxDB influxDB;
	
	@Autowired
	public PageOneController( InfluxDB influxDB) {
		this.influxDB = influxDB;
	}
	
	
	
	@RequestMapping("page_one")
	public String page_one() {
		
	
		return "page_one";
	}
	
	public AirQualPoint CreateDataPoint(String[] row) {
		
		//initialize data point
		AirQualPoint point = new AirQualPoint();
		
		//combine date and time, reformat, and convert to Instant
		String time= row[0] + "," + row[1];
		Instant instant;
		
		instant = ReformatTime(time);
		
		point.setTime(instant);
		//convert all values into proper type and set all proceeding values
		
		double CO = Double.parseDouble(row[2].replaceAll(",","."));
		int S1_CO = Integer.parseInt(row[3]);
		int NMHC = Integer.parseInt(row[4]);
		double C6H6 = Double.parseDouble(row[5].replaceAll(",","."));
		int S2_NMHC = Integer.parseInt(row[6]);
		int NOx = Integer.parseInt(row[7]);
		int S3_NOx = Integer.parseInt(row[8]);
		int NO2 = Integer.parseInt(row[9]);
		int S4_NO2 = Integer.parseInt(row[10]);
		int S5_O3 = Integer.parseInt(row[11]);
		double T = Double.parseDouble(row[12].replaceAll(",","."));
		double RH = Double.parseDouble(row[13].replaceAll(",","."));
		double AH = Double.parseDouble(row[14].replaceAll(",","."));
		
		point.setCO(CO);
		point.setS1_CO(S1_CO);
		point.setNMHC(NMHC);
		point.setC6H6(C6H6);
		point.setS2_NMHC(S2_NMHC);
		point.setNOx(NOx);
		point.setS3_NOx(S3_NOx);
		point.setNO2(NO2);
		point.setS4_NO2(S4_NO2);
		point.setS5_O3(S5_O3);
		point.setT(T);
		point.setRH(RH);
		point.setAH(AH);

		
		return point;
		
	}
	
	public Instant ReformatTime(String time) {
	    DateTimeFormatter f = DateTimeFormatter.ofPattern("d/M/uuuu,kk.mm.ss", Locale.ITALY );
	    LocalDateTime ldt = LocalDateTime.parse( time, f);
	    ZoneId z = ZoneId.of( "Europe/Rome" ) ;
	    ZonedDateTime zdt = ldt.atZone( z ) ;
	    Instant instant = zdt.toInstant();
	    return instant;
	}
	
	
				
	@RequestMapping("load")
	public String loadData() {

		
		
		
		//read in the file, loading each row into an object and inserting it into the database
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\nikko\\eclipse-workspace\\brainflux_entry_test\\AirQualityUCI.csv"))) {
			br.readLine(); //remove header
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(";");
		        if( values.length ==0) {
		        	continue;
		        }
		        else if(values[0].trim().isEmpty()) {
		        	continue;
		        }
		        else {
					AirQualPoint PojoPoint = CreateDataPoint(values);
//					Point point = Point.measurementByPOJO(PojoPoint.getClass())
//							.addFieldsFromPOJO(PojoPoint).build();
//					influxDB.write(point);
					//received 400 http errors on all attempts to write by object so I switched to the manual format
					influxDB.write(Point.measurement("AirQualPoint")
							.time(PojoPoint.getTime().getLong(ChronoField.valueOf("INSTANT_SECONDS")), TimeUnit.HOURS)
							.addField("CO", PojoPoint.getCO())
							.addField("S1_CO", PojoPoint.getS1_CO())
							.addField("NMHC", PojoPoint.getNMHC())
							.addField("C6H6", PojoPoint.getC6H6())
							.addField("S2_NMHC", PojoPoint.getS2_NMHC())
							.addField("NOx", PojoPoint.getNOx())
							.addField("S3_NOx", PojoPoint.getS3_NOx())
							.addField("NO2", PojoPoint.getNO2())
							.addField("S4_NO2", PojoPoint.getS4_NO2())
							.addField("S5_O3", PojoPoint.getS5_O3())
							.addField("T", PojoPoint.getT())
							.addField("RH", PojoPoint.getRH())
							.addField("AH", PojoPoint.getAH())
							.build());
		        }
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		System.out.println("loaded");
				
	
		return "page_one";


	};
	

}

