<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@
page import="java.util.List, com.tap.model.Restaurant"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title> Foodie Express</title>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: Arial, sans-serif;
}

body {
	background: #f8f8f8;
}

/* Navbar */
.navbar {
	background: #ff5a5f;
	color: white;
	padding: 15px 50px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.logo {
	font-size: 28px;
	font-weight: bold;
}

.nav-links {
	list-style: none;
	display: flex;
	gap: 25px;
}

.nav-links a {
	color: white;
	text-decoration: none;
	font-size: 17px;
}

.nav-links a:hover {
	text-decoration: underline;
}

/* Hero Section */
.hero {
	background:
		url('https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=1600')
		center/cover;
	height: 450px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.hero-content {
	text-align: center;
	color: white;
	background: rgba(0, 0, 0, 0.5);
	padding: 30px;
	border-radius: 10px;
}

.hero-content h1 {
	font-size: 45px;
	margin-bottom: 15px;
}

.hero-content p {
	margin-bottom: 20px;
	font-size: 18px;
}

.search-box {
	display: flex;
	justify-content: center;
}

.search-box input {
	width: 400px;
	padding: 12px;
	border: none;
	border-radius: 5px 0 0 5px;
}

.search-box button {
	padding: 12px 20px;
	border: none;
	background: #ff5a5f;
	color: white;
	cursor: pointer;
	border-radius: 0 5px 5px 0;
}

/* Categories */
.categories {
	padding: 40px;
}

.categories h2 {
	margin-bottom: 20px;
}

.category-container {
	display: flex;
	gap: 20px;
	flex-wrap: wrap;
}

.category {
	background: white;
	padding: 20px;
	width: 150px;
	text-align: center;
	border-radius: 10px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	cursor: pointer;
}

.category:hover {
	transform: scale(1.05);
}

/* Restaurant Section */
.restaurants {
	padding: 40px;
}

.restaurants h2 {
	margin-bottom: 25px;
}

.restaurant-grid {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
	gap: 25px;
	text-decoration: none;
    color: inherit;
}

.restaurant-grid a {
    text-decoration: none;
    color: inherit;
    display: block;
}

.restaurant-grid a:hover,
.restaurant-grid a:visited,
.restaurant-grid a:active,
.restaurant-grid a:link {
    text-decoration: none;
    color: inherit;
}

.card {
	background: white;
	border-radius: 12px;
	overflow: hidden;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	transition: 0.3s;
}

.card:hover {
	transform: translateY(-5px);
}

.card img {
	width: 100%;
	height: 200px;
	object-fit: cover;
}

.card-content {
	padding: 15px;
}

.card-content h3 {
	margin-bottom: 8px;
}

.rating {
	color: green;
	font-weight: bold;
}

.offer {
	color: red;
	font-weight: bold;
	margin-top: 8px;
}

/* Footer */
.footer {
	background: #222;
	color: white;
	text-align: center;
	padding: 20px;
	margin-top: 40px;
}

</style>

</head>
<body>

	<!-- Navbar -->
	<nav class="navbar">
		<div class="logo"> Foodie Express</div>

		<ul class="nav-links">
			<li><a href="callRestaurantServlet">Home</a></li>
			<li><a href="callRestaurantServlet">Restaurants</a></li>
			
			<li><a href="callRestaurantServlet">Cart</a></li>
			<li><a href="Register.html">Register</a></li>
			<li><a href="Login.html">Login</a></li>
		</ul>
	</nav>

	<!-- Hero Section -->
	<section class="hero">
		<div class="hero-content">
			<h1>Order Food Anytime, Anywhere</h1>
			<p>Discover the best restaurants near you</p>

			<div class="search-box">
				<input type="text" placeholder="Search for restaurants or dishes">
				<button>Search</button>
			</div>
		</div>
	</section>

	<!-- Restaurants -->
	<section class="restaurants">
		<h2>Top Restaurants</h2>
		<div class="restaurant-grid">
			<%
			@SuppressWarnings("unchecked")			
			List<Restaurant> allRestaurants = (List<Restaurant>) request.getAttribute("all");
			if (allRestaurants == null) {
			    allRestaurants = java.util.Collections.emptyList();
			}
			for (Restaurant rest : allRestaurants) {
			%>
			<a href=menu?RestaurantID=<%=rest.getRestaurantID()%>& RestaurantName=<%=rest.getName() %>>
			<div class="card">
				<img
					src="<%=rest.getImagePath()%>">
				<div class="card-content">
					<h3><%=rest.getName()%></h3>
					<p><%=rest.getAddress()%></p>
					<p><%=rest.getCuisineType()%>
					</p>
					<p class="rating">
						⭐<%=rest.getRating()%>| <%=rest.getDeliveryTime()%> mins</p>	
					   <p>
						   <% if(!rest.getIsActive()){ %>
						       Not Available
						   <% } else { %>
						       Available
						   <% } %>
						</p>	
				</div>
			</div>
			<%
			}
			%>
			</a>
		</div>
	</section>
	<!-- Footer -->
	<footer class="footer">
		<p>© 2026 Foodie Express | Fast Delivery | Fresh Food</p>
	</footer>
</body>
</html>