package ie.sugrue.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.Status;
import ie.sugrue.domain.User;

public class GetUserServiceImpl implements GetUserService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public User getUser(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseWrapper getUser(ResponseWrapper resp, long id) {

		try {
			User user = mySQLUserRepositoryImpl.getUser(id);
			resp.addObject(user);
		} catch (Exception e) {
			Status status = new Status(2, "Problem getting User data from DB");
			resp.setStatus(status);
		}

		return resp;
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseWrapper getUser(ResponseWrapper resp, String email) {

		try {
			User user = mySQLUserRepositoryImpl.getUser(email);
			resp.addObject(user);
		} catch (Exception e) {
			Status status = new Status(2, "Problem getting User data from DB");
			resp.setStatus(status);
		}

		return resp;
	}

}
