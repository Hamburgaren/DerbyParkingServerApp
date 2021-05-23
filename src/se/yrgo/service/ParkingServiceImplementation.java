package se.yrgo.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import se.yrgo.dataaccess.ParkingDataAccess;
import se.yrgo.domain.ParkingTicket;
import se.yrgo.domain.ParkingTicketAlreadyExistsException;
import se.yrgo.domain.StorageException;

@Stateless
public class ParkingServiceImplementation implements ParkingService {
	
	@Inject
	private ParkingDataAccess dao;

	@Override
	public void createTicket(ParkingTicket ticket) throws ParkingTicketAlreadyExistsException, StorageException {
		dao.createTicket(ticket);
	}

	@Override
	public void deleteTicket(int ticketId) {
		dao.deleteTicket(ticketId);
	}

	@Override
	public ParkingTicket findTicketById(int ticketId) {
		return dao.findTicketById(ticketId);
	}


}
