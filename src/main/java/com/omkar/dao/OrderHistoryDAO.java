package com.omkar.dao;

import java.util.List;

import com.omkar.model.OrderHistory;

public interface OrderHistoryDAO {
	void addOrderHistory(OrderHistory orderHistory);
	OrderHistory getOrderHistory(int orderHistoryId);
	void updateOrderHistory(OrderHistory orderHistory);
	void deleteOrderHistory(int orderHistoryId);
	List<OrderHistory> getAllOrderHistoriesByUser(int userId);

}
