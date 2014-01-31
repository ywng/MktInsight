MktInsight
==========

An API portal servlet for news 

Making use of JSOUP library to scrap the Bloomberg News, and reorganise the collected news data into JSON format, which is returned using servlet get api call.

Currently, there are two workable API:
1) Fetching daily asia news from Bloomberg (http://www.bloomberg.com/news/asia/)
http://mktinsight.ywng.cloudbees.net/FetchNews?category=finance
2) Fetching Market Indexes from Bloomberg (http://www.bloomberg.com/markets/)
http://mktinsight.ywng.cloudbees.net/FetchNews?category=finance&subcategory=indexes
