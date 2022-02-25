package com.revature.wedding_planner.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.wedding_planner.dao.DinnerTypeDAO;
import com.revature.wedding_planner.models.DinnerType;

public class DinnerTypeService {

	private final DinnerTypeDAO dinnerTypeDAO;
	private final Logger logger = LogManager.getRootLogger();
	
	public DinnerTypeService(DinnerTypeDAO dinnerTypeDAO) {
		logger.debug("DinnerTypeService initiated");
		this.dinnerTypeDAO = dinnerTypeDAO;
	}
	
	public boolean addDinnerType(DinnerType dinnerType) {
		if(dinnerType.getName().isEmpty()) return false;
		logger.debug("DinnerType Added initiated");
		
		return dinnerTypeDAO.addDinnerType(dinnerType);
	}
	
	public List<DinnerType> getAllDinnerTypes() {
		logger.debug("DinnerType Getting All DinnerType initiated");
		return dinnerTypeDAO.getAllDinnerTypes();
	}
	
	public DinnerType getDinnerTypeByID(int id) {
		logger.debug("Initializing DinnerType By ID");
		return dinnerTypeDAO.getDinnerTypeByID(id);
	}
	
	public void updateDinnerTypeWithSessionMethod(DinnerType dinnerType) {
		logger.debug("DinnerType with Session initiated");
		dinnerTypeDAO.updateDinnerTypeWithSessionMethod(dinnerType);
	}
	
	// TODO implement this in DAO
	public void deleteDinnerType(int id) {
		logger.debug("DinnerType deletion initiated");
		dinnerTypeDAO.deleteDinnerType(id);
	}
}
