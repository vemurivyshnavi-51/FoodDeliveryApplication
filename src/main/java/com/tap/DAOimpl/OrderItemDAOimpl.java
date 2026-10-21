package com.tap.DAOimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.utility.DBConnection;

public class OrderItemDAOimpl implements OrderItemDAO {
	//   OrderID, OrderItemID, Quantity, ItemTotal, MenuId
	
	Connection con=DBConnection.getConnection();
	
	public static String add_Item_Query="insert into `orderitem`(`OrderID`, `Quantity`, `ItemTotal`, `MenuId`) values(?,?,?,?)";
	
	public static String update_item_Query="update `orderitem` SET `OrderID`=?, `Quantity`=?, `ItemTotal`=?, `MenuId`=? where `OrderItemID`=?";
	
	public static String delete_item_Query="delete from `orderitem` where `OrderItemID`=?";
	
	public static String get_item_Query="select * from `orderitem` where `OrderItemID`=?";
	
	public static String get_allItem_Query="select * from `orderitem` ";
	
	@Override
	public void addOrderItem(OrderItem oi) {
		try {
			PreparedStatement stmt = con.prepareStatement(add_Item_Query);
			stmt.setInt(1,oi.getOrderID());
			stmt.setInt(2,oi.getQuantity());
			stmt.setBigDecimal(3, oi.getItemTotal());
			stmt.setInt(4, oi.getMenuId());
			
			int i = stmt.executeUpdate();
			
			System.out.println(i+" Inserted Successfully");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateOrderItem(OrderItem oi) {
		
		try {
			PreparedStatement stmt = con.prepareStatement(update_item_Query);
			stmt.setInt(1,oi.getOrderID());
			stmt.setInt(2,oi.getQuantity());
			stmt.setBigDecimal(3, oi.getItemTotal());
			stmt.setInt(4, oi.getMenuId());
			stmt.setInt(5, oi.getOrderItemID());
			int i = stmt.executeUpdate();
			
			System.out.println(i+" Updated Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void deleteOrderItem(int OrderItemID) {
		try {
			PreparedStatement stmt = con.prepareStatement(delete_item_Query);
			stmt.setInt(1, OrderItemID);
			int i = stmt.executeUpdate();
			System.out.println(i+"Deleted Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public OrderItem getOrderItem(int OrderItemID) {
		OrderItem o=null;
		try {
			PreparedStatement stmt = con.prepareStatement(get_item_Query);
			stmt.setInt(1, OrderItemID);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				int OrderID=result.getInt("OrderID");
				int OrderItemID1=result.getInt("OrderItemID");
				int Quantity=result.getInt("Quantity");
				BigDecimal ItemTotal=result.getBigDecimal("ItemTotal");
				int MenuId=result.getInt("MenuId");
				o=new OrderItem(OrderID, OrderItemID1, Quantity, ItemTotal, MenuId);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return o;
	}
	
	

	
	
	@Override
	public List<OrderItem> getallOrderItem() {
		List<OrderItem> li=new ArrayList<OrderItem>();
		OrderItem o=null;
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(get_allItem_Query);
			
			while(result.next()) {
				int OrderID=result.getInt("OrderID");
				int OrderItemID1=result.getInt("OrderItemID");
				int Quantity=result.getInt("Quantity");
				BigDecimal ItemTotal=result.getBigDecimal("ItemTotal");
				int MenuId=result.getInt("MenuId");
				o=new OrderItem(OrderID, OrderItemID1, Quantity, ItemTotal, MenuId);
				li.add(o);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return li;
	}

}
