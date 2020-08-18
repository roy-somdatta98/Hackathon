package com.cts.Hackathon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class App 
{
    @SuppressWarnings("resource")
	public static void main( String[] args ) throws Exception
    {
    	BufferedReader br = new BufferedReader(new FileReader("D:/CPU.txt"));
		 String line;
		List<String> list=new ArrayList<String>();
		 while((line = br.readLine()) != null) {
		 	String[] columns = line.split(" ");		 	
		 	list.add(columns[14]);		 	
		 }
		 writeJsonSimpleDemo("abcd.json", list);
		 
    }
    
    
    @SuppressWarnings("unchecked")
	public static void writeJsonSimpleDemo(String filename,List<String> array) throws Exception {
    	
    	JSONObject result=new JSONObject();
    	 JSONObject sampletransaction=new JSONObject();
    	 JSONArray values = new JSONArray();
    	 int n=1; 
    	for(String str : array) {
    		if(str!="") {
    			JSONObject sampleObject = new JSONObject();    			   		
    			sampleObject.put( n+"s", str);
    			values.add(sampleObject);
    			n=n+1;
    		}
    	}    	
    	
    	List<Double> num=new ArrayList<Double>();
    	double sum=0;
    	for(String p : array) {
    		if(NumberUtils.isNumber(p)) {
    			num.add(Double.parseDouble(p));
    			sum=sum+Double.parseDouble(p);
    		}
    	}
    	Double max=Collections.max(num);
    	Double avg=sum/num.size();
    	
    	double roundOffMax = Math.round(max * 100.0) / 100.0;
    	double roundOffAvg = Math.round(avg * 100.0) / 100.0;
    	
    	sampletransaction.put("values", values);
    	sampletransaction.put("maxcpu", roundOffMax );
    	sampletransaction.put("avgcpu", roundOffAvg );
    	result.put("sampletransaction", sampletransaction);
    	System.out.println(result);
    	
	    Files.write(Paths.get(filename),result.toJSONString().getBytes());
	}
}
