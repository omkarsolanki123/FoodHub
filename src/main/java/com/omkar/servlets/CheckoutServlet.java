package com.omkar.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.sql.SQLException;

import com.omkar.dao.OrderDAO;
import com.omkar.daoimplimentation.OrderDAOImpl;
import com.omkar.model.Cart;
import com.omkar.model.CartItem;
import com.omkar.model.Order;
import com.omkar.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet{
	private OrderDAO orderDAO;
	@Override
	public void init(){
		orderDAO=new OrderDAOImpl();							
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart=(Cart)session.getAttribute("cart");
		User user=(User)session.getAttribute("loggedIn");
		System.err.println("user: "+user);
		System.err.println("cart: "+cart);
//		System.out.println("checkout called");
		if(cart!=null && user!=null && !cart.getItems().isEmpty()) {
			String paymentMthod=req.getParameter("paymentMethod");
			Order order = new Order();
			order.setUserId(user.getUserd());
			System.out.println(session.getAttribute("restaurantId"));
			order.setRestaurantId((int)session.getAttribute("restaurantId"));
			order.setDate(new Timestamp(0));
			order.setPaymentMode(paymentMthod);
			order.setStatus("Pendding");
			
			double totalAmount=0;
			for(CartItem item:cart.getItems().values()) {
				totalAmount+=item.getPrice()*item.getQuantity();
			}
			order.setTotalAmount(totalAmount);
			
			orderDAO.addOrder(order);
			
			session.removeAttribute("cart");
			session.setAttribute("order", order);
			resp.sendRedirect("orderConfirmation.jsp");
		}else {
			resp.sendRedirect("home");
		}
	}


}
