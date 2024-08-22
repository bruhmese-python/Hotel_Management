package com.hotelmanagement.controller;

import com.hotel.bean.*;
import com.hotelmanagement.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
   private static final long serialVersionUID=-1335255630034573996L;
	private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDAODerbyDatabaseImplementation();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            int userId = Integer.parseInt(request.getParameter("userId"));
            System.out.println(userId);
            ArrayList<Booking> bookingList = userDao.viewByUserId(userId);
            System.out.println(bookingList.get(0).getReservationId());
            request.setAttribute("bookingList", bookingList);
            request.getRequestDispatcher("history.jsp").forward(request, response);
            System.out.println(bookingList.get(0).getClass());
            if(bookingList.isEmpty()) {
                request.setAttribute("errorMessage", "Invalid User ID or Password");
                request.getRequestDispatcher("history.jsp").forward(request, response);
                }
            
            
            
    }
    
  

    
}