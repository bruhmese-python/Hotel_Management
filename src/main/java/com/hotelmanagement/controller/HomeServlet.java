package com.hotelmanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hotel.bean.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/home")

public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Customer user = (Customer) session.getAttribute("user");

        if (user != null) {
            request.setAttribute("username", user.getCustomerName());
            request.setAttribute("userId", user.getCustomerId());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}
