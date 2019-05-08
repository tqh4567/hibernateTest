package com.hibernate.hqltest;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernate.domain.Customer;

public class HqlTest {
	
	/**
	 * 查询所有的客户
	 */
	@Test
	public void query() {
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		Query query = session.createQuery("from Customer");
		List<Customer> customers = query.list();
		for (Customer customer : customers) {
			System.out.println(customer.getCust_name());
		}	
	}
	/**
	 * 按照条件查询
	 */
	
	@Test
	public void queryByParam() {
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		//此方法为精确查询可以使用？作为占位符，也可以使用其他你自定义的符号
		Query query = session.createQuery("from Customer where cust_name=?");
		//设置查询内容
		query.setString(0, "tom");
//		Query query = session.createQuery("from Customer where cust_name=:aaa");
//		//设置查询内容
//		query.setString("aaa", "tom");
		List<Customer> customers = query.list();
		for (Customer customer : customers) {
			System.out.println(customer.getCust_name());
		}
		
	}
	/**
	 *分页条件的构造
	 */
	@Test
	public void queryByPage() {
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		//使用setFristResult和setMaxResult可以控制查询的分页
		Query query = session.createQuery("from Customer");
		query.setFirstResult(0);
		query.setMaxResults(2);
		List<Customer> customers = query.list();
		for (Customer customer : customers) {
			System.out.println(customer.getCust_name());
		}
		
		
	}
}
