package com.revature.wedding_planner.util;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.wedding_planner.models.Attendee;
import com.revature.wedding_planner.models.DinnerType;
import com.revature.wedding_planner.models.PlusOne;
import com.revature.wedding_planner.models.RentedResource;
import com.revature.wedding_planner.models.Resource;
import com.revature.wedding_planner.models.ResourceType;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.models.UserType;
import com.revature.wedding_planner.models.Wedding;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;

	public static Session getSession() throws IOException {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			Properties props = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props.load(loader.getResourceAsStream("hibernate.properties"));

			// Add properties to our configuration
			configuration.setProperties(props);
			// ONE ADDITIONAL STEP I NEED TO INCLUDE
			configuration.addAnnotatedClass(DinnerType.class);
			configuration.addAnnotatedClass(UserType.class);
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Wedding.class);
			configuration.addAnnotatedClass(ResourceType.class);
			configuration.addAnnotatedClass(Resource.class);
			configuration.addAnnotatedClass(RentedResource.class);
			configuration.addAnnotatedClass(Attendee.class);
			configuration.addAnnotatedClass(PlusOne.class);
			
			// ServiceRegistry
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}

		if (session == null) {
			session = sessionFactory.openSession();
		}

		return session;
	}

	public static void closeSession() {
		session.close();
		session = null;

	}
}
