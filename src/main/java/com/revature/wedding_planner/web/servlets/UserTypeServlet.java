package com.revature.wedding_planner.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/usertype")
public class UserTypeServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		System.out.println("Init has been called for UserTypeServlet");
	}
	
	@Override
	public void destroy() {
		System.out.println("UserTypeServlet Destroyer");
	}
}
