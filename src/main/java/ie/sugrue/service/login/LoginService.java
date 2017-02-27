package ie.sugrue.service.login;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.User;
import ie.sugrue.service.Service;

public interface LoginService extends Service {

	public ResponseWrapper login(ResponseWrapper resp, User user);

	public Boolean validateLogin(String password, String storedPassword);
}
