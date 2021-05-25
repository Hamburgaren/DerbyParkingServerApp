package se.yrgo.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import se.yrgo.domain.Car;
import se.yrgo.domain.Customer;
import se.yrgo.domain.ParkingTicket;
import java.util.Date;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;

public class TestClient {

	public static void main(String[] args) {
		// create client object
		Client client = ClientBuilder.newClient();

		// CREATE TICKET AND POST IT 
		Date start = new Date("SAT, 12 AUG 1995 13:30:00 GMT");
	    Date end = new Date("SUN, 13 AUG 1995 13:30:00 GMT");
	    Car car1 = new Car("YRG 023", "LJUSBLï¿½ DACIA");
	    Customer customer1 = new Customer("OLA", "CONNY");
		ParkingTicket ticketPost = new ParkingTicket(start, end, 5, "ROLIGA GATAN 2", car1, customer1);
		
		Entity TicketEntity = Entity.entity(ticketPost, "APPLICATION/JSON");
		Response response = client.target("http://localhost:8080/ParkingTicketManagement/webservice/parkingtickets").request().buildPost(TicketEntity).invoke();
		ParkingTicket ticket = response.readEntity(ParkingTicket.class);
		System.out.println(response.getStatus() + " - " + ticket.getId() + " a parking ticket was posted.");
		response.close();
		
		// Testing GET
		
		response = client.target("http://localhost:8080/ParkingTicketManagement/webservice/parkingtickets/" + ticket.getId()).request("application/JSON").buildGet().invoke();
		
		ParkingTicket ticketGET = null;
		if (response.getStatus() == 200) {
			ticketGET = response.readEntity(ParkingTicket.class);
			System.out.println(response.getStatus() + ":" + " GET was success. Ticket with id: " + ticketGET.getId() + " was retrieved."); 
		}
		else if (response.getStatus() == 404){
			System.out.println("Ticket does not exist.");
		}
		else {
			System.out.println("Something went very wrong...");
		}
		
		response.close();
		
		
		//  Testing PUT. PUT new information to a ParkingTicket.
		response = client.target("http://localhost:8080/ParkingTicketManagement/webservice/parkingtickets/" + ticket.getId()).request("application/JSON").buildGet().invoke();
		ticketGET.setPricePerhour(100);
		Entity ticketEntityPut = Entity.entity(ticketGET, "APPLICATION/JSON");
		
		response.close();
		response = client.target("http://localhost:8080/ParkingTicketManagement/webservice/parkingtickets/").request("application/JSON").buildPut(ticketEntityPut).invoke();
		
		ParkingTicket ticketUpdate = null;
		if (response.getStatus() == 200) {
			ticketUpdate = response.readEntity(ParkingTicket.class);
			System.out.println(response.getStatus() + ":" + " Update was success. Ticket with id: " + ticketUpdate.getId() + " was updated."); 
		}
		else if (response.getStatus() == 404){
			System.out.println("Ticket does not exist. Update failed.");
		}
		else {
			System.out.println("Something went very wrong...");
		}
 
		response.close();

	//  Testing DELETE. DELETE TICKET AGAIN.
		
		response = client.target("http://localhost:8080/ParkingTicketManagement/webservice/parkingtickets/" + ticketUpdate.getId()).request("application/JSON").buildDelete().invoke();
		
		if (response.getStatus() == 200) {
			System.out.println(response.getStatus() + ": Delete was success. Ticket with id: " + ticketUpdate.getId() + " was removed.");
		}
		else if (response.getStatus() == 404){
			System.out.println("Ticket does not exist. Delete failed.");
		}
		else {
			System.out.println("Something went very wrong...");
		}
		
		response.close();
	}

}
