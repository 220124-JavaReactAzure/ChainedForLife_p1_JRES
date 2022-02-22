package com.revature.wedding_planner.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			LoginCredentials loginCredentials = mapper.readValue(req.getInputStream(), LoginCredentials.class);
			User authenticatedUser = userService.authenticateUser(loginCredentials.getUsername(), loginCredentials.getPassword());
		
			HttpSession httpSession = req.getSession(false); //direct paths  if not loggin return null
			httpSession.setAttribute("authUser", authenticatedUser);
		} catch (InvalidRequestException | UnrecognizedPropertyException e ) {
			resp.setStatus(400);
		} catch (AuthenticationException e) {
			resp.setStatus(401);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(500);
		}
		
		
		
	}
	
	

}
