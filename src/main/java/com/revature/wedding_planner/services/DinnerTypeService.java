package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.dao.DinnerTypeDAO;
import com.revature.wedding_planner.models.DinnerType;

public class DinnerTypeService {

	private final DinnerTypeDAO dinnerTypeDAO;
	
	public DinnerTypeService(DinnerTypeDAO dinnerTypeDAO) {
		this.dinnerTypeDAO = dinnerTypeDAO;
	}
	
	public boolean addDinnerType(DinnerType dinnerType) {
		if(dinnerType.getName().isEmpty()) return false;
		
		return dinnerTypeDAO.addDinnerType(dinnerType);
	}
	
	public List<DinnerType> getAllDinnerTypes() {
		return dinnerTypeDAO.getAllDinnerTypes();
	}
	
	public DinnerType getDinnerTypeByID(int id) {
		return dinnerTypeDAO.getDinnerTypeByID(id);
	}
	
	public void updateDinnerTypeWithSessionMethod(DinnerType dinnerType) {
		dinnerTypeDAO.updateDinnerTypeWithSessionMethod(dinnerType);
	}
	
	// TODO implement this in DAO
	public void deleteDinnerType(int id) {
		dinnerTypeDAO.deleteDinnerType(id);
	}
}
