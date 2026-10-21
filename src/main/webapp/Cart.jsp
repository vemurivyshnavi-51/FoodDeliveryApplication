<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.tap.model.Cart, com.tap.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Your Cart</title>
<style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Segoe UI', Arial, sans-serif;
  }

  body {
    background-color: #2b2a3a;
    min-height: 100vh;
    color: #fff;
  }

  /* ---------- Navbar ---------- */
  .navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px 50px;
  }

  .navbar .logo {
    font-size: 24px;
    font-weight: bold;
    color: #fff;
  }

  .navbar .logo span {
    color: #ff7b6f;
  }

  .navbar .nav-links {
    display: flex;
    gap: 35px;
  }

  .navbar .nav-links a {
    color: #ddd;
    text-decoration: none;
    font-size: 15px;
  }

  .navbar .nav-links a:hover {
    color: #ff7b6f;
  }

  /* ---------- Page Header ---------- */
  .cart-header {
    padding: 30px 60px 10px;
  }

  .cart-header h1 {
    font-size: 34px;
    font-weight: 700;
    margin-bottom: 6px;
  }

  .cart-header p {
    color: #b3b0c4;
    font-size: 14px;
  }

  /* ---------- Cart Table ---------- */
  .cart-table-wrapper {
    max-width: 1100px;
    margin: 25px auto 0;
    padding: 0 40px;
  }

  .cart-table {
    width: 100%;
    background-color: #34324a;
    border-radius: 14px;
    overflow: hidden;
    border-collapse: collapse;
  }

  .cart-table th {
    text-align: left;
    font-size: 13px;
    color: #b3b0c4;
    font-weight: 600;
    padding: 20px 24px;
    border-bottom: 1px solid #423f5c;
  }

  .cart-table td {
    padding: 18px 24px;
    font-size: 15px;
    border-bottom: 1px solid #423f5c;
    vertical-align: middle;
  }

  .cart-table tr:last-child td {
    border-bottom: none;
  }

  .item-name {
    font-weight: 700;
    font-size: 16px;
  }

  .price-cell,
  .total-cell {
    color: #ff7b6f;
    font-weight: 700;
  }

  /* ---------- Quantity Controls ---------- */
  .qty-controls {
    display: flex;
    align-items: center;
    gap: 14px;
  }

  .qty-controls form {
    margin: 0;
  }

  .qty-btn {
    width: 34px;
    height: 34px;
    border-radius: 50%;
    border: none;
    background: linear-gradient(135deg, #ffd9a0, #ffb27a);
    color: #3a2a1a;
    font-size: 18px;
    font-weight: bold;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .qty-btn:hover {
    filter: brightness(1.08);
  }

  .qty-value {
    min-width: 18px;
    text-align: center;
    font-weight: 700;
  }

  /* ---------- Remove Button ---------- */
  .btn-remove {
    background: transparent;
    border: 1px solid #ff7b6f;
    color: #ff7b6f;
    padding: 8px 20px;
    border-radius: 20px;
    font-size: 13px;
    font-weight: 600;
    cursor: pointer;
  }

  .btn-remove:hover {
    background-color: #ff7b6f;
    color: #2b2a3a;
  }

  /* ---------- Grand Total Row ---------- */
  .grand-total-row td {
    padding-top: 22px;
    padding-bottom: 22px;
    font-size: 20px;
    font-weight: 700;
  }

  .grand-total-row .grand-total-value {
    color: #ff7b6f;
    text-align: right;
  }

  /* ---------- Bottom Actions ---------- */
  .cart-actions {
    max-width: 1100px;
    margin: 25px auto 60px;
    padding: 0 40px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .btn-add-more {
    background: transparent;
    border: 1px solid #ff7b6f;
    color: #ff7b6f;
    padding: 12px 28px;
    border-radius: 24px;
    font-size: 14px;
    font-weight: 700;
    cursor: pointer;
    text-decoration: none;
    display: inline-block;
  }

  .btn-add-more:hover {
    background-color: #ff7b6f;
    color: #2b2a3a;
  }

  .btn-checkout {
    background: linear-gradient(135deg, #ffd9a0, #ff9b6a);
    border: none;
    color: #3a2a1a;
    padding: 14px 34px;
    border-radius: 26px;
    font-size: 15px;
    font-weight: 700;
    cursor: pointer;
    box-shadow: 0 0 18px rgba(255, 155, 106, 0.45);
  }

  .btn-checkout:hover {
    filter: brightness(1.06);
  }

  .btn-checkout:disabled {
    opacity: 0.5;
    cursor: not-allowed;
    box-shadow: none;
  }

  /* ---------- Empty Cart ---------- */
  .empty-cart {
    text-align: center;
    padding: 90px 20px;
    color: #b3b0c4;
  }

  .empty-cart h2 {
    color: #fff;
    margin-bottom: 10px;
    font-size: 24px;
  }

  .empty-cart a {
    display: inline-block;
    margin-top: 20px;
    background: linear-gradient(135deg, #ffd9a0, #ff9b6a);
    color: #3a2a1a;
    text-decoration: none;
    padding: 12px 28px;
    border-radius: 24px;
    font-weight: 700;
  }
</style>
</head>
<body>

  

  <!-- Header -->
  <div class="cart-header">
    <h1>Your Cart</h1>
    <p>Review your selected food items</p>
  </div>

  <%
    Cart cart = (Cart) session.getAttribute("cart");
    Integer restaurantId = (Integer) session.getAttribute("restaurantId");
    BigDecimal grandTotal = BigDecimal.ZERO;
    boolean cartHasItems = (cart != null && cart.getItems() != null && !cart.getItems().isEmpty());
  %>

  <% if (cartHasItems) { %>

    <div class="cart-table-wrapper">
      <table class="cart-table">
        <thead>
          <tr>
            <th>Item</th>
            <th>Price</th>
            <th>Total</th>
            <th>Quantity</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>

          <%
            for (CartItem cartItem : cart.getItems().values()) {
              grandTotal = grandTotal.add(cartItem.getTotalPrice());
          %>

          <tr>
            <td class="item-name"><%= cartItem.getName() %></td>
            <td class="price-cell">&#8377; <%= cartItem.getPrice() %></td>
            <td class="total-cell">&#8377; <%= cartItem.getTotalPrice() %></td>
            <td>
              <div class="qty-controls">

                <!-- Decrease quantity -->
                <form action="CartServlet" method="POST">
                  <input type="hidden" name="action" value="update">
                  <input type="hidden" name="menuId" value="<%= cartItem.getMenuId() %>">
                  <input type="hidden" name="restaurantId" value="<%= cartItem.getRestaurantId() %>">
                  <input type="hidden" name="quantity" value="<%= cartItem.getQuantity() - 1 %>">
                  <button type="submit" class="qty-btn">-</button>
                </form>

                <span class="qty-value"><%= cartItem.getQuantity() %></span>

                <!-- Increase quantity -->
                <form action="CartServlet" method="POST">
                  <input type="hidden" name="action" value="update">
                  <input type="hidden" name="menuId" value="<%= cartItem.getMenuId() %>">
                  <input type="hidden" name="restaurantId" value="<%= cartItem.getRestaurantId() %>">
                  <input type="hidden" name="quantity" value="<%= cartItem.getQuantity() + 1 %>">
                  <button type="submit" class="qty-btn">+</button>
                </form>

              </div>
            </td>
            <td>
              <form action="CartServlet" method="POST" style="margin:0;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="menuId" value="<%= cartItem.getMenuId() %>">
                <input type="hidden" name="restaurantId" value="<%= cartItem.getRestaurantId() %>">
                <button type="submit" class="btn-remove">Remove</button>
              </form>
            </td>
          </tr>

          <% } %>

          <tr class="grand-total-row">
            <td>Grand Total</td>
            <td></td>
            <td></td>
            <td></td>
            <td class="grand-total-value">&#8377; <%= grandTotal %></td>
          </tr>

        </tbody>
      </table>
    </div>

    <div class="cart-actions">
    
      <a href="menu?RestaurantID=<%= restaurantId %>" class="btn-add-more">Add More Items</a>
      <form action="CheckoutServlet" method="POST" style="margin:0;">
        <button type="submit" class="btn-checkout">Proceed to Checkout</button>
      </form>
    </div>
    
   
    
  <% } else { %>

    <div class="empty-cart">
      <h2>Your cart is empty</h2>
      <p>Looks like you haven't added anything yet.</p>
      <a href="callRestaurantServlet">Browse Restaurants</a>
    </div>

  <% } %>

</body>
</html>
