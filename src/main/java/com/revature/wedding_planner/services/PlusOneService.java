package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.dao.PlusOneDAO;
import com.revature.wedding_planner.models.PlusOne;

public class PlusOneService {
	private final PlusOneDAO plusOneDAO;

	public PlusOneService(PlusOneDAO plusOneDAO) {
		this.plusOneDAO = plusOneDAO;
	}

	public boolean addPlusOne(PlusOne plusOne) {
		return plusOneDAO.addPlusOne(plusOne);
	}

	public List<PlusOne> getAllPlusOnes() {
		return plusOneDAO.getAllPlusOnes();
	}

	public PlusOne getPlusOneByID(int id) {
		return plusOneDAO.getPlusOneByID(id);
	}

	public void updatePlusOneWithSessionMethod(PlusOne plusOne) {
		plusOneDAO.updatePlusOneWithSessionMethod(plusOne);
	}

	// TODO implement this in DAO
	public void deletePlusOne(PlusOne plusOne) {
		plusOneDAO.deletePlusOne(plusOne);
	}
}
