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
import se.yrgo.exceptions.ParkingTicketDoesNotExistException;
import se.yrgo.exceptions.StorageAccessException;
import se.yrgo.service.ParkingService;


@Stateless
@Path("/parkingtickets/")
/**
 * 
 * REST interface for manipulating ParkingTicket Entity.
 * 
 * @author Tommi Ahonen
 *
 */
public class ParkingResource {
	
	@Inject
	private ParkingService service;

	@GET
	@Produces("application/JSON") //@Produces("application/JSON") //
	@Path("{ticketId}")
	/**
	 * 
	 * Retrieve an existing parkingTicket.  
	 * 
	 * @param id Id of parkingTicket to retrieve.
	 * @return ParkingTicket in JSON format. Returns HTTP status code 200 if ticket is found. Or HTTP status code 404 if ticket is not. Or HTTP status code 500 if an error occurs and operation can't be completed.
	 * 
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
	}

	@PUT
	@Consumes("application/JSON")
	@Produces("application/JSON")
	/**
	 * 
	 * Update an existing parkingTicket.
	 * 
	 * @param newTicket The new ParkingTicket that want to be stored.
	 * @return The new ParkingTicket in JSON format. Returns HTTP status code 200 if update has been made succesfully. Or HTTP status code 404 no such ticket could be found. Or HTTP status code 500 if an error occurs and operation can't be completed.
	 */
	public Response updateTicket(ParkingTicket newTicket) {
		
		ParkingTicket oldTicket;
		
		try {
			oldTicket = service.findTicketById(newTicket.getId());
		} catch (Exception ex) {
			return Response.serverError().entity(ex.getLocalizedMessage()).build();
		}
		
		if (oldTicket == null) {
			// No such ticket exists.
			return Response.status(404).build();
		} 
		
		// Ticket seems to exist
		try {
			service.updateTicket(newTicket);
		} catch (ParkingTicketDoesNotExistException ex) {
			return Response.status(404).build();
		} catch (Exception ex) {
			return Response.serverError().entity(ex.getLocalizedMessage()).build();
		}
		
		return Response.ok(newTicket).build();
	}


	@DELETE
	@Produces("application/JSON") // @Produces("application/XML")
	@Path("{ticketId}")
	/**
	 * Delete an existing ParkingTicket.
	 * 
	 * @param id Id of ParkingTicket to delete.
	 * @return Returns HTTP status code 200 if delete was made succesfully. Or HTTP status code 404 no such ticket could be found. Or HTTP status code 500 if an error occurs and operation can't be completed.
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

	}
	
	/**
	 * Create a new ParkingTicket.
	 * 
	 * @param ticket ParkingTicket to create.
	 * @return The newly create ParkingTicket and HTTP status code 200. Or HTTP status code 500 if ticket could not be created.
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
