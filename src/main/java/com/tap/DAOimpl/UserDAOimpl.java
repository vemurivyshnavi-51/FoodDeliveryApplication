package com.tap.DAOimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.UserDAO;
import com.tap.model.User;
import com.tap.utility.DBConnection;
public class UserDAOimpl implements UserDAO{

	@Override
	public int addUser(User user) {
		String sql="insert into user(userName,password,email,address,role,createDate,lastLoginDate) values(?,?,?,?,?,?,?)";
		Connection conn=DBConnection.getConnection();
		int i = 0;
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setString(2,user.getPassword());
			stmt.setString(3,user.getEmail());
			stmt.setString(4, user.getAddress());
			stmt.setString(5, user.getRole());
			stmt.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
			stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			
			i=stmt.executeUpdate();
			
			System.out.println(i+" added users");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public User getUser(int userId) {
		String sql="select * from `user` where userId=userId";
		Connection conn=DBConnection.getConnection();
		User u=null;
		try {
			Statement stmt=conn.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			
			while(result.next()) {
				int userId1=result.getInt("userId");
				String userName=result.getString("userName");
				String password=result.getString("password");
				String email=result.getString("email");
				String address=result.getString("address");
				String role=result.getString("role");
				Timestamp createDate=result.getTimestamp("createDate");
				Timestamp lastLoginDate=result.getTimestamp("lastLoginDate");
				
				u=new User(userId1,userName,password,email,address,role,createDate,lastLoginDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		String sql="Update user SET userName=? ,password=?, email=?,address=?,lastLoginDate=? where userId=?";
		Connection conn=DBConnection.getConnection();
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3,user.getEmail());
			stmt.setString(4, user.getAddress());
			stmt.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
			stmt.setInt(6,user.getUserId());
			int i=stmt.executeUpdate();
			System.out.print(i+"updated successsfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		String sql="delete from `user` where `userId`=userId";
		Connection conn=DBConnection.getConnection();
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			int i=stmt.executeUpdate();
			System.out.println(i+"user deleted.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		String sql="select * from `user`";
		Connection conn=DBConnection.getConnection();
		List<User> list=new ArrayList<User>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			while(result.next()) {
				int userId=result.getInt("userId");
				String userName=result.getString("userName");
				String password=result.getString("password");
				String email=result.getString("email");
				String address=result.getString("address");
				String role=result.getString("role");
				Timestamp createDate=result.getTimestamp("createDate");
				Timestamp lastLoginDate=result.getTimestamp("lastLoginDate");
				
				User u=new User(userId, userName, password, email, address, role, createDate, lastLoginDate);
				list.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	public static User getUserByEmail(String email) {
		String sql="select * from `user` where email=email";
		Connection conn=DBConnection.getConnection();
		User u=null;
		try {
			Statement stmt=conn.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			
			while(result.next()) {
				int userId1=result.getInt("userId");
				String userName=result.getString("userName");
				String password=result.getString("password");
				String email1=result.getString("email");
				String address=result.getString("address");
				String role=result.getString("role");
				Timestamp createDate=result.getTimestamp("createDate");
				Timestamp lastLoginDate=result.getTimestamp("lastLoginDate");
				
				u=new User(userId1,userName,password,email,address,role,createDate,lastLoginDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}	
}


