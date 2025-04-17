<%@page import="java.util.Map"%>
<%@page import="com.omkar.model.Restaurant"%>
<%@page import="com.omkar.model.CartItem"%>
<%@page import="com.omkar.model.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart - FoodHub</title>
    <link rel="stylesheet" href="cart.css">
</head>
<body>
    <nav>
        <div class="nav-left">
            <a href="home" style="text-decoration: none; color: inherit;"><h1 class="logo">FoodHub</h1></a>
        <%
       
       			 Restaurant r = (Restaurant) session.getAttribute("restaurantName");
				if (r != null) {
			
			%>
            <div class="restaurant-info">
                <h2><%=r.getName() %></h2>
                <div class="rating">
                    <span class="stars"><%=r.getRating() %> ★</span>
                    <span class="reviews">(2000+ reviews)</span>
                </div>
            </div>
        </div>
        <div class="nav-right">
            <a href="menu?restaurantId=<%=r.getRestaurantId()%>" class="menu-btn">Back to Menu</a><%--need to go back to same menu of restaurant --%>
        </div>
            <%
        } 
    %>
    </nav>


    <main class="cart-content">
	<%
		Cart cart=(Cart)session.getAttribute("cart");
		
		if(cart!=null && cart.getItems() != null && !cart.getItems().isEmpty()){
			double totalAmount = 0;
			float deliveryFee = 45f;
			float tax = 63.48f;
			
		
	%>
    
        <div class="cart-container">
            <div class="cart-items">
                <h2>Your Cart</h2>
                
        	<%
        		
					for(CartItem item: cart.getItems().values()){
        			totalAmount+=item.getPrice()*item.getQuantity();
        		
        	%>
                <div class="cart-item">
                    <img src="<%=item.getImagePath() %>" alt="Butter Chicken">
                    <div class="item-details">
                        <div class="item-info">
                            <h3><%=item.getItemname()%></h3>
                            <span class="price">&#8377;<%=item.getPrice()%></span>
                        </div>
                        <div class="item-controls">
                            <div class="quantity-controls">
                            	<form action="cart" method="get">
	                            	<input type="hidden" name="itemId" value="<%=item.getMenuId()%>"><%--item.getItemId--%>
	                            	<input type="hidden" name="action" value="update">
	                            	<input type="hidden" name="quantity" value="<%=Math.max(0, item.getQuantity() - 1)%>">
                                	<button type="submit" class="quantity-btn">−</button>
                            	
                            	</form>
                                <span class="quantity"><%=item.getQuantity()%></span>
                            	<form action="cart" method="get">
	                            	<input type="hidden" name="itemId" value="<%=item.getMenuId()%>"><%--item.getItemId--%>
	                            	<input type="hidden" name="action" value="update">
	                            	<input type="hidden" name="quantity" value="<%=item.getQuantity()+1%>">
		                            <button type="submit" class="quantity-btn">+</button>
                            	</form>
                            </div>
                            <form action="cart" method="get">
                            	<input type="hidden" name="itemId" value="<%=item.getMenuId()%>"><%--item.getItemId--%>
                            	<input type="hidden" name="action" value="remove">
	                            <button type="submit" class="remove-btn">Remove</button>
                            </form>
                        </div>
                    </div>
                </div>
            <%} %>

            </div>

            <div class="cart-summary">
                <h2>Order Summary</h2>
                <div class="summary-items">
                    <div class="summary-item">
                        <span>Subtotal</span>
                        <span>&#8377;<%=totalAmount %></span>
                    </div>
                    <div class="summary-item">
                        <span>Delivery Fee</span>
                        <span>&#8377;<%=deliveryFee %></span>
                    </div>
                    <div class="summary-item">
                        <span>Tax</span>
                        <span>&#8377;<%=tax %></span>
                    </div>
                    <div class="summary-item total">
                        <span>Total</span>
                        <span>&#8377;<%=String.format("%.2f", totalAmount+deliveryFee+tax)%></span>
                    </div>
                </div>
                <a href="checkout.jsp"><button class="checkout-btn">Proceed to Checkout</button></a>
                <div class="delivery-time">
                    <span class="delivery-icon">🚚</span>
                    <span>Estimated delivery time: 30-45 minutes</span>
                </div>
            </div>
        </div>
        <%
        		

		}else{
			%>
			
			<h2>Your cart is empty. <a href="menu.jsp">Go to Menu</a></h2>
			<%
		}
        %>
       
    </main>
  
</body>
</html>
