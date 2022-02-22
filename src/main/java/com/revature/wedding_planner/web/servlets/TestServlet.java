package com.revature.wedding_planner.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		System.out.println("Init has been called for TestServlet");
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("<h1> Testing Servlet is working for Wedding Planner </h1>");
	}
	
	
	@Override
	public void destroy() {
		System.out.println("TestServlet Destroyer");
	}
}
