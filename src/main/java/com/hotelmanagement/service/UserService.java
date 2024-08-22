package com.hotelmanagement.service;
import java.util.*;
import com.hotel.bean.*;
import com.hotelmanagement.dao.UserDAODerbyDatabaseImplementation;
import com.hotelmanagement.dao.UserDao;

public class UserService {
	private UserDao userDao = new UserDAODerbyDatabaseImplementation();

    public Customer validateUser(String CustomerId, String password) {
        return userDao.validateUser(CustomerId, password);
    }
 public boolean registerUser(Customer user) {
    	return userDao.registerUser(user);
    }
 public Customer getUserById(String CustomerId) {
		return userDao.getUserById(CustomerId);
 }
public Admin validateAdmin(String userId, String password1) {
	// TODO Auto-generated method stub
	return userDao.validateAdmin(userId, password1);
}
public Integer  reservation(Reservation reservation) {
//public boolean  reservation(Reservation reservation) {
	// TODO Auto-generated method stub
	return  userDao.reserve(reservation);
}
	public double calculatePrice(String roomPreference, String checkindate, String checkoutdate) {
		return  userDao.calculatePrice(roomPreference,checkindate,checkoutdate);
	}
	public Billing getBillingDetails(String BillingId) {
		return  userDao.getBillingDetails( BillingId);
	}
	public ArrayList<Booking> viewByUserId(int userId){
		return userDao.viewByUserId(userId);
	}
	public boolean processPayment(Payment payment) {
		return userDao.processPayment(payment);
	}

	}
