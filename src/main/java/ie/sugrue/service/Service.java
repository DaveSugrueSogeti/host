package ie.sugrue.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.sugrue.utils.ConnectionJDBCTemplate;

public interface Service {
	final ApplicationContext		context					= new ClassPathXmlApplicationContext("context.xml");
	final ConnectionJDBCTemplate	connectionJDBCTemplate	= (ConnectionJDBCTemplate) context.getBean("ConnectionJDBCTemplate");
}
