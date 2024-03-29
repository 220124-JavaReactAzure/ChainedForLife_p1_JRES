package com.revature.wedding_planner.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.wedding_planner.dao.ResourceTypeDAO;
import com.revature.wedding_planner.models.ResourceType;

public class ResourceTypeService {
	
	private final ResourceTypeDAO resourceTypeDAO;
	private final Logger logger = LogManager.getRootLogger();
	
	public ResourceTypeService(ResourceTypeDAO resourceTypeDAO) {
		logger.info("Initializing ResourceType");
		this.resourceTypeDAO = resourceTypeDAO;
	}
	
	public boolean addResourceType(ResourceType resourceType) {
		if(resourceType.getName().isEmpty()) return false;
		logger.info("Adding ResourceType");
		return resourceTypeDAO.addResourceType(resourceType);
	}
	
	public List<ResourceType> getAllResourceTypes() {
		logger.info("Getting All ResourceType");
		return resourceTypeDAO.getAllResourceTypes();
	}
	
	public ResourceType getResourceTypeByID(int id) {
		logger.info("Getting ResourceType by ID");
		return resourceTypeDAO.getResourceTypeByID(id);
	}
	
	public void updateResourceTypeWithSessionMethod(ResourceType resourceType) {
		logger.info("Updating ResourceType");
		resourceTypeDAO.updateResourceTypeWithSessionMethod(resourceType);
	}
	
	// TODO implement this in DAO
	public void deleteResourceType(int id) {
		logger.info("Deleting ResourceType");
		resourceTypeDAO.deleteResourceType(id);
	}
}
