package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.dao.RentedResourceDAO;
import com.revature.wedding_planner.models.RentedResource;

public class RentedResourceService {
	private final RentedResourceDAO rentedResourceDAO;

	public RentedResourceService(RentedResourceDAO rentedResourceDAO) {
		this.rentedResourceDAO = rentedResourceDAO;
	}

	public boolean addRentedResource(RentedResource rentedResource) {
		return rentedResourceDAO.addRentedResource(rentedResource);
	}

	public List<RentedResource> getAllRentedResources() {
		return rentedResourceDAO.getAllRentedResources();
	}

	public RentedResource getRentedResourceByID(int id) {
		return rentedResourceDAO.getRentedResourceByID(id);
	}

	public void updateRentedResourceWithSessionMethod(RentedResource rentedResource) {
		rentedResourceDAO.updateRentedResourceWithSessionMethod(rentedResource);
	}

	// TODO implement this in DAO
	public void deleteRentedResource(int id) {
		rentedResourceDAO.deleteRentedResource(id);
	}
}
