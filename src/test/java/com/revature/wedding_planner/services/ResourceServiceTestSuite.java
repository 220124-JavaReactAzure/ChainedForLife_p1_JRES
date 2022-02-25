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

import com.revature.wedding_planner.dao.ResourceDAO;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.models.Resource;
import com.revature.wedding_planner.models.ResourceType;

public class ResourceServiceTestSuite {
	ResourceService sut;
	ResourceDAO mockResourceDAO;

	@Before
	public void testPrep() {
		mockResourceDAO = mock(ResourceDAO.class);
		sut = new ResourceService(mockResourceDAO);
	}

	@Test
	public void test_addResource_withGoodData() {
		Resource resource = new Resource(1, new ResourceType(), new Date(512341), new Date(1522421), 50);
		when(mockResourceDAO.addResource(resource)).thenReturn(true);
		boolean isValid = sut.addResource(resource);
		assertTrue(isValid);
	}

	@Test
	public void test_addResource_withBadData() {
		Resource resource1 = new Resource(1, new ResourceType(), null, new Date(1522421), 50);
		when(mockResourceDAO.addResource(resource1)).thenReturn(true);
		boolean isValid1 = sut.addResource(resource1);

		Resource resource2 = new Resource(1, new ResourceType(), new Date(512341), null, 50);
		when(mockResourceDAO.addResource(resource2)).thenReturn(true);
		boolean isValid2 = sut.addResource(resource2);
		
		assertFalse(isValid1);
		assertFalse(isValid2);
	}

	@Test
	public void test_updateResourceWithSessionMethod_withGoodData() {
		Resource resource = new Resource(1, new ResourceType(), new Date(512341), new Date(1522421), 50);
		Resource resource1 = new Resource(1, new ResourceType(), new Date(512541), new Date(1122421), 50);
		sut.updateResourceWithSessionMethod(resource1);
		assertNotSame(resource, resource1);
		assertNotEquals(resource, resource1);

	}

	// TODO across the board there needs to be impl of delete
	@Test
	public void test_deleteResource() {
		Resource resource = new Resource(1, new ResourceType(), new Date(512341), new Date(1522421), 50);
		when(sut.getResourceByID(1)).thenReturn(resource);
		sut.deleteResource(1);
		when(sut.getResourceByID(1)).thenReturn(null);
	}

	@Test
	public void test_getAllResources() {
		LinkedList<Resource> resources = new LinkedList<>();
		resources.add(new Resource());
		resources.add(new Resource());
		resources.add(new Resource());
		resources.add(new Resource());
		resources.add(new Resource());

		when(mockResourceDAO.getAllResources()).thenReturn(resources);
		resources = (LinkedList<Resource>) sut.getAllResources();

		assertNotNull(resources);
	}

	@Test
	public void test_getResourceById_resourceDoesExist() {
		Resource resource = new Resource();
		when(mockResourceDAO.getResourceByID(1)).thenReturn(resource);
		resource = sut.getResourceByID(1);

		assertNotNull(resource);
	}

	@Test
	public void test_getResourceById_resourceDoesNotExist() {
		Resource resource = new Resource();
		when(mockResourceDAO.getResourceByID(1)).thenReturn(resource);
		resource = sut.getResourceByID(0);

		assertNull(resource);
	}
}
