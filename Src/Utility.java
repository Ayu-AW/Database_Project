package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class Utility {
	/**
	 * A container class to store various uncategorized utility functions that may need to be used in multiple other classes across the application.
	 */
	
	public static void setString(PreparedStatement p, int index, String s) throws Exception {
		/**
		 * Sets an indexed '?' within a PreparedStatement to a java String value.
		 * Helper method used to allow for efficient allocation of SQL null values.
		 */
		if (s == null) {
			p.setNull(index, Types.NULL);
		}
		else {
			p.setString(index, s);
		}
	}
	
	public static void setInt(PreparedStatement p, int index, Integer n) throws Exception {
		/**
		 * Sets an indexed '?' within a PreparedStatement to a java Integer value.
		 * Helper method used to allow for efficient allocation of SQL null values.
		 */
		if (n == null) {
			p.setNull(index, Types.NULL);
		}
		else {
			p.setInt(index, n);
		}
	}
	
	public static void setDate(PreparedStatement p, int index, java.util.Date d) throws Exception {
		/**
		 * Sets an indexed '?' within a PreparedStatement to a java Date value.
		 * Helper method used to allow for efficient allocation of SQL null values.
		 */
		if (d == null) {
			p.setNull(index, Types.NULL);
		}
		else {
			p.setDate(index, new java.sql.Date(d.getTime()));
		}
	}
	
	public static void printResultSet(ResultSet res) {
		/**
		 * Helper method that prints each row in a given ResultSet
		 */
		if (res == null) {
			System.out.println("\nEmpty set returned");
			return;
		}
		try {
			int columnCount = res.getMetaData().getColumnCount();
			res.last();  // jump to last to get size as it's row id
			System.out.println("\nSet size " + res.getRow() + " returned:\n-----");
			res.beforeFirst();  // cursor back to initial position
			while(res.next()) {
				StringBuilder rowStringBuilder = new StringBuilder();
				for (int i = 1; i <= columnCount; i++) {
					rowStringBuilder.append(res.getString(i));
					rowStringBuilder.append(" ");
				}
				System.out.println(rowStringBuilder.toString());
			}
			Prompt.awaitInput("-----");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
