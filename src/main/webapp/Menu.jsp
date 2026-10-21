<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@
page import="java.util.List, com.tap.model.Restaurant, com.tap.model.Menu"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Menu - Foodie Express</title>

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

/* Restaurant Header */
.restaurant-header {
	background: white;
	margin: 30px 50px;
	padding: 25px 30px;
	border-radius: 12px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	display: flex;
	justify-content: space-between;
	align-items: center;
	flex-wrap: wrap;
	gap: 15px;
}

.restaurant-header h1 {
	font-size: 28px;
	margin-bottom: 6px;
}

.restaurant-header .address {
	color: #666;
	font-size: 15px;
}

.restaurant-rating {
	background: #e6f9ec;
	color: green;
	font-weight: bold;
	padding: 8px 16px;
	border-radius: 20px;
	font-size: 16px;
}

/* Menu Section */
.menu-section {
	padding: 10px 50px 50px;
}

.menu-section h2 {
	margin-bottom: 25px;
}

.menu-grid {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
	gap: 25px;
}

.menu-card {
	background: white;
	border-radius: 12px;
	overflow: hidden;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	transition: 0.3s;
	display: flex;
	flex-direction: column;
}

.menu-card:hover {
	transform: translateY(-5px);
}

.menu-card img {
	width: 100%;
	height: 190px;
	object-fit: cover;
}

.menu-card-content {
	padding: 16px;
	display: flex;
	flex-direction: column;
	flex-grow: 1;
}

.menu-card-content h3 {
	margin-bottom: 6px;
	font-size: 18px;
}

.menu-desc {
	color: #666;
	font-size: 14px;
	margin-bottom: 10px;
	flex-grow: 1;
}

.menu-meta {
	color: #aaa;
	font-size: 12px;
	margin-bottom: 10px;
}

.menu-price {
	font-size: 18px;
	font-weight: bold;
	color: #ff5a5f;
	margin-bottom: 10px;
}

.status {
	display: inline-block;
	padding: 4px 12px;
	border-radius: 20px;
	font-size: 13px;
	font-weight: bold;
	margin-bottom: 12px;
	width: fit-content;
}

.status-available {
	background: #e6f9ec;
	color: green;
}

.status-unavailable {
	background: #fdeaea;
	color: red;
}

/* Quantity selector */
.qty-row {
	display: flex;
	align-items: center;
	justify-content: center;
	gap: 14px;
	margin-bottom: 10px;
}

.qty-btn {
	width: 30px;
	height: 30px;
	border-radius: 50%;
	border: 1px solid #ff5a5f;
	background: white;
	color: #ff5a5f;
	font-size: 18px;
	font-weight: bold;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
	line-height: 1;
}

.qty-btn:hover {
	background: #ff5a5f;
	color: white;
}

.qty-btn:disabled {
	opacity: 0.4;
	cursor: not-allowed;
}

.qty-value {
	min-width: 20px;
	text-align: center;
	font-weight: bold;
}

.add-btn {
	padding: 10px;
	border: none;
	border-radius: 6px;
	background: #ff5a5f;
	color: white;
	font-size: 15px;
	cursor: pointer;
	width: 100%;
}

.add-btn:hover {
	background: #e14448;
}

.empty-msg {
	background: white;
	padding: 30px;
	border-radius: 12px;
	text-align: center;
	color: #666;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
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

	<%
	Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");

	@SuppressWarnings("unchecked")
	List<Menu> menuItems = (List<Menu>) request.getAttribute("allMenu");
	if (menuItems == null) {
	    menuItems = java.util.Collections.emptyList();
	}
	%>

	<!-- Navbar -->
	<nav class="navbar">
		<div class="logo"> Foodie Express</div>

		<ul class="nav-links">
			<li><a href="callRestaurantServlet">Home</a></li>
			<li><a href="callRestaurantServlet">Restaurants</a></li>
			<!--  <li><a href="#">Offers</a></li>-->
			<li><a href="#">Cart</a></li>
			<li><a href="Register.html">Register</a></li>
			<li><a href="Login.html">Login</a></li>
		</ul>
	</nav>

	<!-- Restaurant Header -->
	<%
	if (restaurant != null) {
	%>
	<div class="restaurant-header">
		<div>
			<h1><%=restaurant.getName()%></h1>
			<p class="address"><%=restaurant.getAddress()%></p>
		</div>
		
		<div class="restaurant-rating">⭐ <%=restaurant.getRating()%></div>
	</div>
	
	<%
	} else {
	%>
	<div class="restaurant-header">
		<p>Restaurant details not found.</p>
	</div>
	<%
	}
	%>

	<!-- Menu Section -->
	<section class="menu-section">
		<h2>Menu</h2>

		<%
		if (menuItems.isEmpty()) {
		%>
		<div class="empty-msg">No menu items available for this restaurant yet.</div>
		<%
		} else {
		%>
		
		<div class="menu-grid">
			<%
			for (Menu item : menuItems) {
			%>
			
			<div class="menu-card">
				
				<img src="<%=item.getImagePath()%>" alt="<%=item.getItemName()%>" onerror="this.src='images/default-food.png';">

				<div class="menu-card-content">
					<h3><%=item.getItemName()%></h3>
					<p class="menu-desc"><%=item.getDescription()%></p>
					<p class="menu-meta">Menu ID: <%=item.getMenuID()%> &nbsp;|&nbsp; Restaurant ID: <%=item.getRestaurantID()%></p>
					<p class="menu-price">₹<%=item.getPrice()%></p>

					<%
					if (item.getIsAvailable()) {
					%>
					<span class="status status-available">Available</span>




					<form action="CartServlet" method="post">
						
						<input type="hidden" name="menuId" value="<%=item.getMenuID()%>">
						<input type="hidden" name="restaurantId" value="<%=item.getRestaurantID()%>">

						<!-- <div class="qty-row">
						
							<button type="button" class="qty-btn qty-decrement" onclick="decrementQty(this)">&minus;</button>
							<span class="qty-value">1</span>
							
							<button type="button" class="qty-btn qty-increment" onclick="incrementQty(this)">&plus;</button>
							
							
							<input type="hidden" name="quantity" class="qty-input" value="1">
							
						</div> -->
						
						<input type="hidden" name="quantity" value="1">
						<input type="hidden" name="action" value="add">

						<button type="submit" class="add-btn">Add to Cart</button>
					</form>
					
					
					
					
					
					
					
					<%
					} else {
					%>
					<span class="status status-unavailable">Unavailable</span>
					<%
					}
					%>
				</div>
			</div>
			<%
			}
			%>
		</div>
		<%
		}
		%>
	</section>

	<!-- Footer -->
	<footer class="footer">
		<p>© 2026 Foodie Express | Fast Delivery | Fresh Food</p>
	</footer>

	<script>
		var MIN_QTY = 1;
		var MAX_QTY = 100; // adjust as needed

		function incrementQty(btn) {
			var row = btn.closest('.qty-row');
			var valueEl = row.querySelector('.qty-value');
			var inputEl = row.querySelector('.qty-input');
			var current = parseInt(valueEl.textContent, 10);

			if (current < MAX_QTY) {
				current += 1;
				valueEl.textContent = current;
				inputEl.value = current;
			}
			updateButtons(row, current);
		}

		function decrementQty(btn) {
			var row = btn.closest('.qty-row');
			var valueEl = row.querySelector('.qty-value');
			var inputEl = row.querySelector('.qty-input');
			var current = parseInt(valueEl.textContent, 10);

			if (current > MIN_QTY) {
				current -= 1;
				valueEl.textContent = current;
				inputEl.value = current;
			}
			updateButtons(row, current);
		}

		function updateButtons(row, current) {
			row.querySelector('.qty-decrement').disabled = (current <= MIN_QTY);
			row.querySelector('.qty-increment').disabled = (current >= MAX_QTY);
		}

		document.addEventListener('DOMContentLoaded', function () {
			document.querySelectorAll('.qty-row').forEach(function (row) {
				var current = parseInt(row.querySelector('.qty-value').textContent, 10);
				updateButtons(row, current);
			});
		});
	</script>

</body>
</html>
