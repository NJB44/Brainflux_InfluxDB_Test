package com.njb44.brainflux_entry_test;


import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageTwoController {
	
	
	//connect to initialized database connection
	InfluxDB influxDB;
	
	@Autowired
	public PageTwoController( InfluxDB influxDB) {
		this.influxDB = influxDB;
	}
	
	//helper function to turn the query result into a properly formatted html table
	public String QueryResultToTable(QueryResult queryResult) {
	  String startString = queryResult.toString();
	
	  String tableString = "<table><tr>";
	
	  String[] colSplit = startString.split("columns=");
	  String cols = colSplit[1].split(", values")[0];
	  cols = cols.replaceAll("\\[", "");
	  cols = cols.replaceAll("\\]", "");
	  String[] colHeaders = cols.split(",");
	  for (int i =0; i <= colHeaders.length - 1; i++) {
		 tableString = tableString + "<th>" + colHeaders[i] + "</th>";
	  }
	  tableString = tableString +"</tr>";
				
	  String[] valSplit = startString.split("values=\\[");
	  String allVals = valSplit[1].split("\\]\\]\\], error")[0];
	  String[] valRows = allVals.split("\\],");
	  for (int i =0; i <= valRows.length - 1; i++) {

		  tableString = tableString + "<tr>";
		  String valRow = valRows[i];
		  valRow = valRow.replaceAll("\\[", "");
		  valRow = valRow.replaceAll("\\]", "");
		  String[] vals = valRow.split(",");
		  for (int j = 0; j<= vals.length -1; j++) {
			 tableString=tableString + "<td>" + vals[j] + "</td>";
		  }
		  tableString = tableString + "</tr>";
		  
	  }
	  
	  tableString = tableString + "</table>";
	  System.out.println(tableString);		  

	return tableString;
	}

	
	@RequestMapping("/page_two")
	public ModelAndView runQuery() {
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("page_two");
		
		String QueryText ="SELECT MEAN(CO), MEAN(NO2) "
				+ "FROM AirQualPoint "
				+ "WHERE time >= '2014-01-01T00:00:00Z' AND time < '2015-01-01T00:00:00Z'"
				+ "GROUP BY time(1d)";
		Query InputQuery = new Query(QueryText);
		QueryResult queryResult = influxDB.query(InputQuery);
		
		String tableResults =QueryResultToTable(queryResult);		
		
		mv.addObject("queryResult", tableResults);
		mv.addObject("InputQuery", QueryText);
	
				
		
		return mv;
	
		
	}


}
