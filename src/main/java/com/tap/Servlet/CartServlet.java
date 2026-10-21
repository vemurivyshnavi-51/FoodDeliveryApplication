package com.tap.Servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.DAOimpl.MenuDAOimpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;





@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Cart cart =(Cart)session.getAttribute("cart");
		Integer restaurantId = (Integer)(session.getAttribute("restaurantId"));
		
		
		String action = req.getParameter("action");
		Integer newrestaurantId=Integer.parseInt(req.getParameter("restaurantId"));
		
		
		if (cart == null || restaurantId == null || !restaurantId.equals(newrestaurantId)) {
		    cart = new Cart();
		    session.setAttribute("cart", cart);
		    session.setAttribute("restaurantId", newrestaurantId);
		}
		
		
		
		
		
		if(action.equals("add")) {
			addItemCart(req,cart);
		}
		else if(action.equals("update")) {
			updateItemToCart(req,cart);
		}
		else if(action.equals("delete")) {
			deleteItemFromCart(req,cart);
		}
		
		
		req.setAttribute("cart", cart);
		
		RequestDispatcher rd = req.getRequestDispatcher("Cart.jsp");
		rd.forward(req, resp);
		
		
	}
	
	
	
	
	

	private void deleteItemFromCart(HttpServletRequest req, Cart cart) {
		// TODO Auto-generated method stub
		 int menuId = Integer.parseInt(req.getParameter("menuId"));
		 cart.getItems().remove(menuId);
	}






	private void updateItemToCart(HttpServletRequest req, Cart cart) {
		// TODO Auto-generated method stub
		 int menuId = Integer.parseInt(req.getParameter("menuId"));
		    int quantity = Integer.parseInt(req.getParameter("quantity"));
		    
		    
		    
		    CartItem item = cart.getItems().get(menuId);
		    
		    
		    if (item != null) {
		        if (quantity <= 0) {
		            cart.getItems().remove(menuId);
		        } else {
		            item.setQuantity(quantity);
		        }
		    }
		
	}




	private void addItemCart(HttpServletRequest req, Cart cart) {
		// TODO Auto-generated method stub
		
		int MenuId =Integer.parseInt(req.getParameter("menuId"));
		Integer quantity =Integer.parseInt(req.getParameter("quantity"));
		
		MenuDAOimpl menuDAOimpl = new MenuDAOimpl();
		Menu menu=menuDAOimpl.getMenu(MenuId);
		
		CartItem cartItem=new CartItem(MenuId, menu.getRestaurantID(),menu.getItemName(), menu.getPrice(), quantity);
		
	    cart.addItem(cartItem);
	    
	    
		
	}
	
	
	
	
	
	
	
	
	
}
