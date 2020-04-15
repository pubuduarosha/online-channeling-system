package com;

import java.sql.Date;
import java.sql.Time;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.*;

@Path("/appointment")
public class AppointmentService {

	Appointment appObj = new Appointment();

	@GET
	@Path("/")
	@Produces({ MediaType.TEXT_HTML })
	public String GetallApp() {
		return appObj.GetAllAppoinments();
	}

	@GET
	@Path("/j")
	@Produces({ MediaType.TEXT_HTML })
	@Consumes(MediaType.APPLICATION_JSON)
	public String GetStudent (String id) {
		
		JsonObject itemObject = new JsonParser().parse(id).getAsJsonObject();
		String appointmentID = itemObject.get("appointmentID").getAsString();
		return appObj.GetAppoinments(appointmentID);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("date") String date, @FormParam("time") String time,
			@FormParam("hospitalID") int hospitalID, @FormParam("patientID") int patientID,
			@FormParam("doctorID") int doctorID, @FormParam("paymentID") int paymentID) {
		String output = appObj.insertAppoinment(date, time, hospitalID, patientID, doctorID, paymentID);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String AppData) {
		JsonObject itemObject = new JsonParser().parse(AppData).getAsJsonObject();

		String appointmentID = itemObject.get("appointmentID").getAsString();
		String date = itemObject.get("date").getAsString();
		String time = itemObject.get("time").getAsString();
		String hospitalID = itemObject.get("hospitalID").getAsString();
		String patientID = itemObject.get("patientID").getAsString();
		String doctorID = itemObject.get("doctorID").getAsString();
		String paymentID = itemObject.get("paymentID").getAsString();
		String appointmentStatus = itemObject.get("appointmentStatus").getAsString();
		String refundID = itemObject.get("refundID").getAsString();
		
		String output = appObj.updateAppoinment(appointmentID, date, time, hospitalID, patientID,doctorID,paymentID,appointmentStatus,refundID);
		return output; 
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String DelAppoinment(String ApplicationID) {
		
		Document doc= Jsoup.parse(ApplicationID, "", Parser.xmlParser());
		
		String AppId = doc.select("AID").text();	
		String output = appObj.DeleteAppoinment(AppId); 
		return output;
	}

}