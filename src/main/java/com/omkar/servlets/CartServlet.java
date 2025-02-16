package com.omkar.servlets;

import java.awt.MenuItem;
import java.io.IOException;

import com.omkar.dao.MenuDAO;
import com.omkar.daoimplimentation.MenuDAOImpl;
import com.omkar.model.Cart;
import com.omkar.model.CartItem;
import com.omkar.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		
		int newRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		Integer currentRestaurantId = (Integer)session.getAttribute("restaurantId");
		
		if(cart==null || newRestaurantId!=currentRestaurantId) {
			cart=new Cart();
			session.setAttribute("cart", cart);
			session.setAttribute("restaurantId", newRestaurantId);
		}
		
		String action = req.getParameter("action");
		if(action.equals("add")) {
			addItemToCart(req,cart);
		}else if(action.equals("update")) {
			updateCartItem(req,cart);
		}else if(action.equals("remove")) {
			removeItemFromCart(req,cart);
		}
		
		
		
		
		
//		String action = req.getParameter("action");
//		try {
//			if(action.equals("add")) {
//				addItemToCart(req,cart);
//				
//			}else if(action.equals("update")) {
//				updateCartItem(req,cart);
//				
//			}else if(action.equals("remove")) {
//				removeItemFromCart(req,cart);
//				
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		resp.sendRedirect("cart.jsp");
		
		
	}
	
	private void addItemToCart(HttpServletRequest req,Cart cart) {
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		
		MenuDAO menuDAO = new MenuDAOImpl();
		Menu menuItem = menuDAO.getMenu(itemId);
		
		System.out.println("The menu in Cart Servlet"+menuItem);
		
		if(menuItem!=null) {
			CartItem item = new CartItem(
					menuItem.getMenuId(),
					menuItem.getRestaurantId(),
					menuItem.getItemName(),
					quantity,
					menuItem.getPrice()
			);
			cart.addItem(item);
		}
	
		
	}
	void updateCartItem(HttpServletRequest req,Cart cart) {
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		cart.updateItem(itemId,quantity);
		
	}
	void removeItemFromCart(HttpServletRequest req,Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		cart.removeItem(itemId);
	}
	
}
