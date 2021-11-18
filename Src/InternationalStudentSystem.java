

import java.sql.*;
import java.io.*;

public class InternationalStudentSystem {
	public static void main(String[] args) {
	      Connection conn = null;
	      try {
	         // Connect to server
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         String url = "jdbc:mysql://localhost:3306/NiceEmptySchema?serverTimezone=UTC&useSSL=TRUE";
	         String user, pass;
	         user = readEntry("UserId: ");
	         pass = readEntry("Password: ");                             
	         conn = DriverManager.getConnection(url, user, pass);
	         
	         Statement s = conn.createStatement();
	         // Statement is passed as a parameter so that the menu functions can do queries
	         while(menu0(s))
	         {
	            // Runs the menu until user selects quit
	         }

	         
	         
	         
	      } catch (ClassNotFoundException e){
	         System.out.println(e);
	      } catch (SQLException ex) {
	         System.out.println(ex);
	      }
	   }
	   
	   static boolean menu0(Statement s) {
	      System.out.println("*****************************************************************************");
	      System.out.println("                                 ***********                                 ");
	      System.out.println("                                Welcome to the                               ");
	      System.out.println("                  International Student Information System                   ");
	      System.out.println("                                 ***********                                 ");
	      System.out.println("*****************************************************************************");
	      System.out.println("                             1. Current Students                             ");
	      System.out.println("                            2. Graduated Students                            ");
	      System.out.println("                        3. International Rules & Laws                        ");
	      System.out.println("                                  4. Update                                  ");
	      System.out.println("                                   5. Quit                                   ");
	      String option;
	      option = readEntry("Type in your option: ");
	      switch(option)
	      {
	         /* TODO: Add cases for 2 and 3, and matching menu functions */
	         case "2":
	            while (menu2(s))
	            {
	               // Loop menu2 until user selects quit
	            }
	            break;
	         case "3":
	            menu3(s);
	            break;
	         case "4":
	            menu4(s);
	            break;
	         case "5":
	            return false;
	      }
	      return true;
	   }
	   
	   static boolean menu2(Statement s) {
	      System.out.println("*****************************************************************************");
	      System.out.println("                                 ***********                                 ");
	      System.out.println("                                Welcome to the                               ");
	      System.out.println("                  International Student Information System                   ");
	      System.out.println();
	      System.out.println("                            2. Graduated Students                            ");
	      System.out.println("*****************************************************************************");
	      System.out.println("                           1. Academic Information                           ");
	      System.out.println("                           2. Contact Information                            ");
	      System.out.println("                               3. Visa Status                                ");
	      System.out.println("                                   4. Quit                                   ");
	      String option;
	      option = readEntry("Type in your option: ");
	      switch(option)
	      {
	         case "1":
	            menu21(s);
	            break;
	         case "2":
	            menu22(s);
	            break;
	         case "3":
	            menu23(s);
	            break;
	         case "4":
	            return false;
	      }
	      return true;
	   }
	   
	   static void menu21(Statement s) {
	      System.out.println("*****************************************************************************");
	      System.out.println("                                 ***********                                 ");
	      System.out.println("                                Welcome to the                               ");
	      System.out.println("                  International Student Information System                   ");
	      System.out.println();
	      System.out.println("                            2. Graduated Students                            ");
	      System.out.println("                           1. Academic Information                           ");
	      System.out.println("*****************************************************************************");
	      System.out.println("                     A. Educational History for a Student                    ");
	      System.out.println("                       B. College Students Information                       ");
	      String option;
	      option = readEntry("Type in your option: ");
	      String query = "";
	      ResultSet rs;
	      try
	      {
	         switch (option)
	         {
	            case "A":
	               String ssn = readEntry("Input a ssn that you want an educational history for: ");
	               query = "SELECT SchoolName, DegreeEarned, GPA " + 
	                       "FROM Educational History" + 
	                       "WHERE SSN = " + ssn;
	               rs = s.executeQuery(query);
	               System.out.println(String.format("%-21s| %-21s| GPA", "School", "Degree"));
	               while (rs.next())
	               {
	                  System.out.print(String.format("%-21s| %-21s| ", rs.getString(1), rs.getString(2)));
	                  System.out.println(rs.getInt(3));
	               }
	               readEntry("\nPress enter to return to previous menu");
	               break;
	            case "B":
	               // Not working yet
	               String colName = readEntry("Input a college name to return its student information: ");
	               query = "SELECT SSN, StuName " + 
	                       "FROM EducationalHistory join Students on EducationalHistory.SSN = Students.SSN " +
	                       "WHERE SchoolName = " + colName;
	               rs = s.executeQuery(query);
	               System.out.println("SSN       | Student Name");
	               while (rs.next())
	               {
	                  System.out.println(rs.getInt(1) + " | " + rs.getString(2));
	               }
	               readEntry("\nPress enter to return to previous menu");
	               break;
	         }
	      } catch (SQLException e) {
	         System.out.println(e);
	      }
	   }
	   
	   static void menu22(Statement s) {
	      System.out.println("*****************************************************************************");
	      System.out.println("                                 ***********                                 ");
	      System.out.println("                                Welcome to the                               ");
	      System.out.println("                  International Student Information System                   ");
	      System.out.println();
	      System.out.println("                            2. Graduated Students                            ");
	      System.out.println("                           2. Contact Information                            ");
	      System.out.println("*****************************************************************************");
	      System.out.println("A. All Students' Contact Details");
	      System.out.println("B. Students' Contact Detail by Country");
	      String option;
	      option = readEntry("Type in your option: ");
	      String query = "";
	      try
	      {
	         switch (option)
	         {
	            case "A":
	               query = "SELECT SSN, StuName, StuAddress, MajorDept, College FROM Students WHERE StuType = 'Graduate'";
	               s.executeQuery(query);
	               break;
	            case "B":
	               String country = readEntry("Please enter a Country: ");
	               query = "SELECT SSN, StuName, StuAddress FROM Students WHERE Country = '" + country + "' AND StuType = 'Graduate'"; 
	               s.executeQuery(query);
	               break;
	         }	         
	         ResultSet rs = s.executeQuery(query);
	         ResultSetMetaData rsmd = rs.getMetaData();
	         int columnsNumber = rsmd.getColumnCount();
	         while (rs.next())
	         {
	        	 for (int i = 1; i <= columnsNumber; i++) {
	                 if (i > 1) System.out.print("");
	                 String columnValue = rs.getString(i);
	                 System.out.println(rsmd.getColumnName(i) + ": " + columnValue);
	        	 }
	         }
	      } catch (SQLException e) {
	         System.out.println(e);
	      }
	      readEntry("\nPress enter to return to previous menu");
	      return;
	   }
	   
	   
	   static void menu23(Statement s) {
	      System.out.println("*****************************************************************************");
	      System.out.println("                                 ***********                                 ");
	      System.out.println("                                Welcome to the                               ");
	      System.out.println("                  International Student Information System                   ");
	      System.out.println();
	      System.out.println("                            2. Graduated Students                            ");
	      System.out.println("                               3. Visa Status                                ");
	      System.out.println("*****************************************************************************");
	      System.out.println("A. Visa Status Report");
	      System.out.println("B. J-1 Visa");
	      String option;
	      option = readEntry("Type in your option: ");
	      String query = "";
	      try
	      {
	         switch (option)
	         {
	            case "A":
	               String ssn = readEntry("Please enter the Visa Holder's Social Security Number: ");
	               query = "SELECT VisaType FROM Students WHERE SSN = " + ssn;
	               s.executeQuery(query);
	               break;
	            case "B":
	               query = "SELECT SSN, StuName, StuNationality FROM Students WHERE (VisaType = 'J-1' AND StuType = 'Graduate') "; 
	               s.executeQuery(query);
	               break;
	         }	         
	         ResultSet rs = s.executeQuery(query);
	         ResultSetMetaData rsmd = rs.getMetaData();
	         int columnsNumber = rsmd.getColumnCount();
	         while (rs.next())
	         {
	        	 for (int i = 1; i <= columnsNumber; i++) {
	                 if (i > 1) System.out.print("");
	                 String columnValue = rs.getString(i);
	                 System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
	        	 }
	         }
	      } catch (SQLException e) {
	         System.out.println(e);
	      }
	      readEntry("\nPress enter to return to previous menu");
	      return;
	   }
	        
	   static String readEntry(String prompt) {
	        try {
	            StringBuffer buffer = new StringBuffer();
	            System.out.print(prompt);
	            System.out.flush();
	            int c = System.in.read();
	            while (c != '\n' && c != -1) {
	                buffer.append((char) c);
	                c = System.in.read();
	            }
	            return buffer.toString().trim();
	        } catch (IOException e) {
	            return "";
	        }
	    }
}


	

