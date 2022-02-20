package com.revature.wedding_planner.web.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.revature.wedding_planner.dao.AttendeeDAO;
import com.revature.wedding_planner.dao.DinnerTypeDAO;
import com.revature.wedding_planner.dao.UserTypeDAO;
import com.revature.wedding_planner.services.AttendeeService;
import com.revature.wedding_planner.services.DinnerTypeService;
import com.revature.wedding_planner.services.UserTypeService;
import com.revature.wedding_planner.web.servlets.AttendeeServlet;
import com.revature.wedding_planner.web.servlets.DinnerTypeServlet;
import com.revature.wedding_planner.web.servlets.UserTypeServlet;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate5Module());
		
		DinnerTypeDAO dinnerTypeDAO = new DinnerTypeDAO();
		DinnerTypeService dinnerTypeService = new DinnerTypeService(dinnerTypeDAO);
		DinnerTypeServlet dinnerTypeServlet = new DinnerTypeServlet(dinnerTypeService, mapper);
		
		UserTypeDAO userTypeDAO = new UserTypeDAO();
		UserTypeService userTypeService = new UserTypeService(userTypeDAO);
		UserTypeServlet userTypeServlet = new UserTypeServlet(userTypeService, mapper);
        
        AttendeeDAO attendeeDAO = new AttendeeDAO();
        AttendeeService attendeeService = new AttendeeService(attendeeDAO);
        AttendeeServlet attendeeServlet = new AttendeeServlet(attendeeService, mapper);	
		
		ServletContext context = sce.getServletContext();
		context.addServlet("DinnerTypeServlet", dinnerTypeServlet).addMapping("/dinnerType/*");
		context.addServlet("UserTypeServlet", userTypeServlet).addMapping("/userType/*");
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
	}
}
