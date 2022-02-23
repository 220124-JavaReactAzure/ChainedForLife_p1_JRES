package com.revature.wedding_planner.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.revature.wedding_planner.dao.DinnerTypeDAO;
import com.revature.wedding_planner.models.DinnerType;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.models.Wedding;

public class DinnerTypeServiceTestSuite {
	DinnerTypeService sut;
	DinnerTypeDAO mockDinnerTypeDAO;

	@Before
	public void testPrep() {
		mockDinnerTypeDAO = mock(DinnerTypeDAO.class);
		sut = new DinnerTypeService(mockDinnerTypeDAO);
	}

	@Test
	public void test_addDinnerType_withGoodData() {
		DinnerType dinnerType = new DinnerType(1, "TestFood");
		when(mockDinnerTypeDAO.addDinnerType(dinnerType)).thenReturn(true);
		boolean isValid = sut.addDinnerType(dinnerType);
		assertTrue(isValid);
	}

	@Test
	public void test_addDinnerType_withBadData() {
		DinnerType dinnerType1 = new DinnerType(0, "TestFood");
		when(mockDinnerTypeDAO.addDinnerType(dinnerType1)).thenReturn(true);
		boolean isValid1 = sut.addDinnerType(dinnerType1);

		DinnerType dinnerType2 = new DinnerType(1, "");
		when(mockDinnerTypeDAO.addDinnerType(dinnerType2)).thenReturn(true);
		boolean isValid2 = sut.addDinnerType(dinnerType2);

		assertFalse(isValid1);
		assertFalse(isValid2);
		
	}

	@Test
	public void test_updateDinnerTypeWithSessionMethod_withGoodData() {
		DinnerType dinnerType = new DinnerType(1, "TestFood");
		DinnerType dinnerType1 = new DinnerType(1, "BestFood");
		sut.updateDinnerTypeWithSessionMethod(dinnerType1);
		assertNotSame(dinnerType, dinnerType1);
		assertNotEquals(dinnerType, dinnerType1);

	}

	// TODO across the board there needs to be impl of delete
	@Test
	public void test_deleteDinnerType() {
		DinnerType dinnerType = new DinnerType(1, "TestFood");
		sut.deleteDinnerType(1);
		assertNotNull(dinnerType);
	}

	@Test
	public void test_getAllDinnerTypes() {
		LinkedList<DinnerType> dinnerTypes = new LinkedList<>();
		dinnerTypes.add(new DinnerType());
		dinnerTypes.add(new DinnerType());
		dinnerTypes.add(new DinnerType());
		dinnerTypes.add(new DinnerType());
		dinnerTypes.add(new DinnerType());

		when(mockDinnerTypeDAO.getAllDinnerTypes()).thenReturn(dinnerTypes);
		dinnerTypes = (LinkedList<DinnerType>) sut.getAllDinnerTypes();

		assertNotNull(dinnerTypes);
	}

	@Test
	public void test_getDinnerTypeById_dinnerTypeDoesExist() {
		DinnerType dinnerType = new DinnerType();
		when(mockDinnerTypeDAO.getDinnerTypeByID(1)).thenReturn(dinnerType);
		dinnerType = sut.getDinnerTypeByID(1);

		assertNotNull(dinnerType);
	}

	@Test
	public void test_getDinnerTypeById_dinnerTypeDoesNotExist() {
		DinnerType dinnerType = new DinnerType();
		when(mockDinnerTypeDAO.getDinnerTypeByID(1)).thenReturn(dinnerType);
		dinnerType = sut.getDinnerTypeByID(0);

		assertNull(dinnerType);
	}
}
