package ie.sugrue.utils;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.Status;
import ie.sugrue.domain.User;

public class LoginUtils {

	public static ResponseWrapper validateLogin(User user, String givenPassword) {
		ResponseWrapper resp = new ResponseWrapper();

		if (user.getPw().equals(givenPassword)) {
			resp.addObject(user);
		} else {
			Status status = new Status(1, "Username or Password are incorrect. Please try again");
			resp.setStatus(status);
		}

		return resp;
	}

}
