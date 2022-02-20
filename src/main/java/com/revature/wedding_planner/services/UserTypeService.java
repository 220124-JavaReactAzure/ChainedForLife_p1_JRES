package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.dao.UserTypeDAO;
import com.revature.wedding_planner.models.UserType;

public class UserTypeService {
	
	private final UserTypeDAO userTypeDAO;
	
	public UserTypeService(UserTypeDAO userTypeDAO) {
		this.userTypeDAO = userTypeDAO;
	}
	
	public boolean addUserType(UserType userType) {
		return userTypeDAO.addUserType(userType);
	}
	
	public List<UserType> getAllUserTypes() {
		return userTypeDAO.getAllUserTypes();
	}
	
	public UserType getUserTypeByID(int id) {	
		return userTypeDAO.getUserTypeByID(id);
	}
	
	public void updateUserTypeWithSessionMethod(UserType userType) {
		userTypeDAO.updateUserTypeWithSessionMethod(userType);
	}
	
	//TODO implement in DAO
	public void deleteUserType(int id) {
		userTypeDAO.deleteUserType(id);
	}

}
