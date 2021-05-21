package se.yrgo.service;

import javax.ejb.Stateless;

import se.yrgo.domain.ParkingTicket;

@Stateless
public class ParkingServiceImplementation implements ParkingService {

	@Override
	public void createTicket(ParkingTicket ticket) {
		// TODO Auto-generated method stub
		System.out.println("@@@@@@@@@@@@@@@@@@@@ createTicket  @@@@@@@@@@ SERVICE @@@@@@@@");

	}

	@Override
	public void deleteTicket(int ticketId) {
		// TODO Auto-generated method stub
		System.out.println("@@@@@@@@@@@@@@@@@@@@ deleteTicket  @@@@@@@@@@ SERVICE @@@@@@@@");

	}

	@Override
	public void findTicketById(int ticketId) {
		// TODO Auto-generated method stub
		System.out.println("@@@@@@@@@@@@@@@@@@@@ findTicketById  @@@@@@@@@@ SERVICE @@@@@@@@");
	}

}
