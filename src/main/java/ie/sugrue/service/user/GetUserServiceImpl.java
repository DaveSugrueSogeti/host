package ie.sugrue.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.Status;
import ie.sugrue.domain.User;
import ie.sugrue.repository.UserRepository;

@Service("getUserService")
@Scope("prototype")
public class GetUserServiceImpl implements GetUserService {

	private final Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository	userRepo;

	@Override
	public User getUser(long id) {
		// TODO catch exceptions
		return userRepo.getUser(id);
	}

	@Override
	public ResponseWrapper getUser(ResponseWrapper resp, long id) {

		try {
			User user = userRepo.getUser(id);
			resp.addObject(user);
		} catch (Exception e) {
			Status status = new Status(2, "Problem getting User data from DB");
			resp.setStatus(status);
		}

		return resp;
	}

	@Override
	public User getUser(String email) {
		// TODO catch exceptions
		return userRepo.getUser(email);
	}

	@Override
	public ResponseWrapper getUser(ResponseWrapper resp, String email) {

		try {
			User user = userRepo.getUser(email);
			resp.addObject(user);
		} catch (Exception e) {
			Status status = new Status(2, "Problem getting User data from DB");
			resp.setStatus(status);
		}

		return resp;
	}

}
