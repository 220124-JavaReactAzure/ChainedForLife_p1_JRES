package com.revature.wedding_planner.services;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

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
	public void test_addAttendee_withGoodData() {
		Attendee attendee = new Attendee(1, new User(), new Wedding(), new DinnerType(), true, true);
		when(mockAttendeeDAO.addAttendee(attendee)).thenReturn(attendee);
		attendee = sut.addAttendee(attendee);
		assertNotNull(attendee);
	}
	
	@Test
	public void test_addAttendee_withBadData() {
		Attendee attendee1 = new Attendee(1, null, new Wedding(), new DinnerType(), true, true);
		when(mockAttendeeDAO.addAttendee(attendee1)).thenReturn(attendee1);
		attendee1 = sut.addAttendee(attendee1);
		
		Attendee attendee2 = new Attendee(1, new User(), null, new DinnerType(), true, true);
		when(mockAttendeeDAO.addAttendee(attendee2)).thenReturn(attendee2);
		attendee2 = sut.addAttendee(attendee2);
		
		Attendee attendee3 = new Attendee(1, new User(), new Wedding(), null, true, true);
		when(mockAttendeeDAO.addAttendee(attendee3)).thenReturn(attendee3);
		attendee3 = sut.addAttendee(attendee3);
		
		Attendee attendee4 = new Attendee(0, new User(), new Wedding(), new DinnerType(), true, true);
		when(mockAttendeeDAO.addAttendee(attendee4)).thenReturn(attendee4);
		attendee4 = sut.addAttendee(attendee4);
		
		assertNull(attendee1);
		assertNull(attendee2);
		assertNull(attendee3);
		assertNull(attendee4);
	}
	
	@Test
	public void test_updateAttendeeWithSessionMethod_withGoodData() {
		Attendee attendee = new Attendee(1, new User(), new Wedding(), new DinnerType(), true, true);
		Attendee attendee1 = new Attendee(1, new User(), new Wedding(), new DinnerType(1, "Fish"), true, true);
		sut.updateAttendeeWithSessionMethod(attendee1);
		assertNotSame(attendee, attendee1);
		assertNotEquals(attendee, attendee1);
		
	}
	
	//TODO across the board there needs to be impl of delete
	@Test
	public void test_deleteAttendee() {
		Attendee attendee = new Attendee(1, new User(), new Wedding(), new DinnerType(), true, true);
		sut.deleteAttendee(attendee);
		assertNotNull(attendee);
	}
	
	
	@Test
	public void test_getAllAttendees() {
		LinkedList<Attendee> attendees = new LinkedList<>();
		attendees.add(new Attendee());
		attendees.add(new Attendee());
		attendees.add(new Attendee());
		attendees.add(new Attendee());
		attendees.add(new Attendee());
		
		when(mockAttendeeDAO.getAllAttendees()).thenReturn(attendees);
		attendees = (LinkedList<Attendee>) sut.getAllAttendees();
		
		assertNotNull(attendees);
	}
	
	@Test
	public void test_getAttendeeById_attendeeDoesExist() {
		Attendee attendee = new Attendee();
		when(mockAttendeeDAO.getAttendeeById(1)).thenReturn(attendee);
		attendee = sut.getAttendeeByID(1);
		
		assertNotNull(attendee);
	}
	
	@Test
	public void test_getAttendeeById_attendeeDoesNotExist() {
		Attendee attendee = new Attendee();
		when(mockAttendeeDAO.getAttendeeById(1)).thenReturn(attendee);
		attendee = sut.getAttendeeByID(0);
		
		assertNull(attendee);
	}
	
}
