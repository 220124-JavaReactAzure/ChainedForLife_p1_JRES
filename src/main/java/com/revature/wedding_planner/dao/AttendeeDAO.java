package com.revature.wedding_planner.dao;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.wedding_planner.models.Attendee;
import com.revature.wedding_planner.util.HibernateUtil;

public class AttendeeDAO {
	
	private final Logger logger = LogManager.getRootLogger();
	
	public Attendee addAttendee(Attendee attendee) {
		
		try {
			Session session = HibernateUtil.getSession();
	
			session.save(attendee);
			
			return attendee;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Attendee> getAllAttendees(){
		
		try {
			Session session = HibernateUtil.getSession();
			List<Attendee> attendees = session.createQuery("FROM Attendee").list();
			
			return attendees;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
				HibernateUtil.closeSession();
		}
	}
	
	public Attendee getAttendeeById(int id) {
		
		try {
			Session session = HibernateUtil.getSession();
			
			Attendee attendee = session.get(Attendee.class, id);
			
	
			return attendee;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}

	}
	
	public boolean deleteAttendee(Attendee attendee) {
		try {
			Session session = HibernateUtil.getSession();
			
			//deletion query
			String hql = "delete from attendee where attendee_id = ?";
			
			Query q = session.createQuery(hql);
			q.setParameter("attendee_id", attendee.getId());
			q.executeUpdate();
			
			return true;
			
		}catch(HibernateException | IOException e) {
			e.printStackTrace();
			return false;
			
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void updateAttendeeWithSessionMethod(Attendee attendee) {
		// TODO Auto-generated method stub
		
		try {
			Session session = HibernateUtil.getSession();
			
			Transaction transaction = session.beginTransaction();
			session.merge(attendee);
			transaction.commit();
			
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		
	}
	
	public void deleteAttendeeByID(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
