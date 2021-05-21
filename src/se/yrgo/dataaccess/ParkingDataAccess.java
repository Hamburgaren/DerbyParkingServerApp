package se.yrgo.dataaccess;

import java.util.List;

import se.yrgo.domain.ParkingTicket;

public interface ParkingDataAccess {
	public List<ParkingTicket> getAllParkingTickets();
	public List<ParkingTicket> getCarsbyId(int id);
}
