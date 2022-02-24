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

import com.revature.wedding_planner.dao.WeddingDAO;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.models.Wedding;

public class WeddingServiceTestSuite {
	WeddingService sut;
	WeddingDAO mockWeddingDAO;

	@Before
	public void testPrep() {
		mockWeddingDAO = mock(WeddingDAO.class);
		sut = new WeddingService(mockWeddingDAO);
	}

	@Test
	public void test_addWedding_withGoodData() {
		Wedding wedding = new Wedding(1, new User());
		when(mockWeddingDAO.addWedding(wedding)).thenReturn(true);
		boolean isValid = sut.addWedding(wedding);
		assertTrue(isValid);
	}

	@Test
	public void test_addWedding_withBadData() {
		Wedding wedding1 = new Wedding(0, new User());
		when(mockWeddingDAO.addWedding(wedding1)).thenReturn(true);
		boolean isValid1 = sut.addWedding(wedding1);

		Wedding wedding2 = new Wedding(1, null);
		when(mockWeddingDAO.addWedding(wedding2)).thenReturn(true);
		boolean isValid2 = sut.addWedding(wedding2);

		assertFalse(isValid1);
		assertFalse(isValid2);

	}

	@Test
	public void test_updateWeddingWithSessionMethod_withGoodData() {
		Wedding wedding = new Wedding(1, new User());
		Wedding wedding1 = new Wedding(1, new User());
		sut.updateWeddingWithSessionMethod(wedding1);
		assertNotSame(wedding, wedding1);
		assertNotEquals(wedding, wedding1);

	}

	// TODO across the board there needs to be impl of delete
	@Test
	public void test_deleteWedding() {
		Wedding wedding = new Wedding(1, new User());
		when(sut.getWeddingByID(1)).thenReturn(wedding);
		sut.deleteWedding(wedding);
		when(sut.getWeddingByID(1)).thenReturn(null);
	}

	@Test
	public void test_getAllWeddings() {
		LinkedList<Wedding> weddings = new LinkedList<>();
		weddings.add(new Wedding());
		weddings.add(new Wedding());
		weddings.add(new Wedding());
		weddings.add(new Wedding());
		weddings.add(new Wedding());

		when(mockWeddingDAO.getAllWeddings()).thenReturn(weddings);
		weddings = (LinkedList<Wedding>) sut.getAllWeddings();

		assertNotNull(weddings);
	}

	@Test
	public void test_getWeddingById_weddingDoesExist() {
		Wedding wedding = new Wedding();
		when(mockWeddingDAO.getWeddingByID(1)).thenReturn(wedding);
		wedding = sut.getWeddingByID(1);

		assertNotNull(wedding);
	}

	@Test
	public void test_getWeddingById_weddingDoesNotExist() {
		Wedding wedding = new Wedding();
		when(mockWeddingDAO.getWeddingByID(1)).thenReturn(wedding);
		wedding = sut.getWeddingByID(0);

		assertNull(wedding);
	}
}
