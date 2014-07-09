package com.schedule_maker;

import java.sql.*;
import java.util.ArrayList;

/*
 * 
 */
public class Course extends DatabaseConnection{
	
	/*
	 * 
	 */
	private String SubjectCode;//EECS, AERO, MECH, MATH
	private String CatalogNumber;//370, 381
	private String School;//Eng, LSA, etc
	private String CourseTitle;//Whatever
	private boolean DIS_boolean;
	private boolean LAB_boolean;
	private boolean LEC_boolean;
	private boolean REC_boolean;
	//An array list containing all of the class_numbers
	private ArrayList<ClassType> DIS;
	private ArrayList<ClassType> LAB;
	private ArrayList<ClassType> LEC;
	private ArrayList<ClassType> REC;
	
	/*
	 * 
	 */
	public Course(String subjectCode, String catalogNumber) {
		SubjectCode = subjectCode;
		CatalogNumber = catalogNumber;
		DIS = new ArrayList<ClassType>();
		LAB = new ArrayList<ClassType>();
		LEC = new ArrayList<ClassType>();
		REC = new ArrayList<ClassType>();
		this.buildCourse();
	}
	
	/*
	 * Query the database to construct the course object
	 */
	private void buildCourse() {
		try {
			Statement stmt = conn.createStatement();
			String courseQuery = "SELECT * FROM CLASSES WHERE Subject_Code = '" + SubjectCode + "' AND Catalog_Num = '" + CatalogNumber + "';";
			ResultSet rs = stmt.executeQuery(courseQuery);
			while(rs.next()) {
				School = rs.getString("School");
				CourseTitle = rs.getString("Course_Title");			
				if(rs.getString("Component").equals("DIS")) {
					DIS_boolean = true;
					this.buildList("DIS");
				}
				else if(rs.getString("Component").equals("LAB")) {
					LAB_boolean = true;
					this.buildList("LAB");	
				}
				else if(rs.getString("Component").equals("LEC")) {
					LEC_boolean = true;
					this.buildList("LEC");
				}
				else if(rs.getString("Component").equals("REC")) {
					REC_boolean = true;
					this.buildList("REC");
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("SubjectCode: " + SubjectCode);
		System.out.println("CatalogNumber: " + CatalogNumber);
		System.out.println("School: " + School);
		System.out.println("CourseTitle: " + CourseTitle);
		for(ClassType tempClassType : DIS) {
			System.out.println(tempClassType.CourseNumber + " " +tempClassType.StartTime + " " + tempClassType.EndTime);
		}
		for(ClassType tempClassType : LAB) {
			System.out.println(tempClassType.CourseNumber + " " +tempClassType.StartTime + " " + tempClassType.EndTime);
		}
		for(ClassType tempClassType : LEC) {
			System.out.println(tempClassType.CourseNumber + " " +tempClassType.StartTime + " " + tempClassType.EndTime);
		}
		for(ClassType tempClassType : REC) {
			System.out.println(tempClassType.CourseNumber + " " +tempClassType.StartTime + " " + tempClassType.EndTime);
		}
	}
	
	/*
	 * Builds the list of the discussion, lab, and lecture components for the course
	 */
	private void buildList(String option) {
		try {
			Statement stmt = conn.createStatement();
			String courseQuery = "SELECT * FROM CLASSES WHERE Subject_Code = '" + SubjectCode + "' " +
				"AND Catalog_Num = '" + CatalogNumber + "'" + 
				"AND Component = '" + option + "';";
			ResultSet rs = stmt.executeQuery(courseQuery);
			while(rs.next()) {
				int class_num = Integer.parseInt(rs.getString("Class_Number"));
				boolean monday = rs.getInt("Monday") == 1;
				boolean tuesday = rs.getInt("Tuesday") == 1;
				boolean wednesday = rs.getInt("Wednesday") == 1;
				boolean thursday = rs.getInt("Thursday") == 1;
				boolean friday = rs.getInt("Friday") == 1;
				String startTime = rs.getString("Start_TIme");
				String endTime = rs.getString("End_Time");
				ClassType cType = new ClassType(class_num, monday, tuesday, wednesday, thursday, friday, startTime, endTime);
				switch(option) {
					case "DIS":
						DIS.add(cType);
						break;
					case "LAB":
						LAB.add(cType);
						break;
					case "LEC":
						LEC.add(cType);
						break;
					case "REC":
						REC.add(cType);
						break;
					default:
						break;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
