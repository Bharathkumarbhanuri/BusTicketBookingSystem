package com.btb.busticketbooking.app;

import java.util.*;
import com.btb.busticketbooking.model.*;
import com.btb.busticketbooking.dao.*;

public class App {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		while(true) {
			 System.out.println("1. Register as a new user");
	         System.out.println("2. View available buses");
	         System.out.println("3. Book a ticket");
	         System.out.println("4. View all my bookings");
	         System.out.println("5. Cancel a booking");
	         System.out.println("6. Exit");
	         System.out.print("Enter your choice: ");
	         int choice = sc.nextInt();
	         sc.nextLine();
	         switch (choice) {
             case 1:
                 registerPassenger();
                 break;
             case 2:
                 viewAvailableBuses();
                 break;
             case 3:
                 bookTicket();
                 break;
             case 4:
                 viewBookings();
                 break;
             case 5:
                 cancelBooking();
                 break;
             case 6:
                 System.exit(0);
                 break;
             default:
                 System.out.println("Invalid choice! Please try again.");
         }
		}	
	}
	private static void registerPassenger() {
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your phone: ");
        String phone = sc.nextLine();
        
        Passenger passenger  = new Passenger(email,name,phone);
        PassengerDAO.addPassenger(passenger);
        System.out.println("Registration successful!");
	}
	
	private static Passenger registerPassenger(String email) {
	    System.out.print("Enter your name: ");
	    String name = sc.nextLine();
	    System.out.print("Enter your phone: ");
	    String phone = sc.nextLine();
	    
	    Passenger passenger = new Passenger(email, name, phone);
	    PassengerDAO.addPassenger(passenger);

	    // Retrieve the passenger from the database to get the generated ID
	    passenger = PassengerDAO.getPassengerByEmail(email);
	    
	    System.out.println("Registration successful!");
	    return passenger;
	}

	private static void viewAvailableBuses() {
		List<Bus> buses = BusDAO.getAllBuses();
		if(buses.isEmpty()) {
			System.out.println(" no buses available");
		}
        System.out.println("Available Buses:");
        System.out.println();
        for (Bus bus : buses) {
            System.out.println(bus); // calls Bus.toString()
        }


	}
	private static void bookTicket() {
		viewAvailableBuses();
		List<Bus> buses = BusDAO.getAllBuses();
		Bus selectedbus = null;
		System.out.print("enter the bus id you want to book:");
		int busid = sc.nextInt();
		sc.nextLine();
		
		// checks if the bus exits
		
		for(Bus bus : buses) {
			if(bus.getBus_id()==busid) { 
				selectedbus = bus;
				break;
			}
		}
		if (selectedbus == null) {
		    System.out.println("No bus found with the ID");
		    return;
		}
		System.out.print("enter seat number (1 to " + selectedbus.getTotalseats() + "):");
		int seatnumber = sc.nextInt();
		sc.nextLine();
		
		//checks if the seat is available 
		
		while (BookingDAO.isSeatBooked(busid, seatnumber)) {
		    System.out.println("Seat already booked. Please choose a different seat:");		
		    seatnumber = sc.nextInt();
		    sc.nextLine();
		}
		
		// checks passenger by email, if doesn't exist then register
		
		System.out.print("enter your email id: ");
		String email = sc.nextLine();
		Passenger passenger = PassengerDAO.getPassengerByEmail(email); 
		if(passenger==null) { 
			System.out.println("seems you are not registered");
			passenger = registerPassenger(email);
			if (passenger == null) {
			    System.out.println("Registration failed. Cannot proceed with booking.");
			    return;
			}
		}
			
		//booking process
		
		boolean bookingSuccess = BookingDAO.bookTicket(selectedbus.getBus_id(), passenger.getPassenger_id(), seatnumber);
		if(bookingSuccess) {
			System.out.println("Booking Success");
			System.out.println();
		}else {
			System.out.println("Booking failed");
		}
		
	}
	private static void viewBookings() {
		System.out.print("Enter your email to view bookings: ");
        String email = sc.nextLine();
        Passenger passenger = PassengerDAO.getPassengerByEmail(email);
        if(passenger!=null) {
        	List<Booking> bookings =  BookingDAO.getBookingsByPassenger(passenger.getPassenger_id());
        	if(bookings.isEmpty()) {
        		System.out.println(" no bookings found");
        	}else {
        		System.out.println("your bookings:");
        		for(Booking booking:bookings) {
        			System.out.println(booking);	// calls toString()
        			System.out.println();
        		}
        	}
        }else {
        	System.out.println("No passenger found with the provided email");
        }
	}
	private static void cancelBooking() {
		System.out.print("Enter your email to cancel bookings: ");
        String email = sc.nextLine();
        Passenger passenger = PassengerDAO.getPassengerByEmail(email);
            
		if(passenger!=null) {
			List<Booking> bookings = BookingDAO.getBookingsByPassenger(passenger.getPassenger_id());

            if (bookings.isEmpty()) {
                System.out.println("No bookings found to cancel.");
                return;
            }
  
            System.out.println("Your bookings:");
            for (Booking booking : bookings) {
                System.out.println(booking); // toString() should show booking_id and bus details
                System.out.println();
            }
            
			System.out.println("enter booking id to cancel:");
			int bookid = sc.nextInt();
			sc.nextLine();
			
			boolean cancelSuccess = BookingDAO.cancelTicket( bookid,passenger.getPassenger_id());
			if(cancelSuccess) {
				System.out.println("Booking cancelled successfully");
				System.out.println();
			}else {
				System.out.println("No booking found with the given ID and email");
			}
		}else {
			System.out.println("Passenger not found");
		}
	}
	
}
