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
			logger.debug("AttendeeService initiated");
			this.attendeeDAO = attendeeDAO;
			
		}
		
		public Attendee addAttendee(Attendee attendee) {
			logger.debug("Added attendee");
			if(attendee.getId() <= 0) return null;
			if(attendee.getUser() == null) return null;
			if(attendee.getWedding() == null) return null;
			if(attendee.getDinnerType() == null) return null;
			
			return attendeeDAO.addAttendee(attendee);
		}
		
		public List<Attendee> getAllAttendees() {
			//log not set jet
			logger.debug("Getting All Attendees initialized");
			return attendeeDAO.getAllAttendees();
		}
		
		public Attendee getAttendeeByID(int id) {
			logger.debug("Getting Attendees By Id");
			return attendeeDAO.getAttendeeById(id);
		}
		
		// TODO implement this in DAO
		public void deleteAttendee(Attendee id) {
			logger.debug("Attendee Deletion Initialized");
			attendeeDAO.deleteAttendee(id);
		}

		public void updateAttendeeWithSessionMethod(Attendee attendee) {
			logger.debug("Attendee Updating initiated");
			if(attendee.getId() <= 0) return;
			else if(attendee.getUser() == null) return;
			else if(attendee.getWedding() == null) return;
			else if(attendee.getDinnerType() == null) return;
			
			attendeeDAO.updateAttendeeWithSessionMethod(attendee);
		}
}
