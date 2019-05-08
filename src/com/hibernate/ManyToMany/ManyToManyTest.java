package com.hibernate.ManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernate.domain.Role;
import com.hibernate.domain.User;

public class ManyToManyTest {
	@Test
	public void manyToMany() {
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		//进行操作
		User user1=new User();
		user1.setUser_name("小zhang11");
		user1.setUser_password("1231");
		user1.setUser_code("1111");
		user1.setUser_state("1");
		User user2=new User();
		user2.setUser_name("小wang11");
		user2.setUser_password("1231");
		user2.setUser_code("1111");
		user2.setUser_state("1");
		
		//创建角色
		Role role1=new Role();
		role1.setRole_name("java");
		Role role2=new Role();
		role2.setRole_name("php");
		Role role3=new Role();
		role3.setRole_name("python");
//		role1.getUsers().add(user1);
//		role1.getUsers().add(user2);
//		role2.getUsers().add(user2);
//		role2.getUsers().add(user1);
//		role3.getUsers().add(user2);
		
		user1.getRoles().add(role1);
		user1.getRoles().add(role2);
		user2.getRoles().add(role1);
		user2.getRoles().add(role2);
		user2.getRoles().add(role3);
		session.save(user1);
		session.save(user2);
//		session.save(role1);
//		session.save(role2);
//		session.save(role3);
		
		transaction.commit();
		session.close();
		factory.close();
	}
	@Test
	public void manyToManydelete() {
		Configuration configuration=new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		//-------------------
		User user = session.get(User.class, 28l);
		//User作为级联的主控方
		//删除user需要再其关联的对象配置<set name="users" table="sys_user_role" inverse="true" cascade="delete">
		//cascade="delete"这样就可一删除成功
		session.delete(user);
		//-------------------
		
		transaction.commit();
		session.close();
		factory.close();
	}
}
