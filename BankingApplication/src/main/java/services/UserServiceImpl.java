package services;

import java.util.List;
import dao.AccountDAO;
import dao.AccountDAOImpl;
import dao.CheckingAccountDAO;
import dao.CheckingAccountDAOImpl;
import dao.LoanAccountDAO;
import dao.LoanAccountDAOImpl;
import dao.SavingsAccountDAO;
import dao.SavingsAccountDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import models.Account;
import models.CheckingAccount;
import models.LoanAccount;
import models.SavingsAccount;
import models.User;

public class UserServiceImpl implements UserService {

	public UserDAO udao = new UserDAOImpl();
	public AccountDAO adao = new AccountDAOImpl();
	public SavingsAccountDAO sadao = new SavingsAccountDAOImpl();
	public CheckingAccountDAO cadao = new CheckingAccountDAOImpl();
	public LoanAccountDAO ladao = new LoanAccountDAOImpl();

	/**
	 * Checks input id against list of user ids queried from users table
	 */
	public boolean checkUserId(int userId) {
		List<Integer> validUserIds = udao.viewAllUserIds();
		return validUserIds.contains(userId);
	}

	/**
	 * Checks input username against list of usernames queried from users table
	 */
	public boolean checkUsername(String username) {
		List<String> validUsernames = udao.viewAllUsernames();
		return validUsernames.contains(username);
	}

	/**
	 * Checks input password against username given
	 */
	public boolean checkPassword(String username, String password) {
		User u = udao.viewUser(username);
		return (u.getPassword().equals(password));
	}

	/**
	 * Creates new user and tells DAO to add it to database
	 */
	public boolean registerUser(String username, String password) {

		try {

			User u = new User();
			u.setUsername(username);
			u.setPassword(password);

			return udao.createUser(u);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Returns User associated with username
	 */
	public User viewUser(int userId) {
		return udao.viewUser(userId);
	}

	/**
	 * Returns User associated with username
	 */
	public User viewUser(String username) {
		return udao.viewUser(username);
	}

	/**
	 * Returns list of accounts associated with user
	 */
	public List<Account> viewAccounts(int userId) {
		return adao.viewTheirAccounts(userId);
	}

	/**
	 * Returns list of IDs of accounts associated with user
	 */
	public List<Integer> viewAccountIds(int userId) {
		return adao.viewTheirAccountIds(userId);
	}

	/**
	 * Checks input accountId to see if it exists and belongs to user given userId
	 */
	public boolean checkAccountId(int accountId, int userId) {
		List<Integer> validAccountIds = adao.viewTheirAccountIds(userId);
		return validAccountIds.contains(accountId);
	}

	/**
	 * Returns account given the account id (currently shows id, type, balance, and
	 * userid)
	 */
	public Account viewAccount(int accountId) {
		return adao.viewAccount(accountId);
	}

	/**
	 * Adds input amount to account given account id
	 */
	public boolean makeDeposit(int accountId, Double amount) {

		Account a = adao.viewAccount(accountId);

		if (amount < 0) {

			System.out.println("Cannot deposit a negative amount!");
			return false;

		} else if (adao.viewAccount(accountId) instanceof SavingsAccount) {

			a.setBalance(a.getBalance() + amount);
			adao.updateAccount(a);

			System.out.println(amount + " successfully deposited into Acct.#" + accountId);
			System.out.println("");
			System.out.println("UPDATED ACCOUNT DETAILS");
			System.out.println(viewAccount(accountId));

			return true;

		} else if (adao.viewAccount(accountId) instanceof CheckingAccount) {

			a.setBalance(a.getBalance() + amount);
			adao.updateAccount(a);

			System.out.println(amount + " successfully deposited into Acct.#" + accountId);
			System.out.println("");
			System.out.println("UPDATED ACCOUNT DETAILS");
			System.out.println(viewAccount(accountId));

			return true;

		} else if (adao.viewAccount(accountId) instanceof LoanAccount) {

			if (a.getBalance() + amount >= 0) {

				a.setBalance(0.0);
				adao.deleteAccount(accountId);
				System.out.println("Loan is paid off!");
				System.out.println("Loan Acct.#" + accountId + " has been removed from system");
				
				return true;
				
			} else {

				a.setBalance(a.getBalance() + amount);
				adao.updateAccount(a);

				System.out.println(amount + " successfully paid towards Acct.#" + accountId);
				System.out.println("");
				System.out.println("UPDATED ACCOUNT DETAILS");
				System.out.println(viewAccount(accountId));

				return true;

			}

		} else {

			return false;

		}

	}

	/**
	 * Subtracts input amount from account given account id
	 */
	public boolean makeWithdrawal(int accountId, Double amount) {

		Account a = adao.viewAccount(accountId);

		if (amount < 0) {

			System.out.println("Cannot withdraw a negative amount!");
			return false;

		} else if (adao.viewAccount(accountId) instanceof SavingsAccount) {

			if (a.getBalance() - amount < 0) {
				
				System.out.println("Not enough funds to withdraw amount");
				return false;

			} else {

				a.setBalance(a.getBalance() - amount);
				adao.updateAccount(a);

				System.out.println(amount + " successfully withdrew from Acct.#" + accountId);
				System.out.println("");
				System.out.println("UPDATED ACCOUNT DETAILS");
				System.out.println(viewAccount(accountId));

				return true;

			}

		} else if (adao.viewAccount(accountId) instanceof CheckingAccount) {

			if (a.getBalance() - amount < 0) {
				
				a.setBalance(a.getBalance() - amount - 20);
				adao.updateAccount(a);
				
				System.out.println("Overdraft fee of $20 incurred!");
				System.out.println("");
				System.out.println("UPDATED ACCOUNT DETAILS");
				System.out.println(viewAccount(accountId));

				return true;

			} else {

				a.setBalance(a.getBalance() - amount);
				adao.updateAccount(a);

				System.out.println(amount + " successfully withdrew from Acct.#" + accountId);
				System.out.println("");
				System.out.println("UPDATED ACCOUNT DETAILS");
				System.out.println(viewAccount(accountId));

				return true;

			}

		} else if (adao.viewAccount(accountId) instanceof LoanAccount) {

			a.setBalance(a.getBalance() - amount);
			adao.updateAccount(a);

			System.out.println(amount + " successfully borrowed from Acct.#" + accountId);
			System.out.println("");
			System.out.println("UPDATED ACCOUNT DETAILS");
			System.out.println(viewAccount(accountId));

			return true;

		} else {

			return false;

		}

	}

	/**
	 * Creates new SavingsAccount for given userid
	 */
	public boolean openSavingsAccount(Double amount, int userId) {
		SavingsAccount sa = new SavingsAccount();
		sa.setBalance(amount);
		sa.setUserId(userId);
		return sadao.createAccount(sa);
	}

	/**
	 * Creates new CheckingAccount for given userid
	 */
	public boolean openCheckingAccount(Double amount, int userId) {
		CheckingAccount ca = new CheckingAccount();
		ca.setBalance(amount);
		ca.setUserId(userId);
		return cadao.createAccount(ca);
	}

	/**
	 * Creates new LoanAccount for given userid
	 */
	public boolean openLoanAccount(Double amount, int userId) {
		LoanAccount la = new LoanAccount();
		la.setBalance(amount);
		la.setUserId(userId);
		return ladao.createAccount(la);
	}

	/**
	 * Checks if account is empty
	 */
	public boolean isEmpty(int accountId) {
		Account a = adao.viewAccount(accountId);

		if (a.getBalance() == 0.0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Tells DAO to delete account from database given accountid
	 */
	public boolean deleteAccount(int accountId) {
		return adao.deleteAccount(accountId);
	}

}