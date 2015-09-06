package cs320.Homework2;

public class CoffeeUsers {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String zipCode;
	
	
	public CoffeeUsers(String userName, String password, String firstName,
			String lastName, String zipCode) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.zipCode = zipCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public boolean isUser(String userName, String password){
		return this.userName.trim().equals(userName) && this.password.trim().equals(password);
	}
}
