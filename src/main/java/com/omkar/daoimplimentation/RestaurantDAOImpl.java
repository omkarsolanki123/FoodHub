package com.omkar.daoimplimentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.omkar.dao.RestaurantDAO;
import com.omkar.model.Restaurant;
import com.omkar.model.User;
import com.omkar.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO{
	private static final String INSERT_QUERY="INSERT into `restaurant` (`restaurantId`,`name`,`address`,`phone`,`rating`,`cuisineType`,`isActive`,`eta`,`adminUserId`,`imagePath`) values(?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_USER_QUERY="SELECT *FROM `restaurant` WHERE `restaurantId`=?";
	private static final String UPDATE_USER_QUERY="UPDATE `restaurant` SET `name`=? `address`=? `phone`=? `cuisineType`=? `isActive`=? `imagePath`=?";
	private static final String DELETE_USER_QUERY="DELETE FROM `restaurant` WHERE `restaurantId`=?";
	private static final String GET_ALL_USERS="SELECT *FROM `restaurant`";
//

	@Override
	public void addRestaurant(Restaurant restaurant) {
		try(Connection con = DBConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement(INSERT_QUERY)) {
			
			preparedStatement.setInt(1, restaurant.getRestaurantId());
			preparedStatement.setString(2, restaurant.getName());
			preparedStatement.setString(3,restaurant.getAddress());
			preparedStatement.setString(4,restaurant.getPhone());
			preparedStatement.setFloat(5,restaurant.getRating());
			preparedStatement.setString(6,restaurant.getCuisineType());
			preparedStatement.setInt(7,restaurant.getIsAvailable());
			preparedStatement.setString(8,restaurant.getEta());
			preparedStatement.setInt(9,restaurant.getAdminUserId());
			preparedStatement.setString(10,restaurant.getImagePath());
			
			int executeUpdate=preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public Restaurant getRestaurant(int restaurantId) {
		ResultSet resultSet=null;
		Restaurant restaurant=null;
		
		
		try(Connection con= DBConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement(GET_USER_QUERY);) {
			
			preparedStatement.setInt(1,restaurantId);
			resultSet=preparedStatement.executeQuery();
			if (resultSet.next()) { // Ensure there is data before extracting
	            restaurant = extractRestaurant(resultSet);
	            System.out.println("Fetched Restaurant: " + restaurant.getName());
	        } else {
	            System.out.println("No restaurant found for ID: " + restaurantId);
	        }
//			restaurant= extractRestaurant(resultSet);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return restaurant;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		try(Connection con=DBConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement(UPDATE_USER_QUERY)	) {
			 preparedStatement.setString(1, restaurant.getName());
			 preparedStatement.setString(2, restaurant.getAddress());
			 preparedStatement.setString(3, restaurant.getPhone());
			 preparedStatement.setString(4, restaurant.getCuisineType());
			 preparedStatement.setInt(5, restaurant.getIsAvailable());
			 preparedStatement.setString(6, restaurant.getImagePath());
			 
			 preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		try (Connection con=DBConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement(DELETE_USER_QUERY)){

				preparedStatement.setInt(1, restaurantId);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		ArrayList<Restaurant> restaurantList=new ArrayList<Restaurant>();
		try {
			Connection con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			ResultSet resultSet=stmt.executeQuery(GET_ALL_USERS);
			
			while(resultSet.next()) {
				Restaurant restaurant=extractRestaurant(resultSet);
				restaurantList.add(restaurant);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return restaurantList;
	}
	Restaurant extractRestaurant(ResultSet resultSet) throws SQLException {
//		
		int restaurantId=resultSet.getInt("restaurantId");
		String name=resultSet.getString("name");
		String address=resultSet.getString("address");
		String phone=resultSet.getString("phone");
		int rating=resultSet.getInt("rating");
		String cuisineType=resultSet.getString("cuisineType");
		int isAvailable=resultSet.getInt("isActive");
		String eta=resultSet.getString("eta");
		int adminUserId=resultSet.getInt("adminUserId");
		String imagePath=resultSet.getString("imagePath");
		
		Restaurant restaurant = new Restaurant(restaurantId, name, address, phone, rating, cuisineType, isAvailable, eta, adminUserId, imagePath);
		return restaurant;
	}
	

}
