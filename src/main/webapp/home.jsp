<!DOCTYPE html>
<%@page import="com.omkar.model.CartItem"%>
<%@page import="com.omkar.model.Restaurant"%>
<%@page import="com.omkar.model.Cart"%>

<%@page import="java.util.List"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Delivery - Restaurants Near You</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
    <header>
        <nav>
            <div class="logo">FoodHub</div>
            <div class="search-bar">
                <input type="text" placeholder="Search for restaurants or cuisines">
            </div>
                
            <div class="sign-in">
                <a href="signIn.html"><button class="btn">Sign in</button></a>
            </div>
            <%
				Cart cart=(Cart)session.getAttribute("cart");
				int totalQuntity=0;
				if(cart!=null && cart.getItems() != null && !cart.getItems().isEmpty()){
					for(CartItem item: cart.getItems().values()){
						totalQuntity+=item.getQuantity();
					}
				}
				session.setAttribute("quantity", totalQuntity);
		
			%>
            <div class="cart">
                <a href="cart.jsp"><button class="btn">Cart(<%=totalQuntity %>)</button></a>
            </div>
          
			
		
			
			    
			
	
        </nav>
    </header>

    <main>
        <div class="filters">
            <button class="filter-btn active">All</button>
            <button class="filter-btn">Rating 4.0+</button>
            <button class="filter-btn">Pure Veg</button>
            <button class="filter-btn">Non Veg</button>
        </div>

        <div class="restaurant-grid">
		<%
		List<Restaurant> restaurants=(List<Restaurant>)request.getAttribute("restaurants");
			for(Restaurant r: restaurants){
				
			
		%>
            <!-- Restaurant Card 1 -->
            <div class="restaurant-card">
            	<a href="menu?restaurantId=<%= r.getRestaurantId() %>" style="text-decoration: none; color: inherit;">
	                <div class="restaurant-img">
	                    <img src="<%=r.getImagePath() %>" alt="">
	                </div>
	                <div class="restaurant-info">
	                    <h3><%= r.getName() %></h3>
	                    
	                    <div class="rating">
	                        <span class="rating-number"><%= r.getRating() %> &#9733;</span>
	                        <span class="dot">&#8226;</span>
	                        <span><%=r.getEta() %> mins</span>
	                    </div>
	                    <p class="cuisine"><%=r.getCuisineType() %></p>
	                    <p class="location"><%=r.getAddress() %> &#8226;</p>
	                </div>
	            </a>
            </div>
        
        <%
        
        }
        %>
        </div>
    </main>
</body>
</html>