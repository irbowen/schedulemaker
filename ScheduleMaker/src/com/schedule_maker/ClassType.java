package com.schedule_maker;

/*
 * A class that is used with the Course object to hold the time and course number information
 * for each section of each component of the class
 */
public class ClassType {
	
	/*
	 * CourseNumber is the unique course identifier
	 */
	public int CourseNumber;
	public boolean Monday;
	public boolean Tuesday;
	public boolean Wednesday;
	public boolean Thursday;
	public boolean Friday;
	public String StartTime;
	public String EndTime;
	
	/*
	 * 
	 */
	public ClassType(int classNum, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, String startTime, String endTime) {
		CourseNumber = classNum;
		Monday = monday;
		Tuesday = tuesday;
		Wednesday = wednesday;
		Thursday = thursday;
		Friday = friday;
		StartTime = startTime;
		EndTime = endTime;
	}

	/*
	 * 
	 */
	public String toString() {
		String tempString = "";
		if(Monday) {
			tempString += "M, ";
		}
		if(Tuesday) {
			tempString += "T, ";
		}
		if(Wednesday) {
			tempString += "W, ";
		}
		if(Thursday) {
			tempString += "Th, ";
		}
		if(Friday) {
			tempString += "F, ";
		}
		return "Days: " + tempString + "\nTimes: " + StartTime + "-" + EndTime;
	}
}
