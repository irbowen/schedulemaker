package com.schedule_maker;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Query extends DatabaseConnection {
	
	/*
	 * 
	 */
	public static ArrayList<String> departments = new ArrayList<String>();
	
	public Query() {
		super();
	}

	/*
	 * Returns all departments in the DB in an arraylist of strings
	 */
	public ArrayList<String> getDepartments() {
		if(!departments.isEmpty()) {
			return departments;
		}
		try {
			String queryString = "SELECT DISTINCT Subject_Code FROM CLASSES ORDER BY Subject_Code;";
			Statement stmt;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			while(rs.next()) {
				String temp = "";
				temp += rs.getString("Subject_Code");
				departments.add(temp);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return departments;
	}
}
