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
	
	/*
	 * Just for initializing db
	 */
	@GET
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
	@Path("/departments/{dept_code}/classes")
	@GET
	@Produces("text/html")
	public Response getCoursesByDeparment(@PathParam("dept_code") String dept_code) {
		Query q = new Query();
		JSONObject json = toJSON(q.getCourses(dept_code));
		
		return Response.status(200).entity(json.toString()).build(); 
	}
	
	/*
	 * Returns the list of all lecture course numbers
	 * TODO: Change @Produces to application/json after testing
	 */
	@Path("/departments/{dept_code}/classes/{catalog_num}")
	@GET
	@Produces("text/html")
	public Response getAllComponents(@PathParam("dept_code") String dept_code, @PathParam("catalog_num") String catalog_num) {
		Query q = new Query();
		JSONObject json = toJSON(q.getAllComponents(dept_code, catalog_num));
		return Response.status(200).entity(json.toString()).build(); 
	}
	
	/*
	 * Returns the list of all lecture course numbers
	 * TODO: Change @Produces to application/json after testing
	 */
	@Path("/departments/{dept_code}/classes/{catalog_num}/lec")
	@GET
	@Produces("text/html")
	public Response getLectures(@PathParam("dept_code") String dept_code, @PathParam("catalog_num") String catalog_num) {
		Query q = new Query();
		JSONObject json = toJSON(q.getLectures(dept_code, catalog_num));
		return Response.status(200).entity(json.toString()).build(); 
	}
	
	/*
	 * Returns the list of all departments in JSOM form
	 * TODO: Change @Produces to application/json after testing
	 */
	@Path("/departments/{dept_code}/classes/{catalog_num}/lab")
	@GET
	@Produces("text/html")
	public Response getLabratories(@PathParam("dept_code") String dept_code, @PathParam("catalog_num") String catalog_num) {
		Query q = new Query();
		JSONObject json = toJSON(q.getLabratories(dept_code, catalog_num));
		return Response.status(200).entity(json.toString()).build(); 
	}
	
	/*
	 * Returns the list of all departments in JSOM form
	 * TODO: Change @Produces to application/json after testing
	 */
	@Path("/departments/{dept_code}/classes/{catalog_num}/dis")
	@GET
	@Produces("text/html")
	public Response getDiscussions(@PathParam("dept_code") String dept_code, @PathParam("catalog_num") String catalog_num) {
		Query q = new Query();
		JSONObject json = toJSON(q.getDiscussions(dept_code, catalog_num));
		return Response.status(200).entity(json.toString()).build(); 
	}
	
	/*
	 * Returns the list of all departments in JSOM form
	 * TODO: Change @Produces to application/json after testing
	 */
	@Path("/departments/{dept_code}/classes/{catalog_num}/rec")
	@GET
	@Produces("text/html")
	public Response getRecitations(@PathParam("dept_code") String dept_code, @PathParam("catalog_num") String catalog_num) {
		Query q = new Query();
		JSONObject json = toJSON(q.getRecitations(dept_code, catalog_num));
		return Response.status(200).entity(json.toString()).build(); 
	}
}
