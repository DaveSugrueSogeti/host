package ie.sugrue.service.user;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.User;
import ie.sugrue.service.Service;

public interface CreateUserService extends Service {

	ResponseWrapper createUser(ResponseWrapper resp, User user);

}
