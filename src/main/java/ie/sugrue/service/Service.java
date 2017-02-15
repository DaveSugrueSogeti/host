package ie.sugrue.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.sugrue.utils.ConnectionJDBCTemplate;

public class Service {
	private ApplicationContext			context					= new ClassPathXmlApplicationContext("Beans.xml");
	protected ConnectionJDBCTemplate	connectionJDBCTemplate	= (ConnectionJDBCTemplate) context.getBean("ConnectionJDBCTemplate");
}
