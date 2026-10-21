<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.tap.model.Cart, com.tap.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Checkout</title>
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

  /* ---------- Page Header ---------- */
  .checkout-header {
    padding: 30px 60px 10px;
  }

  .checkout-header h1 {
    font-size: 34px;
    font-weight: 700;
    margin-bottom: 6px;
  }

  .checkout-header p {
    color: #b3b0c4;
    font-size: 14px;
  }

  /* ---------- Layout ---------- */
  .checkout-container {
    max-width: 1100px;
    margin: 25px auto 60px;
    padding: 0 40px;
    display: grid;
    grid-template-columns: 1fr 380px;
    gap: 25px;
    align-items: start;
  }

  @media (max-width: 850px) {
    .checkout-container {
      grid-template-columns: 1fr;
    }
  }

  .panel {
    background-color: #34324a;
    border-radius: 14px;
    padding: 26px 28px;
  }

  .panel h2 {
    font-size: 18px;
    margin-bottom: 18px;
  }

  /* ---------- Address ---------- */
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

  .form-group textarea,
  .form-group input[type="text"],
  .form-group input[type="tel"] {
    width: 100%;
    padding: 12px 14px;
    border-radius: 8px;
    border: 1px solid #4a4762;
    background-color: #2b2a3a;
    color: #fff;
    font-size: 14px;
    outline: none;
    resize: vertical;
    font-family: inherit;
  }

  .form-group textarea:focus,
  .form-group input:focus {
    border-color: #ff7b6f;
  }

  .btn-location {
    margin-top: 8px;
    background: transparent;
    border: 1px solid #ff7b6f;
    color: #ff7b6f;
    padding: 8px 16px;
    border-radius: 20px;
    font-size: 12px;
    font-weight: 600;
    cursor: pointer;
  }

  .btn-location:hover {
    background-color: #ff7b6f;
    color: #2b2a3a;
  }

  .error-msg {
    color: #ff9b9b;
    font-size: 12px;
    margin-top: 6px;
    display: none;
  }

  /* ---------- Payment Options ---------- */
  .payment-option {
    display: flex;
    align-items: center;
    gap: 12px;
    background-color: #2b2a3a;
    border: 1px solid #4a4762;
    border-radius: 8px;
    padding: 14px 16px;
    margin-bottom: 12px;
    cursor: pointer;
  }

  .payment-option:hover {
    border-color: #ff7b6f;
  }

  .payment-option input {
    accent-color: #ff7b6f;
    width: 16px;
    height: 16px;
  }

  .payment-option span {
    font-size: 14px;
  }

  /* ---------- Order Summary ---------- */
  .summary-item {
    display: flex;
    justify-content: space-between;
    font-size: 14px;
    margin-bottom: 12px;
    color: #ddd;
  }

  .summary-item .qty {
    color: #b3b0c4;
    font-size: 12px;
    margin-left: 6px;
  }

  .summary-divider {
    border-top: 1px solid #4a4762;
    margin: 14px 0;
  }

  .summary-row {
    display: flex;
    justify-content: space-between;
    font-size: 14px;
    color: #b3b0c4;
    margin-bottom: 10px;
  }

  .summary-row.total {
    font-size: 18px;
    font-weight: 700;
    color: #fff;
    margin-top: 6px;
  }

  .summary-row.total .amount {
    color: #ff7b6f;
  }

  .btn-place-order {
    width: 100%;
    background: linear-gradient(135deg, #ffd9a0, #ff9b6a);
    border: none;
    color: #3a2a1a;
    padding: 14px;
    border-radius: 26px;
    font-size: 15px;
    font-weight: 700;
    cursor: pointer;
    margin-top: 18px;
    box-shadow: 0 0 18px rgba(255, 155, 106, 0.45);
  }

  .btn-place-order:hover {
    filter: brightness(1.06);
  }

  .empty-checkout {
    text-align: center;
    padding: 90px 20px;
    color: #b3b0c4;
  }

  .empty-checkout h2 {
    color: #fff;
    margin-bottom: 10px;
  }

  .empty-checkout a {
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
  String UserName=(String)session.getAttribute("userName");
  %>

  <div class="checkout-header">
    <h1>Checkout</h1>
    <p>Confirm your delivery details and place your order</p>
    <h2>Hello ,<%=UserName %> </h2>
  </div>

  <%
    Cart cart = (Cart) session.getAttribute("cart");
    Integer restaurantId = (Integer) session.getAttribute("restaurantId");
    BigDecimal grandTotal = BigDecimal.ZERO;
    boolean cartHasItems = (cart != null && cart.getItems() != null && !cart.getItems().isEmpty());

    if (cartHasItems) {
      for (CartItem ci : cart.getItems().values()) {
        grandTotal = grandTotal.add(ci.getTotalPrice());
      }
    }
  %>

  <% if (cartHasItems) { %>

    <div class="checkout-container">

      <!-- Left column: delivery + payment -->
      <div>

        <div class="panel" style="margin-bottom: 20px;">
          <h2>Delivery Address</h2>

          <form action="PlaceOrderServlet" method="POST" id="checkoutForm">

            <div class="form-group">
              <label for="address">Delivery Address</label>
              <textarea id="address" name="address" rows="3" placeholder="Enter your full delivery address" required></textarea>
              </div>

            <div class="form-group">
              <label for="phone">Contact Number</label>
              <input type="tel" id="phone" name="phone" placeholder="10-digit mobile number" pattern="[0-9]{10}" required>
            </div>

            <div class="panel" style="margin-top: 8px; padding: 0;">
              <h2 style="margin-bottom: 14px;">Payment Method</h2>

              <label class="payment-option">
                <input type="radio" name="paymentMethod" value="COD" checked>
                <span>Cash on Delivery</span>
              </label>

              <label class="payment-option">
                <input type="radio" name="paymentMethod" value="UPI">
                <span>UPI</span>
              </label>

              <label class="payment-option">
                <input type="radio" name="paymentMethod" value="CARD">
                <span>Credit / Debit Card</span>
              </label>
            </div>

            <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
          </form>
        </div>

      </div>

      <!-- Right column: order summary -->
      <div class="panel">
        <h2>Order Summary</h2>

        <%
          for (CartItem ci : cart.getItems().values()) {
        %>
        <div class="summary-item">
          <span><%= ci.getName() %> <span class="qty">x <%= ci.getQuantity() %></span></span>
          <span>&#8377; <%= ci.getTotalPrice() %></span>
        </div>
        <% } %>

        <div class="summary-divider"></div>

        <div class="summary-row">
          <span>Subtotal</span>
          <span>&#8377; <%= grandTotal %></span>
        </div>

        <div class="summary-row">
          <span>Delivery Fee</span>
          <span>&#8377; 40.00</span>
        </div>

        <div class="summary-row total">
          <span>Total</span>
          <span class="amount">&#8377; <%= grandTotal.add(new BigDecimal("40.00")) %></span>
        </div>

        <button type="submit" form="checkoutForm" class="btn-place-order">Place Order</button>
      </div>

    </div>

  <% } else { %>

    <div class="empty-checkout">
      <h2>Your cart is empty</h2>
      <p>Add some items before checking out.</p>
      <a href="callRestaurantServlet">Browse Restaurants</a>
    </div>

  <% } %>

  <script>
    function fetchLocation(btn) {
      const addressField = document.getElementById('address');
      const locError = document.getElementById('locError');

      locError.style.display = 'none';

      if (!window.isSecureContext) {
        locError.textContent = 'Location only works on HTTPS or localhost.';
        locError.style.display = 'block';
        return;
      }

      if (!navigator.geolocation) {
        locError.textContent = 'Geolocation is not supported by your browser.';
        locError.style.display = 'block';
        return;
      }

      btn.disabled = true;
      btn.textContent = 'Fetching location...';

      navigator.geolocation.getCurrentPosition(
       
    	async function (position) {
          const lat = position.coords.latitude;
          const lon = position.coords.longitude;

          try {
        	  const response = await fetch(
        			  `https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${lat}&lon=${lon}`,
        			  {
        			      headers:{
        			          "Accept":"application/json"
        			      }
        			  });
        	  
        	  if (!response.ok) {
                  throw new Error("HTTP Error : " + response.status);
              }

              const data = await response.json();

              console.log(data);
              
              
             const data = await response.json();
            
            console.log(data);
            
            
            if (data.display_name) {
            	const a = data.address;

            	addressField.value =
            	(a.house_number || "") + " " +
            	(a.road || "") + ", " +
            	(a.suburb || "") + ", " +
            	(a.city || a.town || a.village || "") + ", " +
            	(a.state || "") + ", " +
            	(a.postcode || "");
              
              
            }
            else {
              addressField.value = `Lat: ${lat}, Lon: ${lon}`;
            }
          }
          
          
          
          catch (err) {
        	 
        	  
        	  console.log(err);
        	    alert(err);

        	    addressField.value = "Unable to fetch address";
          } 
          
          
          
          finally {
            btn.disabled = false;
            btn.textContent = 'Use my current location';
          }
        },
        function (error) {
          locError.textContent = 'Unable to fetch location. Please enter manually.';
          locError.style.display = 'block';
          btn.disabled = false;
          btn.textContent = 'Use my current location';
        },
        { timeout: 10000 }
      );
    }
  </script>

</body>
</html>
