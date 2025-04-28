package com.btb.busticketbooking.model;

public class Passenger {
	private int passenger_id;
	private String email;
	private String name;
	private String phone;
	
	public Passenger() {
	}
	
	public Passenger(String email, String name, String phone) {
		super();
		this.email = email;
		this.name = name;
		this.phone = phone;
	}

	public Passenger(int passenger_id, String email, String name, String phone) {
		super();
		this.passenger_id = passenger_id;
		this.email = email;
		this.name = name;
		this.phone = phone;
	}

	public int getPassenger_id() {
		return passenger_id;
	}

	public void setPassenger_id(int passenger_id) {
		this.passenger_id = passenger_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Passenger [passenger_id=" + passenger_id + ", email=" + email + ", name=" + name + ", phone=" + phone
				+ "]";
	}
}
