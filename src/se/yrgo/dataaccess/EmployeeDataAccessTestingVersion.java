package se.yrgo.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;

import se.yrgo.domain.Employee;
import se.yrgo.domain.ParkingTicket;

@Stateless
@TestingDao
public class EmployeeDataAccessTestingVersion implements EmployeeDataAccess {

	@Override
	public void insert(Employee newEmployee) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Employee> findAll() {
		
		Employee emp1 = new Employee("James","Green","Writer",		3700);
		Employee emp2 = new Employee("Sara","Bild","Editor",		2200);
		Employee emp3 = new Employee("David" , "Baron", "Manager", 	4000);
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
		
		return employees;
		
	}

	@Override
	public List<Employee> findBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployeesWhereIdBetween(int firstId, int secondId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParkingTicket> getAllParkingTickets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParkingTicket> getCarsbyId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
