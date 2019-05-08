package com.hibernate.criteria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.hibernate.domain.Customer;

/**
 * Criteria的用法(仅支持查询)
 * @author tqh4567
 *
 */
public class CriteriaTest {
	
	@Test
	public void criteriaTest() {
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		List<Customer> customers = criteria.list();
		for (Customer customer : customers) {
			System.out.println(customer.getCust_name());
		}
	}
	@Test
	public void criteriaByParam() {
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		//条件查询，可以添加多个条件
//		criteria.add(Restrictions.eq("cust_name", "tom"));
//		criteria.add(Restrictions.eq("cust_industry", "baidu"));
		//模糊查询
		criteria.add(Restrictions.like("cust_name", "%t%"));
		List<Customer> customers = criteria.list();
		for (Customer customer : customers) {
			System.out.println(customer.getCust_name()+customer.getCust_industry());
		}
	}
	
	@Test
	public void criteriaPage() {
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.setFirstResult(0);
		criteria.setMaxResults(2);
		List<Customer> customers = criteria.list();
		for (Customer customer : customers) {
			System.out.println(customer.getCust_name());
		}
	}
}
