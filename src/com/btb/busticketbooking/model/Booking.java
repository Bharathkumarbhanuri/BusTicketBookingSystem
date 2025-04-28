package com.btb.busticketbooking.model;

import java.util.Date;

public class Booking {
	private int booking_id;
	private int bus_id;
	private int passenger_id;
	private String seat_number;
	private Date booking_date;
	
	public Booking() {
	}

	public Booking(int booking_id, int bus_id, int passenger_id, String seat_number, Date booking_date) {
		super();
		this.booking_id = booking_id;
		this.bus_id = bus_id;
		this.passenger_id = passenger_id;
		this.seat_number = seat_number;
		this.booking_date = booking_date;
	}

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public int getBus_id() {
		return bus_id;
	}

	public void setBus_id(int bus_id) {
		this.bus_id = bus_id;
	}

	public int getPassenger_id() {
		return passenger_id;
	}

	public void setPassenger_id(int passenger_id) {
		this.passenger_id = passenger_id;
	}

	public String getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(String seat_number) {
		this.seat_number = seat_number;
	}

	public Date getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}

	@Override
	public String toString() {
		return "Booking [booking_id=" + booking_id + ", bus_id=" + bus_id + ", passenger_id=" + passenger_id
				+ ", seat_number=" + seat_number + ", booking_date=" + booking_date + "]";
	}
}
