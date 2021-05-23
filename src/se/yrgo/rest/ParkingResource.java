package se.yrgo.rest;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import se.yrgo.domain.Car;
import se.yrgo.domain.Customer;
import se.yrgo.domain.Employee;
import se.yrgo.domain.ParkingTicket;
import se.yrgo.service.EmployeeManagementServiceLocal;
import se.yrgo.service.ParkingService;


@Stateless
@Path("/parkingtickets/")
public class ParkingResource {
	
	@Inject
	private ParkingService service;

	@GET
	@Produces("application/JSON") // @Produces("application/XML")
	@Path("{ticketId}")
	/**
	 *  You can access this REST interface from: http://localhost:8080/EmployeeManagement/webservice/parkingtickets/5
	 */
	public Response findTicketById(@PathParam("ticketId") int id) {
		service.findTicketById(id);
		/* This is just some meaningless test code to check that we are able to make requests. */
		if (id == 5) {
			Car car1 = new Car("ZER 992", "grey Lada");
			Customer customer1 = new Customer("George", "Costanza");
			
			ParkingTicket ticket = new ParkingTicket(2, 15, "�gatan 5", car1, customer1);
			return Response.ok(ticket).build();
		}
		
		return Response.status(404).build();
	}

	@DELETE
	@Produces("application/JSON") // @Produces("application/XML")
	@Path("{ticketId}")
	/**
	 *  You can access this REST interface from: http://localhost:8080/EmployeeManagement/webservice/parkingtickets/5
	 */
	public Response deleteTicketById(@PathParam("ticketId") int id) {
		service.deleteTicket(id);
		/* This is just some meaningless test code to check that we are able to make requests. */
		if (id == 5) {
			return Response.ok(null).build();
		}

		return Response.status(404).build();
	}
	
	/**
	 *  You can access this REST interface from: http://localhost:8080/EmployeeManagement/webservice/parkingtickets/
	 *  
	 *  N.B. There must be a JSON formatted ParkingTicket string posted also. 
	 */
	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	public Response createTicket(ParkingTicket ticket) {
		
		// TODO: check that this works and error handling works.
		try {
			service.createTicket(ticket);
		} catch (Exception ex) {
			return Response.serverError().build();
		}
		return Response.ok(ticket).build();
	}

}
