package it.unimib.ds.lab3.es1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CurrencyConverter
 */
@WebServlet("/es1/currencyconverter")
public class CurrencyConverter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Map<String, Double>> exchangeRates;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrencyConverter() {
        super();
        this.exchangeRates = new HashMap<>();
        Map<String, Double> eurMap = new HashMap<>();
        eurMap.put("usd", 0.81);
        eurMap.put("gbp", 1.20);        
        Map<String, Double> usdMap = new HashMap<>();
        usdMap.put("eur", 1.4);
        usdMap.put("gbp", 1.20);
        Map<String, Double> gbpMap = new HashMap<>();
        gbpMap.put("usd", 0.90);
        gbpMap.put("eur", 1.20);
        
        this.exchangeRates.put("eur", eurMap);
        this.exchangeRates.put("usd", usdMap);
        this.exchangeRates.put("gbp", gbpMap);
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>cURRENCY cONVERTER</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<form method=\"post\" action=\"./currencyconverter\">\r\n" + 
				"		<input type='text' name='amount' required>\n"+
				"		<select name='from' required>\n"+
				"			<option value='eur'>EUR</option>\n"+
				"			<option value='usd'>USD</option>\n"+
				"			<option value='gbp'>GBP</option>\n"+
				"		</select>\n"+
				"		<select name='to' required>\n"+
				"			<option value='eur'>EUR</option>\n"+
				"			<option value='usd'>USD</option>\n"+
				"			<option value='gbp'>GBP</option>\n"+
				"		</select>\n"+
				"	 	<button type=\"submit\">Convert</button>\r\n" + 
				"	</form>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double amount = Double.parseDouble(request.getParameter("amount"));
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		
		double exchangerate = this.exchangeRates.get(from).get(to);
		Map<String, Double> toMap = this.exchangeRates.get(from);
		
		double result = amount * exchangerate;
		
		if(toMap == null) {
			response.setStatus(422);
			response.getWriter().append("Error : currency not supported");
		}
		response.getWriter().append("Result : " + result);
	}

}
