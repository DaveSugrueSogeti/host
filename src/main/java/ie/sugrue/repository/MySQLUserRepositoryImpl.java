package ie.sugrue.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ie.sugrue.domain.User;

@Repository
public class MySQLUserRepositoryImpl implements UserRepository {
	// private DataSource dataSource;
	// private JdbcTemplate jdbcTemplateObject;

	private final Logger	log	= LoggerFactory.getLogger(this.getClass());
	@Autowired
	protected JdbcTemplate	jdbc;
	// public void setDataSource(DataSource dataSource) {
	// this.dataSource = dataSource;
	// this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	// }

	public void createUser(User user) {
		String SQL = "insert into appUser ( firstName, lastName, dob, email, pw ) values (?, ?, ?, ?, ?)";

		jdbc.update(SQL, user.getFirstName(), user.getLastName(), user.getDOB(), user.getEmail(), user.getPw());
		log.debug("Created Record Name = " + user.getFirstName() + " " + user.getLastName());
		return;
	}

	public User getUser(long id) {
		String SQL = "select * from appUser where id = ?";
		User user = jdbc.queryForObject(SQL, new Object[] { id }, new UserMapper());
		return user;
	}

	public User getUser(String emailAddress) {
		String SQL = "select * from appUser where email = ?";
		User user = jdbc.queryForObject(SQL, new Object[] { emailAddress }, new UserMapper());
		return user;
	}

	public List<User> listUsers() {
		String SQL = "select * from appUser";
		List<User> users = jdbc.query(SQL, new UserMapper());
		return users;
	}

	public void deleteUser(long id) {
		String SQL = "delete from appUser where id = ?";
		jdbc.update(SQL, id);
		log.debug("Deleted Record with ID = " + id);
		return;
	}

	public User updateUser(User user) {
		user = populateUserNullsWithdefaults(user);

		String SQL = "update appUser set firstName = ?, lastName = ?, dob = ?, email = ?, pw = ? where id = ?";

		jdbc.update(SQL, user.getFirstName(), user.getLastName(), user.getDOB(), user.getEmail(), user.getPw(), user.getId());

		log.debug("Updated Record with ID = " + user.getId());
		return getUser(user.getId());
	}

	private User populateUserNullsWithdefaults(User user) {

		User previousUserDetails = getUser(user.getId());

		if (isNull(user.getDOB())) {
			user.setDOB(previousUserDetails.getDOB());
		}
		if (isNull(user.getEmail())) {
			user.setEmail(previousUserDetails.getEmail());
		}
		if (isNull(user.getFirstName())) {
			user.setFirstName(previousUserDetails.getFirstName());
		}
		if (isNull(user.getLastName())) {
			user.setLastName(previousUserDetails.getLastName());
		}
		if (isNull(user.getPw())) {
			user.setPw(previousUserDetails.getPw());
		}

		return user;
	}

	public Boolean isNotNull(Object val) {
		if (null == val) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean isNull(Object val) {
		return !isNotNull(val);
	}
}