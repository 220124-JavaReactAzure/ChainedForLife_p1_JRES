package com.revature.wedding_planner.dao;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.Resource;
import com.revature.wedding_planner.util.HibernateUtil;

public class ResourceDAO {
	
	private final Logger logger = LogManager.getRootLogger();
	
	public boolean addResource(Resource resource) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(resource);
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Resource> getAllResources() {
		try {
			Session session = HibernateUtil.getSession();
			@SuppressWarnings("unchecked")
			List<Resource> resources = session.createQuery("FROM Resource").list();
			return resources;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public Resource getResourceByID(int id) {
		try {
			Session session = HibernateUtil.getSession();
			Resource resource = session.get(Resource.class, id);
			return resource;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void updateResourceWithSessionMethod(Resource resource) {
		try {
			Session session = HibernateUtil.getSession();

			Transaction transaction = session.beginTransaction();
			session.merge(resource);
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	// TODO implement this for it's not complete
	public void deleteResource(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
