package se.yrgo.client;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import se.yrgo.domain.Car;
import se.yrgo.domain.Customer;
import se.yrgo.domain.ParkingTicket;
import se.yrgo.exceptions.ParkingTicketAlreadyExistsException;
import se.yrgo.exceptions.StorageAccessException;
import se.yrgo.service.ParkingService;
import se.yrgo.service.ParkingServiceLocal;

public class Client {

	public static void main(String[] args) throws NamingException {

		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		Context jndi = new InitialContext(jndiProperties);

		ParkingServiceLocal service = (ParkingServiceLocal) jndi.lookup("ParkingTicketManagementApplication/ParkingServiceImplementation!se.yrgo.service.ParkingServiceLocal");

		// RUN TO ADD TICKETS TO DATABASE
		
		Date start = new Date("Sat, 12 Aug 1995 13:30:00 GMT");
	    Date end = new Date("Sun, 13 Aug 1995 13:30:00 GMT");
	
	    Car car1 = new Car("YRG 023", "Ljusblå Dacia");
	    Customer customer1 = new Customer("Ola", "Conny");
		ParkingTicket ticket = new ParkingTicket(start, end, 5, "Roliga gatan 2", car1, customer1);
		
		Date start1 = new Date("Sat, 19 Aug 1996 11:30:00 GMT");
	    Date end1 = new Date("Sun, 26 Aug 1996 13:30:00 GMT");
	    Car car2 = new Car("ORC 554", "Volvo 240 GL");
	    Customer customer2 = new Customer("Johan", "Larsson");
	    
		ParkingTicket ticket2 = new ParkingTicket(start1, end1, 5, "Grusvägen 3", car2, customer2);
		
	    Car car3 = new Car("HEJ 299", "SAAB 95");
	    Customer customer3 = new Customer("Bosse", "Bredsladd");
	    
		ParkingTicket ticket3 = new ParkingTicket(start1, end1, 1, "Lolvägen 1", car3, customer3);
		
		System.out.println(start.toString());
	
		
		try {
			System.out.println("Creating tickets...");
			service.createTicket(ticket);
			service.createTicket(ticket2);
			service.createTicket(ticket3);
		}
		catch (ParkingTicketAlreadyExistsException ex) {
			System.err.println("The ticket already exists! " + ex.getMessage());
		}
		catch (StorageAccessException ex) {
			System.err.println("Data access went wrong. " + ex.getMessage());
		}
		
		

	}

}
