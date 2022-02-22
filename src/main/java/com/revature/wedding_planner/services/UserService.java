package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.dao.UserDAO;
import com.revature.wedding_planner.exceptions.AuthenticationException;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.models.User;

public class UserService {

	private final UserDAO userDAO;
	
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public boolean addUser(User user) {
		return userDAO.addUser(user);
	}
	
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	public User getUserByID(int id) {
		return userDAO.getUserByID(id);
	}
	
	public void updateUserWithSessionMethod(User user) {
		userDAO.updateUserWithSessionMethod(user);
	}
	
	// TODO implement this in DAO
	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}

	public User authenticateUser(String username, String password) {
		// TODO Auto-generated method stub
		
		if(username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
			throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
		}
		
		User authenticatedUser = userDAO.findByUsernameAndPassword(username, password);
		
		if(authenticatedUser == null) {
			throw new AuthenticationException("Unauthenticated user, information provided was not found in our database.");
		}
		return authenticatedUser;
	}
}
