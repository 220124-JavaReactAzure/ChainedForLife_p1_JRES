package com.revature.wedding_planner.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.UserType;
import com.revature.wedding_planner.util.HibernateUtil;

public class UserTypeDAO {

	public boolean addUserType(UserType userType) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(userType);
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<UserType> getAllUserTypes() {
		try {
			Session session = HibernateUtil.getSession();
			@SuppressWarnings("unchecked")
			List<UserType> userTypes = session.createQuery("FROM UserType").list();
			return userTypes;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public UserType getUserTypeByID(int id) {
		try {
			Session session = HibernateUtil.getSession();
			UserType userType = session.get(UserType.class, id);
			return userType;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updateUserTypeWithSessionMethod(UserType userType) {
		try {
			Session session = HibernateUtil.getSession();
			
			Transaction transaction = session.beginTransaction();
			session.merge(userType);
			transaction.commit();
			
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	//TODO implement this for it's not complete
	public void deleteUserType(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
