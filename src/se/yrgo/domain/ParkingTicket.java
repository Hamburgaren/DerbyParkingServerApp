package se.yrgo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * This is a parking ticket.
 * 
 * @author Tommi
 *
 */
@Entity
public class ParkingTicket implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private Date validThroughBegin;
	private Date validThroughEnd;

	private int pricePerhour;
	private String streetAddress;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Car car;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Customer customer;

	/**
	 * Create a new parking ticket for a specific customer, street address and
	 * vehicle.
	 * 
	 * @param validThroughBegin The time and date before which onward this parking
	 *                          ticket is valid.
	 * @param validThroughEnd   Car that this parking ticket has been issued for.
	 * @param pricePerhour      How much customer will be charged per hour to park
	 *                          at this location.
	 * @param streetAddress     Street address that this parking ticket is valid
	 *                          for.
	 * @param car               Car that this parking ticket has been issued for.
	 * @param customer
	 */
	public ParkingTicket(Date validThroughBegin, Date validThroughEnd, int pricePerhour,
			String streetAddress, Car car, Customer customer) {
		super();
		/*
		LocalDateTime timeNow = LocalDateTime.now();
		LocalDateTime endTime = timeNow.plusHours(hours);
		this.validThroughBegin = timeNow.toString();
		this.validThroughEnd = endTime.toString();
		*/
		this.validThroughBegin = validThroughBegin;
		this.validThroughEnd = validThroughEnd;
		this.pricePerhour = pricePerhour;
		this.streetAddress = streetAddress;
		this.car = car;
		this.customer = customer;
	}

	public ParkingTicket() {
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setValidThroughBegin(Date validThroughBegin) {
		this.validThroughBegin = validThroughBegin;
	}

	public void setValidThroughEnd(Date validThroughEnd) {
		this.validThroughEnd = validThroughEnd;
	}

	public void setPricePerhour(int pricePerhour) {
		this.pricePerhour = pricePerhour;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getValidThroughBegin() {
		return validThroughBegin;
	}

	public Date getValidThroughEnd() {
		return validThroughEnd;
	}
	
	public int getPricePerhour() {
		return pricePerhour;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public Car getCar() {
		return car;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String toString() {
		return "ParkingTicket{" + "id=" + id + ", validThroughBegin=" + validThroughBegin + ", validThroughEnd=" + validThroughEnd + ", pricePerhour=" + pricePerhour + ", streetAddress='" + streetAddress + '\'' + ", car=" + car + ", customer=" + customer + '}';
	}
}
