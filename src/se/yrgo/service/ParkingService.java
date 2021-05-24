package se.yrgo.service;

import javax.ejb.Local;
import javax.ejb.Remote;

import se.yrgo.domain.ParkingTicket;
import se.yrgo.domain.ParkingTicketAlreadyExistsException;
import se.yrgo.domain.ParkingTicketDoesNotExistException;
import se.yrgo.domain.StorageAccessException;

@Local
public interface ParkingService {

	public void createTicket(ParkingTicket ticket) throws ParkingTicketAlreadyExistsException, StorageAccessException;
	public boolean deleteTicket(int ticketId) throws StorageAccessException;
	public ParkingTicket findTicketById(int ticketId) throws StorageAccessException;
	public void updateTicket(ParkingTicket newTicket) throws StorageAccessException, ParkingTicketDoesNotExistException;
}
