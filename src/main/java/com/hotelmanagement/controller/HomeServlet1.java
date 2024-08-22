package com.hotelmanagement.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.bean.Admin;
import com.hotel.bean.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/homeAdmin")

public class HomeServlet1 extends HttpServlet{
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Admin admin = (Admin) session.getAttribute("admin");

        if (admin != null) {
            request.setAttribute("userId", admin.getUserId());
            request.setAttribute("password1", admin.getPassword());
            request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
        } else {
            response.sendRedirect("admin.jsp");
        }
    }

}
