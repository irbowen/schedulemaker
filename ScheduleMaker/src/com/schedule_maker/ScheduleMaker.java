package com.schedule_maker;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 *
 */
@Path("/")
public class ScheduleMaker {
	
	@SuppressWarnings("unchecked")
	private JSONObject construct(ArrayList<String> list) {
		JSONArray arr = new JSONArray();
		for(String item : list) {
			arr.add(item);
		}
		JSONObject json = new JSONObject();
		json.put("departments", arr);
		return json;
	}
	
	@GET
	/*
	 * Just for initializing db
	 */
	public Response getMsg() {
		DatabaseConnection conn = new DatabaseConnection();
		conn.createTables();
		Parse myParse = new Parse();
		myParse.parseFile();
		return Response.status(200).entity("Doing things").build(); 
	}
	
	@Path("/departments")
	@GET
	@Produces("text/html")
	public Response getDepartments() {
		Query q = new Query();
		JSONObject json = construct(q.getDepartments());
		return Response.status(200).entity(json.toString()).build(); 
	}
}
