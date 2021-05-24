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
import se.yrgo.domain.ParkingTicketDoesNotExistException;
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
	public boolean deleteTicket(int ticketId) throws StorageAccessException {
		ParkingTicket ticketToRemove = findTicketById(ticketId);
		if (ticketToRemove == null) {
			return false;
		}

		try {
			em.remove(ticketToRemove);
		} catch (Exception ex) {
			throw new StorageAccessException("Unable to remove ticket from database.", ex);
		}
		return true;
	}

	/**
	 * Returns ParkingTicket or null if no such parking ticket exists.
	 */
	@Override
	public ParkingTicket findTicketById(int ticketId) throws StorageAccessException {
		try {
			return em.find(ParkingTicket.class, ticketId);
		} catch (Exception ex) {
			throw new StorageAccessException("Unable to find ticket from database.", ex);
		}
	}

	@Override
	public void updateTicket(ParkingTicket newTicket)
			throws StorageAccessException, ParkingTicketDoesNotExistException {
		try {
			em.merge(newTicket);
		} catch (IllegalArgumentException ex) {
			throw new ParkingTicketDoesNotExistException("Unable to update parking ticket.", ex);
		} catch (Exception ex) {
			throw new StorageAccessException("Unable to update parking ticket.", ex);
		}

	}

}
