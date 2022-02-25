package com.revature.wedding_planner.dao;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.DinnerType;
import com.revature.wedding_planner.util.HibernateUtil;

public class DinnerTypeDAO {
	private final Logger logger = LogManager.getRootLogger();
	
	public boolean addDinnerType(DinnerType dinnerType) {
		logger.info("Add DinnerType DAO initialized");
		try {
			Session session = HibernateUtil.getSession();
			session.save(dinnerType);
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			logger.info("IOexception when trying to add DinnerType");
			return false;
		} finally {
			HibernateUtil.closeSession();
			logger.info("Clossing Session");
		}
	}

	public List<DinnerType> getAllDinnerTypes() {
		try {
			Session session = HibernateUtil.getSession();
			@SuppressWarnings("unchecked")
			List<DinnerType> dinnerTypes = session.createQuery("FROM DinnerType").list();
			return dinnerTypes;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public DinnerType getDinnerTypeByID(int id) {
		try {
			Session session = HibernateUtil.getSession();
			DinnerType dinnerType = session.get(DinnerType.class, id);
			return dinnerType;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updateDinnerTypeWithSessionMethod(DinnerType dinnerType) {
		try {
			Session session = HibernateUtil.getSession();
			
			Transaction transaction = session.beginTransaction();
			session.merge(dinnerType);
			transaction.commit();
			
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	// TODO implement this for it's not complete
	public void deleteDinnerType(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
