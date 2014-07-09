package com.schedule_maker;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * A class for executing queries against that database
 */
public class Query extends DatabaseConnection {
	
	/*
	 * Opens database connection, initializes 'conn'
	 */
	public Query() {
		super();
	}

	/*
	 * Returns all departments in the DB in an arraylist of strings
	 * On failure, returns an empty list
	 */
	public ArrayList<String> getDepartments() {
		String query = "SELECT DISTINCT Subject_Code FROM CLASSES ORDER BY Subject_Code;";
		return getQueryResults(query, "Subject_Code");
	}

	/*
	 * Returns all courses within a department, given its cdoe, as an arraylist of strings
	 * On failure, returns an empty list
	 */
	public ArrayList<String> getCourses(String code) {
		String query = "SELECT DISTINCT Catalog_Num FROM CLASSES WHERE Subject_Code = '" + code + "' ORDER BY Catalog_Num;";
		return getQueryResults(query, "Catalog_Num");
	}
	
	/*
	 * Returns the query results in an array list
	 */
	public ArrayList<String> getQueryResults(String query, String param) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement stmt;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String department = "";
				department += rs.getString(param);
				list.add(department);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
