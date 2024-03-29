package com.revature.wedding_planner.web.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.revature.wedding_planner.dao.AttendeeDAO;
import com.revature.wedding_planner.dao.DinnerTypeDAO;
import com.revature.wedding_planner.dao.PlusOneDAO;
import com.revature.wedding_planner.dao.RentedResourceDAO;
import com.revature.wedding_planner.dao.ResourceDAO;
import com.revature.wedding_planner.dao.ResourceTypeDAO;
import com.revature.wedding_planner.dao.UserDAO;
import com.revature.wedding_planner.dao.UserTypeDAO;
import com.revature.wedding_planner.dao.WeddingDAO;
import com.revature.wedding_planner.services.AttendeeService;
import com.revature.wedding_planner.services.DinnerTypeService;
import com.revature.wedding_planner.services.PlusOneService;
import com.revature.wedding_planner.services.RentedResourceService;
import com.revature.wedding_planner.services.ResourceService;
import com.revature.wedding_planner.services.ResourceTypeService;
import com.revature.wedding_planner.services.UserService;
import com.revature.wedding_planner.services.UserTypeService;
import com.revature.wedding_planner.services.WeddingService;
import com.revature.wedding_planner.web.servlets.AttendeeServlet;
import com.revature.wedding_planner.web.servlets.AuthServlet;
import com.revature.wedding_planner.web.servlets.DinnerTypeServlet;
import com.revature.wedding_planner.web.servlets.PlusOneServlet;
import com.revature.wedding_planner.web.servlets.RentedResourceServlet;
import com.revature.wedding_planner.web.servlets.ResourceServlet;
import com.revature.wedding_planner.web.servlets.ResourceTypeServlet;
import com.revature.wedding_planner.web.servlets.TestServlet;
import com.revature.wedding_planner.web.servlets.UserServlet;
import com.revature.wedding_planner.web.servlets.UserTypeServlet;
import com.revature.wedding_planner.web.servlets.WeddingServlet;

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
		
		UserDAO userDAO = new UserDAO();
		UserService userService = new UserService(userDAO);
		UserServlet userServlet = new UserServlet(userService, userTypeService, mapper);
		
		WeddingDAO weddingDAO = new WeddingDAO();
		WeddingService weddingService = new WeddingService(weddingDAO);
		WeddingServlet weddingServlet = new WeddingServlet(weddingService, mapper);
		
		ResourceTypeDAO resourceTypeDAO = new ResourceTypeDAO();
		ResourceTypeService resourceTypeService = new ResourceTypeService(resourceTypeDAO);
		ResourceTypeServlet resourceTypeServlet = new ResourceTypeServlet(resourceTypeService, mapper);
        
		ResourceDAO resourceDAO = new ResourceDAO();
		ResourceService resourceService = new ResourceService(resourceDAO);
		ResourceServlet resourceServlet = new ResourceServlet(resourceService, resourceTypeService, mapper);
		
		RentedResourceDAO rentedResourceDAO = new RentedResourceDAO();
		RentedResourceService rentedResourceService = new RentedResourceService(rentedResourceDAO);
		RentedResourceServlet rentedResourceServlet = new RentedResourceServlet(rentedResourceService, mapper);
		
        AttendeeDAO attendeeDAO = new AttendeeDAO();
        AttendeeService attendeeService = new AttendeeService(attendeeDAO);
        AttendeeServlet attendeeServlet = new AttendeeServlet(attendeeService, mapper);
        
        PlusOneDAO plusOneDAO = new PlusOneDAO();
        PlusOneService plusOneService = new PlusOneService(plusOneDAO);
        PlusOneServlet plusOneServlet = new PlusOneServlet(plusOneService, mapper);	
		
        TestServlet testServlet = new TestServlet();
        //TODO Looks like this needs to be uploaded. My ide ain't finding it
        AuthServlet authServlet = new AuthServlet(userService, mapper);
 
        
		ServletContext context = sce.getServletContext();
		context.addServlet("DinnerTypeServlet", dinnerTypeServlet).addMapping("/dinnerType/*");
		context.addServlet("UserTypeServlet", userTypeServlet).addMapping("/userType/*");
		context.addServlet("UserServlet", userServlet).addMapping("/user/*");
		context.addServlet("WeddingServlet", weddingServlet).addMapping("/wedding/*");
		context.addServlet("ResourceTypeServlet", resourceTypeServlet).addMapping("/resourceType/*");
		context.addServlet("ResourceServlet", resourceServlet).addMapping("/resource/*");
		context.addServlet("RentedResourceServlet", rentedResourceServlet).addMapping("/rentedResource/*");
		context.addServlet("AuthServlet" , authServlet).addMapping("/auth");
		context.addServlet("Attendee", attendeeServlet).addMapping("/attendee/*");
		context.addServlet("PlusOne", plusOneServlet).addMapping("/plusOne/*");
		context.addServlet("TestServlet", testServlet).addMapping("/test");
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
	}
}
