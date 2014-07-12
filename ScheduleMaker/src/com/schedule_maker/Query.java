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
		System.out.println(query);
		return getQueryResults(query, "Subject_Code");
	}

	/*
	 * Returns all courses within a department, given its cdoe, as an arraylist of strings
	 */
	public ArrayList<String> getCourses(String dept_code) {
		String query = "SELECT DISTINCT Catalog_Num FROM CLASSES WHERE Subject_Code = '" 
				+ dept_code + "' ORDER BY Catalog_Num;";
		return getQueryResults(query, "Catalog_Num");
	}
	
	/*
	 * Returns all courses within a department, given its cdoe, as an arraylist of strings
	 */
	public ArrayList<String> getAllComponents(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "'";
		return getQueryResults(query, "Class_Number");
	}
	
	/*
	 * Returns the course numbers for each lecture section of the given class
	 */
	public ArrayList<String> getLectures(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'LEC'";
		return getQueryResults(query, "Class_Number");
	}
	
	/*
	 * Returns the course numbers for each lab section of the given class
	 */
	public ArrayList<String> getLabratories(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'LAB'";
		return getQueryResults(query, "Class_Number");
	}
	
	/*
	 * Returns the course numbers for each discussion section of the given class
	 */
	public ArrayList<String> getDiscussions(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'DIS'";
		return getQueryResults(query, "Class_Number");
	}
	
	/*
	 * Returns the course numbers for each recitation section of the given class
	 */
	public ArrayList<String> getRecitations(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'REC'";
		return getQueryResults(query, "Class_Number");
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
