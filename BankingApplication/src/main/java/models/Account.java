package models;

public abstract class Account {
	
	private int id;
	private double balance;
	private int userId;
	
	// no-arg constructor
	public Account() {
		super();
	}
	
	// no-id constructor
	public Account(double balance, int userId) {
		super();
		this.balance = balance;
		this.userId = userId;
	}
	
	// constructor	
	public Account(int id, double balance, int userId) {
		super();
		this.id = id;
		this.balance = balance;
		this.userId = userId;
	}

	// getters and setters
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Account [id=" + this.id + ", balance=" + this.balance + ", userId=" + this.userId + "]";
	}

}
