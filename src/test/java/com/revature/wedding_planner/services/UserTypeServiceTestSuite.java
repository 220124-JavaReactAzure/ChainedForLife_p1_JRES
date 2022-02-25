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

import com.revature.wedding_planner.dao.UserTypeDAO;
import com.revature.wedding_planner.models.UserType;

public class UserTypeServiceTestSuite {
	UserTypeService sut;
	UserTypeDAO mockUserTypeDAO;

	@Before
	public void testPrep() {
		mockUserTypeDAO = mock(UserTypeDAO.class);
		sut = new UserTypeService(mockUserTypeDAO);
	}

	@Test
	public void test_addUserType_withGoodData() {
		UserType userType = new UserType(1, "TestUser");
		when(mockUserTypeDAO.addUserType(userType)).thenReturn(true);
		boolean isValid = sut.addUserType(userType);
		assertTrue(isValid);
	}

	@Test
	public void test_addUserType_withBadData() {
		UserType userType1 = new UserType(0, "TestUser");
		when(mockUserTypeDAO.addUserType(userType1)).thenReturn(true);
		boolean isValid1 = sut.addUserType(userType1);

		UserType userType2 = new UserType(1, "");
		when(mockUserTypeDAO.addUserType(userType2)).thenReturn(true);
		boolean isValid2 = sut.addUserType(userType2);

		assertFalse(isValid1);
		assertFalse(isValid2);

	}

	@Test
	public void test_updateUserTypeWithSessionMethod_withGoodData() {
		UserType userType = new UserType(1, "TestUser");
		UserType userType1 = new UserType(1, "BestUser");
		sut.updateUserTypeWithSessionMethod(userType1);
		assertNotSame(userType, userType1);
		assertNotEquals(userType, userType1);

	}

	// TODO across the board there needs to be impl of delete
	@Test
	public void test_deleteUserType() {
		UserType userType = new UserType(1, "TestUser");
		sut.deleteUserType(1);
		assertNotNull(userType);
	}

	@Test
	public void test_getAllUserTypes() {
		LinkedList<UserType> userTypes = new LinkedList<>();
		userTypes.add(new UserType());
		userTypes.add(new UserType());
		userTypes.add(new UserType());
		userTypes.add(new UserType());
		userTypes.add(new UserType());

		when(mockUserTypeDAO.getAllUserTypes()).thenReturn(userTypes);
		userTypes = (LinkedList<UserType>) sut.getAllUserTypes();

		assertNotNull(userTypes);
	}

	@Test
	public void test_getUserTypeById_userTypeDoesExist() {
		UserType userType = new UserType();
		when(mockUserTypeDAO.getUserTypeByID(1)).thenReturn(userType);
		userType = sut.getUserTypeByID(1);

		assertNotNull(userType);
	}

	@Test
	public void test_getUserTypeById_userTypeDoesNotExist() {
		UserType userType = new UserType();
		when(mockUserTypeDAO.getUserTypeByID(1)).thenReturn(userType);
		userType = sut.getUserTypeByID(0);

		assertNull(userType);
	}
}
