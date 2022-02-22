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
		this.weddingDAO = weddingDAO;
	}

	public boolean addWedding(Wedding wedding) {
		return weddingDAO.addWedding(wedding);
	}

	public List<Wedding> getAllWeddings() {
		return weddingDAO.getAllWeddings();
	}

	public Wedding getWeddingByID(int id) {
		return weddingDAO.getWeddingByID(id);
	}

	public void updateWeddingWithSessionMethod(Wedding wedding) {
		weddingDAO.updateWeddingWithSessionMethod(wedding);
	}

	// TODO implement this in DAO
	public boolean deleteWedding(Wedding wedding) {
		return weddingDAO.deleteWedding(wedding);
	}
}
