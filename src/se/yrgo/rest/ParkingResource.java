package se.yrgo.rest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import se.yrgo.domain.Car;
import se.yrgo.domain.Customer;
import se.yrgo.domain.ParkingTicket;
import se.yrgo.domain.StorageAccessException;
import se.yrgo.service.ParkingService;


@Stateless
@Path("/parkingtickets/")
public class ParkingResource {
	
	@Inject
	private ParkingService service;

	@GET
	@Produces("application/JSON") //@Produces("application/JSON") //
	@Path("{ticketId}")
	/**
	 *  You can access this REST interface from: http://localhost:8080/EmployeeManagement/webservice/parkingtickets/5
	 */
	public Response findTicketById(@PathParam("ticketId") int id) {
		ParkingTicket resultTicket;
		try {
			resultTicket = service.findTicketById(id);
		} catch (Exception ex) {
			return Response.serverError().entity(ex.getLocalizedMessage()).build();
		}
		if (resultTicket == null) {
			return Response.status(404).build();
		}
		return Response.ok(resultTicket).build();
		
		/* This is just some meaningless test code to check that we are able to make requests. */
		/*
		if (id == 5) {
			Car car1 = new Car("ZER 992", "grey Lada");
			Customer customer1 = new Customer("George", "Costanza");

			Date timeNow = new Date();
			int millisecondsInMinute = 1000*60;
			int parkingLengthInMinutes = 120;
			ParkingTicket ticket = new ParkingTicket(timeNow, new Date(timeNow.getTime() + parkingLengthInMinutes * millisecondsInMinute), 2,15, "Långgatan 5", car1, customer1);
			//ParkingTicket ticket = new ParkingTicket(timeNow, timeNow, 2,	15, "Långgatan 5", car1, customer1);
			return Response.ok(ticket).build();
		}

		return Response.status(404).build();
		*/
	}

	//TODO: PUT operation that assigns new values to existing to ParkingTicket.
	@PUT
	@Consumes("application/JSON")
	@Produces("application/JSON")
	public Response updateTicket(ParkingTicket newTicket) {
		return Response.ok(newTicket).build();
	}


	@DELETE
	@Produces("application/JSON") // @Produces("application/XML")
	@Path("{ticketId}")
	/**
	 *  You can access this REST interface from: http://localhost:8080/EmployeeManagement/webservice/parkingtickets/5
	 */
	public Response deleteTicketById(@PathParam("ticketId") int id) {
		
		try {
			if (service.deleteTicket(id) == false) {
				return Response.status(404).build();
			}
		} catch (Exception ex) {
			return Response.serverError().entity(ex.getLocalizedMessage()).build();
		}
		
		return Response.ok(null).build();
		
		/* This is just some meaningless test code to check that we are able to make requests. */
		/*
		if (id == 5) {
			return Response.ok(null).build();
		}
		*/

		
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
			return Response.serverError().entity(ex.getLocalizedMessage()).build();
		}
		return Response.ok(ticket).build();
	}

}
