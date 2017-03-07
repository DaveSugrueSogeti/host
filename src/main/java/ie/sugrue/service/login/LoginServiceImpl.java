package ie.sugrue.service.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.Status;
import ie.sugrue.domain.User;
import ie.sugrue.repository.UserRepository;

@Service("loginService")
@Scope("prototype")
public class LoginServiceImpl implements LoginService {

	private final Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository	userRepo;

	@Override
	public ResponseWrapper login(ResponseWrapper resp, User user) {

		User storedUser = userRepo.getUser(user.getEmail());

		if (validateLogin(user.getPw(), storedUser.getPw())) {
			resp.addObject(storedUser);
		} else {
			Status status = new Status(1, "Username or Password are incorrect. Please try again");
			resp.setStatus(status);
			resp.addObject(user);
		}

		return resp;
	}

	@Override
	public Boolean validateLogin(String password, String storedPassword) {
		return password.equals(storedPassword);
	}

}
