package com.omkar.servlets;

//import java.awt.Menu;
import com.omkar.model.Menu;

import java.io.IOException;
import java.util.List;

import com.omkar.daoimplimentation.MenuDAOImpl;
import com.omkar.daoimplimentation.RestaurantDAOImpl;
import com.omkar.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/menu")
public class MenuServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Received restaurantId: " + req.getParameter("restaurantId"));

		int rId = Integer.parseInt(req.getParameter("restaurantId"));
        
        // Fetch menus for the given restaurant
        MenuDAOImpl menuDAO = new MenuDAOImpl();
        List<Menu> menuList = menuDAO.getAllMenusByRestaurant(rId);
        
        // Fetch all restaurants
        RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
        List<Restaurant> allRestaurants = restaurantDAO.getAllRestaurants();
        
        // Fetch specific restaurant by ID
        Restaurant selectedRestaurant = restaurantDAO.getRestaurant(rId);
        
        // Set attributes
        req.setAttribute("restaurants", allRestaurants);
        req.setAttribute("menu", menuList);
        req.setAttribute("selectedRestaurant", selectedRestaurant); // Setting selected restaurant
        System.out.println("Selected Restaurant: " + selectedRestaurant);
        RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
        rd.forward(req, resp);
//

	}
}
