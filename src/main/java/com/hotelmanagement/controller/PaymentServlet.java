package com.hotelmanagement.controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.bean.Payment;
import com.hotelmanagement.dao.UserDAODerbyDatabaseImplementation;
import com.hotelmanagement.dao.UserDao;



import java.io.IOException;

@WebServlet("/payment")

public class PaymentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	@Override
    public void init() throws ServletException {
    	 userDao = new UserDAODerbyDatabaseImplementation();
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerId = request.getParameter("CustomerId");
        String billAmount = request.getParameter("BillAmount");
        String paymentMode = request.getParameter("PaymentMode");
        String cardNumber = request.getParameter("CardNumber");
        String cardHolderName = request.getParameter("CardHolderName");
        String expiryDate = request.getParameter("ExpiryDate");
        String cvv = request.getParameter("CVV");

        Payment payment = new Payment();
        payment.setCustomerId(customerId);
        payment.setBillAmount(Double.parseDouble(billAmount));
        payment.setPaymentMode(paymentMode);
        payment.setCardNumber(cardNumber);
        payment.setCardHolderName(cardHolderName);
        payment.setExpiryDate(expiryDate);
        payment.setCvv(cvv);

        boolean isPaymentSuccessful = userDao.processPayment(payment);
        if (isPaymentSuccessful) {
            String transactionId = payment.getTransactionId();  // Get the transaction ID set by DAO
            request.setAttribute("successMessage", "Payment Successful. Transaction ID: " + transactionId);
            request.getRequestDispatcher("invoice.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Payment failed. Please try again.");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        }
    }
	
}