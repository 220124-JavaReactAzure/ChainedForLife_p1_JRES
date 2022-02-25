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

import com.revature.wedding_planner.dao.UserDAO;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.models.UserType;

public class UserServiceTestSuite {
	UserService sut;
	UserDAO mockUserDAO;

	@Before
	public void testPrep() {
		mockUserDAO = mock(UserDAO.class);
		sut = new UserService(mockUserDAO);
	}

	@Test
	public void test_addUser_withGoodData() {
		User user = new User(1, "Test", "test@gmail.com", "testPw", new UserType());
		when(mockUserDAO.addUser(user)).thenReturn(true);
		boolean isValid = sut.addUser(user);
		assertTrue(isValid);
	}

	@Test
	public void test_addUser_withBadData() {
		User user = new User(0, "Test", "test@gmail.com", "testPw", new UserType());
		when(mockUserDAO.addUser(user)).thenReturn(true);
		boolean isValid = sut.addUser(user);
		
		User user1 = new User(1, "", "test@gmail.com", "testPw", new UserType());
		when(mockUserDAO.addUser(user1)).thenReturn(true);
		boolean isValid1 = sut.addUser(user1);

		User user2 = new User(1, "Test", "", "testPw", new UserType());
		when(mockUserDAO.addUser(user2)).thenReturn(true);
		boolean isValid2 = sut.addUser(user2);
		
		User user3 = new User(1, "Test", "test@gmail.com", "", new UserType());
		when(mockUserDAO.addUser(user3)).thenReturn(true);
		boolean isValid3 = sut.addUser(user3);
		
		User user4 = new User(1, "Test", "test@gmail.com", "testPw", null);
		when(mockUserDAO.addUser(user4)).thenReturn(true);
		boolean isValid4 = sut.addUser(user4);

		assertFalse(isValid);
		assertFalse(isValid1);
		assertFalse(isValid2);
		assertFalse(isValid3);
		assertFalse(isValid4);
	}

	@Test
	public void test_updateUserWithSessionMethod_withGoodData() {
		User user = new User(1, "Test", "test@gmail.com", "testPw", new UserType());
		User user1 = new User(1, "Testington", "test23@gmail.com", "testPassword", new UserType());
		sut.updateUserWithSessionMethod(user1);
		assertNotSame(user, user1);
		assertNotEquals(user, user1);

	}

	// TODO across the board there needs to be impl of delete
	@Test
	public void test_deleteUser() {
		User user = new User(1, "Test", "test@gmail.com", "testPw", new UserType());
		when(sut.getUserByID(1)).thenReturn(user);
		sut.deleteUser(1);
		when(sut.getUserByID(1)).thenReturn(null);
	}

	@Test
	public void test_getAllUsers() {
		LinkedList<User> users = new LinkedList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		users.add(new User());
		users.add(new User());

		when(mockUserDAO.getAllUsers()).thenReturn(users);
		users = (LinkedList<User>) sut.getAllUsers();

		assertNotNull(users);
	}

	@Test
	public void test_getUserById_userDoesExist() {
		User user = new User();
		when(mockUserDAO.getUserByID(1)).thenReturn(user);
		user = sut.getUserByID(1);

		assertNotNull(user);
	}

	@Test
	public void test_getUserById_userDoesNotExist() {
		User user = new User();
		when(mockUserDAO.getUserByID(1)).thenReturn(user);
		user = sut.getUserByID(0);

		assertNull(user);
	}
}
