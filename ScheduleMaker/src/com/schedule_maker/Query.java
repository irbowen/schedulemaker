package com.schedule_maker;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	public JSONArray getDepartments() {
		String query = "SELECT DISTINCT Subject_Code, Subject FROM CLASSES ORDER BY Subject_Code;";
		String[] array = {"Subject_Code", "Subject"};
		return getQueryResults(query, array);
	}

	/*
	 * Returns all courses within a department, given its cdoe, as an arraylist of strings
	 */
	public JSONArray getCourses(String dept_code) {
		String query = "SELECT DISTINCT Catalog_Num, Course_Title FROM CLASSES WHERE Subject_Code = '" 
				+ dept_code + "' ORDER BY Catalog_Num;";
		String[] array = {"Catalog_Num", "Course_Title"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns all courses within a department, given its code, as an arraylist of strings
	 */
	public JSONArray getAllComponents(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "'";
		String[] array = {"Class_Number"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns the course numbers for each lecture section of the given class
	 */
	public JSONArray getLectures(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'LEC'";
		String[] array = {"Class_Number"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns the course numbers for each lab section of the given class
	 */
	public JSONArray getLabratories(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'LAB'";
		String[] array = {"Class_Number"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns the course numbers for each discussion section of the given class
	 */
	public JSONArray getDiscussions(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'DIS'";
		String[] array = {"Class_Number"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns the course numbers for each recitation section of the given class
	 */
	public JSONArray getRecitations(String dept_code, String catalog_num) {
		String query = "SELECT Class_Number FROM CLASSES WHERE Subject_Code = '" + dept_code + 
						"' AND Catalog_Num = '" + catalog_num + "' AND Component = 'REC'";
		String[] array = {"Class_Number"};
		return getQueryResults(query, array);
	}
	
	/*
	 * Returns the query results in an array list
	 */
	public JSONArray getQueryResults(String query, String[] params) {
		JSONArray arr = new JSONArray();
		try {
			Statement stmt;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				for(String item : params) {
					obj.put(item, rs.getString(item));
				}
				arr.add(obj);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
}
