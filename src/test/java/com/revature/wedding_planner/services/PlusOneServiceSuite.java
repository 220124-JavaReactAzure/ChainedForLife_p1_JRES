package com.revature.wedding_planner.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.revature.wedding_planner.dao.PlusOneDAO;
import com.revature.wedding_planner.models.Attendee;
import com.revature.wedding_planner.models.DinnerType;
import com.revature.wedding_planner.models.PlusOne;
import com.revature.wedding_planner.models.Wedding;

public class PlusOneServiceSuite {
	PlusOneService sut;
	PlusOneDAO mockPlusOneDAO;

	@Before
	public void testPrep() {
		mockPlusOneDAO = mock(PlusOneDAO.class);
		sut = new PlusOneService(mockPlusOneDAO);
	}

	@Test
	public void test_addPlusOne_withGoodData() {
		PlusOne plusOne = new PlusOne(1, new Attendee(), new Wedding(), new DinnerType());
		when(mockPlusOneDAO.addPlusOne(plusOne)).thenReturn(true);
		boolean isValid = sut.addPlusOne(plusOne);
		assertTrue(isValid);
	}

	@Test
	public void test_addPlusOne_withBadData() {
		PlusOne plusOne1 = new PlusOne(0, new Attendee(), new Wedding(), new DinnerType());
		when(mockPlusOneDAO.addPlusOne(plusOne1)).thenReturn(true);
		boolean isValid1 = sut.addPlusOne(plusOne1);

		PlusOne plusOne2 = new PlusOne(1, null, new Wedding(), new DinnerType());
		when(mockPlusOneDAO.addPlusOne(plusOne2)).thenReturn(true);
		boolean isValid2 = sut.addPlusOne(plusOne2);
		
		PlusOne plusOne3 = new PlusOne(1, new Attendee(), null, new DinnerType());
		when(mockPlusOneDAO.addPlusOne(plusOne3)).thenReturn(true);
		boolean isValid3 = sut.addPlusOne(plusOne1);
		
		PlusOne plusOne4 = new PlusOne(1, new Attendee(), new Wedding(), null);
		when(mockPlusOneDAO.addPlusOne(plusOne4)).thenReturn(true);
		boolean isValid4 = sut.addPlusOne(plusOne4);

		assertFalse(isValid1);
		assertFalse(isValid2);
		assertFalse(isValid3);
		assertFalse(isValid4);
	}

	@Test
	public void test_updatePlusOneWithSessionMethod_withGoodData() {
		PlusOne plusOne = new PlusOne(1, new Attendee(), new Wedding(), new DinnerType());
		PlusOne plusOne1 = new PlusOne(1, new Attendee(), new Wedding(), new DinnerType());
		sut.updatePlusOneWithSessionMethod(plusOne1);
		assertNotSame(plusOne, plusOne1);
	}

	// TODO across the board there needs to be impl of delete
	@Test
	public void test_deletePlusOne() {
		PlusOne plusOne = new PlusOne(1, new Attendee(), new Wedding(), new DinnerType());
		when(sut.getPlusOneByID(1)).thenReturn(plusOne);
		sut.deletePlusOne(plusOne);
		when(sut.getPlusOneByID(1)).thenReturn(null);
	}

	@Test
	public void test_getAllPlusOnes() {
		LinkedList<PlusOne> plusOnes = new LinkedList<>();
		plusOnes.add(new PlusOne());
		plusOnes.add(new PlusOne());
		plusOnes.add(new PlusOne());
		plusOnes.add(new PlusOne());
		plusOnes.add(new PlusOne());

		when(mockPlusOneDAO.getAllPlusOnes()).thenReturn(plusOnes);
		plusOnes = (LinkedList<PlusOne>) sut.getAllPlusOnes();

		assertNotNull(plusOnes);
	}

	@Test
	public void test_getPlusOneById_plusOneDoesExist() {
		PlusOne plusOne = new PlusOne();
		when(mockPlusOneDAO.getPlusOneByID(1)).thenReturn(plusOne);
		plusOne = sut.getPlusOneByID(1);

		assertNotNull(plusOne);
	}

	@Test
	public void test_getPlusOneById_plusOneDoesNotExist() {
		PlusOne plusOne = new PlusOne();
		when(mockPlusOneDAO.getPlusOneByID(1)).thenReturn(plusOne);
		plusOne = sut.getPlusOneByID(0);

		assertNull(plusOne);
	}
}
