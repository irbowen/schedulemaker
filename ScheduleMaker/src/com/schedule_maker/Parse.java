package com.schedule_maker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/*
 * For parsing the class csv file
 */
public class Parse {
	
	/*
	 * The connection to the database
	 */
	private DatabaseConnection conn;
	
	/*
	 * CHANGE PATH
	 */
	private static final String PATH_TO_FILE= "C:\\Users\\Isaac\\Downloads\\FA2014.csv";
	
	/*
	 * Initializes the connection to the database
	 */
	public Parse() {
		conn = new DatabaseConnection();
	}
	
	/*
	 * Parse the file, line by line
	 */
	public void parseFile() {
		try {
			FileReader file = new FileReader(PATH_TO_FILE);
			BufferedReader bf = new BufferedReader(file);
			String line = bf.readLine();
			while((line = bf.readLine()) != null) {
				ArrayList<String> items = this.parseLine(line);
				if(!items.isEmpty()) {
					String insertStmt = createInsertStatement(items);
					conn.createOrUpdate(insertStmt);
				}
			}
			bf.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Returns an ArrayList<String> containing each field in the csv file
	 * Returns an empty ArrayList if there is a problem with the line
	 */
	public ArrayList<String> parseLine(String line) {
		ArrayList<String> items = new ArrayList<String>();
		String[] array = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		for(int i=0; i<array.length; i++){
			items.add(array[i].replaceAll("\"", ""));
		}
		if(items.get(19).equals("ARR") || items.get(20).equals("ARR")) {
			return new ArrayList<String>();
		}
		return items;
	}
	
	/*
	 * Turns the array list into a SQL state that can be inserted into the classes table
	 */
	private String createInsertStatement(ArrayList<String> items) {
		for(int i=0; i<items.size(); i++) {
			items.set(i, items.get(i).replaceAll("'", ""));
		}
		String Session = items.get(1);
		String School = items.get(2);
		String Class_Number = items.get(3);
		String Subject = items.get(4);
		String Subject_Code = getSubjectCode(Subject);
		String Catalog_Num = items.get(5).replace(" " , "");
		String Section_Num = items.get(6);
		String Course_Title = items.get(7);
		String Component = items.get(8);
		int Monday = dayToInt(items.get(10));
		int Tuesday = dayToInt(items.get(11));
		int Wednesday = dayToInt(items.get(12));
		int Thursday = dayToInt(items.get(13));
		int Friday = dayToInt(items.get(14));
		String time = items.get(19);
		ArrayList<Double> times = parseTime(time);
		double Start_Time = times.get(0);
		double End_Time = times.get(1);
		String Location = items.get(20);
		String Instructor = items.get(21);
		String insertStatement =
			"INSERT OR IGNORE INTO CLASSES ( " +
				"Session, School, Class_Number, Subject, Subject_Code, Catalog_Num, " + 
				"Section_Num, Course_Title, Component, Monday, Tuesday, Wednesday, Thursday, " + 
				"Friday, Start_Time, End_Time, Location, Instructor) " +
			"VALUES ( " +
				"'" + Session + "', " + 
				"'" + School + "', " + 
				"'" + Class_Number  + "', " +
				"'" + Subject + "', " + 
				"'" + Subject_Code + "', " + 
				"'" + Catalog_Num  + "', " +
				"'" + Section_Num  + "', " +
				"'" + Course_Title + "', " + 
				"'" + Component + "', " + 
				Monday + ", " +
				Tuesday + ", " +
				Wednesday + ", " +
				Thursday + ", " +
				Friday + ", " +
				Start_Time + ", " +
				End_Time + ", " +
				"'" + Location + "', " + 
				"'" + Instructor + "' " + 
			");";
		return insertStatement;
	}
	
	/*
	 * Retrieves the short code version (EECS vs Electrical Engineering and...) from the whole string 
	 */
	private String getSubjectCode(String inputString) {
		int firstIndex = inputString.indexOf('(');
		int secondIndex = inputString.indexOf(')');
		return inputString.substring(firstIndex+1, secondIndex);
	}
	
	/*
	 * The way the spreadsheet is formatted, if that days column has something in it, then there
	 * is a class that day.  This returns an int value based on the string
	 */
	private int dayToInt(String day) {
		if(day.equals("")) {
			return 0;
		}
		return 1;
	}
	
	/*
	 * Might not work perfectly
	 */
	private ArrayList<Double> parseTime(String time) {
		String[] things = time.split("-");
		String startTime = things[0];
		String endTime = things[1].substring(0, things[1].length()-2);
		String AM_PM = things[1].substring(3);
		try {
			if(startTime.indexOf('3') != -1 && startTime.length() > 1) {
				startTime = startTime.substring(0,startTime.length()-2) + ".5";
			}
			if(endTime.indexOf('3') != -1 && endTime.length() > 1) {
				endTime = endTime.substring(0,endTime.length()-2) + ".5";
			}
		}
		catch(Exception e) {
			System.out.println("Start: " + startTime + " End: " + endTime);
		}
		ArrayList<Double> list = new ArrayList<Double>();
		if(AM_PM.equals("PM") && Double.parseDouble(startTime) < 12) {
			list.add(Double.parseDouble(startTime) + 12);
		}
		else {
			list.add(Double.parseDouble(startTime));
		}
		
		if(AM_PM.equals("PM") && Double.parseDouble(endTime) < 12){
			list.add(Double.parseDouble(endTime) + 12);
		}
		else  {
			list.add(Double.parseDouble(endTime));
		}
		
		if(AM_PM.equals("AM")) {
			list.add(Double.parseDouble(startTime));
			list.add(Double.parseDouble(endTime));
		}
		return list;
	}
}
