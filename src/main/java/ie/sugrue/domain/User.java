package ie.sugrue.domain;

import java.sql.Date;

public class User {
	private long	id;
	private String	firstName, lastName, email, pw;
	private Date	dob;

	public User() {

	}

	public User(long id, String firstName, String lastName, Date dateOfBirth, String email, String pw) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dateOfBirth;
		this.email = email;
		this.pw = pw;
	}

	public User(String firstName, String lastName, Date dateOfBirth, String email, String pw) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dateOfBirth;
		this.email = email;
		this.pw = pw;
	}

	public User(long id, String firstName, String lastName, String dobString, String email, String pw) {
		this(id, firstName, lastName, Date.valueOf(dobString), email, pw);
	}

	public User(String firstName, String lastName, String dobString, String email, String pw) {
		this(firstName, lastName, Date.valueOf(dobString), email, pw);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Date getDOB() {
		return dob;
	}

	public void setDOB(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", pw=" + pw + ", dob=" + dob + "]";
	}

}