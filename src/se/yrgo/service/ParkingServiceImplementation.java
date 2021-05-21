package se.yrgo.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import se.yrgo.domain.ParkingTicket;

@Stateless
public class ParkingServiceImplementation implements ParkingService {
	
	@Inject
	private ParkingService dao;

	@Override
	public void createTicket(ParkingTicket ticket) {
		dao.createTicket(ticket);
	}

	@Override
	public void deleteTicket(int ticketId) {
		dao.deleteTicket(ticketId);

	}

	@Override
	public void findTicketById(int ticketId) {
		dao.findTicketById(ticketId);

	}

}
