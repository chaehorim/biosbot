package com.afes.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.afes.common.dto.OfferInfo;
import com.afes.util.JsonWriter;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = LogManager.getLogger(Dashboard.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("datatype");
		Object data = null;
		switch(type) {
		case "offerData":
			break;
		case "orderData":
			List<OfferInfo> offerList = new ArrayList<OfferInfo>();
			OfferInfo offer = new OfferInfo(System.currentTimeMillis(), "huobi", "okex", "xrp","usdt", 0.5, 0.6, 100, 10.0, 10 );
			offerList.add(offer);
			data = offerList;
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
