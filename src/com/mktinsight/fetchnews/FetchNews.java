package com.mktinsight.fetchnews;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.jsoup.*;

/**
 * Servlet implementation class FetchNews
 */
@WebServlet("/FetchNews")
public class FetchNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchNews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		String category=request.getParameter("category");
	
		PrintWriter out = response.getWriter();
	     
		if(category.equalsIgnoreCase("finance")){
			//fetch financial news
			
			if(request.getParameter("subcategory")!=null&&request.getParameter("subcategory").equalsIgnoreCase("indexes")){
				try {
					
					FetchIndex.getIndex(out);
					out.close();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				
			}

	        try {
				out.print(FetchFinancialNews.addDailyNews());
				out.close();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        out.flush();
		  
			
		}
		
		
		
		
		
       
	                
	          
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
