package com.btb.busticketbooking.model;

public class Bus {
	private int bus_id;
	private String bus_type;
	private String route;
	private float fare;
	private int totalseats;
	
	public Bus() {
	}

	public Bus(int bus_id, String bus_type, String route, float fare, int totalseats) {
		super();
		this.bus_id = bus_id;
		this.bus_type = bus_type;
		this.route = route;
		this.fare = fare;
		this.totalseats = totalseats;
	}

	public int getBus_id() {
		return bus_id;
	}

	public void setBus_id(int bus_id) {
		this.bus_id = bus_id;
	}

	public String getBus_type() {
		return bus_type;
	}

	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public float getFare() {
		return fare;
	}

	public void setFare(float fare) {
		this.fare = fare;
	}

	public int getTotalseats() {
		return totalseats;
	}

	public void setTotalseats(int totalseats) {
		this.totalseats = totalseats;
	}

	@Override
	public String toString() {
	    return "bus_id=" + bus_id + 
	           ", bus_type=" + bus_type + 
	           ", route=" + route + 
	           ", fare=" + fare + 
	           ", totalseats=" + totalseats + "\n";
	}




	
}
