package se.yrgo.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import se.yrgo.dataaccess.ParkingDataAccess;
import se.yrgo.domain.ParkingTicket;
import se.yrgo.domain.ParkingTicketAlreadyExistsException;
import se.yrgo.domain.StorageAccessException;

@Stateless
public class ParkingServiceImplementation implements ParkingService {
	
	@Inject
	private ParkingDataAccess dao;

	@Override
	public void createTicket(ParkingTicket ticket) throws ParkingTicketAlreadyExistsException, StorageAccessException {
		dao.createTicket(ticket);
	}

	@Override
	public boolean deleteTicket(int ticketId) throws StorageAccessException {
		return dao.deleteTicket(ticketId);
	}

	@Override
	public ParkingTicket findTicketById(int ticketId) throws StorageAccessException {
		return dao.findTicketById(ticketId);
	}

}
