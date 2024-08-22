package com.hotelmanagement.dao;

import com.hotel.bean.*;

import java.sql.Date;
import java.util.*;
import com.hotel.bean.Billing;
import com.hotel.bean.Customer;
import com.hotel.bean.Reservation;
public interface UserDao {
	
	public boolean addToBillings(Integer id,double price);
	public boolean addToBookings(Integer id, Reservation r, Double price);
	public Customer validateUser(String CustomerId, String password);
	public boolean registerUser(Customer user);
	public Customer getUserById(String CustomerId);
	public Admin validateAdmin(String userId,String password1);
//	public boolean reserve(Reservation reservation) ;
	public Integer reserve(Reservation reservation) ;
	public double calculatePrice(String roomPreference, String checkindate, String checkoutdate);
public Billing getBillingDetails(String BillingId);

	public ArrayList<Booking> viewByUserId(int userId);
	
	public boolean isRoomAvailable(String preference, Date check_in,Date check_out);

	public boolean processPayment(Payment payment);
    String generateTransactionId();
	



}

