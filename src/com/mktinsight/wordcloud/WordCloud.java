package com.mktinsight.wordcloud;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WordCloud
 */
@WebServlet("/WordCloud")
public class WordCloud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordCloud() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Set the response MIME type of the response message
	      response.setContentType("text/html");
	      // Allocate a output writer to write the response message into the network socket
	      PrintWriter out = response.getWriter();
	 
	      // Write the response message, in an HTML page
	      try {
	         out.println("<html>");
	         out.println("<head><title>Hello, World</title></head>");
	         out.println("<body>");
	         out.println("<h1>Hello, world!</h1>");  // says Hello
	         // Echo client's request information
	         out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
	         out.println("<p>Protocol: " + request.getProtocol() + "</p>");
	         out.println("<p>PathInfo: " + request.getPathInfo() + "</p>");
	         out.println("<p>Remote Address: " + request.getRemoteAddr() + "</p>");
	         // Generate a random number upon each request
	         out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
	         out.println("</body></html>");
	      } finally {
	         out.close();  // Always close the output writer
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
