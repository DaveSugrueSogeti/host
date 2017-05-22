package ie.sugrue.service.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.User;
import ie.sugrue.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class GetUserServiceTest {

	@Configuration
	static class UserServiceTestContextConfiguration {
		@Bean
		public GetUserService getUserService() {
			return new GetUserServiceImpl();
		}

		@Bean
		public UserRepository userRepo() {
			return Mockito.mock(UserRepository.class);
		}

		@Bean
		public ResponseWrapper resp() {
			return Mockito.mock(ResponseWrapper.class);
		}
	}

	private final Logger	log	= LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GetUserService	getUserService;
	@Autowired
	private UserRepository	userRepo;

	private ResponseWrapper	resp;
	private User			user1;
	private User			user2;
	private Date			dob;

	@Before
	public void setup() {
		resp = new ResponseWrapper();

		dob = Date.valueOf("1985-05-01");
		user1 = new User(1, "John", "Doe", dob, "john@doe.ie", "123456");
		dob = Date.valueOf("1985-05-02");
		user2 = new User(2, "Jane", "Doe", dob, "jane@doe.ie", "123456");

		Mockito.when(userRepo.getUser(1)).thenReturn(user1);
		doThrow(new EmptyResultDataAccessException(0)).when(userRepo).getUser(2);
		Mockito.when(userRepo.getUser("john@doe.ie")).thenReturn(user1);
		doThrow(new EmptyResultDataAccessException(0)).when(userRepo).getUser("jane@doe.ie");
	}

	@After
	public void tearDown() {
		Mockito.reset(userRepo);
	}

	@Test
	public void testGetUserByIdSuccess() {
		resp = getUserService.getUser(resp, 1);
		assertEquals(0, resp.getStatus().getCode());
		assertEquals(user1, (User) resp.getObjects().get(0));
	}

	@Test
	public void testGetNonExistantUserById() {
		resp = getUserService.getUser(resp, 2);
		assertEquals(1, resp.getStatus().getCode());
	}

	@Test
	public void testGetUserByEmailSuccess() {
		user1.setId(0);
		resp = getUserService.getUser(resp, "john@doe.ie");
		assertEquals(0, resp.getStatus().getCode());
		assertEquals(user1, (User) resp.getObjects().get(0));
	}

	@Test
	public void testGetNonExistantUserByEmail() {
		user1.setId(0);
		resp = getUserService.getUser(resp, "jane@doe.ie");
		assertEquals(1, resp.getStatus().getCode());
	}

}
