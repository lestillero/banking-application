package services;

import java.util.List;
import java.util.Scanner;
import models.Account;
import models.CheckingAccount;
import models.LoanAccount;
import models.SavingsAccount;
import models.SuperUser;
import models.User;

public class MenuImpl implements Menu {

	static Scanner scanner = new Scanner(System.in);
	static UserService us = new UserServiceImpl();
	static SuperUserService ss = new SuperUserServiceImpl();

	// Sets up variable "loggedInUser" to be whoever logs into the application
	// Only one User can log in at a time
	static User loggedInUser = new User();

	// ------------------------------------------------------------------------
	// PROMPTS (Major Menus)
	// ------------------------------------------------------------------------
	
	/**
	 * Prompts user to log in with valid username or register as a new user
	 * Should throw username not found exception
	 */
	public void signInPrompt() {
		System.out.println("Please choose a Sign-In option below:");
		System.out.println("1.  Log in with username");
		System.out.println("2.  Register new user");

		// Read user's sign-in choice and react
		String input = scanner.nextLine();

		// OPTION 1: LOG IN WITH USERNAME
		if (input.equals("1")) {
			logInWithUsername();

		// OPTION 2: REGISTER NEW USER
		} else if (input.equals("2")) {
			registerNewUser();

		// If input is not specifically "1" or "2", give user clearer directions
		} else {
			System.out.println("Command unclear");
			System.out.println("Please type the number 1 or 2");
			System.out.println("");
			signInPrompt();
		}
	}

	/**
	 * Offers options to user
	 */
	public void userPrompt() {
		System.out.println("Please choose a User option below:");
		System.out.println("1.  View my accounts");
		System.out.println("2.  Make deposit or withdrawal");
		System.out.println("3.  Open new account");
		System.out.println("4.  Close account");
		System.out.println("5.  Log out");

		String input = scanner.nextLine();

		// OPTION 1: VIEW MY ACCOUNTS
		if (input.equals("1")) {
			viewMyAccounts();

		// OPTION 2: MAKE DEPOSIT OR WITHDRAWAL
		} else if (input.equals("2")) {
			makeTransaction();

		// OPTION 3: OPEN NEW ACCOUNT
		} else if (input.equals("3")) {
			openNewAccount();

		// OPTION 4: CLOSE ACCOUNT
		} else if (input.equals("4")) {
			closeAccount();

		// OPTION 5: LOG OUT
		} else if (input.equals("5")) {
			logOut();

		// If input is not a number from 1-5, give user clearer directions
		} else {
			System.out.println("Command not recognized");
			System.out.println("Please type the number 1, 2, 3, 4, or 5");
			System.out.println("");
			userPrompt();
		}
	}
	
	/**
	 * Offers options to superuser
	 */
	public void superUserPrompt() {
		System.out.println("Please choose a SuperUser option below:");
		System.out.println("1.  View all users");
		System.out.println("2.  View all accounts");
		System.out.println("3.  Create new user");
		System.out.println("4.  Delete user");
		System.out.println("5.  Log out");

		String input = scanner.nextLine();

		// OPTION 1: VIEW ALL USERS
		if (input.equals("1")) {
			viewAllUsers();

		// OPTION 2: VIEW ALL ACCOUNTS
		} else if (input.equals("2")) {
			viewAllAccounts();

		// OPTION 3: CREATE NEW USER
		} else if (input.equals("3")) {
			registerNewUser();

		// OPTION 4: DELETE USER
		} else if (input.equals("4")) {
			deleteUser();

		// OPTION 5: LOG OUT
		} else if (input.equals("5")) {
			logOut();

		} else {
			System.out.println("Command not recognized");
			System.out.println("Please type the number 1, 2, 3, 4, or 5");
			System.out.println("");
			superUserPrompt();
		}
	}

	/**
	 * Gives superuser access to EITHER user OR superuser functionality
	 */
	public void managerPrompt() {
		System.out.println("Please choose a Manager option below:");
		System.out.println("1.  Manage my accounts");
		System.out.println("2.  Manage bank accounts");

		String input = scanner.nextLine();

		if (input.equals("1")) {
			userPrompt();
		} else if (input.equals("2")) {
			superUserPrompt();
		} else {

			System.out.println("Command not recognized");
			System.out.println("Please type the number 1 or 2");
			System.out.println("");
			managerPrompt();
		}
	}

	// ------------------------------------------------------------------------
	// TASKS (Minor Menus)
	// ------------------------------------------------------------------------

	/**
	 * Closes account as long as its balance is 0
	 */
	public void closeAccount() {

		displayMyAccounts();
		
		System.out.println("");
		System.out.println("Please type ID# of account you wish to close");

		String inputAccountIdString = scanner.nextLine();
		int inputAccountId = Integer.parseInt(inputAccountIdString);
		
		try {

			if (us.checkAccountId(inputAccountId, loggedInUser.getId())) {
	
				System.out.println("Account found!");
				if (us.isEmpty(inputAccountId)) {
					us.deleteAccount(inputAccountId);
					System.out.println("Acct.#" + inputAccountId + " successfully closed!");
					System.out.println("");
					userPrompt();
	
				} else {
					System.out.println("Account must be empty to be closed");
					System.out.println("");
					userPrompt();
				}
	
			}
		
		} catch (Exception e) {
			System.out.println("ID# not recognized");
			userPrompt();
		}
		
	}
	
	/**
	 * Deletes user from the system FUNCTIONALITY RESTRICTED TO SUPERUSERS
	 */
	public void deleteUser() {

		displayAllUsers();
		
		System.out.println("");
		System.out.println("Enter ID# of user you wish to delete");

		String inputUserIdString = scanner.nextLine();
		int inputUserId = Integer.parseInt(inputUserIdString);
		
		try {

			if (us.checkUserId(inputUserId)) {
	
				System.out.println("User found!");
				System.out.println(us.viewUser(inputUserId));
				System.out.println("");
				System.out.println("Are you sure you wish to proceed?");
				System.out.println("1.  Yes, delete user");
				System.out.println("2.  No, do NOT delete user");
	
				String inputDeleteUserCheck = scanner.nextLine();
	
				if (inputDeleteUserCheck.equals("1")) {
					ss.deleteUser(inputUserId);
					System.out.println("User#" + inputUserId + " successfully deleted!");
					System.out.println("");
					superUserPrompt();
	
				} else if (inputDeleteUserCheck.equals("2")) {
					System.out.println("User not deleted");
					System.out.println("");
					superUserPrompt();
	
				} else {
					System.out.println("Instruction unclear");
					System.out.println("Please type the number 1 or 2");
					System.out.println("");
					superUserPrompt();
	
				}
	
			} else {
	
				System.out.println("User not found!");
				System.out.println("");
				superUserPrompt();
	
			}
		
		} catch (Exception e) {
			System.out.println("ID# not recognized");
			superUserPrompt();
		}

	}
	
	/**
	 * Attempts to log in and assign given credentials to variable loggedInUser
	 * - Check if username is in the system
	 * - Check if password matches username in the system
	 * - If username and password pass checks, store user info in variable
	 * - Check if username belongs to superuser or user
	 */
	public void logInWithUsername() {
		
		System.out.println("Enter username");
		String username = scanner.nextLine();

		if (us.checkUsername(username)) {
			System.out.println("Enter password");
			String password = scanner.nextLine();

			if (us.checkPassword(username, password)) {
				loggedInUser = us.viewUser(username);

				if (ss.checkSuperUsername(username)) {
					System.out.println("SuperUser successfully signed in!");
					System.out.println("Welcome back, " + username);
					System.out.println("");
					managerPrompt();
				} else {
					System.out.println("User successfully signed in!");
					System.out.println("Welcome back, " + username);
					System.out.println("");
					userPrompt();
				}

			} else {
				System.out.println("Password does not match record");
				System.out.println("");
				signInPrompt();
			}

		} else {
			System.out.println("Username not found");
			System.out.println("");
			signInPrompt();
		}
		
	}

	/**
	 * Logs user out of session
	 * - Menu for user options goes away
	 * - Brings back to "main page" (sign-in menu)
	 */
	public void logOut() {
		loggedInUser = null;
		System.out.println("Successfully logged out!");
		System.out.println("Thank you for using this banking app!");
		System.out.println("");
		signInPrompt();
	}
		
	/**
	 * Makes deposit or withdrawal from an account
	 */
	public void makeTransaction() {

		displayMyAccounts();

		System.out.println("");
		System.out.println("Enter ID# of account you wish to deposit to/withdraw from");

		String inputAccountIdString = scanner.nextLine();
		int inputAccountId = Integer.parseInt(inputAccountIdString);

		try {
			
			if (us.checkAccountId(inputAccountId, loggedInUser.getId())) {

				System.out.println("Account found!");
				System.out.println(us.viewAccount(inputAccountId));
				System.out.println("");
				System.out.println("Choose transaction to make:");
				System.out.println("1.  Make deposit");
				System.out.println("2.  Make withdrawal");

				String inputDepOrWith = scanner.nextLine();

				if (inputDepOrWith.equals("1")) {
					System.out.println("Enter amount to deposit");

					String depositAmountString = scanner.nextLine();
					Double depositAmount = Double.parseDouble(depositAmountString);

					try {
						us.makeDeposit(inputAccountId, depositAmount);
					} catch (Exception e) {
						System.out.println("Amount not recognized");
						makeTransaction();
					}
					
					System.out.println("");
					System.out.println("Do you wish to make another transaction?");
					System.out.println("1.  YES, make another transaction");
					System.out.println("2.  NO, go back to User options");
					
					String inputAnotherTransaction = scanner.nextLine();
					
					if (inputAnotherTransaction.equals("1")) {
						makeTransaction();
					} else {
						userPrompt();
					}

				} else if (inputDepOrWith.equals("2")) {
					System.out.println("Enter amount to withdraw");
					
					String withdrawalAmountString = scanner.nextLine();
					Double withdrawalAmount = Double.parseDouble(withdrawalAmountString);
					
					try {
						us.makeWithdrawal(inputAccountId, withdrawalAmount);
					} catch (Exception e) {
						System.out.println("Amount not recognized");
						makeTransaction();
					}
					
					System.out.println("");
					System.out.println("Do you wish to make another transaction?");
					System.out.println("1.  YES, make another transaction");
					System.out.println("2.  NO, go back to User options");
					
					String inputAnotherTransaction = scanner.nextLine();
					
					if (inputAnotherTransaction.equals("1")) {
						makeTransaction();
					} else {
						userPrompt();
					}

				} else {
					System.out.println("Command not recognized");
					System.out.println("Please type the number 1 or 2");
					System.out.println("");
					userPrompt();
				}

			} else {
				System.out.println("Account not found");
				System.out.println("");
				makeTransaction();
			}
			
		} catch (Exception e) {
			System.out.println("ID# not recognized");
			userPrompt();
		}
	}
		
	/**
	 * Opens new account inside a user's log in
	 */
	public void openNewAccount() {
		System.out.println("Please choose the type of account to open:");
		System.out.println("1.  Savings");
		System.out.println("2.  Checking");
		System.out.println("3.  Loan");

		String inputAcctType = scanner.nextLine();

		if (inputAcctType.equals("1")) {
			us.openSavingsAccount(0.0, loggedInUser.getId());
			System.out.println("Savings Account successfully opened!");
			System.out.println("");
			userPrompt();

		} else if (inputAcctType.equals("2")) {
			us.openCheckingAccount(0.0, loggedInUser.getId());
			System.out.println("Checking Account successfully opened!");
			System.out.println("");
			userPrompt();

		} else if (inputAcctType.equals("3")) {
			
			System.out.println("Enter amount to borrow");
			
			String loanAmountString = scanner.nextLine();
			Double loanAmount = Double.parseDouble(loanAmountString);

			try {
				
				if (loanAmount < 0) {
					System.out.println("Cannot borrow a negative amount!");
				} else {
					us.openLoanAccount(0 - loanAmount, loggedInUser.getId());
					System.out.println("Loan approved!");
				}
				
			} catch (Exception e) {
				System.out.println("Amount not recognized");
				makeTransaction();
			}
			
			System.out.println("");
			userPrompt();
			
		} else {
			System.out.println("Command not recognized");
			userPrompt();
		}
	}
	
	/**
	 * Registers new user with username and password
	 * - Check if username is already in the system
	 * - If not, establish password
	 * - When user is created, go back to sign-in prompt to log in
	 */
	public void registerNewUser() {
		
		System.out.println("Enter new username");
		System.out.println("NEW USERNAME REQUIREMENTS:");
		System.out.println("- Must be no longer than 20 characters");
		System.out.println("- Must not already be in the system");
		
		String newUsername = scanner.nextLine();

		if (newUsername.length() < 1) {
			System.out.println("Need to input username and then press Enter");
			System.out.println("Please try again");
			registerNewUser();
			
		} else if (newUsername.length() > 20) {
			System.out.println("Username is too long!");
			System.out.println("Please try again");
			registerNewUser();
			
		} else if (us.checkUsername(newUsername)) {
			System.out.println("Username already exists!");
			System.out.println("");
			registerNewUser();

		} else {

			System.out.println("Username available!");
			
			establishPassword(newUsername);

		}

	}

	/**
	 * Establishes password for a given username
	 * @param newUsername
	 */
	public void establishPassword(String newUsername) {
		
		System.out.println("Enter new password");
		System.out.println("NEW PASSWORD REQUIREMENTS:");
		System.out.println("- Must be no longer than 20 characters");
		
		String newPassword = scanner.nextLine();
		
		if (newPassword.length() < 1) {
			System.out.println("Need to input password and then press Enter");
			System.out.println("Please try again");
			establishPassword(newUsername);
			
		} else if (newPassword.length() > 20) {
			System.out.println("Password is too long!");
			System.out.println("Please try again");
			establishPassword(newUsername);
			
		} else {
			
			us.registerUser(newUsername, newPassword);
			System.out.println("Password successfully established!");
			System.out.println("Please sign back in");
			System.out.println("");
			signInPrompt();
			
		}
		
	}
		
	/**
	 * Displays all accounts in system
	 * RESTRICTED FUNCTIONALITY TO SUPERUSERS ONLY
	 * SUBMENU:	1.	View account by ID#
	 * 			2.	Go back to superuser options
	 */
	public void viewAllAccounts() {
		
		displayAllAccounts();
		
		System.out.println("");
		System.out.println("Choose an option to continue:");
		System.out.println("1.  View account by ID#");
		System.out.println("2.  Go back to SuperUser options");

		String inputContinue = scanner.nextLine();

		// OPTION 1: VIEW ACCOUNT BY ID#
		if (inputContinue.equals("1")) {
			viewAnyAccountDetails();

		// OPTION 2: GO BACK TO LAST MENU
		} else if (inputContinue.equals("2")) {
			superUserPrompt();

		// If input is not specifically "1" or "2", give user clearer directions
		} else {
			System.out.println("Command unclear");
			System.out.println("Please type the number 1 or 2");
			System.out.println("");
			viewAllAccounts();
		}

	}
	
	/**
	 * Displays all users in system
	 * SUBMENU: 1. View user by ID#
	 * 			2. Go back to superuser options
	 */
	public void viewAllUsers() {

		displayAllUsers();
		
		System.out.println("");
		System.out.println("Choose an option to continue:");
		System.out.println("1.  View user by ID#");
		System.out.println("2.  Go back to SuperUser options");

		String inputContinue = scanner.nextLine();

		// OPTION 1: VIEW USER BY ID#
		if (inputContinue.equals("1")) {
			viewAnyUserDetails();
			
		// OPTION 2: GO BACK TO LAST MENU
		} else if (inputContinue.equals("2")) {
			superUserPrompt();

		// If input is not specifically "1" or "2", give user clearer directions
		} else {
			System.out.println("Instruction unclear");
			System.out.println("Please type the number 1 or 2");
			System.out.println("");
			viewAllUsers();
		}

	}

	/**
	 * Displays menu for choosing SPECIFIC account to view
	 * RESTRICTED FUNCTIONALITY TO SUPERUSERS ONLY
	 */
	public void viewAnyAccountDetails() {
		System.out.println("Enter ID# of account you wish to view");

		String inputAccountIdString = scanner.nextLine();
		int inputAccountId = Integer.parseInt(inputAccountIdString);
		
		try {
			
			System.out.println("ACCOUNT DETAILS");
			Account a = us.viewAccount(inputAccountId);
			System.out.println(a);
			System.out.println("");
			
			System.out.println("OWNER OF ACCOUNT");
			
			if (a.getUserId() % 2 == 1) {
				SuperUser owner = ss.viewSuperUser(a.getUserId());
				System.out.println(owner);
				
			} else {
				User owner = us.viewUser(a.getUserId());
				System.out.println(owner);
				
			}
			
		} catch (Exception e) {
			System.out.println("ID# not recognized");
			viewAnyAccountDetails();
		}
		
		System.out.println("");

		System.out.println("Do you wish to view another account?");
		System.out.println("1.  YES, view another account");
		System.out.println("2.  YES, view ALL accounts");
		System.out.println("3.  NO, go back to SuperUser options");
		
		String inputAnotherAccount = scanner.nextLine();
		
		if (inputAnotherAccount.equals("1")) {
			viewAnyAccountDetails();
		} else if (inputAnotherAccount.equals("2")) {
			viewAllAccounts();
		} else {
			superUserPrompt();
		}
		
	}

	/**
	 * Displays menu for choosing SPECIFIC user to view
	 * RESTRICTED FUNCTIONALITY TO SUPERUSERS ONLY
	 */
	public void viewAnyUserDetails() {
		System.out.println("Enter ID# of user you wish to view");

		String inputUserIdString = scanner.nextLine();
		int inputUserId = Integer.parseInt(inputUserIdString);
		
		try {
			
			System.out.println("USER DETAILS");
			System.out.println(us.viewUser(inputUserId));
			System.out.println("");
			
			System.out.println("ACCOUNTS BELONGING TO USER");
			List<Account> listOfTheirAccounts= us.viewAccounts(inputUserId);
			
			for (Account a : listOfTheirAccounts) {
				System.out.println(a);
			}
			System.out.println("");

			// Go again or ?
			System.out.println("Do you wish to view another user?");
			System.out.println("1.  YES, view another user");
			System.out.println("2.  YES, view ALL users");
			System.out.println("3.  NO, go back to SuperUser options");
			
			String inputAnotherUser = scanner.nextLine();
			
			if (inputAnotherUser.equals("1")) {
				viewAnyUserDetails();
			} else if (inputAnotherUser.equals("2")) {
				viewAllUsers();
			} else {
				superUserPrompt();
			}
			
		} catch (Exception e) {
			System.out.println("ID# not recognized");
			viewAnyAccountDetails();
		}
	
	}
	
	/**
	 * Displays menu for choosing account to view
	 * Logged in user MUST own account to be viewed
	 */
	public void viewMyAccountDetails() {
		System.out.println("Enter ID# of account you wish to view");

		String inputAccountIdString = scanner.nextLine();
		int inputAccountId = Integer.parseInt(inputAccountIdString);
		
		try {
			
			if (us.checkAccountId(inputAccountId, loggedInUser.getId())) {

				System.out.println("Account found!");
				System.out.println(us.viewAccount(inputAccountId));
				System.out.println("");
				
				System.out.println("Do you wish to view another account?");
				System.out.println("1.  YES, view another account");
				System.out.println("2.  YES, view ALL accounts");
				System.out.println("3.  NO, go back to User options");
				
				String inputAnotherAccount = scanner.nextLine();
				
				if (inputAnotherAccount.equals("1")) {
					viewMyAccountDetails();
				} else if (inputAnotherAccount.equals("2")) {
					viewMyAccounts();
				} else {
					userPrompt();
				}
			}
			
		} catch (Exception e) {
			System.out.println("ID# not recognized");
			viewMyAccountDetails();
		}

	}
	
	/**
	 * Displays logged in user's accounts
	 */
	public void viewMyAccounts() {
		
		displayMyAccounts();
		
		System.out.println("");
		System.out.println("Do you wish to view account details?");
		System.out.println("1.  YES, view account details");
		System.out.println("2.  NO, go back to User options");
		
		String inputAccountDetails = scanner.nextLine();
		
		if (inputAccountDetails.equals("1")) {
			viewMyAccountDetails();
		} else {
			userPrompt();
		}
		
	}
	
	// ------------------------------------------------------------------------
	// DISPLAYS
	// ------------------------------------------------------------------------
	
	public void displayAllAccounts() {
		
		System.out.println("ALL ACCOUNTS");
		List<Account> listOfAllAccounts= ss.viewAllAccounts();
		
		for (Account a : listOfAllAccounts) {
			
			if (a instanceof SavingsAccount) {
				System.out.println("Acct.#" + a.getId() + ", Type: Savings" + ", Belongs to UserID: " + a.getUserId());
			} else if (a instanceof CheckingAccount) {
				System.out.println("Acct.#" + a.getId() + ", Type: Checking" + ", Belongs to UserID: " + a.getUserId());
			} else if (a instanceof LoanAccount) {
				System.out.println("Acct.#" + a.getId() + ", Type: Loan" + ", Belongs to UserID: " + a.getUserId());
			}
		}
		
	}
	
	public void displayAllUsers() {
		
		System.out.println("ALL USERS");
		List<User> listOfAllUsers= ss.viewAllUsers();
		
		for (User u : listOfAllUsers) {
			
			if (u instanceof SuperUser) {
				System.out.println("SuperUser#" + u.getId() + ", Username: " + u.getUsername());
			} else {
				System.out.println("User#" + u.getId()+ ", Username: " + u.getUsername());
			}
		}
		
	}
	
	public void displayMyAccounts() {
		
		System.out.println("MY ACCOUNTS");
		List<Account> listOfMyAccounts= us.viewAccounts(loggedInUser.getId());
		
		for (Account a : listOfMyAccounts) {
			
			if (a instanceof SavingsAccount) {
				System.out.println("Acct.#" + a.getId() + ", Type: Savings");
			} else if (a instanceof CheckingAccount) {
				System.out.println("Acct.#" + a.getId() + ", Type: Checking");
			} else if (a instanceof LoanAccount) {
				System.out.println("Acct.#" + a.getId() + ", Type: Loan");
			}
		}
		
	}
}