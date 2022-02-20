package com.revature.wedding_planner.web.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.revature.wedding_planner.dao.DinnerTypeDAO;
import com.revature.wedding_planner.services.DinnerTypeService;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate5Module());
		
		DinnerTypeDAO dinnerTypeDAO = new DinnerTypeDAO();
		DinnerTypeService dinnerTypeService = new DinnerTypeService(dinnerTypeDAO);
		
		ServletContext context = sce.getServletContext();
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
	}
}
