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

import com.revature.wedding_planner.dao.ResourceTypeDAO;
import com.revature.wedding_planner.models.ResourceType;

public class ResourceTypeTestSuite {
	ResourceTypeService sut;
	ResourceTypeDAO mockResourceTypeDAO;

	@Before
	public void testPrep() {
		mockResourceTypeDAO = mock(ResourceTypeDAO.class);
		sut = new ResourceTypeService(mockResourceTypeDAO);
	}

	@Test
	public void test_addResourceType_withGoodData() {
		ResourceType resourceType = new ResourceType(1, "TestResource");
		when(mockResourceTypeDAO.addResourceType(resourceType)).thenReturn(true);
		boolean isValid = sut.addResourceType(resourceType);
		assertTrue(isValid);
	}

	@Test
	public void test_addResourceType_withBadData() {
		ResourceType resourceType1 = new ResourceType(0, "TestResource");
		when(mockResourceTypeDAO.addResourceType(resourceType1)).thenReturn(true);
		boolean isValid1 = sut.addResourceType(resourceType1);

		ResourceType resourceType2 = new ResourceType(1, "");
		when(mockResourceTypeDAO.addResourceType(resourceType2)).thenReturn(true);
		boolean isValid2 = sut.addResourceType(resourceType2);

		assertFalse(isValid1);
		assertFalse(isValid2);

	}

	@Test
	public void test_updateResourceTypeWithSessionMethod_withGoodData() {
		ResourceType resourceType = new ResourceType(1, "TestResource");
		ResourceType resourceType1 = new ResourceType(1, "BestResource");
		sut.updateResourceTypeWithSessionMethod(resourceType1);
		assertNotSame(resourceType, resourceType1);
		assertNotEquals(resourceType, resourceType1);

	}

	// TODO across the board there needs to be impl of delete
	@Test
	public void test_deleteResourceType() {
		ResourceType resourceType = new ResourceType(1, "TestResource");
		sut.deleteResourceType(1);
		assertNotNull(resourceType);
	}

	@Test
	public void test_getAllResourceTypes() {
		LinkedList<ResourceType> resourceTypes = new LinkedList<>();
		resourceTypes.add(new ResourceType());
		resourceTypes.add(new ResourceType());
		resourceTypes.add(new ResourceType());
		resourceTypes.add(new ResourceType());
		resourceTypes.add(new ResourceType());

		when(mockResourceTypeDAO.getAllResourceTypes()).thenReturn(resourceTypes);
		resourceTypes = (LinkedList<ResourceType>) sut.getAllResourceTypes();

		assertNotNull(resourceTypes);
	}

	@Test
	public void test_getResourceTypeById_resourceTypeDoesExist() {
		ResourceType resourceType = new ResourceType();
		when(mockResourceTypeDAO.getResourceTypeByID(1)).thenReturn(resourceType);
		resourceType = sut.getResourceTypeByID(1);

		assertNotNull(resourceType);
	}

	@Test
	public void test_getResourceTypeById_resourceTypeDoesNotExist() {
		ResourceType resourceType = new ResourceType();
		when(mockResourceTypeDAO.getResourceTypeByID(1)).thenReturn(resourceType);
		resourceType = sut.getResourceTypeByID(0);

		assertNull(resourceType);
	}
}
