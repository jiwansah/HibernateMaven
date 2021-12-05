package com.jcg.hibernate.maven.service;

import java.util.List;

import org.hibernate.Session;

import com.jcg.hibernate.maven.AppMain;
import com.jcg.hibernate.maven.dao.Category;
import com.jcg.hibernate.maven.dao.Product;
import com.jcg.hibernate.maven.dao.ProductDetail;

public class ProductService {

	private static int recordCount =1001;
	static Session sessionObj;
	
	public void save() {
		
		try {
			sessionObj = AppMain.getSessionFactory().openSession();
			sessionObj.beginTransaction();

			Product product = new Product("Product-"+recordCount, "decription", 200, null);
			ProductDetail productDetail = new ProductDetail();
			product.setProductDetail(productDetail);
			productDetail.setDimension("dimension");
			productDetail.setManufacturer("manufacturer");
			productDetail.setOrigin("origin");
			productDetail.setWeight(12);
			productDetail.setProduct(product);
			recordCount++;

			sessionObj.save(product);
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
	
	

	public void get() {
		
		try {
			sessionObj = AppMain.getSessionFactory().openSession();
			sessionObj.beginTransaction();
			List<Product> list = sessionObj.createCriteria(Product.class).list();
			for (Product product : list) {
				System.out.println(product);
				System.out.println("Details: "+product.getProductDetail());
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
			
			Object persistentInstance = sessionObj.load(Product.class, id);
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
