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
		
		
	}

	@Override
	public void deleteTicket(int ticketId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findTicketById(int ticketId) {
		// TODO Auto-generated method stub
		
	}


}
