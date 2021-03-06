package ie.sugrue;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.sugrue.domain.Tester1;
import ie.sugrue.domain.User;
import ie.sugrue.utils.ConnectionJDBCTemplate;

@SpringBootApplication
public class MainApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		System.out.println("------Starting Up--------");

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		ConnectionJDBCTemplate connectionJDBCTemplate = (ConnectionJDBCTemplate) context.getBean("ConnectionJDBCTemplate");

		// popUsers(connectionJDBCTemplate);

		// runAbstracts();
	}

	public void runAbstracts() {
		Tester1 t1a = new Tester1();
		Tester1 t1b = new Tester1();

		t1a.tryThis();
		t1a.tryThis();
		t1a.tryThis();
		t1a.doExtra();
		t1b.tryThis();

		t1a.met1();
		t1a.met2();
		t1a.met3();
		t1a.met4();

	}

	private void popUsers(ConnectionJDBCTemplate connectionJDBCTemplate) {
		// Delete all after 1
		System.out.println("------Records Reset--------");
		List<User> users = connectionJDBCTemplate.listUsers();
		int numberOfRows = users.size();
		System.out.println("There are currently " + numberOfRows + " rows.");
		for (int i = 0; i < users.size(); i++) {
			System.out.println("Deleting index " + i);
			Long id = users.get(i).getId();
			connectionJDBCTemplate.deleteUser(id);
			System.out.println("Deleted id = " + id);
		}

		System.out.println("------Records Creation--------");

		User c1 = new User(0l, "Mike", "Cleary", "1985-05-01", "mike@cleary.net", "111111");
		connectionJDBCTemplate.createUser(c1);

		User c2 = new User(0l, "Mary", "Cleary", "1985-05-02", "mary@cleary.net", "222222");
		connectionJDBCTemplate.createUser(c2);

		User c3 = new User(0l, "Lucy", "Cleary", "1985-05-03", "lucy@cleary.net", "333333");
		connectionJDBCTemplate.createUser(c3);

		User c4 = new User(0l, "John", "Cleary", "1985-05-04", "john@cleary.net", "444444");
		connectionJDBCTemplate.createUser(c4);

		User c5 = new User(0l, "Anne", "Cleary", "1985-05-05", "anne@cleary.net", "555555");
		connectionJDBCTemplate.createUser(c5);

		User c6 = new User(0l, "Paul", "Cleary", "1985-05-06", "paul@cleary.net", "666666");
		connectionJDBCTemplate.createUser(c6);

		User c7 = new User(0l, "Jack", "Cleary", "1985-05-07", "jack@cleary.net", "777777");
		connectionJDBCTemplate.createUser(c7);

		User c8 = new User(0l, "Lara", "Cleary", "1985-05-08", "lara@cleary.net", "888888");
		connectionJDBCTemplate.createUser(c8);

		User c9 = new User(0l, "Dave", "Sugrue", "1985-05-09", "dave.sugrue@gmail.com", "888888");
		connectionJDBCTemplate.createUser(c9);

		System.out.println("------Listing Multiple Records--------");
		users = connectionJDBCTemplate.listUsers();
		for (User record : users) {
			System.out.print("ID : " + record.getId());
			System.out.print(", First Name : " + record.getFirstName());
			System.out.println(", Last Name : " + record.getLastName());
		}

		User userTobBeUpdate = users.get(6);
		userTobBeUpdate.setPw("999999");
		System.out.println("----Updating Record with ID = " + userTobBeUpdate.getId() + " -----");
		connectionJDBCTemplate.updateUser(userTobBeUpdate);

		System.out.println("----Updated Record with ID = " + userTobBeUpdate.getId() + " -----");
		User user = connectionJDBCTemplate.getUser(userTobBeUpdate.getId());
		System.out.print("ID : " + user.getId());
		System.out.print(", First Name : " + user.getFirstName());
		System.out.println(", Last Name : " + user.getLastName());

		System.out.println("#####     AND, WE'RE DONE !!!     #####");
	}
}