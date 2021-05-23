package se.yrgo.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import se.yrgo.domain.ParkingTicket;

@Stateless
@Default
public class ParkingDataAccessProductionVersion implements ParkingDataAccess {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ParkingTicket> getAllParkingTickets() {
		Query q = em.createQuery("select ticket from Ticket ticket");
		List<ParkingTicket> tickets = q.getResultList();
		return tickets;
	}
	
	@Override
	public List<ParkingTicket> getCarsbyId(int id) {
		Query q = em.createQuery("select car from Car car where car.id = :carId");
		q.setParameter("carId", id);
		return q.getResultList();
	}

	@Override
	public void createTicket(ParkingTicket ticket) {
		em.persist(ticket);
		
	}

	@Override
	public void deleteTicket(int ticketId) {
		em.remove(ticketId);
		
	}

	@Override
	public ParkingTicket findTicketById(int ticketId) {
		Query q = em.createQuery("select parkingticket from Parkingticket parkingticket where parkingticket.id = :parkingticketId");
		q.setParameter("parkingticketId", ticketId);
		return (ParkingTicket)q.getResultList();
	}
	
}
