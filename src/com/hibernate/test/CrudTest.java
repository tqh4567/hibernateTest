package com.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernate.domain.Customer;

/**
 * 测试hibernate的增删改查（单表）
 * @author tqh4567
 *
 */
public class CrudTest {
	
	@Test
	public void addCustomer() {
		//1.创建Configuration对象
		Configuration configuration=new Configuration().configure();
		//2创建Factory
		SessionFactory factory = configuration.buildSessionFactory();
		//3获得session
		Session session = factory.openSession();
		//开启事务
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		//进行数据库的操作
		Customer customer=new Customer();
		customer.setCust_name("tom");
		customer.setCust_industry("baidu");
		session.save(customer);
		//提交事务
		transaction.commit();
		//关闭资源
		session.close();
		factory.close();
		
	}
	
	@Test
	public void deleteCustomer() {
		//1.创建Configuration对象
		Configuration configuration=new Configuration().configure();
		//2创建Factory
		SessionFactory factory = configuration.buildSessionFactory();
		//3获得session
		Session session = factory.openSession();
		//开启事务
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		//进行数据库的操作
		Customer customer = session.get(Customer.class, 1l);
		session.delete(customer);
		//提交事务
		transaction.commit();
		//关闭资源
		session.close();
		factory.close();
		
	}
	@Test
	public void selectCustomer() {
		//1.创建Configuration对象
		Configuration configuration=new Configuration().configure();
		//2创建Factory
		SessionFactory factory = configuration.buildSessionFactory();
		//3获得session
		Session session = factory.openSession();
	
		//进行数据库的操作
		Customer customer = session.get(Customer.class, 2l);
		System.out.println(customer.getCust_industry());
		
		//关闭资源
		session.close();
		factory.close();
		
	}
	
	@Test
	public void updateCustomer() {
		//1.创建Configuration对象
		Configuration configuration=new Configuration().configure();
		//2创建Factory
		SessionFactory factory = configuration.buildSessionFactory();
		//3获得session
		Session session = factory.openSession();
		//开启事务
		Transaction beginTransaction = session.beginTransaction();
		beginTransaction.begin();
		//进行数据库的操作
		
		Customer customer = session.get(Customer.class, 2l);
		customer.setCust_phone("13298392009");
		session.update(customer);
		
		//提交事务
		beginTransaction.commit();
		//关闭资源
		session.close();
		factory.close();
		
	}
}
