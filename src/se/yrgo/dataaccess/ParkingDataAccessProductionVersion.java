package se.yrgo.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import se.yrgo.domain.ParkingTicketAlreadyExistsException;
import se.yrgo.domain.StorageAccessException;
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
	public void createTicket(ParkingTicket ticket) throws ParkingTicketAlreadyExistsException, StorageAccessException {
		try {
			System.out.println("Creating ticket: " + ticket);
			em.persist(ticket);
		} catch (EntityExistsException ex) {
			throw new ParkingTicketAlreadyExistsException(ex);
		} catch (Exception ex) {
			throw new StorageAccessException("Error: Unable to store new ticket in database", ex);
		}
	}

	@Override
	public void deleteTicket(int ticketId) throws StorageAccessException {
		
		em.remove(findTicketById(ticketId));
		
	}

	@Override
	public ParkingTicket findTicketById(int ticketId) throws StorageAccessException {
		ParkingTicket resultTicket;
		try {
			Query q = em.createQuery("select parkingticket from Parkingticket parkingticket where parkingticket.id = :parkingticketId");
			q.setParameter("parkingticketId", ticketId);
			resultTicket = (ParkingTicket)q.getResultList();
		} catch (Exception ex) {
			throw new StorageAccessException("Error: Unable to make query to database to find ticketById.", ex);
		}
		
		return resultTicket;
	}
	
}
