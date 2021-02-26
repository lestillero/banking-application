package dao;

import models.Account;

public interface CheckingAccountDAO extends AccountDAO {

	// CREATE
	public boolean createAccount(Account a);

}
