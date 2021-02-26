package models;

public class User {

	private int id;
	private String username;
	private String password;
	
	// no-arg constructor
	public User() {
		super();
	}
	
	// no-id constructor	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	// constructor
	public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	// getters and setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User#" + id + "  [Username: " + username + ",  Password: " + password + "]";
	}
	
}
