package com.hotelmanagement.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.bean.Billing;
import com.hotel.bean.Customer;
import com.hotelmanagement.dao.*;

@WebServlet("/billing")
public class BillingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	Billing billing=new Billing();
	

	 @Override
	    public void init() throws ServletException {
	    	 userDao = new UserDAODerbyDatabaseImplementation();
	    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String BillingId = request.getParameter("BillingId");
 

         billing = userDao.getBillingDetails( BillingId);
        if (billing!=null) {
        request.setAttribute("billing", billing);
        request.setAttribute("BillingId", billing.getBillingId());
          
        request.getRequestDispatcher("billing.jsp").forward(request, response);
    }else {
    	request.setAttribute("errorMessage2", "Invalid Billing ID");
        request.getRequestDispatcher("billing.jsp").forward(request, response);

    }
    }
}