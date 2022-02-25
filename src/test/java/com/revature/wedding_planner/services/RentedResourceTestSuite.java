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

import com.revature.wedding_planner.dao.RentedResourceDAO;
import com.revature.wedding_planner.models.RentedResource;
import com.revature.wedding_planner.models.Resource;
import com.revature.wedding_planner.models.Wedding;

public class RentedResourceTestSuite {
	RentedResourceService sut;
	RentedResourceDAO mockRentedResourceDAO;

	@Before
	public void testPrep() {
		mockRentedResourceDAO = mock(RentedResourceDAO.class);
		sut = new RentedResourceService(mockRentedResourceDAO);
	}

	@Test
	public void test_addRentedResource_withGoodData() {
		RentedResource rentedResource = new RentedResource(1, new Resource(), new Wedding(), new Date(1522421));
		when(mockRentedResourceDAO.addRentedResource(rentedResource)).thenReturn(true);
		boolean isValid = sut.addRentedResource(rentedResource);
		assertTrue(isValid);
	}

	@Test
	public void test_addRentedResource_withBadData() {
		RentedResource rentedResource1 = new RentedResource(1, null, new Wedding(), new Date(1522421));
		when(mockRentedResourceDAO.addRentedResource(rentedResource1)).thenReturn(true);
		boolean isValid1 = sut.addRentedResource(rentedResource1);

		RentedResource rentedResource2 = new RentedResource(1, new Resource(), null, new Date(1522421));
		when(mockRentedResourceDAO.addRentedResource(rentedResource2)).thenReturn(true);
		boolean isValid2 = sut.addRentedResource(rentedResource2);
		
		RentedResource rentedResource3 = new RentedResource(0, new Resource(), null, new Date(1522421));
		when(mockRentedResourceDAO.addRentedResource(rentedResource2)).thenReturn(true);
		boolean isValid3 = sut.addRentedResource(rentedResource2);

		assertFalse(isValid1);
		assertFalse(isValid2);
		assertFalse(isValid3);
	}

	@Test
	public void test_updateRentedResourceWithSessionMethod_withGoodData() {
		RentedResource rentedResource = new RentedResource(1, new Resource(), new Wedding(), new Date(1522421));
		RentedResource rentedResource1 = new RentedResource(1, new Resource(), new Wedding(), new Date(1553221));
		sut.updateRentedResourceWithSessionMethod(rentedResource1);
		assertNotSame(rentedResource, rentedResource1);
		assertNotEquals(rentedResource, rentedResource1);

	}

	// TODO across the board there needs to be impl of delete
	@Test
	public void test_deleteRentedResource() {
		RentedResource rentedResource = new RentedResource(1, new Resource(), new Wedding(), new Date(1522421));
		when(sut.getRentedResourceByID(1)).thenReturn(rentedResource);
		sut.deleteRentedResource(1);
		when(sut.getRentedResourceByID(1)).thenReturn(null);
	}

	@Test
	public void test_getAllRentedResources() {
		LinkedList<RentedResource> rentedResources = new LinkedList<>();
		rentedResources.add(new RentedResource());
		rentedResources.add(new RentedResource());
		rentedResources.add(new RentedResource());
		rentedResources.add(new RentedResource());
		rentedResources.add(new RentedResource());

		when(mockRentedResourceDAO.getAllRentedResources()).thenReturn(rentedResources);
		rentedResources = (LinkedList<RentedResource>) sut.getAllRentedResources();

		assertNotNull(rentedResources);
	}

	@Test
	public void test_getRentedResourceById_rentedResourceDoesExist() {
		RentedResource rentedResource = new RentedResource();
		when(mockRentedResourceDAO.getRentedResourceByID(1)).thenReturn(rentedResource);
		rentedResource = sut.getRentedResourceByID(1);

		assertNotNull(rentedResource);
	}

	@Test
	public void test_getRentedResourceById_rentedResourceDoesNotExist() {
		RentedResource rentedResource = new RentedResource();
		when(mockRentedResourceDAO.getRentedResourceByID(1)).thenReturn(rentedResource);
		rentedResource = sut.getRentedResourceByID(0);

		assertNull(rentedResource);
	}
}
