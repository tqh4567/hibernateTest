package com.hibernate.sqlQuery;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernate.domain.Customer;

public class SqlQueryTest {
	@Test
	public void sqlQueryTest() {
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		SQLQuery query = session.createSQLQuery("select * from cst_customer");
		//****重要：必须封装到对象
		query.addEntity(Customer.class);
		List<Customer> customers = query.list();
		for (Customer customer : customers) {
			System.out.println(customer.getCust_name());
		}
		
	}
}
