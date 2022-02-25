package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.dao.WeddingDAO;
import com.revature.wedding_planner.models.Wedding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WeddingService {
	private final WeddingDAO weddingDAO;
    private final Logger logger = LogManager.getRootLogger();

	public WeddingService(WeddingDAO weddingDAO) {
		logger.info("WeddingService initialized");
		this.weddingDAO = weddingDAO;
	}

	public boolean addWedding(Wedding wedding) {
<<<<<<< HEAD
<<<<<<< HEAD
		logger.info("Wedding added");
=======
		if(wedding.getId() <= 0) return false;
		if(wedding.getUserID() == null) return false;
>>>>>>> 04ad1e03cfc770a24d6e8f95206bff8e2620e273
=======
		if(wedding.getUserID() == null) return false;
		logger.info("Wedding added");
>>>>>>> c7ce8f4beed186c9325e5ae56956e4676ed74eee
		return weddingDAO.addWedding(wedding);
	}

	public List<Wedding> getAllWeddings() {
		logger.info("Getting All Wedding");
		return weddingDAO.getAllWeddings();
	}

	public Wedding getWeddingByID(int id) {
		logger.info("Getting Wedding by ID");
		return weddingDAO.getWeddingByID(id);
	}

	public void updateWeddingWithSessionMethod(Wedding wedding) {
		logger.info("Updating Wedding");
		weddingDAO.updateWeddingWithSessionMethod(wedding);
	}

	// TODO implement this in DAO
	public boolean deleteWedding(Wedding wedding) {
		logger.info("Deleting Wedding");
		return weddingDAO.deleteWedding(wedding);
	}
}
