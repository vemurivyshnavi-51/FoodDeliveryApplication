package com.tap.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
//import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.DAOimpl.RestaurantDAOimpl;
import com.tap.model.Restaurant;
@WebServlet("/callRestaurantServlet")
public class RestaurantServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RestaurantDAOimpl restaurant = new RestaurantDAOimpl();
		List<Restaurant> allRestaurants=restaurant.getAllRestaurant();
		
		//PrintWriter out = resp.getWriter();
//		for(Restaurant rest:allRestaurants) {
//			
//			System.out.println(rest);
//		}
		
		
		req.setAttribute("all", allRestaurants);
		
		RequestDispatcher rd = req.getRequestDispatcher("HomePage.jsp");
		
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {

	    doGet(req, resp);
	}
	
}