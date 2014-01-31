package com.mktinsight.fetchnews;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FetchIndex {
	
	private static class indexObj{
		String index;
		float value;
		float percentChange;
		float open;
		float high;
		float low;
		String time;
		
		public JSONObject printJsonFormat() throws JSONException{
			JSONObject obj = new JSONObject();
            obj.put("index",index);
            obj.put("value",value);
            obj.put("percentage change",percentChange);
            obj.put("open",open);
            obj.put("high", high);
            obj.put("low", low);
            obj.put("time", time);
            
            return obj;
		}
	}
	
	private static ArrayList<indexObj> americanIndexes=new ArrayList<indexObj>();
	private static ArrayList<indexObj> eur_midEast_africa_Indexes=new ArrayList<indexObj>();
	private static ArrayList<indexObj> asiaPacificIndexes=new ArrayList<indexObj>();
	
	
	
	public static void getIndex(PrintWriter out) throws IOException, JSONException{
		
		 Document doc = Jsoup.connect("http://www.bloomberg.com/markets/").get();

		 Elements text = doc.select("#indexes_data_table");
	     for (Element e :text) {
	    	String tableTxt=e.text();
	    	
	    	int beginPos=tableTxt.indexOf("Dow Jones Industrial Average");
	    	int endPos=tableTxt.indexOf(" Europe, Middle East");
	    	String americanIndexTxt=tableTxt.substring(beginPos, endPos);
	    	
	    	beginPos=tableTxt.indexOf("EURO STOXX 50 Price EUR");
	    	endPos=tableTxt.indexOf(" Asia-Pacific Indexes");
	    	String eur_midEast_africa_indexTxt=tableTxt.substring(beginPos, endPos);
	    	
	    	beginPos=tableTxt.indexOf("Nikkei");
	    	String asiaPacificIndexTxt=tableTxt.substring(beginPos);
	    	
	    	System.out.println(americanIndexTxt);
	    	System.out.println(eur_midEast_africa_indexTxt);
	    	System.out.println(asiaPacificIndexTxt);
	    	
	    	indexObj newIndex;
	    	//make american indexes
	    	americanIndexes.clear();
	    	//Dow Jones
	    	beginPos=americanIndexTxt.indexOf("Dow Jones Industrial Average");
	    	endPos=americanIndexTxt.indexOf(" S&P 500 Index");
	    	newIndex=makeIndex("Dow Jones Industrial Average",americanIndexTxt.substring(beginPos,endPos));
	    	americanIndexes.add(newIndex);
	    	//out.println(newIndex.printJsonFormat().toString());
	    	
	    	beginPos=americanIndexTxt.indexOf("S&P 500 Index");
	    	endPos=americanIndexTxt.indexOf(" NASDAQ Composite Index");
	    	newIndex=makeIndex("S&P 500 Index",americanIndexTxt.substring(beginPos,endPos));
	    	americanIndexes.add(newIndex);
	    	//out.println(newIndex.printJsonFormat().toString());
	    	
	    	beginPos=americanIndexTxt.indexOf("NASDAQ Composite Index");
	    	newIndex=makeIndex("NASDAQ Composite Index",americanIndexTxt.substring(beginPos));
	    	americanIndexes.add(newIndex);
	    	//out.println(newIndex.printJsonFormat().toString());
	    	
	    	
	    	//make eur mid-east africa indexes
	    	eur_midEast_africa_Indexes.clear();
	    	//EURO STOXX 50 Price EUR
	    	beginPos=eur_midEast_africa_indexTxt.indexOf("EURO STOXX 50 Price EUR");
	    	endPos=eur_midEast_africa_indexTxt.indexOf(" FTSE 100 Index");
	    	newIndex=makeIndex("EURO STOXX 50 Price EUR",eur_midEast_africa_indexTxt.substring(beginPos,endPos));
	    	eur_midEast_africa_Indexes.add(newIndex);
	    	//out.println(newIndex.printJsonFormat().toString());
	    	
	    	beginPos=eur_midEast_africa_indexTxt.indexOf("FTSE 100 Index");
	    	endPos=eur_midEast_africa_indexTxt.indexOf(" Deutsche Borse AG German Stock Index DAX");
	    	newIndex=makeIndex("FTSE 100 Index",eur_midEast_africa_indexTxt.substring(beginPos,endPos));
	    	eur_midEast_africa_Indexes.add(newIndex);
	    	//out.println(newIndex.printJsonFormat().toString());
	    	
	    	beginPos=eur_midEast_africa_indexTxt.indexOf("Deutsche Borse AG German Stock Index DAX");
	    	newIndex=makeIndex("Deutsche Borse AG German Stock Index DAX",eur_midEast_africa_indexTxt.substring(beginPos));
	    	eur_midEast_africa_Indexes.add(newIndex);
	    	//out.println(newIndex.printJsonFormat().toString());
	    	
	    	//make asia pacific indexes
	    	asiaPacificIndexes.clear();
	    	beginPos=asiaPacificIndexTxt.indexOf("Nikkei 225");
	    	endPos=asiaPacificIndexTxt.indexOf(" Hong Kong Hang Seng Index");
	    	newIndex=makeIndex("Nikkei 225",asiaPacificIndexTxt.substring(beginPos,endPos));
	    	asiaPacificIndexes.add(newIndex);
	    	//out.println(newIndex.printJsonFormat().toString());
	    	
	    	beginPos=asiaPacificIndexTxt.indexOf("Hong Kong Hang Seng Index");
	    	endPos=asiaPacificIndexTxt.indexOf(" Shanghai Shenzhen CSI");
	    	newIndex=makeIndex("Hong Kong Hang Seng Index",asiaPacificIndexTxt.substring(beginPos,endPos));
	    	asiaPacificIndexes.add(newIndex);
	    	//out.println(newIndex.printJsonFormat().toString());
	    	
	    	beginPos=asiaPacificIndexTxt.indexOf("Shanghai Shenzhen CSI 300 Index");
	    	newIndex=makeIndex("Shanghai Shenzhen CSI 300 Index",asiaPacificIndexTxt.substring(beginPos));
	    	asiaPacificIndexes.add(newIndex);
	    	//out.println(newIndex.printJsonFormat().toString());
	    	
	    	
	    	
	    	//out all indexes in different markets
	    	out.println(outputJson().toString());
	     
	     
	     
	     
	     
	     }
	     
	     
			
		
		
		
		
	}

	private static indexObj makeIndex(String name, String rawNumericalData){
		indexObj temp=new FetchIndex.indexObj();
		temp.index=name;
		String splitNumData[]=rawNumericalData.substring(name.length()+1).split(" ");
		
		System.out.println(splitNumData[0]);
		temp.value=Float.parseFloat(splitNumData[0].replace(",",""));
		temp.percentChange=Float.parseFloat(splitNumData[1].substring(0,splitNumData[1].length()-1));
		temp.open=Float.parseFloat(splitNumData[2].replace(",",""));
		temp.high=Float.parseFloat(splitNumData[3].replace(",",""));
		temp.low=Float.parseFloat(splitNumData[4].replace(",",""));
		temp.time=splitNumData[5];
		
		return temp;
		
	}
	
	private static JSONObject outputJson() throws JSONException{
		JSONArray indexList = new JSONArray();
		JSONObject mktList= new JSONObject();
		
		for(int i=0;i<americanIndexes.size();i++){
			indexList.put(americanIndexes.get(i).printJsonFormat());
		}
		mktList.put("Americas Indexes",indexList);
		
		indexList = new JSONArray();
		for(int i=0;i<eur_midEast_africa_Indexes.size();i++){
			indexList.put(eur_midEast_africa_Indexes.get(i).printJsonFormat());
		}
		mktList.put("Europe, Middle East & Africa Indexes",indexList);
		
		indexList = new JSONArray();
		for(int i=0;i<asiaPacificIndexes.size();i++){
			indexList.put(asiaPacificIndexes.get(i).printJsonFormat());
		}
		mktList.put("Asia-Pacific Indexes",indexList);
		
		return mktList;
		
	}
}
