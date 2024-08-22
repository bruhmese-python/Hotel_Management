package com.hotelmanagement.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.bean.Customer;
import com.hotelmanagement.dao.UserDAODerbyDatabaseImplementation;
import com.hotelmanagement.dao.UserDao;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDAODerbyDatabaseImplementation();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String CustomerId = request.getParameter("CustomerId");
        String Email = request.getParameter("Email");
        String CustomerName = request.getParameter("CustomerName");
        String password = request.getParameter("password");
        String Address = request.getParameter("Address");
        String MobileNo = request.getParameter("MobileNo");
        String confirmPassword = request.getParameter("confirm-password");

        if(!password.equals(confirmPassword)) {
        	request.setAttribute("errorMessage", "Passwords do not match.Please try again.");
        	request.getRequestDispatcher("register.jsp").forward(request,response);
        	return;
        }
        
        Customer user = new Customer();
        user.setCustomerId(CustomerId);
        user.setEmail(Email);
        user.setAddress(Address);
        user.setMobileNo(MobileNo);
        user.setCustomerName(CustomerName);
        user.setPassword(password);

     
        Customer existingUser = userDao.getUserById(CustomerId);
        
        if(existingUser!=null) {
        	request.setAttribute("errorMessage", "Customer already exists or choose a Different ID");
        	
         	request.getRequestDispatcher("register.jsp").forward(request,response);
        }else {
         		 boolean isRegistered = userDao.registerUser(user);
         		if (isRegistered) {
         	     	request.setAttribute("successPage", "UserRegistarion is successful.");
         	     	request.getRequestDispatcher("login.jsp").forward(request, response);
         		  } else {
         	            request.setAttribute("errorMessage", "User registration failed. Please try again.");
         	            request.getRequestDispatcher("register.jsp").forward(request, response);
         	}
        

        
      
        }
    }
}

