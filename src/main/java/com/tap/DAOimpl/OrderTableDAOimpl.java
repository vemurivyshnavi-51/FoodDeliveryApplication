package com.tap.DAOimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderTableDAO;
import com.tap.model.OrderTable;
import com.tap.utility.DBConnection;

public class OrderTableDAOimpl implements OrderTableDAO {
	Connection con=DBConnection.getConnection();
	
	public String addOrderTable_query="insert into `ordertable`(`userID`,"
			+ "`OrderDate`,"
			+ "`TotalAmount`,"
			+ "`Status`,"
			+ "`PaymentMethod`,"
			+ "`RestaurantID`) values(?,?,?,?,?,?)";
	
	
	public String updateOrderTable_query="update `ordertable` SET `userID`=? ,`OrderDate`=?, `TotalAmount`=?, `Status`=? ,"
			+"`PaymentMethod`=? ,`RestaurantID`=? where `OrderID`=?";
	
	public String deleteOrderTable_Query="delete from `ordertable` where `OrderID`=?";
	
	public String getOrderTable_Query="select * from `ordertable` where  `OrderID`=?";
	
	public String getOrderTable_Query_userID="select * from `ordertable` where  `userID`=?";
	
	public String getOrderTable_Query_restaurantID="select * from `ordertable` where  `OrderID`=?";
	
	public String get_All_OrderItems_Query="select * from `ordertable`";
	
	@Override
	public void addOrderTable(OrderTable ot) {
		
		try {
			PreparedStatement stmt = con.prepareStatement(addOrderTable_query);
			stmt.setInt(1,ot.getUserID() );
			stmt.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
			stmt.setBigDecimal(3,ot.getTotalAmount());
			stmt.setString(4, ot.getStatus());
			stmt.setString(5, ot.getPaymentMethod());
			stmt.setInt(6, ot.getRestaurantID());
			
			int i=stmt.executeUpdate();
			System.out.println(i+" Order Completed Successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void updateOrderTable(OrderTable ot) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = con.prepareStatement(updateOrderTable_query);
			stmt.setInt(1, ot.getUserID());
			stmt.setTimestamp(2, ot.getOrderDate());
			stmt.setBigDecimal(3, ot.getTotalAmount());
			stmt.setString(4, ot.getStatus());
			stmt.setString(5, ot.getPaymentMethod());
			stmt.setInt(6, ot.getRestaurantID());
			stmt.setInt(7, ot.getOrderID());
			int i = stmt.executeUpdate();
			System.out.println(i+" updated successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteOrderTable(int OrderID) {
		
		try {
			PreparedStatement stmt = con.prepareStatement(deleteOrderTable_Query);
			stmt.setInt(1, OrderID);
			int i = stmt.executeUpdate();
			System.out.println(i+" order deleted Successfully.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public OrderTable getOrderTable(int OrderID) {
		OrderTable o=null;
		
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(getOrderTable_Query);
			stmt.setInt(1, OrderID);
			ResultSet result = stmt.executeQuery();
			 
			while(result.next()) {
				
				int userID=result.getInt("userID");
				int orderID=result.getInt("OrderID");
				Timestamp orderDate = result.getTimestamp("orderDate");
				BigDecimal totalAmount = result.getBigDecimal("totalAmount");
				String status = result.getString("status");
				String paymentMethod = result.getString("paymentMethod");
				int restaurantID = result.getInt("restaurantID");
				
				o=new OrderTable(userID,orderID,orderDate,totalAmount, status,paymentMethod,restaurantID);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;

	}
	
	public List<OrderTable> getOrderTable_userID(int userID) {

		List<OrderTable> li=new ArrayList<OrderTable>();
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement(getOrderTable_Query_userID);
			stmt.setInt(1, userID);
			ResultSet result = stmt.executeQuery();
			OrderTable o=null;
			while(result.next()) {
				
				int userID1=result.getInt("userID");
				int orderID=result.getInt("OrderID");
				Timestamp orderDate = result.getTimestamp("orderDate");
				BigDecimal totalAmount = result.getBigDecimal("totalAmount");
				String status = result.getString("status");
				String paymentMethod = result.getString("paymentMethod");
				int restaurantID = result.getInt("restaurantID");
				
				o=new OrderTable(userID1,orderID,orderDate,totalAmount, status,paymentMethod,restaurantID);
				li.add(o);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return li;
	}
		
	@Override
	public List<OrderTable> getallOrderTable() {
		List<OrderTable> li=new ArrayList<OrderTable>();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(get_All_OrderItems_Query);
			OrderTable o=null;
			while(result.next()) {
				
				int userID1=result.getInt("userID");
				int orderID=result.getInt("OrderID");
				Timestamp orderDate = result.getTimestamp("orderDate");
				BigDecimal totalAmount = result.getBigDecimal("totalAmount");
				String status = result.getString("status");
				String paymentMethod = result.getString("paymentMethod");
				int restaurantID = result.getInt("restaurantID");
				
				o=new OrderTable(userID1,orderID,orderDate,totalAmount, status,paymentMethod,restaurantID);
				li.add(o);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return li;
	}

}
