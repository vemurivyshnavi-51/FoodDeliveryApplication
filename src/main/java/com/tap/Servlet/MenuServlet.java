package com.tap.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.DAOimpl.MenuDAOimpl;
import com.tap.DAOimpl.RestaurantDAOimpl;
import com.tap.model.Menu;
import com.tap.model.Restaurant;
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int restaurantId=Integer.parseInt(req.getParameter("RestaurantID"));
		
		 MenuDAOimpl menu=new MenuDAOimpl();
		 List<Menu> menu_restID = menu.getMenu_restID(restaurantId);
		 for(Menu m:menu_restID) {
			 System.out.println(m);
		 }
		 RestaurantDAOimpl rdao=new RestaurantDAOimpl();
		 Restaurant restaurant = rdao.getRestaurant(restaurantId);
		 
		 req.setAttribute("allMenu", menu_restID);
		 req.setAttribute("restaurant", restaurant);
		 RequestDispatcher rd = req.getRequestDispatcher("Menu.jsp");
		 rd.forward(req, resp);
	}

	
}
