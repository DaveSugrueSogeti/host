package ie.sugrue.service.user;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.User;
import ie.sugrue.service.Service;

public interface GetUserService extends Service {

	public User getUser(long id);

	public ResponseWrapper getUser(ResponseWrapper resp, long id);

	public User getUser(String email);

	public ResponseWrapper getUser(ResponseWrapper resp, String email);
}
