package com.salesforce.cce.interview.mongodb;

public class Vehicle {

	private int mileage;
	private String make;
	private String model;

	public Vehicle() {
	}

	public Vehicle(int mileage, String make, String model) {
		this.mileage = mileage;
		this.make = make;
		this.model = model;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
