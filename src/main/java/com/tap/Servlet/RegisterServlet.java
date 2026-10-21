package com.tap.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

//import org.apache.catalina.tribes.group.Response;

import com.tap.DAOimpl.UserDAOimpl;
import com.tap.model.User;


@WebServlet("/callRegisterServlet")
public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.print("Register servlet");
		
		 String name=req.getParameter("fullname");
		 String password=req.getParameter("password");
		 String email=req.getParameter("email");
		 String address=req.getParameter("address");
		 String role=req.getParameter("role");
		
		 String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
		 
		User u = new User(name, hashpw, email, address, role);
		System.out.println(u);
		
		UserDAOimpl user = new UserDAOimpl();
		
		int add= user.addUser(u);
		req.setAttribute("user", u);
		
		
		PrintWriter out = resp.getWriter();
		if(add==1) {
			resp.sendRedirect("Login.html");
			//rd.forward(request, response);
			out.println("User Registered Successfully");

		}
		else {
			resp.sendRedirect("Register.html");
			out.println("User Registered not Successfully");
		}
		
		
	}
	
	
	
}
