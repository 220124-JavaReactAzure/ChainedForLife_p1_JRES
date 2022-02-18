package com.revature.wedding_planner.util;

import static org.junit.Assert.assertNotNull;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

public class HibernateUtilTestSuite {
	
	@Test
	public void test_getSession_returnsValidSessuib_givenProviderCredentials() {
		try {
			Session sess = HibernateUtil.getSession();
			HibernateUtil.closeSession();
			System.out.println(sess);
			assertNotNull(sess);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
