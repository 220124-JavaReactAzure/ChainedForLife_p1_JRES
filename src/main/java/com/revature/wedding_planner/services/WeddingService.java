package com.revature.wedding_planner.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.wedding_planner.dao.WeddingDAO;
import com.revature.wedding_planner.models.Attendee;
import com.revature.wedding_planner.models.Wedding;

public class WeddingService {

	
	private final WeddingDAO weddingDAO;
	private final Logger logger = LogManager.getRootLogger();
	
	public WeddingService(WeddingDAO weddingDAO) {
		this.weddingDAO = weddingDAO;
	}
	
	public Wedding addWedding(Wedding wedding) {
		//lwog
		return weddingDAO.addWedding(wedding);
	}
	
	public List<Wedding> getAllWeddings() {
		//log not set jet
		return weddingDAO.getAllWeddings();
	}
	
	public Wedding getWeddingByID(int id) {
		return weddingDAO.getWeddingById(id);
	}
	
	// TODO implement this in DAO
	public boolean deleteWedding(Wedding id) {
		
		return weddingDAO.deletesWedding(id);
	}
	
}
