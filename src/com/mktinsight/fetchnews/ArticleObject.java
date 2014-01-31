package com.mktinsight.fetchnews;

import java.util.Date;

public class ArticleObject {
	public String title;
	public String dateTime;
	public String category;
	public String content;
	
	public ArticleObject(String _title,String _dateTime,String _category,String _content){
		title=_title;
		dateTime=_dateTime;
		category=_category;
		content=_content;
		
	}
}
