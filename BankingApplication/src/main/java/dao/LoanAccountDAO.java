package dao;

import models.Account;

public interface LoanAccountDAO extends AccountDAO {

	// CREATE
	public boolean createAccount(Account a);

}
