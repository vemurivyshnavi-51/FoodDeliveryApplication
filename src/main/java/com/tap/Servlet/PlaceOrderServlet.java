package com.tap.Servlet;



import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.model.Cart;
import com.tap.model.CartItem;

@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {

	private static final BigDecimal DELIVERY_FEE = new BigDecimal("40.00");

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String action = req.getParameter("action");

		// Step 1: first hit from checkout.jsp's "Place Order" button (no action param yet)
		if (action == null) {
			handleInitialPlaceOrder(req, resp, session);
			return;
		}

		// Step 2: user confirmed payment on upiPayment.jsp / cardPayment.jsp
		if (action.equals("confirmPayment")) {
			finalizeOrder(req, resp, session);
			return;
		}

		// Unknown action, just send back to checkout
		resp.sendRedirect(req.getContextPath() + "/CheckoutServlet");
	}

	private void handleInitialPlaceOrder(HttpServletRequest req, HttpServletResponse resp, HttpSession session)
			throws ServletException, IOException {

		Cart cart = (Cart) session.getAttribute("cart");
		boolean cartHasItems = (cart != null && cart.getItems() != null && !cart.getItems().isEmpty());

		if (!cartHasItems) {
			resp.sendRedirect(req.getContextPath() + "/Cart.jsp");
			return;
		}

		Object userId = session.getAttribute("userId");
		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}

		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		String paymentMethod = req.getParameter("paymentMethod");
		String restaurantId = req.getParameter("restaurantId");

		// Stash the delivery details in session until payment is confirmed.
		// We deliberately do NOT write to the database yet for UPI/CARD —
		// only once the "payment" step succeeds should the order become real.
		session.setAttribute("pendingAddress", address);
		session.setAttribute("pendingPhone", phone);
		session.setAttribute("pendingPaymentMethod", paymentMethod);
		session.setAttribute("pendingRestaurantId", restaurantId);

		if ("COD".equals(paymentMethod)) {
			// Cash on Delivery needs no payment step — place the order immediately.
			finalizeOrder(req, resp, session);

		} else if ("UPI".equals(paymentMethod)) {
			RequestDispatcher rd = req.getRequestDispatcher("Upipayment.jsp");
			rd.forward(req, resp);

		} else if ("CARD".equals(paymentMethod)) {
			RequestDispatcher rd = req.getRequestDispatcher("Cardpayment.jsp");
			rd.forward(req, resp);

		} else {
			// Unknown/missing payment method — fall back to COD-style immediate placement.
			finalizeOrder(req, resp, session);
		}
	}

	private void finalizeOrder(HttpServletRequest req, HttpServletResponse resp, HttpSession session)
			throws ServletException, IOException {

		Cart cart = (Cart) session.getAttribute("cart");
		boolean cartHasItems = (cart != null && cart.getItems() != null && !cart.getItems().isEmpty());

		if (!cartHasItems) {
			// Session likely expired or the order was already placed once.
			resp.sendRedirect(req.getContextPath() + "/Cart.jsp");
			return;
		}

		String address = (String) session.getAttribute("pendingAddress");
		String phone = (String) session.getAttribute("pendingPhone");
		String paymentMethod = (String) session.getAttribute("pendingPaymentMethod");
		Object userId = session.getAttribute("userId");

		BigDecimal subtotal = BigDecimal.ZERO;
		for (CartItem item : cart.getItems().values()) {
			subtotal = subtotal.add(item.getTotalPrice());
		}
		BigDecimal grandTotal = subtotal.add(DELIVERY_FEE);

		// ------------------------------------------------------------------
		// TODO: Replace this block with your real DB insert, e.g.:
		//
		//   OrderDAOimpl orderDAO = new OrderDAOimpl();
		//   int orderId = orderDAO.placeOrder(
		//       (Integer) userId, address, phone, paymentMethod,
		//       grandTotal, cart.getItems().values()
		//   );
		//
		// orderDAO.placeOrder(...) should insert one row into OrderTable
		// and one row per CartItem into OrderItem, then return the new order ID.
		//
		// For now, we generate a placeholder order ID so the confirmation
		// page has something to display.
		int orderId = (int) (System.currentTimeMillis() % 1000000);
		// ------------------------------------------------------------------

		// Save a snapshot of the order for the confirmation page, since we're
		// about to clear the live cart.
		session.setAttribute("lastOrderId", orderId);
		session.setAttribute("lastOrderAddress", address);
		session.setAttribute("lastOrderPhone", phone);
		session.setAttribute("lastOrderPaymentMethod", paymentMethod);
		session.setAttribute("lastOrderTotal", grandTotal);
		session.setAttribute("lastOrderItems", cart.getItems().values());

		// Clear the cart — the order has been placed.
		session.removeAttribute("cart");
		session.removeAttribute("restaurantId");
		session.removeAttribute("pendingAddress");
		session.removeAttribute("pendingPhone");
		session.removeAttribute("pendingPaymentMethod");
		session.removeAttribute("pendingRestaurantId");

		RequestDispatcher rd = req.getRequestDispatcher("orderConfirmation.jsp");
		rd.forward(req, resp);
	}

}

