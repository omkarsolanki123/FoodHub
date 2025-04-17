package com.omkar.daoimplimentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.omkar.dao.OrderDAO;
import com.omkar.model.Order;
import com.omkar.utility.DBConnection;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public void addOrder(Order order) {
		
		String query="INSERT INTO `order`(`userId`,`restaurantId`,`orderDate`,`totalAmount`,`status`,`paymentMode`) VALUES(?,?,?,?,?,?) ";
		try(Connection con=DBConnection.getConnection();
				PreparedStatement stmt=con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
			stmt.setInt(1,order.getUserId());
			stmt.setInt(2, order.getRestaurantId());
			stmt.setTimestamp(3, order.getDate());
			stmt.setDouble(4,order.getTotalAmount());
			stmt.setString(5, order.getStatus());
			stmt.setString(6, order.getPaymentMode());
			
			int affected_rows=stmt.executeUpdate();
			if(affected_rows==0) {
				throw new SQLException("Creating order failed, no rows affected");
			}
			try(ResultSet generatedKey=stmt.getGeneratedKeys()){
				if(generatedKey.next()) {
					order.setOrderId(generatedKey.getInt(1));
				}else {
					throw new SQLException("Creating order failed, no ID obtained");
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Error adding order",e);
		}
	}

	@Override
	public Order getOrderItem(int orderId) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM `order` WHERE orderId=?";
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement stmt = con.prepareStatement(query)) {

			stmt.setInt(1, orderId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt("orderId"));
					order.setUserId(rs.getInt("userId"));
					order.setRestaurantId(rs.getInt("restaurantId"));
					order.setDate(rs.getTimestamp("orderDate"));
					order.setTotalAmount(rs.getDouble("totalAmount"));
					order.setStatus(rs.getString("status"));
					order.setPaymentMode(rs.getString("paymentMode"));
					return order;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error retrieving order", e);
		}
		return null;
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		String query = "UPDATE `order` SET userId=?, restaurantId=?, orderDate=?, totalAmount=?, status=?, paymentMode=? WHERE orderId=?";
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement stmt = con.prepareStatement(query)) {

			stmt.setInt(1, order.getUserId());
			stmt.setInt(2, order.getRestaurantId());
			stmt.setTimestamp(3, order.getDate());
			stmt.setDouble(4, order.getTotalAmount());
			stmt.setString(5, order.getStatus());
			stmt.setString(6, order.getPaymentMode());
			stmt.setInt(7, order.getOrderId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error updating order", e);
		}
		
	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM `order` WHERE orderId=?";
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement stmt = con.prepareStatement(query)) {

			stmt.setInt(1, orderId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error deleting order", e);
		}
		
	}

	@Override
	public List<Order> getAllOrdersByUser(int userId) {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<>();
		String query = "SELECT * FROM `order` WHERE userId=?";
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement stmt = con.prepareStatement(query)) {

			stmt.setInt(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt("orderId"));
					order.setUserId(rs.getInt("userId"));
					order.setRestaurantId(rs.getInt("restaurantId"));
					order.setDate(rs.getTimestamp("orderDate"));
					order.setTotalAmount(rs.getDouble("totalAmount"));
					order.setStatus(rs.getString("status"));
					order.setPaymentMode(rs.getString("paymentMode"));
					orders.add(order);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error retrieving orders", e);
		}
		return orders;
	}
	

}
