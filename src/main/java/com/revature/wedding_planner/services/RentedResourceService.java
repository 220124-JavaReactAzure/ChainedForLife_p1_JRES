package com.revature.wedding_planner.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.wedding_planner.dao.RentedResourceDAO;
import com.revature.wedding_planner.models.RentedResource;

public class RentedResourceService {
	private final RentedResourceDAO rentedResourceDAO;
	private final Logger logger = LogManager.getRootLogger();

	public RentedResourceService(RentedResourceDAO rentedResourceDAO) {
		logger.info("RentedResourceService initialized");
		this.rentedResourceDAO = rentedResourceDAO;
	}

	public boolean addRentedResource(RentedResource rentedResource) {
		if(rentedResource.getResource() == null) return false;
		if(rentedResource.getWedding() == null) return false;
		logger.info("Added Rented Resource");
		return rentedResourceDAO.addRentedResource(rentedResource);
	}

	public List<RentedResource> getAllRentedResources() {
		logger.info("Getting all Rented Resources");
		return rentedResourceDAO.getAllRentedResources();
	}

	public RentedResource getRentedResourceByID(int id) {
		logger.info("Getting Rented Resource By ID");
		return rentedResourceDAO.getRentedResourceByID(id);
	}

	public void updateRentedResourceWithSessionMethod(RentedResource rentedResource) {
		logger.info("Updating Rented Resource");
		
		rentedResourceDAO.updateRentedResourceWithSessionMethod(rentedResource);
	}

	// TODO implement this in DAO
	public void deleteRentedResource(int id) {
		
		logger.info("Deleting RentedResources");
		rentedResourceDAO.deleteRentedResource(id);
	}
}
