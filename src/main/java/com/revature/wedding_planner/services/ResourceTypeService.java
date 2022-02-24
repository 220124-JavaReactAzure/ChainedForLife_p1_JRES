package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.dao.ResourceTypeDAO;
import com.revature.wedding_planner.models.ResourceType;

public class ResourceTypeService {
	
	private final ResourceTypeDAO resourceTypeDAO;
	
	public ResourceTypeService(ResourceTypeDAO resourceTypeDAO) {
		this.resourceTypeDAO = resourceTypeDAO;
	}
	
	public boolean addResourceType(ResourceType resourceType) {
		if(resourceType.getId() <= 0) return false;
		else if(resourceType.getName().isEmpty()) return false;
		return resourceTypeDAO.addResourceType(resourceType);
	}
	
	public List<ResourceType> getAllResourceTypes() {
		return resourceTypeDAO.getAllResourceTypes();
	}
	
	public ResourceType getResourceTypeByID(int id) {
		return resourceTypeDAO.getResourceTypeByID(id);
	}
	
	public void updateResourceTypeWithSessionMethod(ResourceType resourceType) {
		resourceTypeDAO.updateResourceTypeWithSessionMethod(resourceType);
	}
	
	// TODO implement this in DAO
	public void deleteResourceType(int id) {
		resourceTypeDAO.deleteResourceType(id);
	}
}
