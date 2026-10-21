package com.tap.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String url="jdbc:mysql://localhost:3306/food_delivery_dao";
	private static final String name="root";
	private static final String password=System.getenv("DB_PASSWORD");
	private static Connection connection=null;
	
	public static Connection getConnection(){
		//System.out.println("DB_PASSWORD from env: " + (System.getenv("DB_PASSWORD") == null ? "NULL" : "found"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url, name, password);
		} 
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return connection;
	}	
}
