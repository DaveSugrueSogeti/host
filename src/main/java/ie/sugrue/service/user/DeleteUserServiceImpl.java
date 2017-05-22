package ie.sugrue.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.User;
import ie.sugrue.repository.UserRepository;

@Service("deleteUserService")
@Scope("prototype")
public class DeleteUserServiceImpl implements DeleteUserService {

	private final Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository	userRepo;

	@Override
	public ResponseWrapper deleteUser(ResponseWrapper resp, User user) {

		try {
			if (user.getId() > 0) {
				userRepo.deleteUser(user.getId());
			} else if (null != user.getEmail()) {
				userRepo.deleteUser(user.getEmail());
			} else {
				log.error("Cannot identify user to be deleted when trying to delete {} from Database", user);
				resp.getStatus().updateStatus(1, "I'm not sure what User you are trying to delete. Please try again.");
			}
		} catch (EmptyResultDataAccessException erdae) {
			log.info("Problem occured deleting user with id of {} from DB - ", user.getId(), erdae);
			resp.getStatus().updateStatus(1, "User cannot be deleted as it does not exist.");
		} catch (DataAccessException dae) {
			log.error("Data Access exception encountered trying to delete {} from Database", user, dae);
			resp.getStatus().updateStatus(2, "We encountered a problem deleting user details from our database. Please try again.");
		} catch (Exception e) {
			log.error("Unknown exception encountered trying to delete {} to Database", user, e);
			resp.getStatus().updateStatus(2, "We encountered a problem deleting user details from our database. Please try again.");
		}

		return resp;
	}
}
