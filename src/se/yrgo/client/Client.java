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
import se.yrgo.domain.ParkingTicketAlreadyExistsException;
import se.yrgo.domain.StorageAccessException;
import se.yrgo.service.ParkingService;
import se.yrgo.service.ParkingServiceLocal;

public class Client {

	public static void main(String[] args) throws NamingException {

		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		Context jndi = new InitialContext(jndiProperties);

		ParkingService service = (ParkingService) jndi.lookup("ParkingTicketManagementApplication/ParkingServiceImplementation!se.yrgo.service.ParkingServiceLocal");
//
//		Employee emp1 = new Employee("Andreas", "Green", "Designer", 2700);
//		Employee emp2 = new Employee("Rikard", "Blue", "Tester", 1800);
//		Employee emp3 = new Employee("Ronald", "Black", "Programmer", 2900);
//		Employee emp4 = new Employee("Eric", "Black", "Manager", 2000);
//
//		service.registerEmployee(emp1);
//		service.registerEmployee(emp2);
//		service.registerEmployee(emp3);
//		service.registerEmployee(emp4);
//
//		// EmployeeManagementServerApplication/EmployeeManagementImplementation!se.yrgo.service.EmployeeManagementService
//		List<Employee> employees = service.getAllEmployees();
//		for (Employee employee : employees) {
//			System.out.println(employee);
//		}
//
//		System.out.println("******");
//		
//		employees = service.searchBysurname("Black");
//		for (Employee employee : employees) {
//			System.out.println("---" + employee);
//		}
		
		Date now = new Date();
	    Date later = new Date();
	    later.setHours(now.getHours() + 1);
	    
	
	    Car car1 = new Car("YRG 023", "Ljusblå Dacia");
	    Customer customer1 = new Customer("Ola", "Conny");
	    
		ParkingTicket ticket = new ParkingTicket(now, later, 2, 5, "Roliga gatan 2", car1, customer1);
		
		try {
			service.createTicket(ticket);
		}
		catch (ParkingTicketAlreadyExistsException ex) {
			System.err.println("The ticket already exists! " + ex.getMessage());
		}
		catch (StorageAccessException ex) {
			System.err.println("Data access went wrong. " + ex.getMessage());
		}

	}

}
