package se.yrgo.service;

import javax.ejb.Local;
import javax.ejb.Remote;

import se.yrgo.domain.ParkingTicket;
import se.yrgo.exceptions.ParkingTicketAlreadyExistsException;
import se.yrgo.exceptions.ParkingTicketDoesNotExistException;
import se.yrgo.exceptions.StorageAccessException;

@Remote
public interface ParkingServiceLocal {

	public void createTicket(ParkingTicket ticket) throws ParkingTicketAlreadyExistsException, StorageAccessException;
	public boolean deleteTicket(int ticketId) throws StorageAccessException;
	public ParkingTicket findTicketById(int ticketId) throws StorageAccessException;
	public void updateTicket(ParkingTicket newTicket) throws StorageAccessException, ParkingTicketDoesNotExistException;
}
