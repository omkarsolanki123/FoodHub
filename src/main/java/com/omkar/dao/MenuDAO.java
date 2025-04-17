package com.omkar.dao;

import com.omkar.model.Menu;
import java.util.List;

public interface MenuDAO {
//	Menu addMenu(int menuId);
	Menu getMenu(int menuId);
	void updateMenu(Menu menu);
	void deleteMenu(int menuId);
	List<Menu> getAllMenusByRestaurant(int restaurantId);
	void addMenu(Menu menu);
	String getRestaurantName(int restaurantId);

}
