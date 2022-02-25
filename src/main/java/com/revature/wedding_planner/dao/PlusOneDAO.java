package com.revature.wedding_planner.dao;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.PlusOne;
import com.revature.wedding_planner.util.HibernateUtil;

public class PlusOneDAO {
	
	private final Logger logger = LogManager.getRootLogger();
	
	public boolean addPlusOne(PlusOne plusOne) {
		
		
		
		try {
			Session session = HibernateUtil.getSession();
			session.save(plusOne);
			
			
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			
			HibernateUtil.closeSession();
		}
	}

	public List<PlusOne> getAllPlusOnes() {
		try {
			
			Session session = HibernateUtil.getSession();
			@SuppressWarnings("unchecked")
			List<PlusOne> plusOne = session.createQuery("FROM PlusOne").list();
			
			
			return plusOne;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public PlusOne getPlusOneByID(int id) {
		try {
			Session session = HibernateUtil.getSession();
			PlusOne plusOne = session.get(PlusOne.class, id);
			
			
			return plusOne;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updatePlusOneWithSessionMethod(PlusOne plusOne) {
		try {
			Session session = HibernateUtil.getSession();
			
			Transaction transaction = session.beginTransaction();
			session.merge(plusOne);
			transaction.commit();
			
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	// TODO implement this for it's not complete
	public boolean deletePlusOne(PlusOne plusOne) {
		try {
			Session session = HibernateUtil.getSession();
			
			String hql = "delete from plusone where plusone_id = ?";
			
			Query q = session.createQuery(hql);
			q.setParameter("plus_one_id", plusOne.getAttendee());
			q.executeUpdate();
			
			return true;
			
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
