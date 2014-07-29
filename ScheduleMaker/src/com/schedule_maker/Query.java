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
	public ArrayList<ArrayList<String>> getDepartments() {
		String query = "SELECT DISTINCT Subject_Code, Subject FROM CLASSES ORDER BY Subject_Code;";
		String[] array = {"Subject_Code", "Subject"};
		return getQueryResults(query, array);
	}

	/*
	 * Returns all courses within a department, given its cdoe, as an arraylist of strings
	 */
	public ArrayList<ArrayList<String>> getCourses(String dept_code) {
		String query = "SELECT DISTINCT Catalog_Num FROM CLASSES WHERE Subject_Code = '" 
				+ dept_code + "' ORDER BY Catalog_Num;";
		String[] array = {"Catalog_Num"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns all courses within a department, given its code, as an arraylist of strings
	 */
	public ArrayList<ArrayList<String>> getAllComponents(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "'";
		String[] array = {"Class_Number"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns the course numbers for each lecture section of the given class
	 */
	public ArrayList<ArrayList<String>> getLectures(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'LEC'";
		String[] array = {"Class_Number"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns the course numbers for each lab section of the given class
	 */
	public ArrayList<ArrayList<String>> getLabratories(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'LAB'";
		String[] array = {"Class_Number"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns the course numbers for each discussion section of the given class
	 */
	public ArrayList<ArrayList<String>> getDiscussions(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'DIS'";
		String[] array = {"Class_Number"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns the course numbers for each recitation section of the given class
	 */
	public ArrayList<ArrayList<String>> getRecitations(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'REC'";
		String[] array = {"Class_Number"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns the query results in an array list
	 */
	public ArrayList<ArrayList<String>> getQueryResults(String query, String[] params) {
		ArrayList<ArrayList<String>>  grid = new ArrayList<ArrayList<String>>();
		try {
			Statement stmt;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				for(String item : params) {
					list.add(rs.getString(item));
				}
				grid.add(list);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return grid;
	}
}
