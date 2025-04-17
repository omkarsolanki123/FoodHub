package com.omkar.servlets;

import java.io.IOException;

import com.omkar.dao.UserDAO;
import com.omkar.daoimplimentation.UserDAOImpl;
import com.omkar.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	 private UserDAO userDAO;

	    @Override
	    public void init() {
	        userDAO = new UserDAOImpl();
	    }
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	// TODO Auto-generated method stub
	    	  String email = req.getParameter("email");
	          String password = req.getParameter("password");

	          User user = userDAO.valideUser(email, password);

	          if (user != null) {
	              HttpSession session = req.getSession();
	              session.setAttribute("user", user);

	              if ("admin".equalsIgnoreCase(user.getRole())) {
	                  resp.sendRedirect("admin-dashboard.jsp");
	              } else {
	                  resp.sendRedirect("user-dashboard.jsp");
	              }
	          } else {
	              resp.sendRedirect("login-failed.jsp");
	          }
	    }
}
