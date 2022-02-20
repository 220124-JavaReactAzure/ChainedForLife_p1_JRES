package com.revature.wedding_planner.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.wedding_planner.models.Attendee;
import com.revature.wedding_planner.util.HibernateUtil;

public class AttendeeDAO {
	
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
}
