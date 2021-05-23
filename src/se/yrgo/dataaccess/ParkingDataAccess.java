package se.yrgo.dataaccess;

import java.util.List;

import se.yrgo.domain.ParkingTicket;

public interface ParkingDataAccess {
	public List<ParkingTicket> getAllParkingTickets();
	public List<ParkingTicket> getCarsbyId(int id);
	public void createTicket(ParkingTicket ticket);
	public void deleteTicket(int ticketId);
	public ParkingTicket findTicketById(int ticketId);
}
