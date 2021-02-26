package models;

import java.util.List;

public class SuperUser extends User {
	
	private int id;
	private String username;
	private String password;
	private List<Account> accounts;
	
	// no-arg constructor
	public SuperUser() {
		super();
	}
	
	// no-id constructor	
	public SuperUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	// constructor
	public SuperUser(int id, String username, String password) {
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

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "SuperUser#" + id + "  [Username: " + username + ",  Password: " + password + "]";
	}
	
}
