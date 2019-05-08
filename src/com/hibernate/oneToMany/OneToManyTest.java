package com.hibernate.oneToMany;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernate.domain.Customer;
import com.hibernate.domain.LinkMan;

public class OneToManyTest {
	@Test
	public void addLinkMans() {
		
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Customer customer=new Customer();
		customer.setCust_name("狄仁杰");
		Set<LinkMan> linkMans = customer.getLinkMans();
		
		LinkMan linkMan1=new LinkMan();
		LinkMan linkMan2=new LinkMan();
		linkMan1.setLkm_name("李元芳");
		linkMan2.setLkm_name("如燕");
		
		linkMans.add(linkMan1);
		linkMans.add(linkMan2);
		linkMan1.setCustomer(customer);
		linkMan2.setCustomer(customer);
		//在一对多的映射关系中，必须先保存是一个的对象，再保存它所对应的多个对象
		//如果不按照这个顺序，将会造成多个对象不能保存，事务回滚
		session.save(customer);
		session.save(linkMan1);
		session.save(linkMan2);
		//customer后于linkman保存会产生错误，只产生一条sql语句
//		session.save(customer);
		
		transaction.commit();
		
		session.close();
		factory.close();
	}
	
	
	
	@Test
	public void addLinkMansTest() {
		
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Customer customer=new Customer();
		customer.setCust_name("张经理");
		Set<LinkMan> linkMans = customer.getLinkMans();
		
		LinkMan linkMan1=new LinkMan();
		LinkMan linkMan2=new LinkMan();
		LinkMan linkMan3=new LinkMan();
		linkMan1.setLkm_name("小张");
		linkMan2.setLkm_name("小李");
		linkMan3.setLkm_name("小王");
		//将联系人添加到Customer
//		linkMans.add(linkMan1);
		linkMans.add(linkMan2);
		linkMans.add(linkMan3);
		//将Customer与LinkMan建立关系
		linkMan1.setCustomer(customer);
//		linkMan2.setCustomer(customer);
//		linkMan3.setCustomer(customer);
		
		
		
		session.save(linkMan1);//只有两条insert语句，分别像两张表中添加
//		session.save(customer);
//		session.save(linkMan2);
//		session.save(linkMan3);
		
		transaction.commit();
		
		session.close();
		factory.close();
	}
	
	@Test
	public void deleteTest() {
		
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Customer customer = session.get(Customer.class, 6l);
		session.delete(customer);
		
		transaction.commit();
		session.close();
		factory.close();
	}
	
	@Test
	public void deleteLinkManTest() {
		//都设置了级联的主要级别，则牵一发而动全身，无论是删除联系人还是删除客户都会删除与之相关联的所有联系人和客户
		
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		LinkMan linkMan = session.get(LinkMan.class, 5l);
		session.delete(linkMan);
		
		transaction.commit();
		session.close();
		factory.close();
	}
}
