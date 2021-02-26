package dao;

import models.Account;

public interface SavingsAccountDAO extends AccountDAO {

	// CREATE
	public boolean createAccount(Account a);

}
