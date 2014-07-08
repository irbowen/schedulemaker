/*
 * Isaac Bowen
 * 2014-7-3
 */

package com.schedule_maker;

import java.sql.*;

/*
 * This class handles the connection to the database
 */
public class DatabaseConnection {

	/*
	 * Connection used throughout the class
	 */
	Connection conn = null;
	
	/*
	 * Initializes the connection to the database file
	 * Exits the program on failure
	 */
	public DatabaseConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:schedule.db");
		}
		catch(Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/*
	 * Because I'm lazy
	 */
	public void createOrUpdate(String statement) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(statement);
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(statement);
			System.exit(0);
		}
	}
	
	/*
	 * Schemas
	 */
	public void createTables() {
		String tableSchema = 
			"CREATE TABLE IF NOT EXISTS CLASSES ( " +
				"Session TEXT, " +
				"School TEXT, " +
				"Class_Number TEXT PRIMARY KEY, " + 
				"Subject TEXT, " +
				"Subject_Code TEXT, " +
				"Catalog_Num TEXT, " + 
				"Section_Num TEXT, " +
				"Course_Title TEXT, " +
				"Component TEXT, " +
				"Monday INT, " + 
				"Tuesday INT, " + 
				"Wednesday INT, " + 
				"Thursday INT, " + 
				"Friday INT, " + 
				"Start_Time INT NOT NULL, " +
				"End_Time INT NOT NULL, " + 
				"Location TEXT, " +
				"Instructor TEXT " +
			");";
		this.createOrUpdate("DROP TABLE IF EXISTS CLASSES;");
		this.createOrUpdate(tableSchema);
	}
	

}
