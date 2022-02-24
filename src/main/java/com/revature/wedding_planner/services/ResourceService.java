package com.revature.wedding_planner.services;

import java.sql.Date;
import java.util.List;

import com.revature.wedding_planner.dao.ResourceDAO;
import com.revature.wedding_planner.models.Resource;

public class ResourceService {

	private final ResourceDAO resourceDAO;
	
	public ResourceService(ResourceDAO resourceDAO) {
		this.resourceDAO = resourceDAO;
	}
	
	public boolean addResource(Resource resource) {
		if(resource.getDateAvailableStart() == null) return false;
		if(resource.getDateAvailableEnd() == null) return false;
		return resourceDAO.addResource(resource);
	}
	
	public List<Resource> getAllResources() {
		return resourceDAO.getAllResources();
	}
	
	public Resource getResourceByID(int id) {
		return resourceDAO.getResourceByID(id);
	}
	
	public void updateResourceWithSessionMethod(Resource resource) {
		resourceDAO.updateResourceWithSessionMethod(resource);
	}
	
	// TODO implement this in DAO
	public void deleteResource(int id) {
		resourceDAO.deleteResource(id);
	}
}
