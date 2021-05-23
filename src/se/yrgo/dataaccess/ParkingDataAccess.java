package se.yrgo.dataaccess;

import java.util.List;

import se.yrgo.domain.ParkingTicket;
import se.yrgo.domain.ParkingTicketAlreadyExistsException;
import se.yrgo.domain.StorageException;

public interface ParkingDataAccess {
	public List<ParkingTicket> getAllParkingTickets();
	public List<ParkingTicket> getCarsbyId(int id);
	public void createTicket(ParkingTicket ticket) throws ParkingTicketAlreadyExistsException, StorageException;
	public void deleteTicket(int ticketId);
	public ParkingTicket findTicketById(int ticketId);
}
