package com.revature.wedding_planner.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.wedding_planner.dao.UserDAO;
import com.revature.wedding_planner.exceptions.AuthenticationException;
import com.revature.wedding_planner.models.User;

public class UserService {

	private final UserDAO userDAO;
	private final Logger logger = LogManager.getRootLogger();
	
	public UserService(UserDAO userDAO) {
		logger.info("userService initialized");
		this.userDAO = userDAO;
	}
	
	public boolean addUser(User user) {
		logger.info("Addinguser");
		return userDAO.addUser(user);
	}
	
	public List<User> getAllUsers() {
		logger.info("GEting All user");
		return userDAO.getAllUsers();
	}
	
	public User getUserByID(int id) {
		logger.info("Get user by ID");
		return userDAO.getUserByID(id);
	}
	
	public User getUserByUserEmail(String email) {
		return userDAO.getUserByEmail(email);
	}
	
	public void updateUserWithSessionMethod(User user) {
		logger.info("Updating user");
		userDAO.updateUserWithSessionMethod(user);
	}
	
	// TODO implement this in DAO
	public void deleteUser(int id) {
		logger.info("Deleting user");
		userDAO.deleteUser(id);
	}

	//TODO uncomment this when uploaded throw classes
	public User authenticateUser(String email, String password) {
		// TODO Auto-generated method stub
		logger.info("Authenticating user");
		
		if(email == null || email.trim().equals("") || password == null || password.trim().equals("")) {
			
			
			//throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
		}
		
		User authenticatedUser = userDAO.findByUsernameAndPassword(email, password);
		
		if(authenticatedUser == null) {
			logger.info("Unauthenticated user exception");
			throw new AuthenticationException("Unauthenticated user, information provided was not found in our database.");
		}
		return authenticatedUser;
	}
	
	
}
