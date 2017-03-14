package ie.sugrue.service.user;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.User;

public interface DeleteUserService {

	ResponseWrapper deleteUser(ResponseWrapper resp, User user);

}
