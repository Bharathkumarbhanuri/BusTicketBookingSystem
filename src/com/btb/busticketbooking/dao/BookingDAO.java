package com.btb.busticketbooking.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.btb.busticketbooking.model.Booking;
import com.btb.busticketbooking.util.DBconnection;

public class BookingDAO {
	public static boolean bookTicket(int busId, int passengerId, int seatNumber) {
		String sql = "insert into booking(bus_id, passenger_id, seat_number, booking_date) values(?,?,?,CURDATE())";
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, busId);
            stmt.setInt(2, passengerId);
            stmt.setInt(3, seatNumber);
			stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean cancelTicket(int bookingId, int passengerId) {
		String sql = "DELETE FROM booking WHERE booking_id = ? AND passenger_id = ?";
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, bookingId);
            stmt.setInt(2, passengerId);
			int rows = stmt.executeUpdate();
	        return rows > 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isSeatBooked(int busId, int seatNumber) {
		String sql = "SELECT 1 FROM booking WHERE bus_id = ? AND seat_number = ? LIMIT 1";
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, busId);	
            stmt.setInt(2, seatNumber);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;		
	}
	
	public static List<Booking> getBookingsByPassenger(int passengerId) {
		List<Booking> bookings  = new ArrayList<>();
		String sql = "select * from booking where passenger_id = ?";
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, passengerId);
			ResultSet rs =stmt.executeQuery();
			while(rs.next()) {
				int bookingid = rs.getInt("booking_id");
				int busid = rs.getInt("bus_id");
				String seatnumber = rs.getString("seat_number");
				Date bookingdate = rs.getDate("booking_date");
				
				Booking booking =  new Booking(bookingid,passengerId,busid,seatnumber,bookingdate);
				bookings.add(booking);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return bookings;
	}
}
