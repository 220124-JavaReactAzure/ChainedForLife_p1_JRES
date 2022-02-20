package com.revature.wedding_planner.web.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.revature.wedding_planner.dao.DinnerTypeDAO;
import com.revature.wedding_planner.services.DinnerTypeService;
import com.revature.wedding_planner.web.servlets.DinnerTypeServlet;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate5Module());
		
		DinnerTypeDAO dinnerTypeDAO = new DinnerTypeDAO();
		DinnerTypeService dinnerTypeService = new DinnerTypeService(dinnerTypeDAO);
		DinnerTypeServlet dinnerTypeServlet = new DinnerTypeServlet(dinnerTypeService, mapper);
        
        AttendeeDAO attendeeDAO = new AttendeeDAO();
        AttendeeService attendeeService = new AttendeeService(attendeeDAO);
        AttendeeServlet attendeeServlet = new AttendeeServlet(attendeeService, mapper);	
		
		ServletContext context = sce.getServletContext();
		context.addServlet("DinnerTypeServlet", dinnerTypeServlet).addMapping("/dinnerType/*");
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
	}
}
