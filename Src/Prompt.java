package util;
import java.util.Date;
import java.text.SimpleDateFormat;
import main.Application;

public class Prompt {
	/**
	 *  A utility class used to quickly query the user for inputs of various data types.
	 */
	
	public static Integer promptInt(String prompt) {
		/**
		 * Returns a user input integer.
		 */
		System.out.println(prompt);
		while (true) {
			System.out.print("? ");
			String inputString = "";
			try {
				inputString = Application.getInputReader().readLine();
				Integer outInt = Integer.parseInt(inputString);
				return outInt;
			}
			catch (Exception e){
				if (inputString.strip().equalsIgnoreCase("") || inputString.strip().equalsIgnoreCase("null")) {  // escape to enter a null value
					return null;
				}
				else {
					System.out.println("Invalid input, try again!");
				}
			}
		}
	}
	
	public static String promptString(String prompt) {
		/**
		 * Returns a user input string.
		 */
		System.out.println(prompt);
		while (true) {
			System.out.print("? ");
			String inputString = "";
			try {
				inputString = Application.getInputReader().readLine();
				String outString = inputString;
				if (inputString.strip().equalsIgnoreCase("") || inputString.strip().equalsIgnoreCase("null")) {
					return null;
				}
				else {
					return outString;
				}
			}
			catch (Exception e){
				System.out.println("Invalid input, try again!");
			}
		}
	}
	
	public static Date promptDate(String prompt) {
		/**
		 * Returns a valid user input Date.
		 */
		System.out.println(prompt);
		while (true) {
			System.out.print("? ");
			String inputString = "";
			try {
				inputString = Application.getInputReader().readLine();
				Date outDate= new SimpleDateFormat("dd/MM/yyyy").parse(inputString);
				return outDate;
			}
			catch (Exception e){
				if (inputString.strip().equalsIgnoreCase("") || inputString.strip().equalsIgnoreCase("null")) {
					return null;
				}
				else {
					System.out.println("Invalid input, try again!");
				}
			}
		}
	}
	
	public static String promptEnum(String prompt, String ... validStrings) {
		/**
		 * Returns a user input enum of a select amount of types.
		 */
		System.out.println(prompt);
		while (true) {
			System.out.print("? ");
			String inputString = "";
			try {
				inputString = Application.getInputReader().readLine();
				if (inputString.strip().equalsIgnoreCase("") || inputString.strip().equalsIgnoreCase("null")) {
					return null;
				}
				else {
					for (int i = 0; i < validStrings.length; i++) {
						if (validStrings[i].equalsIgnoreCase(inputString.strip())) {
							return validStrings[i];
						}
					}
					throw new Exception();
				}
			}
			catch (Exception e){
				System.out.println("Invalid input, try again!");
			}
		}
	}
	
	public static void awaitInput(String display) {
		if (display != null) {
			System.out.println(display);
		}
		System.out.print("Press ENTER to proceed... ");
		try {
			Application.getInputReader().readLine();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void awaitInput() {
		awaitInput(null);
	}
	
}