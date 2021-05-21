package se.yrgo.dataaccess;

import se.yrgo.domain.Employee;
import se.yrgo.domain.ParkingTicket;

import java.util.List;

import javax.ejb.Local;

@Local
public interface EmployeeDataAccess {

	public  void insert(Employee newEmployee);
	public  List<Employee> findAll();
	public  List<Employee> findBySurname(String surname);
	public Employee findById(int id) throws EmployeeNotFoundException;
	public List<Employee> getAllEmployeesWhereIdBetween(int firstId, int secondId);
	public List<ParkingTicket> getAllParkingTickets();
	List<ParkingTicket> getCarsbyId(int id);
}