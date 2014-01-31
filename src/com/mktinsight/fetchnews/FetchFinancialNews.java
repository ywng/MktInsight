package com.mktinsight.fetchnews;


import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;


public class FetchFinancialNews {
	public static final int collectionCapacity=10;//Max capacity for the financial collection
	private static NewsObject financialCollectionBin1=new NewsObject();
	private static NewsObject financialCollectionBin2=new NewsObject();
	private static NewsObject activeBin=financialCollectionBin1;
	
	public FetchFinancialNews(){
		activeBin=financialCollectionBin1;
	}
	public static NewsObject getFinancialNewsCollection(){
		return activeBin;
	}
	
	private static void removeOldNews(){
		if(activeBin.getNewsCollection().size()>10){
			
			if(activeBin==financialCollectionBin1){
				financialCollectionBin2.getNewsCollection().clear();
				activeBin=financialCollectionBin2;
				
			}else{
				financialCollectionBin1.getNewsCollection().clear();
				activeBin=financialCollectionBin1;
			}
		}
	}
	
	public static String addDailyNews() throws IOException, JSONException{
		ArrayList<String> newsLinks=new ArrayList<String>();
		
		FetchFinancialNews.removeOldNews();
		
		Document doc = Jsoup.connect("http://www.bloomberg.com/news/asia/").get();
		// get all links
		Elements asiaRegion = doc.select("#ASIA");
		for (Element region: asiaRegion) {
			Elements links =region.select("div.q_link_wrapper");
			for (Element link: links) {
				String linkStr="http://www.bloomberg.com/"+link.select("a").attr("href");
				System.out.println(linkStr);
				newsLinks.add(linkStr);
			}
			
		}
		
		Elements more_asiaRegion = doc.select("#more_ASIA");
		for (Element region: more_asiaRegion) {
			Elements links =region.select("a");
			for (Element link: links) {
				String linkStr="http://www.bloomberg.com/"+link.attr("href");
				System.out.println(linkStr);
				newsLinks.add(linkStr);
			}
			
		}
		
		HashMap<String, ArticleObject> dailyCollection=new  HashMap<String, ArticleObject> ();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String todayDate=sdf.format(date);
		JSONArray newsList = new JSONArray();//JSON
		for (int i=0;i<newsLinks.size();i++){
			doc = Jsoup.connect(newsLinks.get(i)).get();
			String title=doc.title();
			String txtBody="";
			Elements newsBody = doc.select("p");
			for (Element p: newsBody) {
				txtBody+=p.text()+" ";
			}
			
			txtBody=txtBody.substring(0,txtBody.indexOf(" To contact the"));
			System.out.println(title);
			System.out.println(txtBody);
			ArticleObject article=new ArticleObject(title,todayDate,"Finance",txtBody);
            dailyCollection.put(title, article);
            
            //json
            JSONObject obj = new JSONObject();
            obj.put("title",title);
            obj.put("date",todayDate);
            obj.put("category","Finance");
            obj.put("content",txtBody);
            obj.put("link", newsLinks.get(i));
            
            newsList.put(obj);
		}
		
		activeBin.putDailyNewsSet(todayDate,dailyCollection);
		
		
	    return newsList.toString();
		
	}
	
	public static HashMap<String,ArticleObject>getTodayNews(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		System.out.println(sdf.format(date));
		return activeBin.getDailyNewsSet(sdf.format(date));
	}
	public static HashMap<String,ArticleObject>getSpecificDateNews(String date){
		return activeBin.getDailyNewsSet(date);
	}
}
