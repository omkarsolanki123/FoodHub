package com.omkar.daoimplimentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.omkar.dao.MenuDAO;
import com.omkar.model.Menu;
//import com.omkar.dao.RestaurantDAO;
import com.omkar.model.Restaurant;
import com.omkar.model.User;
import com.omkar.utility.DBConnection;

public class MenuDAOImpl implements MenuDAO{
	private static final String INSERT_QUERY="INSERT into `menu` (`menuId`,`restaurantId`,`itemName`,`description`,`price`,`ratings`,`isActive`,`imagePath`) values(?,?,?,?,?,?,?,?)";
	private static final String GET_MENU_BY_MenuId_QUERY="SELECT *FROM `menu` WHERE `menuId`=?";
	private static final String UPDATE_MENU_QUERY="UPDATE `menu` SET `itemName`=? `description`=? `price`=? `isAvailable`=? `imagePath`=?";
	private static final String DELETE_MENU_QUERY="DELETE FROM `menu` WHERE `userId`=?";
	private static final String GET_MENU_BY_RestaurantId="SELECT *FROM `Menu` where `restaurantId`=?";
//

//	@Override
//	public Menu addMenu(int menuId) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	@Override
	public void updateMenu(Menu menu) {
		try(Connection con=DBConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement(UPDATE_MENU_QUERY)	) {
			 preparedStatement.setString(1, menu.getItemName());
			 preparedStatement.setString(2, menu.getDescription());
			 preparedStatement.setFloat(3, menu.getPrice());
			 preparedStatement.setInt(5, menu.getIsAvailable());
			 preparedStatement.setString(6, menu.getImagePath());
			 
			 preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void deleteMenu(int menuId) {
		try (Connection con=DBConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement(DELETE_MENU_QUERY)){

				preparedStatement.setInt(1, menuId);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}


	
	@Override
	public List<Menu> getAllMenusByRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		ArrayList<Menu> menuList=new ArrayList<Menu>();
		try (Connection con=DBConnection.getConnection();
			PreparedStatement preparedStatement=con.prepareStatement(GET_MENU_BY_RestaurantId)){
			preparedStatement.setInt(1, restaurantId);
				
			try (ResultSet resultSet=preparedStatement.executeQuery()){
				while(resultSet.next()) {
					Menu menu=extractMenu(resultSet);
					menuList.add(menu);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return menuList;
	}
		
		Menu extractMenu(ResultSet resultSet) throws SQLException {
			int menuId=resultSet.getInt("menuId");
			int restaurantId=resultSet.getInt("restaurantId");
			String itemName=resultSet.getString("itemName");
			String description=resultSet.getString("description");
			int price=resultSet.getInt("price");
			int ratings=resultSet.getInt("ratings");
			int isAvailable=resultSet.getInt("isAvailable");
			String imagePath=resultSet.getString("imagePath");
			
			Menu menu = new Menu(menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath);
			return menu;
		}


	@Override
	public void addMenu(Menu menu) {
		// TODO Auto-generated method stub
		try(Connection con = DBConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement(INSERT_QUERY)) {
			
			preparedStatement.setInt(1,menu.getMenuId());
			preparedStatement.setInt(2,menu.getRestaurantId());
			preparedStatement.setString(3,menu.getItemName());
			preparedStatement.setString(4,menu.getDescription());
			preparedStatement.setInt(5,menu.getPrice());
			preparedStatement.setInt(6,menu.getIsAvailable());
			preparedStatement.setString(7,menu.getImagePath());
			
			int executeUpdate=preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public Menu getMenu(int menuId) {
		ResultSet resultSet=null;
		Menu menu=null;
		
		
		try(Connection con= DBConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement(GET_MENU_BY_MenuId_QUERY);) {
			
			preparedStatement.setInt(1,menuId);
			resultSet=preparedStatement.executeQuery();
			
			menu= extractMenu(resultSet);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return menu;
	}
	

}
