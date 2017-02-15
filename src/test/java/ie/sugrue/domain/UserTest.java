package ie.sugrue.domain;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private Date	dob;
	private User	c;

	@Before
	public void setUp() throws Exception {
		dob = Date.valueOf("1985-05-01");
		c = new User(1l, "Mike", "Cleary", dob, "mike@cleary.net", "111111");
	}

	@After
	public void tearDown() throws Exception {
		c = null;
	}

	@Test
	public void testUserLongStringStringDateStringString() {
		assertEquals(1l, c.getId());
		assertEquals("Mike", c.getFirstName());
		assertEquals("Cleary", c.getLastName());
		assertEquals(dob, c.getDOB());
		assertEquals("mike@cleary.net", c.getEmail());
		assertEquals("111111", c.getPw());
	}

	@Test
	public void testToString() {
		assertEquals("User[id=1, firstName='Mike', lastName='Cleary', dob='1985-05-01', email='mike@cleary.net', pw='111111']", c.toString());
	}

}
