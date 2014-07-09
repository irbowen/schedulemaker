package com.schedule_maker;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

/*
 *
 */
@Path("/")
public class View {
	
	private static ArrayList<String> departments;
	private static JSONObject json;
	
	@GET
	public Response getMsg() {
		String output = "Hellow world!"; 
		return Response.status(200).entity(output).build();
	}
	
	@Path("/departments")
	@GET
	@Produces("application/json")
	public Response getDepartments() {
		if(departments == null) {
			return Response.status(200).entity("to implement").build(); 
		}
		return Response.status(200).entity("to implement").build(); 
	}
}
