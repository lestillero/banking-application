package services;

public interface Menu {
	
	public void signInPrompt();
	public void userPrompt();
	public void superUserPrompt();
	public void managerPrompt();

	public void closeAccount();
	public void deleteUser();
	public void logInWithUsername();
	public void logOut();
	public void makeTransaction();
	public void openNewAccount();
	public void registerNewUser();
	public void establishPassword(String newUsername);
	public void viewAllAccounts();
	public void viewAllUsers();
	public void viewAnyAccountDetails();
	public void viewAnyUserDetails();
	public void viewMyAccountDetails();
	public void viewMyAccounts();
	
	public void displayAllAccounts();	
	public void displayAllUsers();
	public void displayMyAccounts();


}
