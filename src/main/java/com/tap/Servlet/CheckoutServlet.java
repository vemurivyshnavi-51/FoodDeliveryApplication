package com.tap.Servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.model.Cart;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		boolean cartHasItems = (cart != null && cart.getItems() != null && !cart.getItems().isEmpty());

		if (!cartHasItems) {
			// Nothing to check out — send them back to the cart page,
			// which already shows the "Your cart is empty" state.
			RequestDispatcher rd = req.getRequestDispatcher("Cart.jsp");
			rd.forward(req, resp);
			return;
		}

		// Optional: require login before checkout. Your LoginServlet already
		// sets session.setAttribute("userId", ...) on successful login.
		Object userId = session.getAttribute("userId");
		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}

		RequestDispatcher rd = req.getRequestDispatcher("/checkout.jsp");
			rd.forward(req, resp);
	}

	
}
