package models;

public class CheckingAccount extends Account {
	
	private int id;
	private double balance;
	private int userId;
	
	// no-arg constructor
	public CheckingAccount() {
		super();
	}
	
	// no-id constructor
	public CheckingAccount(double balance, int userId) {
		super();
		this.balance = balance;
		this.userId = userId;
	}

	// constructor
	public CheckingAccount(int id, double balance, int userId) {
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
		return "Checking Acct.#" + this.id + "  [Balance = $" + this.balance + ",  Belongs to User#" + this.userId + "]";
	}

}
