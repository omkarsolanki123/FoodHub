package com.omkar.dao;

import java.util.List;

import com.omkar.model.Restaurant;
import com.omkar.model.User;

public interface RestaurantDAO {
	void addRestaurant(Restaurant restaurant);
	Restaurant getRestaurant(int restaurantId);
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(int restaurantId);
	List<Restaurant> getAllRestaurants();
	

}
