package com.tap.DAOimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class RestaurantDAOimpl implements RestaurantDAO {
    public String add_Rest_query="insert into restaurant(`Name`,`CuisineType`,`DeliveryTime`,`Address`,`Rating`,`IsActive`,`ImagePath`)"
             + "values(?,?,?,?,?,?,?)";
	
    public String get_rest_query="select * from `restaurant` where `RestaurantID`=?";
    
    public String update_rest_query="update `restaurant` SET `Name`=?,`CuisineType`=?,`DeliveryTime`=?,`Address`=?,`Rating`=?,`IsActive`=?,`ImagePath`=? where `RestaurantID`=?";
	
    public String getall_rest_query="select * from `restaurant`";
    
    public String delete_rest_query="delete from `restaurant` where `RestaurantID`=?";
	
	@Override
	public void addRestaurant(Restaurant r) {
		Connection con=DBConnection.getConnection();
		
		try {
			 PreparedStatement stmt = con.prepareStatement(add_Rest_query);
			 
			 stmt.setString(1, r.getName());
			 stmt.setString(2, r.getCuisineType());
			 stmt.setInt(3, r.getDeliveryTime());
			 stmt.setString(4, r.getAddress());
			 stmt.setBigDecimal(5, r.getRating());
			 stmt.setBoolean(6, r.getIsActive());
			 stmt.setString(7, r.getImagePath());
			 
			 int i = stmt.executeUpdate();
			 System.out.println(i+" added users");
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Restaurant getRestaurant(int RestaurantID) {
		
	 	Connection con=DBConnection.getConnection();
	 	Restaurant r=null;
	 	try {
			PreparedStatement stmt = con.prepareStatement(get_rest_query);
			stmt.setInt(1, RestaurantID);
			ResultSet result=stmt.executeQuery();
			
			while(result.next()) {
				int RestaurantID1=result.getInt("RestaurantID");
				String Name=result.getString("Name");
				String CuisineType=result.getString("CuisineType");
				int DeliveryTime=result.getInt("DeliveryTime");
				String Address=result.getString("Address");
				BigDecimal Rating=result.getBigDecimal("Rating");
				boolean IsActive=result.getBoolean("IsActive");
				String ImagePath=result.getString("ImagePath");	
				r=new Restaurant(RestaurantID1,Name,CuisineType,DeliveryTime,Address,Rating,IsActive,ImagePath);
				
			}
			
			
	 	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	
	
	@Override
	public void updateRestaurant(Restaurant r) {
		Connection conn=DBConnection.getConnection();
		
		try 
		{
			PreparedStatement stmt = conn.prepareStatement(update_rest_query);
			
			
			stmt.setString(1, r.getName());
			stmt.setString(2, r.getCuisineType());
			stmt.setInt(3, r.getDeliveryTime());
			stmt.setString(4,r.getAddress());
			stmt.setBigDecimal(5, r.getRating());
			stmt.setBoolean(6, r.getIsActive());
			stmt.setString(7,r.getImagePath());
			stmt.setInt(8, r.getRestaurantID());
			
			int i=stmt.executeUpdate();
			System.out.print(i+" Updates Successfully");
			
	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();	
		}
	}

	@Override
	public void deleteRestaurant(int RestaurantID) {
		Connection conn=DBConnection.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(delete_rest_query);
			stmt.setInt(1, RestaurantID);
			int i=stmt.executeUpdate();
			System.out.print(i+" Restaurant deleted successfully.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		Connection conn=DBConnection.getConnection();
		List<Restaurant> li = new ArrayList<Restaurant>();
		Restaurant r=null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet result=stmt.executeQuery(getall_rest_query);
			
			while(result.next()) {
				int RestaurantID=result.getInt("RestaurantID");
				String Name=result.getString("Name");
				String CuisineType=result.getString("CuisineType");
				int DeliveryTime=result.getInt("DeliveryTime");
				String Address=result.getString("Address");
				BigDecimal Rating=result.getBigDecimal("Rating");
				boolean IsActive=result.getBoolean("IsActive");
				String ImagePath=result.getString("ImagePath");	
				r=new Restaurant(RestaurantID,Name,CuisineType,DeliveryTime,Address,Rating,IsActive,ImagePath);
				li.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return li;
	}

	

}
