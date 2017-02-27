package ie.sugrue.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.User;
import ie.sugrue.repository.MySQLUserRepositoryImpl;
import ie.sugrue.utils.LoginUtils;

@CrossOrigin(origins = "http://desktop:5000", maxAge = 3600)

@RestController
@RequestMapping("/")
public class LoginController extends PrimaryController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseWrapper login(@RequestBody User user) {
		System.out.println("validating for email = '" + user.getEmail() + "' and pw = '" + user.getPw() + "'");
		MySQLUserRepositoryImpl mySQLUserRepositoryImpl = (MySQLUserRepositoryImpl) context.getBean("MySQLUserRepositoryImpl");

		User storedUser = mySQLUserRepositoryImpl.getUser(user.getEmail());

		return LoginUtils.validateLogin(storedUser, user.getPw());
	}
}
