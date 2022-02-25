package com.revature.wedding_planner.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.util.HibernateUtil;

public class UserDAO {

	public boolean addUser(User user) {
		try {
			Session session = HibernateUtil.getSession();		
			session.save(user);
			
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<User> getAllUsers() {
		try {
			Session session = HibernateUtil.getSession();
			@SuppressWarnings("unchecked")
			List<User> users = session.createQuery("FROM User").list();
			return users;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public User getUserByID(int id) {
		try {
			Session session = HibernateUtil.getSession();
			User user = session.get(User.class, id);
			return user;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void updateUserWithSessionMethod(User user) {
		try {
			Session session = HibernateUtil.getSession();

			//merge always use transaction and commit
			Transaction transaction = session.beginTransaction();
			session.merge(user);
			transaction.commit();

		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	// TODO
	public void deleteUser(int id) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			User deletedUser = this.getUserByID(id);
			
			session.delete(deletedUser);
			transaction.commit();
			
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	

	public User findByUsernameAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		Session session;
		try {
			session = HibernateUtil.getSession();
			User user = session.get(User.class, email);
			
			
			return user;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
}
