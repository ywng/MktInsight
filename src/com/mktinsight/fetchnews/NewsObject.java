package com.mktinsight.fetchnews;


import java.util.HashMap;

public class NewsObject {
	private  HashMap<String, HashMap<String, ArticleObject>> newsCollection=new HashMap<String, HashMap<String, ArticleObject>>();
	
	public HashMap<String, HashMap<String, ArticleObject>> getNewsCollection(){
		return newsCollection;
	}
	
	public  void putDailyNewsSet(String date,HashMap<String, ArticleObject> collection){
		newsCollection.put(date, collection);
	}

	public  HashMap<String, ArticleObject>getDailyNewsSet(String date){
		return newsCollection.get(date);
	}
}
