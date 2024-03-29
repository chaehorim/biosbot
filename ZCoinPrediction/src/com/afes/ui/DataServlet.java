package com.afes.ui;

import java.io.IOException;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.afes.util.JsonWriter;
import com.afes.util.KeyValueData;

import engine.analysis.statistic.PriceWindow;
import engine.dto.MarketPriceDTO;

@WebServlet("/Data")
public class DataServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Logger logger = LogManager.getLogger(Dashboard.class.getName());
	
	public DataServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("datatype");
		Object data = null;
		
		Queue<MarketPriceDTO> priceQueue = PriceWindow.getPriceStatistic();
		logger.error("Shit~~" + priceQueue.size() + "   XX  " + type);
		
		
		switch(type) {
		case "user":
			break;
		case "chart":
			break;
		case "grid":
			break;
		case "keyvalue":
			data = KeyValueData.getData();
			break;
		}
		JsonWriter.writeJson(response, data);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
