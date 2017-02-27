package ie.sugrue.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.sugrue.repository.MySQLUserRepositoryImpl;

public interface Service {
	final ApplicationContext		context					= new ClassPathXmlApplicationContext("context.xml");
	final MySQLUserRepositoryImpl	mySQLUserRepositoryImpl	= (MySQLUserRepositoryImpl) context.getBean("MySQLUserRepositoryImpl");
}
