package services;

import java.util.List;
import models.Account;
import models.User;

public interface UserService {
	
	public boolean checkUserId(int userId);
	
	public boolean checkUsername(String username);
	public boolean checkPassword(String username, String password);
	public boolean registerUser(String username, String password);
	
	public User viewUser(int userId);
	public User viewUser(String username);
	public List<Account> viewAccounts(int userId);
	public List<Integer> viewAccountIds(int userId);
	
	public boolean checkAccountId(int accountId, int userId);
	
	public Account viewAccount(int accountId);
	public boolean makeDeposit(int accountId, Double amount);
	public boolean makeWithdrawal(int accountId, Double amount);
	
	public boolean openSavingsAccount(Double amount, int userId);
	public boolean openCheckingAccount(Double amount, int userId);
	public boolean openLoanAccount(Double amount, int userId);
	
	public boolean isEmpty(int accountId);
	public boolean deleteAccount(int accountId);
	
}
