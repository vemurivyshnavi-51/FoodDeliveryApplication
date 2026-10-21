<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.BigDecimal, java.util.Collection" %>
<%@ page import="com.tap.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Order Confirmed - FoodHub</title>
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

  .confirm-wrapper {
    max-width: 600px;
    margin: 0 auto;
    padding: 60px 20px;
  }

  .success-icon {
    width: 70px;
    height: 70px;
    border-radius: 50%;
    background: linear-gradient(135deg, #ffd9a0, #ff9b6a);
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 auto 20px;
    font-size: 34px;
    color: #3a2a1a;
  }

  .confirm-wrapper h1 {
    text-align: center;
    font-size: 28px;
    margin-bottom: 8px;
  }

  .confirm-wrapper > p.sub {
    text-align: center;
    color: #b3b0c4;
    font-size: 14px;
    margin-bottom: 30px;
  }

  .panel {
    background-color: #34324a;
    border-radius: 14px;
    padding: 26px 28px;
    margin-bottom: 20px;
  }

  .panel h2 {
    font-size: 16px;
    margin-bottom: 16px;
    color: #ff7b6f;
  }

  .detail-row {
    display: flex;
    justify-content: space-between;
    font-size: 14px;
    margin-bottom: 10px;
    color: #ddd;
  }

  .detail-row .label {
    color: #b3b0c4;
  }

  .item-row {
    display: flex;
    justify-content: space-between;
    font-size: 14px;
    margin-bottom: 10px;
    color: #ddd;
  }

  .divider {
    border-top: 1px solid #4a4762;
    margin: 14px 0;
  }

  .total-row {
    display: flex;
    justify-content: space-between;
    font-size: 18px;
    font-weight: 700;
  }

  .total-row .amount {
    color: #ff7b6f;
  }

  .btn-home {
    display: block;
    text-align: center;
    background: linear-gradient(135deg, #ffd9a0, #ff9b6a);
    color: #3a2a1a;
    text-decoration: none;
    padding: 14px;
    border-radius: 26px;
    font-weight: 700;
    box-shadow: 0 0 18px rgba(255, 155, 106, 0.45);
  }

  .no-order {
    text-align: center;
    color: #b3b0c4;
    padding: 80px 20px;
  }

  .no-order a {
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

 

  <%
    Object orderIdObj = session.getAttribute("lastOrderId");
  %>

  <% if (orderIdObj == null) { %>

    <div class="no-order">
      <h2>No recent order found</h2>
      <p>Looks like you haven't placed an order yet.</p>
      <a href="callRestaurantServlet">Browse Restaurants</a>
    </div>

  <% } else { %>

    <%
      Integer orderId = (Integer) orderIdObj;
      String address = (String) session.getAttribute("lastOrderAddress");
      String phone = (String) session.getAttribute("lastOrderPhone");
      String paymentMethod = (String) session.getAttribute("lastOrderPaymentMethod");
      BigDecimal total = (BigDecimal) session.getAttribute("lastOrderTotal");

      @SuppressWarnings("unchecked")
      Collection<CartItem> orderedItems = (Collection<CartItem>) session.getAttribute("lastOrderItems");

      String paymentLabel = "Cash on Delivery";
      if ("UPI".equals(paymentMethod)) paymentLabel = "UPI";
      else if ("CARD".equals(paymentMethod)) paymentLabel = "Credit / Debit Card";
    %>

    <div class="confirm-wrapper">

      <div class="success-icon">&#10003;</div>
      <h1>Order Placed Successfully!</h1>
      <p class="sub">Order #<%= orderId %> has been confirmed and is being prepared.</p>

      <div class="panel">
        <h2>Delivery Details</h2>
        <div class="detail-row">
          <span class="label">Address</span>
          <span><%= address %></span>
        </div>
        <div class="detail-row">
          <span class="label">Phone</span>
          <span><%= phone %></span>
        </div>
        <div class="detail-row">
          <span class="label">Payment Method</span>
          <span><%= paymentLabel %></span>
        </div>
      </div>

      <div class="panel">
        <h2>Items Ordered</h2>

        <% if (orderedItems != null) {
             for (CartItem item : orderedItems) { %>
        <div class="item-row">
          <span><%= item.getName() %> x <%= item.getQuantity() %></span>
          <span>&#8377; <%= item.getTotalPrice() %></span>
        </div>
        <% } } %>

        <div class="divider"></div>

        <div class="total-row">
          <span>Total Paid</span>
          <span class="amount">&#8377; <%= total %></span>
        </div>
      </div>

      <a href="callRestaurantServlet" class="btn-home">Back to Restaurants</a>

    </div>

  <% } %>

</body>
</html>
