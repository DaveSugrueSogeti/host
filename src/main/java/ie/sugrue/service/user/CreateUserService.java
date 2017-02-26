package ie.sugrue.service.user;

import ie.sugrue.domain.Status;
import ie.sugrue.domain.User;
import ie.sugrue.service.Service;

public interface CreateUserService extends Service {

	Status createUser(User user, Status status);

}
