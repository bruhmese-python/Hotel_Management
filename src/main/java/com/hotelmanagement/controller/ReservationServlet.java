package com.hotelmanagement.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.bean.Reservation;
import com.hotelmanagement.dao.UserDAODerbyDatabaseImplementation;
import com.hotelmanagement.dao.UserDao;

@WebServlet("/reservation")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDAODerbyDatabaseImplementation();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    	String checkindate = request.getParameter("check_in_date");
        String checkoutdate = request.getParameter("check_out_date");
        String roomPreference = request.getParameter("room_Preference");
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
            
        Reservation reservation = new Reservation();
      
        reservation.setCheck_in_date(checkindate);
        reservation.setCheck_out_date(checkoutdate);
        reservation.setPreferences(roomPreference);
        reservation.setName(name);
        reservation.setContact(contact);

     
//            boolean isRegistered = userDao.reserve(reservation);
            Integer reservationId = userDao.reserve(reservation);
//         		if(isRegistered) {
            if(reservationId!=-1) {
         	            double totalPrice=userDao.calculatePrice(roomPreference,checkindate, checkoutdate);
         	            
         	            //update reservation in billings and bookings
         	            userDao.addToBillings(reservationId,totalPrice);
         	            userDao.addToBookings(reservationId,reservation,totalPrice);
         	            
         	           request.setAttribute("acknowledgmentMessage", "Reservation Successful");
         	  
						request.setAttribute("totalPrice",totalPrice);
         	            request.getRequestDispatcher("reservation.jsp").forward(request, response);
         	}else {
         		request.setAttribute("errorMessage", "User registration failed. Please try again.");
  	            request.getRequestDispatcher("reservation.jsp").forward(request, response);
         	}
        

        
      
        }
}


