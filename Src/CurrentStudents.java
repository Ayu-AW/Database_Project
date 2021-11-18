package mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.Prompt;

public class CurrentStudents {
	
	// object containing methods used in Scenario 2
	
	/* 
	 * See Updates.java for how Prompt, Utility, and other objects are used for an input-based sql query.
	 * Try to structure your queries somewhat similarly in terms of using a class for storing table data,
	 * like how Student was used in Updates, and using the Utility and Prompt methods for input
	 * and null input processing when applicable.  If you have any questions definitely ask.
	 * 
	 */
	
	//Education History for a student
	public static ResultSet getStudentEducationalHistory(Connection conn) {
		ResultSet rs = null;
		try {
			String ssn = Prompt.promptString("Input a ssn that you want an educational history for: ");
			String query = "SELECT SchoolName, DegreeEarned, GPA " + "FROM EducationalHistory" + "WHERE SSN = " + ssn;
			PreparedStatement s = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}

	// College student Information
	public static ResultSet getStudentInformation(Connection conn) {
		// see my comments here for how to fix these methods
		// surround in a try catch, move 'rs' null initialization and return to outside
		ResultSet rs = null;
		try {
			String colName = Prompt.promptString("Input a college name to return its student information: ");
			String query = "SELECT SSN, StuName"  + "FROM EducationalHistory join Students on EducationalHistory.SSN = Students.SSN " +
				"WHERE SchoolName = " + colName; 
			PreparedStatement s = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery(query);  
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	// All student contact information
	public static ResultSet getStudentContactDetails(Connection conn) {
		ResultSet rs = null;
		try {
			String query = "SELECT s.SSN, p.FullName, p.Address, s.MajorDepartmentID,c.CollegeName FROM Student AS s INNER JOIN Person AS p ON s.PersonID ="
					+ " p.PersonID INNER JOIN College AS c ON p.personID = c.DeanPID WHERE StudentType = 'undergraduate';";
			PreparedStatement s = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	// Student from a country
	public static ResultSet getStudentContactDetailsByCountry(Connection conn) {
		ResultSet rs = null;
		try {
			String Nationality = Prompt.promptString("Please enter a Country: ");
			String query = "SELECT s.SSN, p.FullName, p.Address FROM Student as s join Person as p on s.PersonID = "
					+ "p.PersonID  WHERE Nationality = " + Nationality + "AND StudentType = 'undergraduate'";
			PreparedStatement s = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}
	//Working students contact information
	public static ResultSet getWorkingStudentContactDetails(Connection conn) {
		ResultSet rs = null;
		try {
			String query = "SELECT s.SSN, p.FullName, p.Address, j.HoursPerWeek FROM Student AS s INNER JOIN Person AS p ON s.PersonID "
					+ "= p.PersonID INNER JOIN Job AS j ON p.PersonID = j.EmployerPID WHERE s.StudentType = 'Undergraduate''";
			PreparedStatement s = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery(query);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	//Visa status report
	public static ResultSet getStudentVisaStatus(Connection conn) {
		ResultSet rs = null;
		try {
			String query = "SELECT  g.VisaType FROM GraduatedVisas as g";
			PreparedStatement s = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery(query);
			return rs;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}  
	//F-1 visa
	public static ResultSet getUndergradF1VisaInfo(Connection conn) {
		ResultSet rs = null;
		try {
			String query = "SELECT SSN, FullName, Nationality FROM Students WHERE (VisaType = 'F-1' AND StudentType = 'undergraduate') "; 
			PreparedStatement s = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery(query);
			return rs;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

}

			
			
				
			
			



