package com.revature.wedding_planner.services;

import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.wedding_planner.dao.ResourceDAO;
import com.revature.wedding_planner.models.Resource;

public class ResourceService {

	private final ResourceDAO resourceDAO;
	private final Logger logger = LogManager.getRootLogger();
	
	public ResourceService(ResourceDAO resourceDAO) {
		logger.info(" Adding PlusOne");
		this.resourceDAO = resourceDAO;
	}
	
	public boolean addResource(Resource resource) {

		logger.info("Adding Resource");

		if(resource.getDateAvailableStart() == null) return false;
		if(resource.getDateAvailableEnd() == null) return false;

		logger.info("Adding Resource");
		if(resource.getDateAvailableStart() == null) return false;
		if(resource.getDateAvailableEnd() == null) return false;

		return resourceDAO.addResource(resource);
	}
	
	public List<Resource> getAllResources() {
		logger.info("Getting All Resource");
		return resourceDAO.getAllResources();
	}
	
	public Resource getResourceByID(int id) {
		logger.info("Getting Resource By Id");
		return resourceDAO.getResourceByID(id);
	}
	
	public void updateResourceWithSessionMethod(Resource resource) {
		logger.info("Updating Resource");
		resourceDAO.updateResourceWithSessionMethod(resource);
	}
	
	// TODO implement this in DAO
	public void deleteResource(int id) {
		logger.info("Delete Resource");
		resourceDAO.deleteResource(id);
	}
}
