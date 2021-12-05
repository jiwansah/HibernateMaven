package com.jcg.hibernate.maven.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.jcg.hibernate.maven.AppMain;
import com.jcg.hibernate.maven.dao.Category;
import com.jcg.hibernate.maven.dao.Product;
import com.jcg.hibernate.maven.dao.User;
import com.oracle.jrockit.jfr.Producer;

public class CategoryService {

	private static int recordCount =1001;
	static Session sessionObj;
	
	public void saveCategory() {
		
		try {
			sessionObj = AppMain.getSessionFactory().openSession();
			sessionObj.beginTransaction();

			Category category = new Category("Category-"+recordCount);
			recordCount++;
			Product producer = new Product("Product-"+recordCount, "decription", 200, category);
			recordCount++;
			Product producer2 = new Product("Product-"+recordCount, "decription", 300, category);
			category.getProducts().add(producer);
			category.getProducts().add(producer2);

			sessionObj.save(category);
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
	
	

	public void getCategory() {
		
		try {
			sessionObj = AppMain.getSessionFactory().openSession();
			sessionObj.beginTransaction();
			List<Category> list = sessionObj.createCriteria(Category.class).list();
			for (Category category : list) {
				System.out.println(category);
				for (Product product : category.getProducts()) {
					System.out.println(product);
				}
				
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
	
	

	
	public void delete(long id) {
		
		try {
			sessionObj = AppMain.getSessionFactory().openSession();
			sessionObj.beginTransaction();
			
			Object persistentInstance = sessionObj.load(Category.class, id);
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
