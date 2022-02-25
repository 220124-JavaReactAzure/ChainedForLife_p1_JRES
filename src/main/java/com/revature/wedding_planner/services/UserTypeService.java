package com.revature.wedding_planner.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.wedding_planner.dao.UserTypeDAO;
import com.revature.wedding_planner.models.UserType;

public class UserTypeService {
	
	private final UserTypeDAO userTypeDAO;
	private final Logger logger = LogManager.getRootLogger();
	
	public UserTypeService(UserTypeDAO userTypeDAO) {
		logger.info("userTypeService Initialized");
		this.userTypeDAO = userTypeDAO;
	}
	
	public boolean addUserType(UserType userType) {
<<<<<<< HEAD
		logger.info("userType Added");
=======
		if(userType.getId() <= 0) return false;
		else if(userType.getName().isEmpty()) return false;
>>>>>>> 04ad1e03cfc770a24d6e8f95206bff8e2620e273
		return userTypeDAO.addUserType(userType);
	}
	
	public List<UserType> getAllUserTypes() {
		logger.info("GEtting ALl userType");
		return userTypeDAO.getAllUserTypes();
	}
	
	public UserType getUserTypeByID(int id) {	
		logger.info("Getting userType by ID");
		return userTypeDAO.getUserTypeByID(id);
	}
	
	public void updateUserTypeWithSessionMethod(UserType userType) {
		logger.info("Updating userType");
		userTypeDAO.updateUserTypeWithSessionMethod(userType);
	}
	
	public UserType getUserTypeByEmail(String email) {
		logger.info("Getting usertype by email");
		return userTypeDAO.getUserTypeByEmail(email);
	}
	//TODO implement in DAO
	public void deleteUserType(int id) {
		logger.info("Deleting UserType by ID");
		userTypeDAO.deleteUserType(id);
	}

}
