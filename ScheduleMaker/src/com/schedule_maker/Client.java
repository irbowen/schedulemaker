/*
 * Isaac Bowen
 * 2014-7-3
 */

package com.schedule_maker;

/*
 * A client for testing the code
 */
public class Client {

	/*
	 * The driver
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseConnection conn = new DatabaseConnection();
		conn.createTables();
		Parse myParse = new Parse();
		myParse.parseFile();
		Course eecs381 = new Course("EECS", "481");
		Course a = new Course("EECS", "485");
		Course aa = new Course("EECS", "496");
		Course aaa = new Course("EECS", "584");
		Course aaaa = new Course("EECS", "587");
		Course eecs3a81 = new Course("EECS", "381");
	}

}
