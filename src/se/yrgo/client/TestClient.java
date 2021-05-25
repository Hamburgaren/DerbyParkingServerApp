package se.yrgo.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import se.yrgo.domain.Car;
import se.yrgo.domain.Customer;
import se.yrgo.domain.ParkingTicket;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class TestClient {

	public static void main(String[] args) {
		
		// GET TICKET BY ID
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/ParkingTicketManagement/webservice/parkingtickets/1").request("application/JSON").buildGet().invoke();
		System.out.println(response.getHeaders() + " GET WAS GOOD"); 
		response.close();
		
//		
		// CREATE TICKET AND POST IT 
		Date start = new Date("SAT, 12 AUG 1995 13:30:00 GMT");
	    Date end = new Date("SUN, 13 AUG 1995 13:30:00 GMT");
	    Car car1 = new Car("YRG 023", "LJUSBLï¿½ DACIA");
	    Customer customer1 = new Customer("OLA", "CONNY");
		ParkingTicket ticketPost = new ParkingTicket(start, end, 5, "ROLIGA GATAN 2", car1, customer1);
		
		Entity TicketEntity = Entity.entity(ticketPost, "APPLICATION/JSON");
		response = client.target("http://localhost:8080/ParkingTicketManagement/webservice/parkingtickets").request().buildPost(TicketEntity).invoke();
		System.out.println(response.getStatus() + " a parking ticket was posted.");
		response.close();
		
		//  UPDATE/PUT new information to a ParkingTicket
		ticketPost.setPricePerhour(5000);
		Entity ticketEntityPut = Entity.entity(ticketPost, "APPLICATION/JSON");
		response = client.target("http://localhost:8080/ParkingTicketManagement/webservice/parkingtickets/1").request("application/JSON").buildPut(ticketEntityPut).invoke();
//
//		response = client.target("http://localhost:8080/ParkingTicketManagement/webservice/parkingtickets/1").request().buildGet().invoke();
//		ParkingTicket ticketGET = response.readEntity(ParkingTicket.class);
		
//		System.out.println(ticketGET); 
		response.close();
		

		System.out.println(response.getStatus() + " ticket " + ticketPost.getId() + " was updated.");
		response.close();
		
	//  DELETE TICKET WITH ID 10
		response = client.target("http://localhost:8080/ParkingTicketManagement/webservice/parkingtickets/10").request("application/JSON").buildDelete().invoke();
		System.out.println(response.getStatus() + " OLA CONNY with id 10 was deleted");
		response.close();
	}

}
