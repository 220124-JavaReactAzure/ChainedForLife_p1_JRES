package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.dao.UserDAO;
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
	
	// TODO implement this in DAO
	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}
}
