package com.omkar.servlets;

import java.awt.MenuItem;
import java.io.IOException;
import java.util.List;

import com.omkar.dao.MenuDAO;
import com.omkar.daoimplimentation.MenuDAOImpl;
import com.omkar.daoimplimentation.RestaurantDAOImpl;
import com.omkar.model.Cart;
import com.omkar.model.CartItem;
import com.omkar.model.Menu;
import com.omkar.model.Restaurant;

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
		
		int rId = Integer.parseInt(req.getParameter("restaurantId"));
		System.out.println("cartServlet RID:"+rId);
		
		 	RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
	        List<Restaurant> allRestaurants = restaurantDAO.getAllRestaurants();
	        
	        // Fetch specific restaurant by ID
	        Restaurant selectedRestaurant = restaurantDAO.getRestaurant(rId);
	        if (selectedRestaurant != null) {
	            System.out.println("Selected Restaurant**********: " + selectedRestaurant.getName());
	            session.setAttribute("restaurantName", selectedRestaurant);
	        } else {
	            System.out.println("Restaurant not found for ID: " + rId);
	        }
		
		if(cart==null || newRestaurantId!=currentRestaurantId && currentRestaurantId!=null) {
			cart=new Cart();
			session.setAttribute("cart", cart);
			session.setAttribute("restaurantId", newRestaurantId);
//			session.setAttribute("restaurantName", MenuDAO.ge);
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
		session.setAttribute("cart", cart);;
		resp.sendRedirect("cart.jsp");
		
		
	}
	
	private void addItemToCart(HttpServletRequest req,Cart cart) {
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		System.out.println("Qunatity: "+quantity);
		System.out.println("ItemId: "+itemId);
		
		MenuDAO menuDAO = new MenuDAOImpl();
		Menu menuItem = menuDAO.getMenu(itemId);
		
		System.out.println("The menu in Cart Servlet: "+menuItem);
		
		if(menuItem!=null) {
			int uniqueKey=menuItem.getMenuId();
			CartItem item = new CartItem(
					menuItem.getMenuId(),
					menuItem.getRestaurantId(),
					menuItem.getItemName(),
					quantity,
					menuItem.getPrice(),
					menuItem.getImagePath()
			);
//			  if (cart.getItems().containsKey(uniqueKey)) {
//		            CartItem existingItem = cart.getItems().get(uniqueKey);
//		            existingItem.setQuantity(existingItem.getQuantity() + quantity);
//		        } else {
//		            cart.addItem(item);
//		        }
			cart.addItem(item);
		}
	
		
	}
//	void updateCartItem(HttpServletRequest req,Cart cart) {
//		int quantity = Integer.parseInt(req.getParameter("quantity"));
//		int itemId = Integer.parseInt(req.getParameter("itemId"));
//		cart.updateItem(itemId,quantity);
//		
//	}
	void updateCartItem(HttpServletRequest req, Cart cart) {
	    String quantityStr = req.getParameter("quantity");
	    String itemIdStr = req.getParameter("itemId");

	    if (quantityStr == null || itemIdStr == null) {
	        System.out.println("Error: Missing parameters for update");
	        return;  // Stop execution if parameters are missing
	    }

	    int quantity = Integer.parseInt(quantityStr);
	    int itemId = Integer.parseInt(itemIdStr);

	    if (quantity <= 0) {
	        cart.removeItem(itemId); // If quantity is 0, remove item
	    } else {
	        cart.updateItem(itemId, quantity);
	    }
	}

	void removeItemFromCart(HttpServletRequest req,Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		cart.removeItem(itemId);
	}
	
}
