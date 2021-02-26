package driver;

import services.Menu;
import services.MenuImpl;

public class App {
	
	// THE MAIN METHOD -- Runs the application
	public static void main(String[] args) {
		
		Menu menu = new MenuImpl();
		
		// Display welcome text to console
		System.out.println("Welcome to the First Official Operational Bank Application and Record System!");
		
		// Run application from Sign-In Prompt Menu
		menu.signInPrompt();

	}
	
}