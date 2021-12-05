package com.jcg.hibernate.maven;

import java.util.Date;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.jcg.hibernate.maven.dao.User;
import com.jcg.hibernate.maven.service.CategoryService;
import com.jcg.hibernate.maven.service.ProductService;
import com.jcg.hibernate.maven.service.UserService;

public class AppMain {

	
	static SessionFactory sessionFactoryObj=null;

	

	public static void main(String[] args) {
		getSessionFactory();
		System.out.println(".......Hibernate Maven Example.......\n");
		boolean flag = true;
		while(flag) {
			System.out.println("===============================================");
			System.out.println("1: Add User");
			System.out.println("2: Display User");
			System.out.println("3: Delete User");
			
			System.out.println("4: Add Category");
			System.out.println("5: Display Category");
			System.out.println("6: Delete Category");
			
			System.out.println("7: Add Product");
			System.out.println("8: Display Product");
			System.out.println("9: Delete Product");
			
			System.out.println("99: Exit for any key");
			System.out.println("===============================================");
			System.out.println("");
			Scanner input = new Scanner(System.in);
			int num = input.nextInt();
			switch(num) {
				case 1: {
					new UserService().saveUserData();
					break;
				}
				case 2: {
					new UserService().getUserData();
					break;
				}
				case 3: {
					System.out.println("Enter ID");
					Scanner inputScan = new Scanner(System.in);
					int id = input.nextInt();
					new UserService().delete(id);
					break;
				}
				case 4: {
					new CategoryService().saveCategory();
					break;
				}
				case 5: {
					new CategoryService().getCategory();
					break;
				}
				case 6: {
					System.out.println("Enter ID");
					Scanner inputScan = new Scanner(System.in);
					int id = input.nextInt();
					new CategoryService().delete(id);
					break;
				}
				
				case 7: {
					new ProductService().save();
					break;
				}
				case 8: {
					new ProductService().get();
					break;
				}
				case 9: {
					System.out.println("Enter ID");
					Scanner inputScan = new Scanner(System.in);
					int id = input.nextInt();
					new ProductService().delete(id);
					break;
				}
				
				default:{
					flag= false;
				}
			}
			
		}
		
	}
	
	
	
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactoryObj != null) {
			return sessionFactoryObj;
		}
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}
}