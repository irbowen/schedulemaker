package com.schedule_maker;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * The root class for the RESTful service
 */
@Path("/")
public class ScheduleMaker {
	
	/*
	 * Converts an arraylist of strings to json
	 */
	@SuppressWarnings("unchecked")
	private JSONObject toJSON(ArrayList<String> list) {
		JSONArray arr = new JSONArray();
		for(String item : list) {
			arr.add(item);
		}
		JSONObject json = new JSONObject();
		json.put("data", arr);
		return json;
	}
	
	@GET
	/*
	 * Just for initializing db
	 */
	public Response getMsg() {
	//	DatabaseConnection conn = new DatabaseConnection();conn.createTables();Parse myParse = new Parse();myParse.parseFile();
		return Response.status(200).entity("Doing things").build(); 
	}
	
	/*
	 * Returns the list of all departments in JSOM form
	 * TODO: Change @Produces to application/json after testing
	 */
	@Path("/departments")
	@GET
	@Produces("text/html")
	public Response getDepartments() {
		Query q = new Query();
		JSONObject json = toJSON(q.getDepartments());
		return Response.status(200).entity(json.toString()).build(); 
	}
	
	/*
	 * Returns the list of all departments in JSOM form
	 * TODO: Change @Produces to application/json after testing
	 */
	@Path("/departments/{code}/classes")
	@GET
	@Produces("text/html")
	public Response getCoursesByDeparment(@PathParam("code") String code) {
		Query q = new Query();
		JSONObject json = toJSON(q.getCourses(code));
		return Response.status(200).entity(json.toString()).build(); 
	}
}
