package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DBrepository.LoginRepository;
import DBrepository.PatientRepository;


@Path("patient")
public class PatientService {
	
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getPatient()
	{
//		Patient patient=new Patient();
//		
//		
//		patient.setNIC("981254793v");
//		patient.setFirstName("Malidi");
//		patient.setLastName("Wageesha");
//		patient.setEmail("malidi@gmail.com");
//		patient.setGender("female");
//		patient.setAddress("rathnapura");
//		patient.setPassword("1234");
//		patient.setCity("kuruwita");
//		patient.setContact("0771526879");
//		
//		
//		return patient;
		return "Welcome to patient API";
	}


	PatientRepository repo=new PatientRepository();

	
	
	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String CreatePatient(@FormParam("NIC") String NIC,@FormParam("firstname") String firstname,@FormParam("lastname") String lastname,@FormParam("email") String email,@FormParam("gender") String gender,@FormParam("address") String address,@FormParam("password") String password,@FormParam("city") String city,@FormParam("contact") String contact)
	{
		System.out.println(NIC);
		
		if(NIC!=null)
		{
			
			String output = repo.createPatientAsForm(NIC, firstname, lastname, email, gender, address, password, city, contact);
			return output;
			
			
		}else {
			
			System.out.println("its null");
			return null;
		}
		
	}
	
	
	@PUT
	@Path("update")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	public String UpdatePatient(String AppData)
	{
		JsonObject itemObject = new JsonParser().parse(AppData).getAsJsonObject();

		
	int patientID=itemObject.get("patientID").getAsInt();
	String NIC=itemObject.get("NIC").getAsString();
	String firstName=itemObject.get("firstName").getAsString();
	String lastName=itemObject.get("lastName").getAsString();
	String email=itemObject.get("email").getAsString();
	String gender=itemObject.get("gender").getAsString();
	String address=itemObject.get("address").getAsString();
	String password=itemObject.get("password").getAsString();
	String city=itemObject.get("city").getAsString();
	String contact=itemObject.get("contact").getAsString();
	
	String object=repo.UpdatePatient(patientID,NIC,firstName,lastName,email,gender,address,password,city,contact);
	return object;
		
	}
	

	
	@GET
	@Path("/id")
	@Produces({ MediaType.TEXT_HTML })
	@Consumes(MediaType.APPLICATION_JSON)
	public String getPatientByID(String id)
	{
		JsonObject itemObject = new JsonParser().parse(id).getAsJsonObject();
		String patientID = itemObject.get("patientID").getAsString();
		System.out.println(patientID);
		return repo.getPatient(patientID);
	
	}
	
	@GET
	@Path("/all")
	@Produces({ MediaType.TEXT_HTML })
	public String GetallPatients() {
		return repo.getAllPatients();
	}
	
	
}