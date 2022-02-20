package com.revature.wedding_planner.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class WeddingServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		System.out.println("Init has been called for WeddingServlet");
	}
	
	@Override
	public void destroy() {
		System.out.println("WeddingServlet Destroyer");
	}
}
