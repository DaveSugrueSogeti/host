package ie.sugrue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.User;
import ie.sugrue.service.user.CreateUserService;
import ie.sugrue.service.user.DeleteUserService;
import ie.sugrue.service.user.GetUserService;
import ie.sugrue.service.user.UpdateUserService;

@CrossOrigin(origins = "http://desktop:5000", maxAge = 3600)

@RestController
@RequestMapping("/user")
@Scope("request")
public class UserController extends PrimaryController {

	private final Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	ResponseWrapper			resp;
	@Autowired
	GetUserService			getUserService;
	@Autowired
	CreateUserService		createUserService;
	@Autowired
	UpdateUserService		updateUserService;
	@Autowired
	DeleteUserService		deleteUserService;

	@RequestMapping("")
	public ResponseWrapper user(@RequestParam(value = "id", defaultValue = "1") long id) {
		return getUserService.getUser(resp, id);
	}

	/*
	 * @RequestMapping(value = "/user/create", method = RequestMethod.POST) public void createUser(@ModelAttribute User user, Model model) {
	 * System.out.println("Posting..."); model.addAttribute("user", user); MySQLUserRepositoryImpl.createUser(user); return; }
	 */

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseWrapper create(@RequestBody User user) {
		log.info("CREATING -> {}", user);

		return createUserService.createUser(resp, user);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseWrapper updateUser(@RequestBody User user) {
		log.info("UPDATING -> {}", user);

		return updateUserService.updateUser(resp, user);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseWrapper deleteUser(@RequestBody User user) {
		log.info("DELETING -> {}", user);
		return deleteUserService.deleteUser(resp, user);
	}
}