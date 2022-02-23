package com.revature.wedding_planner.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.wedding_planner.dao.AttendeeDAO;

import com.revature.wedding_planner.models.Attendee;


public class AttendeeService {

	private final AttendeeDAO attendeeDAO;
	private final Logger logger = LogManager.getRootLogger();
	
		public AttendeeService(AttendeeDAO attendeeDAO) {
			//log
			this.attendeeDAO = attendeeDAO;
			
		}
		
		public Attendee addAttendee(Attendee attendee) {
			//log
			return attendeeDAO.addAttendee(attendee);
		}
		
		public List<Attendee> getAllAttendees() {
			//log not set jet
			return attendeeDAO.getAllAttendees();
		}
		
		public Attendee getAttendeeByID(int id) {
			return attendeeDAO.getAttendeeById(id);
		}
		
		// TODO implement this in DAO
		public void deleteAttendee(Attendee id) {
			attendeeDAO.deleteAttendee(id);
		}

		public void updateAttendeeWithSessionMethod(Attendee attendee) {
			// TODO Auto-generated method stub
			attendeeDAO.updateAttendeeWithSessionMethod(attendee);
		}
}
