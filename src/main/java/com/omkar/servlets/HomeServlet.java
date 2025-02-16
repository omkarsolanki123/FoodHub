package com.omkar.servlets;

import java.io.IOException;
import java.util.List;

import com.omkar.daoimplimentation.RestaurantDAOImpl;
import com.omkar.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/home")
public class HomeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Home Servlet called");
		RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
		List<Restaurant> allRestaurants = restaurantDAO.getAllRestaurants();
		
		req.setAttribute("restaurants", allRestaurants);
		RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
		rd.forward(req, resp);
		
//		for (Restaurant restaurant : allRestaurants) {
//			System.out.println(restaurant);
//		}
	}

}
