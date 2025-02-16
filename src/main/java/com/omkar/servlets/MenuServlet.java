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
		MenuDAOImpl menuDAO = new MenuDAOImpl();
		List<Menu> menuList = menuDAO.getAllMenusByRestaurant(rId);
		
		RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
		List<Restaurant> allRestaurants = restaurantDAO.getAllRestaurants();
		req.setAttribute("restaurants", allRestaurants);
		
		req.setAttribute("menu", menuList);
		RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		rd.forward(req, resp);
//

	}
}
