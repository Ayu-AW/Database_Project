
package main;

// Make sure to start an Eclipse workspace in a folder outside of the InternationalStudentProgram folder created by your git clone
// Also make sure to add the external JAR file for mysql-connector from the Project>Properties>Libraries>Classpath menu

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.Menu;
import util.Prompt;
import util.Utility;

@SuppressWarnings("unused")
public class Application {

	private static Connection conn;  // main database connection
	private static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));  // application global in-stream; access with Application.inputReader for readline() calls.  Do not close.
	
	private final static String starline = "***********************************************************";
	private final static String headline = "		    Welcome to the\n"+
			  						 	   "	International Student Information System\n";
	
	public static BufferedReader getInputReader() {
		return inputReader;
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	public static void mainMenu() {
		/**
		 * Primary menu function.
		 */
		
		// initialize DB connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String schemaName = "International_Student_Program";  // Note: make sure your schema name matches with this, or your code will not execute.
			String url = "jdbc:mysql://localhost:3306/" + schemaName + "?serverTimezone=UTC&useSSL=TRUE";
			String username = "", password = "";
			username = Prompt.promptString("Enter username:");
			password = Prompt.promptString("Enter password:");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected successfully!");
			
			/* Use Menu objects with appropriate option input strings for the various menu's and submenus needed, 
			 * since that will show a good use of OO techniques.  Use the integer output of the menu object to activate
			 * if - else cases or a switch statement to navigate to new menu objects or call methods in other classes.
			 * You should probably compartmentalize these switches into their own functions to allow for the nesting of switch
			 * Statements (see example idea)
			 *
			 * The menus we need are as follows:
			 * 
			 * Main Menu
			 * 
			 * Update Menu: Scenario 1, option 4 in main menu
			 *   Insert Sub-Menu include a switch with all four sub-option methods defined in Updates class
			 *   
			 *  Current Student Menu: Scenario 2, option 1 in main menu
			 *    Academic Information Sub-Menu; include a switch with the appropriate two methods defined in CurrentStudents class
			 *    Contact Information Sub-Menu; include a switch with the appropriate three methods defined in CurrentStudents class
			 *    Visa Status Sub-Menu; include a switch with the appropriate two methods defined in CurrentStudents class
			 *    
			 *  International Rules & Laws Menu: Scenario 3, option 2 in main menu
			 *  
			 *  All other menu's display something like "work in progress" and include a single valid option to go back to the previous menu.
			 *  
			 *  All menu's have the option to go back to the previous menu.
			 */
			
			boolean quitSelected = false;
			while (!quitSelected) {
				String mainHead = starline + "\n" + headline + starline;
				String[] mainOptions = {"Current Students","Graduated Students", "International Rules & Laws", "Updates", "Quit"};
				Menu outerMenu = new Menu(mainHead, mainOptions);
				int selection = outerMenu.activateMenu();
				switch (selection) {
				case 1:
					currentStudentsMenu();
					break;
				case 2:
					Prompt.awaitInput("Under construction!");
					break;
				case 3:
					Utility.printResultSet(RulesAndLaws.InterStuReg(conn));
					break;
				case 4:
					updateMenu();
					break;	
				case 5:
					quitSelected = true;
					break;
				}
			}
			System.out.println("Goodbye!");
			conn.close();  // close the connection at the end of the program
			inputReader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void currentStudentsMenu() {
		/**
		 * Scenario 2 Menu
		 */
		String menuHead = starline + "\n" + headline + "\n		1. Current Students\n" + starline;
		String[] menuOptions = {"Academic information", "Contact information", "Visa status", "Main Menu"};
		Menu currStudMenu = new Menu(menuHead, menuOptions);
		int selection = currStudMenu.activateMenu();
		switch (selection) {
			case 1: 
				academicInfoMenu();
				break;
			case 2:
				contactInfoMenu();
				break;
			case 3:
				visaStatusMenu();
				break;
			case 4:
				return;
		}
	}
	
	private static void academicInfoMenu() {
		/**
		 * Scenario 2 sub-option 1 Menu
		 */
		String menuHead = starline + "\n" + headline + "\n		1. Current Students\n"
								+ "\n		1. Academic information\n" + starline;
		String[] menuOptions = {"Educational history for a student", "College students information", "Main Menu"};
		Menu acadInfoMenu = new Menu(menuHead, menuOptions);
		int selection = acadInfoMenu.activateMenu();
		switch (selection) {
			case 1: 
				Utility.printResultSet(CurrentStudents.getStudentEducationalHistory(conn));
				break;
			case 2:
				// Utility.printResultSet(CurrentStudents.getStudentInformation(conn));
				break;
			case 3:
				return;
		}
	}
	
	private static void contactInfoMenu() {
		/**
		 * Scenario 2 sub-option 2 Menu
		 */
		String menuHead = starline + "\n" + headline + "\n		1. Current Students\n"
								+ "\n		2. Contact information\n" + starline;
		String[] menuOptions = {"All student contact details", "Student from a country contact details", "Working students contact details", "Main Menu"};
		Menu contactInfoMenu = new Menu(menuHead, menuOptions);
		int selection = contactInfoMenu.activateMenu();
		switch (selection) {
			case 1: 
				// Utility.printResultSet(CurrentStudents.getStudentContactDetails(conn));
				break;
			case 2:
				// Utility.printResultSet(CurrentStudents.getStudentContactDetailsByCountry(conn));
				break;
			case 3:
				// Utility.printResultSet(CurrentStudents.getWorkingStudentContactDetails(conn));
				break;
			case 4:
				return;
		}
	}
	
	private static void visaStatusMenu() {
		/**
		 * Scenario 2 sub-option 3 Menu
		 */
		String menuHead = starline + "\n" + headline + "\n		1. Current Students\n"
								+ "\n		3. Visa status\n" + starline;
		String[] menuOptions = {"Visa status report", "F-1 Visa", "Main Menu"};
		Menu visaStatMenu = new Menu(menuHead, menuOptions);  // initialize menu as an object
		int selection = visaStatMenu.activateMenu();  // display menu and get input
		switch (selection) {
			case 1: 
				// Utility.printResultSet(CurrentStudents.getStudentVisaStatus(conn));
				break;
			case 2:
				// Utility.printResultSet(CurrentStudents.getUndergradF1VisaInfo(conn));
				break;
			case 3:
				return;
		}
	}

	private static void updateMenu() {
		/**
		 * Scenario 1 Menu
		 */
		String menuHead = starline + "\n" + headline + "\n		1. Updates\n" + starline;
		String[] menuOptions = {"Insert new information", "Delete some information", "Modify current information", "Main Menu"};
		Menu updMenu = new Menu(menuHead, menuOptions);
		int selection = updMenu.activateMenu();
		switch (selection) {
			case 1: 
				// insertMenu();
				break;
			case 2:
				Prompt.awaitInput("Under construction!");
				break;
			case 3:
				Prompt.awaitInput("Under construction!");
				break;
			case 4:
				return;
		}
	}
	
	private static void insertMenu() {
		/**
		 * Scenario 1 sub-option 1 Menu
		 */
		String menuHead = starline + "\n" + headline + "\n		1. Updates\n" 
							+ "		1. Insert new information\n" + starline;
		String[] menuOptions = {"Add new student information", "Add new course information", "Add new department information", "Register a student in a course", "Main Menu"};
		Menu insMenu = new Menu(menuHead, menuOptions);
		int selection = insMenu.activateMenu();
		switch (selection) {
			case 1: 
				Updates.insertStudent(conn);;
				break;
			case 2:
				Updates.insertCourse(conn);;
				break;
			case 3:
				Updates.insertDepartment(conn);
				break;
			case 4:
				Updates.insertTakesCourse(conn);
				break;
			case 5:
				return;
		}
	}
	
	private static void exampleMenu() {
		/**
		 * An example menu function to show a good way to handle switch statements for each menu and sub menu
		 * TODO: Delete this before final submission
		 */
		String testhead = "*** Cool Menu Heading ***";
		String testoption1 = "Another Menu";
		String testoption2 = "Example Function";
		String testoption3 = "Back to Main Menu";
		Menu exMenu = new Menu(testhead, testoption1, testoption2, testoption3);  // initialize menu as an object
		int selection = exMenu.activateMenu();  // display menu and get input
		switch (selection) {
			case 1: 
				// return AnotherMenu(); unimplemented menu function; returns void
				break;
			case 2:
				// printResultSet(ExampleClass.ExampleFunction(conn)); unimplemented object method to execute SQL, returning a ResultSet
				// return
				break;
			case 3:
				return;  // called from main menu, so it will return there after exiting
		}
	}
	
	public static void main(String[] args) {
		/**
		 * Main program execution.  Initializes the main menu.
		 */
		mainMenu();
	}
	
}