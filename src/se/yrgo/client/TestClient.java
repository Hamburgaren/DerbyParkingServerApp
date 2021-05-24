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
		ParkingTicket ticketGET = response.readEntity(ParkingTicket.class);
		
		Response responseGET = client.target("http://localhost:8080/ParkingTicketManagement/webservice/parkingtickets/1").request("application/JSON").buildDelete().invoke();
		System.out.println(responseGET.getHeaders() + " GET WAS GOOD"); 

		
//		
//		// CREATE TICKET AND POST IT 
//		DATE START = NEW DATE("SAT, 12 AUG 1995 13:30:00 GMT");
//	    DATE END = NEW DATE("SUN, 13 AUG 1995 13:30:00 GMT");
//	    CAR CAR1 = NEW CAR("YRG 023", "LJUSBLÅ DACIA");
//	    CUSTOMER CUSTOMER1 = NEW CUSTOMER("OLA", "CONNY");
//		PARKINGTICKET TICKETPOST = NEW PARKINGTICKET(START, END, 5, "ROLIGA GATAN 2", CAR1, CUSTOMER1);
//
//		ENTITY TICKETENTITY = ENTITY.ENTITY(TICKETPOST, "APPLICATION/JSON");
//		RESPONSE RESPONSEPOST = CLIENT.TARGET("HTTP://LOCALHOST:8080/PARKINGTICKETMANAGEMENT/WEBSERVICE/PARKINGTICKETS/").REQUEST().BUILDPOST(TICKETENTITY).INVOKE();
//		SYSTEM.OUT.PRINTLN(RESPONSE.GETSTATUS() + RESPONSE.READENTITY(PARKINGTICKET.CLASS).GETID());
//		
		responseGET.close();
//		responsePOST.close();
	    

	}

}
