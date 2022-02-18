package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.dao.ResourceDAO;
import com.revature.wedding_planner.models.Resource;

public class ResourceService {

	private final ResourceDAO resourceDAO;
	
	public ResourceService(ResourceDAO resourceDAO) {
		this.resourceDAO = resourceDAO;
	}
	
	public boolean addResource(Resource resource) {
		return resourceDAO.addResource(resource);
	}
	
	public List<Resource> getAllResources() {
		return resourceDAO.getAllResources();
	}
	
	public Resource getResourceByID(int id) {
		return resourceDAO.getResourceByID(id);
	}
	
	// TODO implement this in DAO
	public void deleteResource(int id) {
		resourceDAO.deleteResource(id);
	}
}
