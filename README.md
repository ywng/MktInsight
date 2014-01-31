MktInsight
==========

An API portal servlet for news 

Making use of JSOUP library to scrape the Bloomberg News, and reorganise the collected news data into JSON format, which is returned using servlet get api call.

Currently, there are two workable API:<br />
1) [Fetching daily asia news from Bloomberg] (http://www.bloomberg.com/news/asia/)<br />
http://mktinsight.ywng.cloudbees.net/FetchNews?category=finance<br />
2) [Fetching Market Indexes from Bloomberg] (http://www.bloomberg.com/markets/)<br />
http://mktinsight.ywng.cloudbees.net/FetchNews?category=finance&subcategory=indexes
