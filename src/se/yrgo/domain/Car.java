package se.yrgo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String regNumber;
	private String description;

	/**
	 * Create a new vehicle that can be used for parking.
	 * 
	 * @param regNumber   Registration number of vehicle.
	 * @param description Description of vehicle (e.g. grey Saab 900).
	 */
	public Car(String regNumber, String description) {
		super();
		this.regNumber = regNumber;
		this.description = description;
	}

	public Car() {
	}

	public int getId() {
		return id;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", regNumber=" + regNumber + ", description=" + description + "]";
	}

	
}
