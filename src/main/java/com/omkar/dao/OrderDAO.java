package com.omkar.dao;

import java.util.List;

import com.omkar.model.Order;

public interface OrderDAO {
	void addOrder(Order order);
	Order getOrderItem(int order);
	void updateOrder(Order order);
	void deleteOrder(int orderId);
	List<Order> getAllOrdersByUser(int userId);

}
