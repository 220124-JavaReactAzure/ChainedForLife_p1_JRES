package com.revature.wedding_planner.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.postgresql.core.ConnectionFactory;

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
			
			transaction.commit();
			session.delete(deletedUser);
			
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	

	@SuppressWarnings("deprecation")
	public User findByUsernameAndPassword(String email, String password) {
		

		
		
//		Session session;
		try {
			
			//logger.info("Finding email and password");
			
			String hql = "";
			Session session = HibernateUtil.getSession();
			User user = new User();
			
			hql = "FROM User email:email and password=:password";
			
			
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			query.setParameter("password", password);
			
			user = (User)query.uniqueResult();
			
			return user;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
//		boolean isValid = false;
//		
//		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//			
//			String sql = "select * from users where email = ? and password = ?";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, email);
//			pstmt.setString(2, password);
//			ResultSet rs = pstmt.executeQuery();
//
//	
//			isValid = rs.next();
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return isValid;
//		}
		
		
		
	}
}
