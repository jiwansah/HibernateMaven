package com.jcg.hibernate.maven.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.jcg.hibernate.maven.AppMain;
import com.jcg.hibernate.maven.dao.User;

public class UserService {

	private static int usercount =1001;
	static User userObj;
	static Session sessionObj;
	
	public void saveUserData() {
		
		try {
			sessionObj = AppMain.getSessionFactory().openSession();
			sessionObj.beginTransaction();

			userObj = new User();
			userObj.setUserid(usercount);
			userObj.setUsername("Editor " + usercount);
			userObj.setCreatedBy("Administrator");
			userObj.setCreatedDate(new Date());

			sessionObj.save(userObj);
			
			System.out.println("\n.......Records Saved Successfully To The Database.......\n");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
	
	public void getUserData() {
		
		try {
			sessionObj = AppMain.getSessionFactory().openSession();
			sessionObj.beginTransaction();
			List<User> list = sessionObj.createCriteria(User.class).list();
			for (User user : list) {
				System.out.println(user);
			}
			sessionObj.getTransaction().commit();
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
	
	
	public void delete(int id) {
		
		try {
			sessionObj = AppMain.getSessionFactory().openSession();
			sessionObj.beginTransaction();
			
			Object persistentInstance = sessionObj.load(User.class, id);
			if (persistentInstance != null) {
				sessionObj.delete(persistentInstance);
			}
			
			sessionObj.getTransaction().commit();
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}
