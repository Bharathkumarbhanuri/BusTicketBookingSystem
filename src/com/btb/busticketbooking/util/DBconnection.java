package com.btb.busticketbooking.util;

import java.sql.*;

public class DBconnection {
	public static Connection getConnection()throws SQLException {
		String url = "jdbc:mysql://localhost:3306/btb";
		String username = "root";
		String password = "bharath";
		return DriverManager.getConnection(url, username, password);
	}
}
