package com.omkar.daoimplimentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.omkar.dao.UserDAO;
import com.omkar.model.User;
import com.omkar.utility.DBConnection;

public class UserDAOImpl implements UserDAO{
	private static final String INSERT_QUERY="INSERT into `user` (`name`,`username`,`password`,`email`,`phone`,`address`,`role`) values(?,?,?,?,?,?,?)";
	private static final String GET_USER_QUERY="SELECT *FROM `user` WHERE `userId`=?";
	private static final String UPDATE_USER_QUERY="UPDATE `user` SET `name`=? `password`=? `phone`=? `address`=? `role`=?";
	private static final String DELETE_USER_QUERY="DELETE FROM `user` WHERE `userId`=?";
	private static final String GET_ALL_USERS="SELECT *FROM `user`";

	@Override
	public void addUser(User user) {
		
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement(INSERT_QUERY)) {
			
			preparedStatement.setString(1,user.getName());
			preparedStatement.setString(2,user.getUserName());
			preparedStatement.setString(3,user.getPassword());
			preparedStatement.setString(4,user.getEmail());
			preparedStatement.setString(5,user.getPhone());
			preparedStatement.setString(6,user.getAddress());
			preparedStatement.setString(7,user.getPhone());
			
			int executeUpdate=preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUser(int userId) {
		ResultSet resultSet=null;
		User user=null;
		
		
		try(Connection con= DBConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement(GET_USER_QUERY);) {
			
			preparedStatement.setInt(1,userId);
			resultSet=preparedStatement.executeQuery();
			
			user= extractUser(resultSet);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return user;
	}

	@Override
	public void updateUser(User user) {
		
		
		try(Connection con=DBConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement(UPDATE_USER_QUERY)	) {
			 preparedStatement.setString(1, user.getName());
			 preparedStatement.setString(2, user.getPassword());
			 preparedStatement.setString(3, user.getPhone());
			 preparedStatement.setString(4, user.getAddress());
			 preparedStatement.setString(5, user.getRole());
			 
			 preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(int userId) {
		
		
		try (Connection con=DBConnection.getConnection();
			PreparedStatement preparedStatement=con.prepareStatement(DELETE_USER_QUERY)){

			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers(){
		
		
		ArrayList<User> userList=new ArrayList<User>();
		try {
			Connection con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			ResultSet resultSet=stmt.executeQuery(GET_ALL_USERS);
			
			while(resultSet.next()) {
				User user=extractUser(resultSet);
				userList.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	User extractUser(ResultSet resultSet) throws SQLException {
		int userId=resultSet.getInt("userid");
		String name=resultSet.getString("name");
		String username=resultSet.getString("username");
		String password=resultSet.getString("password");
		String email=resultSet.getString("email");
		String phone=resultSet.getString("phone");
		String address=resultSet.getString("address");
		String role=resultSet.getString("role");
		
		User user=new User(userId, name, username, password, email, phone, address, role, null, null);
		return user;
	}
//	public static void main(String[] args) {
//		System.out.println("Hello");
//	}

	@Override
	public User valideUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
