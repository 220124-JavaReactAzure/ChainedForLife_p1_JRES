package com.revature.wedding_planner.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.wedding_planner.web.util.*;


import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.util.HibernateUtil;

public class UserDAO {

	private static final User user = null;
	
	
	private final Logger logger = LogManager.getRootLogger();
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
	
	public User getUserByEmail(String email) {
		try {
			Session session = HibernateUtil.getSession();
			User user = session.get(User.class, email);
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
		
		logger.info("Finding email and password");
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from users where username = ? and password = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("user_email"));
			}
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
//		Session session;
//		try {
//			
//			
//			
//		
		
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
