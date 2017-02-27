package ie.sugrue.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;

import ie.sugrue.repository.MySQLUserRepositoryImpl;

@CrossOrigin(origins = "http://desktop:5000", maxAge = 3600)
public class PrimaryController {

	ApplicationContext		context					= new ClassPathXmlApplicationContext("context.xml");
	MySQLUserRepositoryImpl	mySQLUserRepositoryImpl	= (MySQLUserRepositoryImpl) context.getBean("MySQLUserRepositoryImpl");

}
