<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="com.omkar.model.Menu,com.omkar.model.Restaurant"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Menu</title>
    <link rel="stylesheet" href="menu.css">
</head>
<body>
    <nav>
        <div class="nav-left">
            <a href="home" style="text-decoration: none; color: inherit;"><h1 class="logo">FoodHub</h1></a>
            <%
		
				Restaurant r = (Restaurant) request.getAttribute("selectedRestaurant");
				if (r != null) {	
			
			%>
            <div class="restaurant-info">
         
                <h2><%=r.getName() %></h2>
                <div class="rating">
                    <span class="stars"><%=r.getRating() %> &#9733;</span>
                    <%-- <span class="reviews">(2000+ reviews)</span>--%>
                </div>
                
            </div>
            <%} %>
        </div>
        <div class="nav-right">
        <a href="cart.jsp">
            <button class="cart-btn">
                Cart (<%=session.getAttribute("quantity") %>)
            </button>
        </a>
        </div>
    </nav>

    <main class="menu-content">
        <section class="menu-section">
            <h2>Our Menu</h2>
            <div class="menu-items">
            
            <%
            	List<Menu> menuList=(List<Menu>)request.getAttribute("menu");
            if(menuList!=null && !menuList.isEmpty()){
            	for(Menu m: menuList){
            	
            %>
                <div class="menu-item">
                    <img src="<%=m.getImagePath()%>" alt="Image not found">
                    <div class="item-details">
                        <div class="item-header">
                            <h3><%=m.getItemName() %></h3>
                            <span class="bestseller"><%=m.getRating() %>&#9733; Bestseller</span>
                        </div>
                        <p class="price">&#8377;<%=m.getPrice() %></p>
                        <p class="description"><%=m.getDescription() %></p>
                        <form action="cart?menuId=<%=m.getMenuId()%>,restaurantId=<%= r.getRestaurantId()%>" method="get">
						    
						    <input type="hidden" name="restaurantId" value="<%= session.getAttribute("restaurantId") != null ? session.getAttribute("restaurantId") : request.getParameter("restaurantId") %>">
						    <input type="hidden" name="itemId" value="<%=m.getMenuId()%>">
						    <input type="hidden" name="quantity" value="1" min="1">
						    <input type="hidden" name="action" value="add">
						    <button class="add-btn">Add to Cart</button>
						</form>

                    </div>
                </div>
			<%
					}
	            }else{
			%>
			<p>No Menu items</p>
			<%
	            }
			
			%>
            </div>
           
        </section>
    </main>
</body>
</html>