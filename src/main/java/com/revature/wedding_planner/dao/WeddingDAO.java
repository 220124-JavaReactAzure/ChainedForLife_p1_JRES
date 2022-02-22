package com.revature.wedding_planner.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.wedding_planner.models.Wedding;
import com.revature.wedding_planner.util.HibernateUtil;

public class WeddingDAO {

	public boolean addWedding(Wedding wedding) {

		try {
			Session session = HibernateUtil.getSession();

			session.save(wedding);

			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Wedding> getAllWeddings() {

		try {
			Session session = HibernateUtil.getSession();
			List<Wedding> weddings = session.createQuery("FROM wedding").list();

			return weddings;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public Wedding getWeddingByID(int id) {

		try {
			Session session = HibernateUtil.getSession();

			Wedding wedding = session.get(Wedding.class, id);

			return wedding;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}

	}
	
	public void updateWeddingWithSessionMethod(Wedding wedding) {
		try {
			Session session = HibernateUtil.getSession();

			Transaction transaction = session.beginTransaction();
			session.merge(wedding);
			transaction.commit();

		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public boolean deleteWedding(Wedding wedding) {
		try {
			Session session = HibernateUtil.getSession();

			// deletion query
			String hql = "delete from attendee where attendee_id = ?";

			Query q = session.createQuery(hql);
			q.setParameter("wedding_id", wedding.getId());
			q.executeUpdate();

			return true;

		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return false;

		} finally {
			HibernateUtil.closeSession();
		}
	}
}
