package se.yrgo.service;

import javax.ejb.Local;

import se.yrgo.domain.Employee;
import se.yrgo.domain.ParkingTicket;

@Local
public interface ParkingService {

	public void createTicket(ParkingTicket ticket);
	public void deleteTicket(int ticketId);
	public ParkingTicket findTicketById(int ticketId);
}
