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
            <div class="restaurant-info">
                <h2>The Grand Kitchen</h2>
                <div class="rating">
                    <span class="stars">4.8 ★</span>
                    <span class="reviews">(2000+ reviews)</span>
                </div>
            </div>
        </div>
        <div class="nav-right">
            <a href="menu.jsp" class="menu-btn">Back to Menu</a><%--need to go back to same menu of restaurant --%>
        </div>
    </nav>


    <main class="cart-content">
	<%
		Cart cart=(Cart)session.getAttribute("cart");
		Integer restaurantId=(Integer)session.getAttribute("restaurantId");
		
		if(cart!=null && cart.getItems() != null && !cart.getItems().isEmpty()){
			for(CartItem item: cart.getItems().values()){
			
		
	%>
    
        <div class="cart-container">
            <div class="cart-items">
                <h2>Your Cart</h2>
                
                <div class="cart-item">
                    <img src="https://images.unsplash.com/photo-1593560708920-61dd98c46a4e?w=300&auto=format" alt="Butter Chicken">
                    <div class="item-details">
                        <div class="item-info">
                            <h3><%=item.getItemname()%></h3>
                            <span class="price">&#8377;<%=item.getPrice()%></span>
                        </div>
                        <div class="item-controls">
                            <div class="quantity-controls">
                            	<form action="cart">
	                            	<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
	                            	<input type="hidden" name="action" value="update">
	                            	<input type="hidden" name="quantity" value="<%=item.getQuantity()-1%>">
                                	<button class="quantity-btn">−</button>
                            	
                            	</form>
                                <span class="quantity"><%=item.getQuantity()%></span>
                            	<form action="cart">
	                            	<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
	                            	<input type="hidden" name="action" value="update">
	                            	<input type="hidden" name="quantity" value="<%=item.getQuantity()+1%>">
		                            <button class="quantity-btn">+</button>
                            	</form>
                            </div>
                            <form action="cart">
                            	<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
                            	<input type="hidden" name="action" value="remove">
	                            <button class="remove-btn">Remove</button>
                            </form>
                        </div>
                    </div>
                </div>

            </div>

            <div class="cart-summary">
                <h2>Order Summary</h2>
                <div class="summary-items">
                    <div class="summary-item">
                        <span>Subtotal</span>
                        <span>&#8377;55.96</span>
                    </div>
                    <div class="summary-item">
                        <span>Delivery Fee</span>
                        <span>&#8377;2.99</span>
                    </div>
                    <div class="summary-item">
                        <span>Tax</span>
                        <span>&#8377;4.48</span>
                    </div>
                    <div class="summary-item total">
                        <span>Total</span>
                        <span>&#8377;63.43</span>
                    </div>
                </div>
                <button class="checkout-btn">Proceed to Checkout</button>
                <div class="delivery-time">
                    <span class="delivery-icon">🚚</span>
                    <span>Estimated delivery time: 30-45 minutes</span>
                </div>
            </div>
        </div>
        <%
			}
		}else{
			%>
			
			<h2>Your cart is empty. <a href="menu.jsp">Go to Menu</a></h2>
			<%
		}
        %>
       
    </main>
  
</body>
</html>
