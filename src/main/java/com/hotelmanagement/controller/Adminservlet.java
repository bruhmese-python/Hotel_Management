package com.hotelmanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotel.bean.Admin;
import com.hotel.bean.Customer;
import com.hotelmanagement.service.UserService;

@WebServlet("/admin")
public class Adminservlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

   		Admin admin = userService.validateAdmin(userId, password);

        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            response.sendRedirect("homeAdmin");
        } else {
            request.setAttribute("errorMessage", "Invalid User ID or Password");
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
    }
}
