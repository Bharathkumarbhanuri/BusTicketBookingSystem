package com.btb.busticketbooking.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.btb.busticketbooking.model.Bus;
import com.btb.busticketbooking.util.DBconnection;

public class BusDAO {
	public static List<Bus> getAllBuses(){
		List<Bus> buses = new ArrayList<>();
		String sql = "SELECT * FROM bus";
		try {
			Connection conn = DBconnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int bus_id= rs.getInt("bus_id");
				String bus_type= rs.getString("bus_type");
				String route= rs.getString("route");
				float fare = rs.getFloat("fare");
				int totalseats = rs.getInt("total_seats");
				Bus bus = new Bus(bus_id, bus_type, route, fare, totalseats);
				buses.add(bus);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println();
		}
		return buses;
	}
}
