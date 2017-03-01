package ie.sugrue.service.user;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/test-context.xml")
@Rollback
public class CreateUserServiceImplTest {

	private CreateUserService	createUser;
	private ResponseWrapper		resp;
	private final Logger		log	= LoggerFactory.getLogger(this.getClass());

	@Before
	public void setUp() throws Exception {
		createUser = new CreateUserServiceImpl();
		resp = new ResponseWrapper();
	}

	@After
	public void tearDown() throws Exception {
		createUser = null;
		resp = null;
	}

	@Test
	@Transactional
	public void testCreateUser() {
		User user = new User(1l, "Mike", "Cleary", "1981-01-01", "mike99@cleary.net", "111111");
		resp = createUser.createUser(resp, user);
		assertEquals(0, resp.getStatus().getCode());
		resp = createUser.createUser(resp, user);
		assertEquals(1, resp.getStatus().getCode());
		assertEquals("The email address 'mike99@cleary.net' is already in use.", resp.getStatus().getMessages().get(0));
	}
}
