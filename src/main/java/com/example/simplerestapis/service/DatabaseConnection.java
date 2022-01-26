package com.example.simplerestapis.service;

import java.sql.*;

public class DatabaseConnection {
	String url;
	String username;
	String password;
	
	public DatabaseConnection(String url, String username, String password)
	{
		this.url=url;
		this.username=username;
		this.password=password;
	}
	
	public Connection getConnection() throws SQLException {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println(e.getMessage());
		} catch (IllegalAccessException i) {
			System.out.println(i.getMessage());
		} catch (ClassNotFoundException c) {
			System.out.println(c.getMessage());
		}
		con=DriverManager.getConnection(url, username, password);
		System.out.println("Connection is successful");
		return con;
	}
	
	public void closeConnection(PreparedStatement stmt, Connection con) {
		try {
			stmt.close();
			con.close();
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		
	}
	public void closeConnection(Statement stmt, Connection con) {
		try {
			stmt.close();
			con.close();
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		
	}
}
