<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.tap.model.Cart, com.tap.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Card Payment - FoodHub</title>
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
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .pay-card {
    background: #34324a;
    max-width: 440px;
    width: 100%;
    padding: 36px 32px;
    border-radius: 14px;
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.35);
  }

  .pay-card h2 {
    font-size: 22px;
    margin-bottom: 8px;
    text-align: center;
  }

  .pay-card .amount {
    text-align: center;
    font-size: 30px;
    font-weight: 700;
    color: #ff7b6f;
    margin-bottom: 24px;
  }

  .form-group {
    margin-bottom: 18px;
  }

  .form-group label {
    display: block;
    font-size: 13px;
    color: #b3b0c4;
    margin-bottom: 8px;
    font-weight: 600;
  }

  .form-group input {
    width: 100%;
    padding: 12px 14px;
    border-radius: 8px;
    border: 1px solid #4a4762;
    background-color: #2b2a3a;
    color: #fff;
    font-size: 14px;
    outline: none;
  }

  .form-group input:focus {
    border-color: #ff7b6f;
  }

  .row-2 {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 14px;
  }

  .btn-pay {
    width: 100%;
    background: linear-gradient(135deg, #ffd9a0, #ff9b6a);
    border: none;
    color: #3a2a1a;
    padding: 14px;
    border-radius: 26px;
    font-size: 15px;
    font-weight: 700;
    cursor: pointer;
    margin-top: 10px;
    box-shadow: 0 0 18px rgba(255, 155, 106, 0.45);
  }

  .btn-pay:hover {
    filter: brightness(1.06);
  }

  .note {
    text-align: center;
    font-size: 12px;
    color: #8a8698;
    margin-top: 16px;
  }
</style>
</head>
<body>

  <%
    Cart cart = (Cart) session.getAttribute("cart");
    BigDecimal total = BigDecimal.ZERO;

    if (cart != null && cart.getItems() != null) {
      for (CartItem item : cart.getItems().values()) {
        total = total.add(item.getTotalPrice());
      }
      total = total.add(new BigDecimal("40.00")); // delivery fee, matches checkout.jsp
    }
  %>

  <div class="pay-card">
    <h2>Pay with Card</h2>
    <div class="amount">&#8377; <%= total %></div>

    <form action="PlaceOrderServlet" method="POST">
      <input type="hidden" name="action" value="confirmPayment">

      <div class="form-group">
        <label for="cardNumber">Card Number</label>
        <input type="text" id="cardNumber" name="cardNumber" placeholder="1234 5678 9012 3456"
               maxlength="19" required pattern="[0-9\s]{13,19}">
      </div>

      <div class="form-group">
        <label for="cardName">Name on Card</label>
        <input type="text" id="cardName" name="cardName" placeholder="Full name as on card" required>
      </div>

      <div class="row-2">
        <div class="form-group">
          <label for="expiry">Expiry (MM/YY)</label>
          <input type="text" id="expiry" name="expiry" placeholder="MM/YY" maxlength="5" required
                 pattern="(0[1-9]|1[0-2])\/[0-9]{2}">
        </div>

        <div class="form-group">
          <label for="cvv">CVV</label>
          <input type="password" id="cvv" name="cvv" placeholder="123" maxlength="4" required
                 pattern="[0-9]{3,4}">
        </div>
      </div>

      <button type="submit" class="btn-pay">Pay Now</button>
    </form>

    <p class="note">This is a simulated payment for demo purposes. No real transaction is processed.</p>
  </div>

</body>
</html>
