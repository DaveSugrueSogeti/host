package ie.sugrue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/users")
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

	@RequestMapping("/{id}")
	public ResponseWrapper user(@PathVariable int id) {
		return getUserService.getUser(resp, id);
	}

	/*
	 * @RequestMapping(value = "/user/create", method = RequestMethod.POST) public void createUser(@ModelAttribute User user, Model model) {
	 * System.out.println("Posting..."); model.addAttribute("user", user); MySQLUserRepositoryImpl.createUser(user); return; }
	 */

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseWrapper create(@RequestBody User user) {
		log.info("CREATING -> {}", user);

		return createUserService.createUser(resp, user);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseWrapper updateUser(@RequestBody User user) {
		log.info("UPDATING -> {}", user);

		return updateUserService.updateUser(resp, user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseWrapper deleteUser(@PathVariable String id) {
		log.info("DELETING User with id -> {}", id);
		return deleteUserService.deleteUser(resp, id);
	}
}