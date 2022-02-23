package com.revature.wedding_planner.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.revature.wedding_planner.dao.AttendeeDAO;
import com.revature.wedding_planner.models.Attendee;
import com.revature.wedding_planner.models.DinnerType;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.models.Wedding;

public class AttendeeServiceTestSuite {
	
	AttendeeService sut;
	AttendeeDAO mockAttendeeDAO;
	
	@Before
	public void testPrep() {
		mockAttendeeDAO = mock(AttendeeDAO.class);
		sut = new AttendeeService(mockAttendeeDAO);
	}
	
	@Test
	public void test_addAttendeeGoodData() {
		Attendee attendee = new Attendee(1, new User(), new Wedding(), new DinnerType(), true, true);
		when(mockAttendeeDAO.addAttendee(attendee)).thenReturn(attendee);
		attendee = sut.addAttendee(attendee);
		assertNotNull(attendee);
	}
	
	@Test
	public void test_addAttendeeBadData() {
		Attendee attendee1 = new Attendee(1, null, new Wedding(), new DinnerType(), true, true);
		when(mockAttendeeDAO.addAttendee(attendee1)).thenReturn(attendee1);
		attendee1 = sut.addAttendee(attendee1);
		
		Attendee attendee2 = new Attendee(1, new User(), null, new DinnerType(), true, true);
		when(mockAttendeeDAO.addAttendee(attendee2)).thenReturn(attendee2);
		attendee2 = sut.addAttendee(attendee2);
		
		Attendee attendee3 = new Attendee(1, new User(), new Wedding(), null, true, true);
		when(mockAttendeeDAO.addAttendee(attendee3)).thenReturn(attendee3);
		attendee3 = sut.addAttendee(attendee3);
		
		assertNull(attendee1);
		assertNull(attendee2);
		assertNull(attendee3);
	}
}
