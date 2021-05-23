package se.yrgo.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import se.yrgo.dataaccess.ParkingDataAccess;
import se.yrgo.domain.ParkingTicket;

@Stateless
public class ParkingServiceImplementation implements ParkingService {
	
	@Inject
	private ParkingDataAccess dao;

	@Override
	public void createTicket(ParkingTicket ticket) {
		dao.createTicket(ticket);
	}

	@Override
	public void deleteTicket(int ticketId) {
		dao.createTicket(null);
	}

	@Override
	public ParkingTicket findTicketById(int ticketId) {
		return dao.findTicketById(ticketId);
	}


}
