package com.schedule_maker;

public class ClassType {
	public String CourseNumber;
	public int Monday;
	public int Tuesday;
	public int Wednesday;
	public int Thursday;
	public int Friday;
	public String startTime;
	public String endTime;
	
	public void print() {
		String tempString = "";
		if(Monday==1) {
			tempString += "M, ";
		}
		if(Tuesday==1) {
			tempString += "T, ";
		}
		if(Wednesday==1) {
			tempString += "W, ";
		}
		if(Thursday==1) {
			tempString += "Th, ";
		}
		if(Friday==1) {
			tempString += "F, ";
		}
		System.out.println("Days: " + tempString + "\nTimes: " + startTime + "-" + endTime);
	}
}
