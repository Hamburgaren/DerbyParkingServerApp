package se.yrgo.service;

import javax.ejb.Local;

import se.yrgo.domain.Employee;
import se.yrgo.domain.ParkingTicket;
import se.yrgo.domain.ParkingTicketAlreadyExistsException;
import se.yrgo.domain.StorageException;

@Local
public interface ParkingService {

	public void createTicket(ParkingTicket ticket) throws ParkingTicketAlreadyExistsException, StorageException;
	public void deleteTicket(int ticketId);
	public ParkingTicket findTicketById(int ticketId);
}
