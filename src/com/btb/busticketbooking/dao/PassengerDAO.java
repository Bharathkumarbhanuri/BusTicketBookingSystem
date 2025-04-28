package com.btb.busticketbooking.dao;

import java.sql.*;
import com.btb.busticketbooking.model.Passenger;
import com.btb.busticketbooking.util.DBconnection;

public class PassengerDAO {
	public static void addPassenger(Passenger passenger) {
		String sql = "insert into passenger(email, name, phone) values(?,?,?)";
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, passenger.getEmail());
            stmt.setString(2, passenger.getName());
            stmt.setString(3, passenger.getPhone());
			stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Passenger getPassengerByEmail(String email) {
		Passenger passenger = null;
		String sql = "select * from passenger where email=? ";
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("passenger_id");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				passenger = new Passenger(id, email, name, phone); 
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return passenger;
	}
}
