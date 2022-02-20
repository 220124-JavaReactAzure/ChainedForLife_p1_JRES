package com.revature.wedding_planner.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AttendeeServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
		System.out.println("Init has been called for TestServlet");
	}
	
	@Override
	public void destroy() {
		System.out.println("TestServlet Destroyer");
	}
}
