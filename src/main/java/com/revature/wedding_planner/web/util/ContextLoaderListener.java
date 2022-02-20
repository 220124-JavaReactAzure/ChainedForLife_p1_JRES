package com.revature.wedding_planner.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.wedding_planner.dao.AttendeeDAO;
import com.revature.wedding_planner.services.AttendeeService;
import com.revature.wedding_planner.web.servlets.AttendeeServlet;

public class ContextLoaderListener implements ServletContextListener{
		
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//logger.info("The ContextLoader has started");
		
		ObjectMapper mapper = new ObjectMapper();
		
		AttendeeDAO attendeeDAO = new AttendeeDAO();
		AttendeeService attendeeService = new AttendeeService(attendeeDAO);
		AttendeeServlet attendeeServlet = new AttendeeServlet(attendeeService, mapper);
		
		
	}
	
}
