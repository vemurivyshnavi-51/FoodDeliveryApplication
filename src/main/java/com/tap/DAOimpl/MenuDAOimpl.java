package com.tap.DAOimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.MenuDAO;
import com.tap.model.Menu;
import com.tap.utility.DBConnection;

public class MenuDAOimpl implements MenuDAO {
	
	Connection con=DBConnection.getConnection();
	
	public String addMenu_query="insert into `menu`(`RestaurantID`,`ItemName`,`Description`,`Price`,`IsAvailable`,`ImagePath`) values(?,?,?,?,?,?)";
	
	public String getMenuID_query="select * from `menu` where `MenuID`=?";
	
	public String getMenu_restID_query="select * from `menu` where `RestaurantID`=?";
	
	public String deleteMenu_query="delete from `menu` where `MenuID`=?";
	
	public String updateMenu_query="update `menu` SET `ItemName`=?,`Description`=?,`Price`=?,`IsAvailable`=?,`ImagePath`=? where `MenuID`=?";
	
	public String getAllMenu_query="select * from `menu`";
	
	
	@Override
	public void addMenu(Menu m) {
		
		try 
		{
			
			PreparedStatement stmt = con.prepareStatement(addMenu_query);
			
			stmt.setInt(1, m.getRestaurantID());
			stmt.setString(2, m.getItemName());
			stmt.setString(3, m.getDescription());
			stmt.setBigDecimal(4, m.getPrice());
			stmt.setBoolean(5, m.getIsAvailable());
			stmt.setString(6,m.getImagePath());
			
			int i=stmt.executeUpdate();
			
			System.out.print(i+" Menu Inserted Successfully.");
				
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
		
	}

	@Override
	public Menu getMenu(int MenuID) {
		Menu m=null;
		try {
			PreparedStatement stmt = con.prepareStatement(getMenuID_query);
			stmt.setInt(1,MenuID);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				int MenuID1=result.getInt("MenuID");
				int RestaurantID=result.getInt("RestaurantID");
				String ItemName=result.getString("ItemName");
				String Description=result.getString("Description");
				BigDecimal Price=result.getBigDecimal("Price");
				Boolean IsAvailable=result.getBoolean("IsAvailable");
				String ImagePath=result.getString("ImagePath");
				m=new Menu(MenuID1, RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}

	@Override
	public void deleteMenu(int MenuID) {
		try {
			PreparedStatement stmt = con.prepareStatement(deleteMenu_query);
			stmt.setInt(1, MenuID);
			int i=stmt.executeUpdate();
			System.out.println(i+" Menu item Deleted Successfully.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	@Override
	public List<Menu> get_all_Menus() {
		
		List<Menu> li=new ArrayList<Menu>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(getAllMenu_query);
			Menu m=null;
			while(result.next()) {
				int MenuID1=result.getInt("MenuID");
				int RestaurantID=result.getInt("RestaurantID");
				String ItemName=result.getString("ItemName");
				String Description=result.getString("Description");
				BigDecimal Price=result.getBigDecimal("Price");
				Boolean IsAvailable=result.getBoolean("IsAvailable");
				String ImagePath=result.getString("ImagePath");
				m=new Menu(MenuID1, RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath);
				li.add(m);
			}	
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return li;
	
	}

	@Override
	public List<Menu> getMenu_restID(int RestaurantID) {
		
		List<Menu> li=new ArrayList<Menu>();
		
		
		
		try {
			PreparedStatement stmt = con.prepareStatement(getMenu_restID_query);
			stmt.setInt(1, RestaurantID);
			ResultSet result = stmt.executeQuery();
			Menu m=null;
			while(result.next()) {
				int MenuID1=result.getInt("MenuID");
				int RestaurantID1=result.getInt("RestaurantID");
				String ItemName=result.getString("ItemName");
				String Description=result.getString("Description");
				BigDecimal Price=result.getBigDecimal("Price");
				Boolean IsAvailable=result.getBoolean("IsAvailable");
				String ImagePath=result.getString("ImagePath");
				m=new Menu(MenuID1, RestaurantID1, ItemName, Description, Price, IsAvailable, ImagePath);
				li.add(m);
			}	
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		return li;
		
	}

	@Override
	public void UpdateMenu(Menu m) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement stmt = con.prepareStatement(updateMenu_query);
			stmt.setString(1, m.getItemName());
			stmt.setString(2, m.getDescription());
			stmt.setBigDecimal(3, m.getPrice());
			stmt.setBoolean(4, m.getIsAvailable());
			stmt.setString(5,m.getImagePath());
			stmt.setInt(6, m.getMenuID());
			int i = stmt.executeUpdate();
			System.out.println(i+" updated Successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
