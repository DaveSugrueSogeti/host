package ie.sugrue.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

import ie.sugrue.domain.Status;
import ie.sugrue.domain.User;
import ie.sugrue.service.Service;

public class CreateUserService extends Service {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public Status createUser(User user, Status status) {

		try {
			connectionJDBCTemplate.createUser(user);
		} catch (DuplicateKeyException dk) {
			log.info("Duplicate key on email address: {}", user.getEmail());
			status.updateStatus(1, "The email address '" + user.getEmail() + "' is already in use.");
		} catch (DataAccessException dae) {
			log.error("Data Access exception encountered trying to save {} to Database", user, dae);
			status.updateStatus(2, "We encountered a problem storing your details to our database. Please try again.");
		} catch (Exception e) {
			log.error("Unknown exception encountered trying to save {} to Database", user, e);
			status.updateStatus(2, "We encountered a problem storing your details to our database. Please try again.");
		}

		return status;
	}

}
