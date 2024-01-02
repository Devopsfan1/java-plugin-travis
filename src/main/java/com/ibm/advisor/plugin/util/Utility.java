/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ibm.advisor.dto.MasterData;
import com.ibm.advisor.dto.MasterDataModel;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Utility {
	
	private Utility() {
		
	}
	
	public static String readFileAsString(String fileName) {
		String data = "";
		try {
			data = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static void writeStringToFile(String content, String loc, String fileName){
		writeStringToFile(content, loc, fileName,true);
	}
	
	public static void writeStringToFile(String content, String loc, String fileName, boolean unify){
		String unifier = "";
		if (unify) {
			unifier = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss-").format(new Date());
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(loc+"/"+unifier+fileName))){
			writer.write(content);   
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String,String> getFilesFromFolder(String folderLoc) {
		Map<String,String> result = new HashMap<>();
		File folder = new File(folderLoc);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	result.put(file.getName().split("_")[0], readFileAsString(file.getAbsolutePath())) ;
		    }
		}
		return result;
	}
	
	public static String convertToJsonString(Object object) {
		try {
			ObjectMapper om = new ObjectMapper();
			om.registerModule(new Jdk8Module());
			om.registerModule(new JavaTimeModule());
			ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
			return ow.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static JSONObject convertToJsonObject(Object object) {
		try {
			ObjectMapper om = new ObjectMapper();
			om.registerModule(new Jdk8Module());
			om.registerModule(new JavaTimeModule());
			ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
			JSONParser parser = new JSONParser();  
			return (JSONObject) parser.parse(ow.writeValueAsString(object)); 
		} catch (JsonProcessingException|ParseException e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}
	
	public static LocalDate toLocalDate(String inp, String fromFormat) {
		return LocalDate.parse(inp, DateTimeFormatter.ofPattern(fromFormat));
	}
	
	public static boolean isValidUrl(String url) {
	    try {
	        new URL(url);
	        return true;
	    } catch (MalformedURLException e) {
	        return false;
	    }
	}

	public static void populateMasterDataById(MasterData masterData) {
		masterData.getMasterEntity().forEach((k,v) -> masterData.getMasterAllById().put(v.getRef_id(), v));
	}
	
	public static List<Map<String, String>> readCSVToMap(String csvLocGeo) {
		List<Map<String, String>> result = new ArrayList<>();
	    try {
	        FileReader filereader = new FileReader(csvLocGeo);
	        // create csvReader object and skip first Line
	        CSVReader csvReader = new CSVReaderBuilder(filereader)
	                                  //.withSkipLines(1)
	                                  .build();
	        List<String[]> allData = csvReader.readAll();
	  
	        // print Data
	        boolean isHeader=true;
	        String[] header=null;
	        for (String[] row : allData) {
	        	if (isHeader) {
	        		header=row;
	        		isHeader=false;
	        		continue;
				}
	        	Map<String, String> rowMap = new LinkedHashMap<String, String>();
	        	int colCount = 0;
	            for (String cell : row) {
	            	rowMap.put(header[colCount++], cell);
	            }
	            result.add(rowMap);
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		return result;
	}
	
	public static List<Map<String, String>> readCSVStringContentToMap(String content) {
		List<Map<String, String>> result = new ArrayList<>();
	    try {
	    	StringReader contentReader = new StringReader(content);
	        // create csvReader object and skip first Line
	        CSVReader csvReader = new CSVReaderBuilder(contentReader)
	                                  //.withSkipLines(1)
	                                  .build();
	        List<String[]> allData = csvReader.readAll();
	  
	        // print Data
	        boolean isHeader=true;
	        String[] header=null;
	        for (String[] row : allData) {
	        	if (isHeader) {
	        		header=row;
	        		isHeader=false;
	        		continue;
				}
	        	Map<String, String> rowMap = new LinkedHashMap<String, String>();
	        	int colCount = 0;
	            for (String cell : row) {
	            	rowMap.put(header[colCount++], cell);
	            }
	            result.add(rowMap);
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		return result;
	}
	
}
