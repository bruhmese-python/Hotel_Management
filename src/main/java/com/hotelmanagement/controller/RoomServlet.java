package com.hotelmanagement.controller;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelmanagement.dao.UserDAODerbyDatabaseImplementation;
import com.hotelmanagement.dao.UserDao;

/**
 * Servlet implementation class RoomStatus
 */
@WebServlet("/roomStatus")
public class RoomServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 private UserDao userDao;
	 public void init() throws ServletException {
    	 userDao = new UserDAODerbyDatabaseImplementation();
    }

    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String s1 = request.getParameter("check_in");
	        String s2 = request.getParameter("check_out");
	        System.out.println("(roomservlet)check_in_date :" + s1);
	    	Date check_in = Date.valueOf(s1);
	        Date check_out= Date.valueOf(s2);
	        String preference = request.getParameter("preference"); // AC or non-AC
	       
//	        SimpleDateFormat df=new SimpleDateFormat("MM-dd-yyyy");
//	        Date check_in=null;
//	        Date check_out=null;
//	        try {
//	        	 check_in = (Date) df.parse(check_in_date1);
//	        	 check_out= (Date) df.parse(check_out_date2);
//	        }catch(ParseException e) {
//	        	e.printStackTrace();
//	        }
//	        
	    
			
			boolean isAvailable = userDao.isRoomAvailable(preference, check_in,check_out);
	        
	        if (isAvailable) {
	        	   request.setAttribute("status", "Room Available");
	            
	               request.getRequestDispatcher("roomStatus.jsp").forward(request, response);
	        } else {
	        	 request.setAttribute("status1", "Room Unavailbale,Please Choose different Date");
		            
	               request.getRequestDispatcher("roomStatus.jsp").forward(request, response);
	        }
	    }
	}