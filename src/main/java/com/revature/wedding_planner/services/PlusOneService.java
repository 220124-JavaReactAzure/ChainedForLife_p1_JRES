package com.revature.wedding_planner.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.wedding_planner.dao.PlusOneDAO;
import com.revature.wedding_planner.models.PlusOne;

public class PlusOneService {
	private final PlusOneDAO plusOneDAO;
	private final Logger logger = LogManager.getRootLogger();

	public PlusOneService(PlusOneDAO plusOneDAO) {
		logger.info("PlusOneService Initialized");
		this.plusOneDAO = plusOneDAO;
	}

	public boolean addPlusOne(PlusOne plusOne) {

		logger.info(" Adding PlusOne");

		if(plusOne.getId() <= 0) return false;
		if(plusOne.getAttendee() == null) return false;
		if(plusOne.getWedding() == null) return false;
		if(plusOne.getDinnerType() == null) return false;

		if(plusOne.getAttendee() == null) return false;
		if(plusOne.getWedding() == null) return false;
		if(plusOne.getDinnerType() == null) return false;
		logger.info(" Adding PlusOne");

		return plusOneDAO.addPlusOne(plusOne);
	}

	public List<PlusOne> getAllPlusOnes() {
		logger.info(" Getting All PlusOne");
		return plusOneDAO.getAllPlusOnes();
	}

	public PlusOne getPlusOneByID(int id) {
		logger.info(" Getting PlusOne By ID");
		return plusOneDAO.getPlusOneByID(id);
	}

	public void updatePlusOneWithSessionMethod(PlusOne plusOne) {
		logger.info("Updating plusOne");
		plusOneDAO.updatePlusOneWithSessionMethod(plusOne);
	}

	// TODO implement this in DAO
	public void deletePlusOne(PlusOne plusOne) {
		logger.info("Deleting PlusOne");
		plusOneDAO.deletePlusOne(plusOne);
	}
}
