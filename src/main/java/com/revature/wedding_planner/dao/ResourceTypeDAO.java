package com.revature.wedding_planner.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.wedding_planner.models.ResourceType;
import com.revature.wedding_planner.util.HibernateUtil;

public class ResourceTypeDAO {

	public boolean addResourceType(ResourceType resourceType) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(resourceType);
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<ResourceType> getAllResourceTypes() {
		try {
			Session session = HibernateUtil.getSession();
			@SuppressWarnings("unchecked")
			List<ResourceType> resourceType = session.createQuery("FROM ResourceType").list();
			return resourceType;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public ResourceType getResourceTypeByID(int id) {
		try {
			Session session = HibernateUtil.getSession();
			ResourceType resourceType = session.get(ResourceType.class, id);
			return resourceType;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	// TODO implement this for it's not complete
	public void deleteResourceType(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
