package com.omkar.dao;

import java.util.List;

import com.omkar.model.OrderItem;

public interface OrderItemDAO {
	void addOrderItem(OrderItem orderItem);
	OrderItem getOrderItem(int orderItemId);
	void updateOrderItem(OrderItem orderItem);
	void deleteOrderItem(int orderItemId);
	List<OrderItem> getAllOrderItemByOrder(int orderId);

}
