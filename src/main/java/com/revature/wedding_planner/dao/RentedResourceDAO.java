package com.revature.wedding_planner.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import com.revature.wedding_planner.models.RentedResource;
import com.revature.wedding_planner.util.HibernateUtil;


public class RentedResourceDAO {

	private final Logger logger = LogManager.getRootLogger();
	
		public boolean addRentedResource(RentedResource rentedResource) {
			try {
				Session session = HibernateUtil.getSession();
				session.save(rentedResource);
				return true;
				
				
			} catch (HibernateException | IOException e) {
				e.printStackTrace();
				return false;
				
			} finally {
				HibernateUtil.closeSession();
			}
		}

		public List<RentedResource> getAllRentedResources() {
			try {
				
				//logger
				Session session = HibernateUtil.getSession();
				@SuppressWarnings("unchecked")
				List<RentedResource> rentedResources = session.createQuery("FROM RentedResource").list();
				return rentedResources;
				
			} catch (HibernateException | IOException e) {
				e.printStackTrace();
				return null;
				
			} finally {
				HibernateUtil.closeSession();
			}
		}

		public RentedResource getRentedResourceByID(int id) {
			try {
				
				//logger 
				Session session = HibernateUtil.getSession();
				RentedResource rentedResource = session.get(RentedResource.class, id);
				
				
				return rentedResource;
			} catch (HibernateException | IOException e) {
				e.printStackTrace();
				return null;
				
			} finally {
				HibernateUtil.closeSession();
			}
		}
		
		public void updateRentedResourceWithSessionMethod(RentedResource rentedResource) {
			try {
				Session session = HibernateUtil.getSession();

				Transaction transaction = session.beginTransaction();
				session.merge(rentedResource);
				transaction.commit();

			} catch (HibernateException | IOException e) {
				e.printStackTrace();
			} finally {
				HibernateUtil.closeSession();
			}
		}
		
		//TODO implement this for it's not complete
		public void deleteRentedResource(int id) {
			try {
				Session session = HibernateUtil.getSession();
				
				
			} catch (HibernateException | IOException e) {
				e.printStackTrace();
			} finally {
				HibernateUtil.closeSession();
			}
		}
		
}

