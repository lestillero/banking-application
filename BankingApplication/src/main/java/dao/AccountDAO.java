package dao;

import java.util.List;

import models.Account;
import models.User;

public interface AccountDAO {
	
	// CREATE
	// -- Cannot create generic Account
	// -- Must create CheckingAccount, LoanAccount, or SavingsAccount
	
	// READ
	public Account viewAccount(int id);
	public List<Account> viewAllAccounts();
	public List<Account> viewTheirAccounts(int userId);
	public List<Integer> viewTheirAccountIds(int userId);
	
	// UPDATE
	public boolean updateAccount(Account a);
	
	// DELETE
	public boolean deleteAccount(int id);

}
