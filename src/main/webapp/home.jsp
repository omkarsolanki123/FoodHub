<!DOCTYPE html>
<%@page import="com.omkar.model.Restaurant"%>
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
        </nav>
    </header>

    <main>
        <div class="filters">
            <button class="filter-btn active">All</button>
            <button class="filter-btn">Rating 4.0+</button>
            <button class="filter-btn">Pure Veg</button>
            <button class="filter-btn">Fast Delivery</button>
            <button class="filter-btn">Premium</button>
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