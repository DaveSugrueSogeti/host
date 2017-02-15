package ie.sugrue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.sugrue.domain.Greeting;
import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.Status;
import ie.sugrue.domain.User;
import ie.sugrue.service.user.CreateUserService;

@CrossOrigin(origins = "http://desktop:5000", maxAge = 3600)

@RestController
@RequestMapping("/user")
public class UserController extends PrimaryController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	// public ResponseWrapper user(@RequestParam(value = "id", defaultValue =
	// "1") long id) {
	public ResponseWrapper user(@PathVariable long id) {

		ResponseWrapper resp = new ResponseWrapper();

		try {
			User user = connectionJDBCTemplate.getUser(id);
			Greeting greeting = new Greeting(5, "Hell ya");

			resp.addObject(user);
			resp.addObject(greeting);
		} catch (Exception e) {
			Status status = new Status(2, "Problem getting User data from DB");
			resp.setStatus(status);
		}

		return resp;
	}

	@RequestMapping("/user/delete")
	public long deleteUser(@RequestParam(value = "id", defaultValue = "1") long id) {
		connectionJDBCTemplate.deleteUser(id);

		return id;
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public void updateUser(@ModelAttribute User user, Model model) {
		System.out.println("Posting...");
		model.addAttribute("user", user);
		connectionJDBCTemplate.updateUser(user);
		return;
	}

	/*
	 * @RequestMapping(value = "/user/create", method = RequestMethod.POST) public void createUser(@ModelAttribute User user, Model model) { System.out.println("Posting...");
	 * model.addAttribute("user", user); connectionJDBCTemplate.createUser(user); return; }
	 */

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseWrapper create(@RequestBody User user) {
		log.info("Here is my user object -> {}", user);
		ResponseWrapper resp = new ResponseWrapper();
		CreateUserService createUserService = new CreateUserService();

		resp.setStatus(createUserService.createUser(user, resp.getStatus()));

		log.info("Returning :: {}", resp);
		return resp;
	}

}