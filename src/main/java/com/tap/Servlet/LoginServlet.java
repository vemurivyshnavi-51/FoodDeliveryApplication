package com.tap.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.DAO.UserDAO;
import com.tap.DAOimpl.UserDAOimpl;
import com.tap.model.User;

@WebServlet("/callLoginServlet")
public class LoginServlet extends HttpServlet{
	
	UserDAOimpl userDAO = new UserDAOimpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String Password = request.getParameter("password");

        User user = userDAO.getUserByEmail(email);

        // BCrypt.checkpw re-hashes the submitted password with the salt embedded in the
        // stored hash and compares the results — this is the only correct way to verify
        // a bcrypt password. You can never "decrypt" a hash back to plain text.
        

         String dbpassword=user.getPassword();
            if (user != null && (BCrypt.checkpw(Password, dbpassword))) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userName", user.getUserName());
                session.setAttribute("userEmail", user.getEmail());
                
                response.sendRedirect("callRestaurantServlet");
                
              // RequestDispatcher rd = request.getRequestDispatcher("callRestaurantServlet");
              // rd.forward(request, response);
                
             //response.sendRedirect(request.getContextPath() + "callRestaurantServlet");
             //} 
            }
                
                
               // else {
               //   response.sendRedirect(request.getContextPath() + "/Login.html?error=1");
              //}
             // } 
    else {
            response.sendRedirect("Login.html");
        }
    }
}

