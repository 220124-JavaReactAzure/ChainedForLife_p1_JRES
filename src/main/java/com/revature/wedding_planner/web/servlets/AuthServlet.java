package com.revature.wedding_planner.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.revature.wedding_planner.dao.UserDAO;
import com.revature.wedding_planner.exceptions.AuthenticationException;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.services.UserService;
import com.revature.wedding_planner.web.dto.LoginCredentials;

public class AuthServlet extends HttpServlet{
	
	private final UserService userService; //usertype
	private final ObjectMapper mapper;
	
	public AuthServlet(UserService userService, ObjectMapper mapper){
		this.userService = userService;
		this.mapper = mapper;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.getWriter().write("<h1>ChainedForLife</h1>\r\n"
				+ "\r\n"
				+ "	<h2> Wedding Planner Application Landing Page </h2>\r\n"
				+ "\r\n"
				+ "	<form action=\"auth\" method=\"post\">\r\n"
				+ "		Username:<input type=\"text\" name=\"username\" /><br /><br />\r\n"
				+ "		Password:<input type=\"password\" name=\"password\" /><br /><br />\r\n"
				+ "		<input type=\"submit\" value=\"login\" />\r\n"
				+ "	</form>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		try {

			LoginCredentials loginCredentials = mapper.readValue(req.getInputStream(), LoginCredentials.class);
			
			User user = mapper.readValue(req.getInputStream(), User.class);
			
			
			User authenticatedUser = userService.authenticateUser(user.getEmail());
		   
			HttpSession httpSession = req.getSession(true);
			httpSession.setAttribute("auth", authenticatedUser);

			resp.getWriter().write("<h1> Success </h1>)");

		} catch (InvalidRequestException e) {
			resp.setStatus(400);

		} catch (AuthenticationException e) {
			resp.setStatus(401);
		} catch (Exception e) {
			e.printStackTrace();
			// resp.getWriter().write("<h1> Sorry Failed to Login Server Error 500 </h1>");

			User authenticatedUser = userService.getUserByUserEmail("ro@gmail.com");
			resp.getWriter().write(authenticatedUser.getType().getId());
			resp.setStatus(500);
		}
		

		
//		try {
//			
//			//LoginCredentials loginCredentials = mapper.readValue(req.getInputStream(), LoginCredentials.class);
//			PrintWriter out = resp.getWriter();
//			//User user = new User(); 
//			String useremail = req.getParameter("email");
//			String userpassword = req.getParameter("password");
//			//User authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());
//			
//			User authenticatedUser = userService.getUserByUserEmail(useremail);
//		
//			resp.setContentType("text/html");
//			
//			if(authenticatedUser.getType().getId() == 1) {
//				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/test-app/attendee/\">");
//				
//			} else if(authenticatedUser.getType().getId() == 2) {
//				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/test-app/wedding/\">");
//				
//			}
//			
//			
//			
//			User authenticatedUser = userService.authenticateUser(loginCredentials.getUseremail(), loginCredentials.getPassword());
//			HttpSession httpSession = req.getSession(true);
//			httpSession.setAttribute("auth", authenticatedUser);
//			
//			resp.getWriter().write("<h1> Success </h1>)");
//			
//			
//		} catch (InvalidRequestException e) {
//			resp.setStatus(400);
//		
//		} catch (AuthenticationException e){
//			resp.setStatus(401);
//			resp.getWriter().write("<h1> The information you entered does not match our records!! </h1>");
//		} catch (Exception e) {
//			e.printStackTrace();
//			//resp.getWriter().write("<h1> Sorry Failed to Login Server Error 500 </h1>");
//			
//			User authenticatedUser = userService.getUserByUserEmail("password");
//			resp.getWriter().write(authenticatedUser.getType().getId());
//			resp.setStatus(500);
//		}
//		
//		
//		//try {
//			
////		resp.setContentType("text/html");
////		
////		PrintWriter writer = resp.getWriter();
////		
////		String usern = req.getParameter("email");
////		String userp = req.getParameter("password");
////		
////		
////		if(userService.authenticateUser(usern, userp)) {
////			RequestDispatcher rd = req.getRequestDispatcher("user");
////			rd.forward(req, resp);
////			
////			
////		} else {
////			
////			writer.print("Sorry username or password incorrect go head and try again..");
////			RequestDispatcher rd = req.getRequestDispatcher("index.html");
////			rd.include(req, resp);
////		
////		}
//		//User authenticatedUser = userService.authenticateUser(loginCredentials.getUsername(), loginCredentials.getPassword());
//		
//		//} finally {
			
		
	}
	
}


