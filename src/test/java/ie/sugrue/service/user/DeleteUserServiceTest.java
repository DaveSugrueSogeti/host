package ie.sugrue.service.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
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
public class DeleteUserServiceTest {

	@Configuration
	static class UserServiceTestContextConfiguration {
		@Bean
		public DeleteUserService deleteUserService() {
			return new DeleteUserServiceImpl();
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

	private final Logger		log	= LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DeleteUserService	deleteUserService;
	@Autowired
	private UserRepository		userRepo;

	private ResponseWrapper		resp;
	private User				user1;
	private User				user2;
	private Date				dob;

	@Before
	public void setup() {
		resp = new ResponseWrapper();

		dob = Date.valueOf("1985-05-01");
		user1 = new User(1, "John", "Doe", dob, "john@doe.ie", "123456");
		dob = Date.valueOf("1985-05-02");
		user2 = new User(2, "Jane", "Doe", dob, "jane@doe.ie", "123456");

		doNothing().when(userRepo).deleteUser(1);
		doThrow(new EmptyResultDataAccessException(0)).when(userRepo).deleteUser(2);
	}

	@After
	public void tearDown() {
		Mockito.reset(userRepo);
	}

	@Test
	public void testDeleteUserByIdSuccess() {
		resp = deleteUserService.deleteUser(resp, user1);
		assertEquals(0, resp.getStatus().getCode());
	}

	@Test
	public void testDeleteNonExistantUserById() {
		resp = deleteUserService.deleteUser(resp, user2);
		assertEquals(1, resp.getStatus().getCode());
	}

	@Test
	public void testDeleteUnkownUserById() {
		User user3 = new User();
		resp = deleteUserService.deleteUser(resp, user3);
		assertEquals(1, resp.getStatus().getCode());
		assertEquals("I'm not sure what User you are trying to delete. Please try again.", resp.getStatus().getMessages().get(0));
	}

	@Test
	public void testDeleteUserByEmailSuccess() {
		user1.setId(0);
		resp = deleteUserService.deleteUser(resp, user1);
		assertEquals(0, resp.getStatus().getCode());
	}

	@Test
	public void testDeleteNonExistantUserByEmail() {
		user1.setId(0);
		resp = deleteUserService.deleteUser(resp, user2);
		assertEquals(1, resp.getStatus().getCode());
	}

}
