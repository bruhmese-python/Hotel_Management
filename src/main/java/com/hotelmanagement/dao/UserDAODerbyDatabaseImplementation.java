package com.hotelmanagement.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;
import java.util.HashSet;
import java.util.Set;


import com.hotel.bean.Admin;
import com.hotel.bean.Billing;
import com.hotel.bean.Booking;
import com.hotel.bean.Customer;
import com.hotel.bean.Payment;
import com.hotel.bean.Reservation;
import com.hotelmanagement.util.DBConnection;
public class UserDAODerbyDatabaseImplementation implements UserDao {
	@Override
    public Customer validateUser(String CustomerId, String password) {
        Connection connection = null;
        Customer user = null;

        try {
            connection = DBConnection.getDBConnection();

            String sql = "SELECT * FROM Customers WHERE CustomerId = ? AND password = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, CustomerId);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new Customer();
                user.setCustomerId(rs.getString("CustomerId"));
                user.setCustomerName(rs.getString("CustomerName"));
                user.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDBConnection(connection);
        }

        return user;
    }
	@Override
	public boolean registerUser(Customer user) {
		 Connection connection = null;
	        PreparedStatement pstmt = null;
	        boolean isRegistered = false;

	        try {
	            connection = DBConnection.getDBConnection();

	            String sql = "INSERT INTO Customers (CustomerName, Email,MobileNo,CustomerId, password) VALUES (?, ?, ?,?,?)";

	            pstmt = connection.prepareStatement(sql);
	            pstmt.setString(1, user.getCustomerName());
	            pstmt.setString(2, user.getEmail());
	            pstmt.setString(3, user.getMobileNo());
	            pstmt.setString(4, user.getCustomerId());
	      
	            pstmt.setString(5, user.getPassword());
	            



	            int rowsAffected = pstmt.executeUpdate();
	            isRegistered = rowsAffected > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Close PreparedStatement
	            if (pstmt != null) {
	                try {
	                    pstmt.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }

	            // Close Connection
	            DBConnection.closeDBConnection(connection);
	        }

	        return isRegistered;
	    }
	@Override
	public Customer getUserById(String CustomerId) {
		 Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs=null;
	        Customer user=null;
	        
	        try {
	            conn = DBConnection.getDBConnection();

	            String sql ="SELECT * FROM customers where CustomerId=?";
	            stmt=conn.prepareStatement(sql);
	            stmt.setString(1,CustomerId);
	            rs=stmt.executeQuery();
	            if(rs.next()) {
	            	user=new Customer();
	            	 
	            	user.setCustomerId(rs.getString("CustomerId"));
	            	user.setEmail(rs.getString("Email"));
	            	user.setAddress(rs.getString("Address"));
	            	user.setMobileNo(rs.getString("MobileNo"));
	            	user.setCustomerName(rs.getString("CustomerName"));
	             	user.setPassword(rs.getString("password"));
	            	
	            }
	        }
	        catch(SQLException ex) {
	        	ex.printStackTrace();
	        }finally {
	        	try {
	        		if(rs!=null) rs.close();
	        		if(stmt!=null) stmt.close();
	        		if(conn!=null) conn.close();
	        	}catch(SQLException ex) {
	        		ex.printStackTrace();
	        	}
	        }
	        return user;
	       
	        
	        
	}
	@Override
	public Admin validateAdmin(String userId, String password1) {
		 Connection connection = null;
	        Admin user = null;

	        try {
	            connection = DBConnection.getDBConnection();

	            String sql = "SELECT * FROM Admins WHERE userId = ? AND password1 = ?";

	            PreparedStatement pstmt = connection.prepareStatement(sql);
	            pstmt.setString(1, userId);
	            pstmt.setString(2, password1);

	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                user = new Admin();
	                user.setUserId(rs.getString("userId"));
	                
	                user.setPassword(rs.getString("password1"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBConnection.closeDBConnection(connection);
	        }

	        return user;
	    }
//	@Override
//	public boolean reserve(Reservation reservation) {
//		Connection conn = null;
//	    PreparedStatement stmt = null;
//	    try {
//	        conn = DBConnection.getDBConnection();
//	        String query = "INSERT INTO reservations (check_in_date, check_out_date, room_preference, name, contact) VALUES (?, ?, ?, ?, ?)";
//	        stmt = conn.prepareStatement(query);
//	        stmt.setString(1, reservation.getCheck_in_date());
//	        stmt.setString(2, reservation.getCheck_out_date());
//	        stmt.setString(3, reservation.getPreferences());
//	        stmt.setString(4, reservation.getName());
//	        stmt.setString(5, reservation.getContact());
//
//	        int rowsAffected = stmt.executeUpdate();
//	        return rowsAffected > 0; // Returns true if at least one row was affected (successful registration)
//	    } catch (SQLException ex) {
//	        ex.printStackTrace();
//	        return false; // Registration failed
//	    } finally {
//	        try {
//	            if (stmt != null) stmt.close();
//	            if (conn != null) conn.close();
//	        } catch (SQLException ex) {
//	            ex.printStackTrace();
//	        }
//	    }
//	}
	@Override
	public Integer reserve(Reservation reservation) {
		
		int MAX_ROOMS = 3;
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    PreparedStatement stmt2 = null;
	    PreparedStatement stmt3 = null;
	    
	    ResultSet generatedKeys = null;
	    ResultSet roomCountSet = null;
	    try {
	        conn = DBConnection.getDBConnection();
	        
	        //MAX_ROOMS maximum number of rooms filled/not
	        String countCheckQuery = "SELECT COUNT(*) FROM rooms";
	        stmt2 = conn.prepareStatement(countCheckQuery);
	        roomCountSet = stmt2.executeQuery();
	        Integer roomCount = 0;
	        if (roomCountSet.next()) {
				roomCount = roomCountSet.getInt(1);
				System.out.println("(count of rooms)"+roomCount);
			} 
	        
	        if(roomCount<MAX_ROOMS) {
	        	String insertRoomQuery = "INSERT INTO rooms (Preference,Check_in_date,Check_out_date) VALUES(?,?,?)";
	        	stmt3 = conn.prepareStatement(insertRoomQuery);
	        	stmt3.setString(1,reservation.getPreferences());
	        	stmt3.setString(2,reservation.getCheck_in_date());
	        	stmt3.setString(3,reservation.getCheck_out_date());
	        	
	        	int rowsAffected1 = stmt3.executeUpdate();
	        	if(rowsAffected1<=0)
	        		return -1;
	        	
	        }else {
	        	return -1;
	        }
	        
	        String query = "INSERT INTO reservations (check_in_date, check_out_date, room_preference, name, contact) VALUES (?, ?, ?, ?, ?)";
	        stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, reservation.getCheck_in_date());
	        stmt.setString(2, reservation.getCheck_out_date());
	        stmt.setString(3, reservation.getPreferences());
	        stmt.setString(4, reservation.getName());
	        stmt.setString(5, reservation.getContact());

	        int rowsAffected = stmt.executeUpdate();	        
	        // Check if the insertion was successful 
	        if (rowsAffected > 0) {
	            generatedKeys = stmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1); // Return the generated reservationId
	            }
	        }
	        return -1; // Return null if no key was generated or insertion failed
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return -1; // Return null in case of an exception
	    } finally {
	        try {
	            if (generatedKeys != null) generatedKeys.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	@Override
	public double calculatePrice(String roomPreference, String checkindate, String checkoutdate) {
		   double pricePerNight;
	        if (roomPreference.equalsIgnoreCase("AC")) {
	            pricePerNight = 2000;
	        } else {
	            pricePerNight = 1500;
	        }

	        // Calculate the number of nights
	        int numberOfNights = calculateNumberOfNights(checkindate, checkoutdate);

	        // Calculate total price
	        double totalPrice = pricePerNight * numberOfNights;

	        return totalPrice;
	    }
	private int calculateNumberOfNights(String checkindate, String checkoutdate) {
		// TODO Auto-generated method stub	
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 LocalDate startDate = LocalDate.parse(checkindate);
		    LocalDate endDate = LocalDate.parse(checkoutdate);

		   
		    return (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
		

		
		  
	}
	@Override
	  public boolean addToBookings(Integer id, Reservation r, Double price) {
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = DBConnection.getDBConnection();
	            String query = "INSERT INTO Bookings (ReservationId, CheckInDate, CheckOutDate, RoomNumber, Bill, BookingDate) VALUES (?, ?, ?, ?, ?, ?)";
	            stmt = conn.prepareStatement(query);

	            stmt.setInt(1, id);
	            stmt.setString(2, r.getCheck_in_date()); 
	            stmt.setString(3, r.getCheck_out_date());
	            stmt.setString(4, r.getPreferences()); 
	            stmt.setDouble(5, price);
	            stmt.setString(6, LocalDate.now().format(DateTimeFormatter.ISO_DATE)); 

	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0; // Returns true if at least one row was affected (successful registration)
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false; // Registration failed
	        } finally {
	            try {
	                if (stmt != null) stmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	@Override
	  public boolean addToBillings(Integer id,double price) {
	        Connection connection = null;
	        
	        String sql = "INSERT INTO billings (BillingId, RoomCharges, AdditionalServices, TotalCharges) VALUES (?,?, 0, ?)";
	        try  {

	        	connection = DBConnection.getDBConnection();
	        	PreparedStatement pstmt = connection.prepareStatement(sql);
	        	pstmt.setInt(1, id);
	            pstmt.setDouble(2, price);
	            pstmt.setDouble(3, price);

	            int rowsAffected = pstmt.executeUpdate();
	            return rowsAffected > 0; 
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
    }

	@Override
	public Billing getBillingDetails(String BillingId) {
		// TODO Auto-generated method stub
		 Billing billing = null;
	        Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;

	        try {
	            // Get connection and prepare statement
	        	 connection = DBConnection.getDBConnection();
	            String query = "SELECT * FROM billings WHERE BillingId=?";
	            statement = connection.prepareStatement(query);
	            statement.setString(1, BillingId);

	            // Execute query
	            resultSet = statement.executeQuery();

	            // Process result set
	            if (resultSet.next()) {
	                billing = new Billing();
	                billing.setBillingId(resultSet.getString("BillingId"));
	                billing.setRoomCharges(resultSet.getDouble("roomCharges"));
	                billing.setAdditionalServices(resultSet.getDouble("additionalServices"));
	                billing.setTotalCharges(resultSet.getDouble("totalCharges"));
	                // Populate other user properties if needed
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle exceptions appropriately
	        } finally {
	            // Close resources
	            try {
	                if (resultSet != null) resultSet.close();
	                if (statement != null) statement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return billing;
	    }
	@Override
	public ArrayList<Booking> viewByUserId(int userId) {
		  Booking book=new Booking();
	        try {
	           
	        	Connection connection = DBConnection.getDBConnection();
	        	//so bookings may have multiple user bookings under same customerid
	        	String sql = "SELECT * FROM bookings WHERE reservationid = ?";
	            PreparedStatement ps = connection.prepareStatement(sql);
	            ps.setInt(1, userId);
	            ResultSet rs = ps.executeQuery();
	            
	            while (rs.next()) {
	             
	                book.setReservationId(rs.getInt(1));
	                book.setCheckInDate(Date.valueOf(rs.getString(2)));
	                //System.out.println("(history)checkindate:"+Date.valueOf(rs.getString(2)));
	                book.setCheckOutDate(Date.valueOf(rs.getString(3)));
	                book.setRoomNumber(rs.getInt(4));
	                book.setBill(rs.getDouble(5));
	                book.setBookingDate(Date.valueOf(rs.getString(6)));
	                
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	      ArrayList<Booking> cus=new ArrayList<Booking>();
	      cus.add(book);
	      return cus;
	    }
//	@Override
//	public boolean isRoomAvailable(String preference, Date check_in_date1, Date check_out_date2) {
//		Connection conn = null;
//		int MAX_ROOMS=10;
//	       
//		try {
//				 conn = DBConnection.getDBConnection();
//				 String query="SELECT COUNT(*) FROM rooms WHERE preference = ? AND  ((check_in_date >= ? AND check_in_date < ?) OR (check_out_date > ? AND check_out_date <= ?) OR (check_in_date < ? AND check_out_date > ?))";
//	            PreparedStatement ps = conn.prepareStatement(query);
//	            System.out.println("(isroomavailabl sql)"+ps);
//	            
//	            //either should say if no rooms available to rent out ,return true
//	            //okay set total number of rooms (by checking if count of rooms <= TOTAL rooms (in reservationsservlet) )
//	            // or say  if there are rooms with said parameters -  then true
//	            ps.setString(1,preference);
//	            ps.setDate(2, check_in_date1);
//	            ps.setDate(3, check_in_date1);
//	            ps.setDate(4, check_out_date2);
//	            ps.setDate(5, check_out_date2);
//	            ps.setDate(6, check_in_date1);
//	            ps.setDate(7, check_out_date2);
//	            ResultSet rs = ps.executeQuery();
//	            
//	            String countQuery = "SELECT COUNT(*) FROM rooms";
//	            PreparedStatement ps2 = conn.prepareStatement(countQuery);
//	            ResultSet rs2 = ps2.executeQuery();
//	            
//	            if (rs.next() && rs2.next()) {
//	                int rscount = rs.getInt(1);
//	                System.out.println("(room-check-count)"+rscount);
//	                int count = rs2.getInt(1);
//	                System.out.println("(room-count)"+count);
//	                if(count < MAX_ROOMS) {
//		                if(count - rscount > 0) { //rest of the rooms are available
//		                	return false;
//		                }else {
//		                	return true;
//		                }
//	                }else {
//	                	return false;
//	                }
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	        return false;
//
//	}
	
	@Override
	public boolean isRoomAvailable(String preference, Date check_in_date1, Date check_out_date2) {
	    Connection conn = null;
	    int MAX_ROOMS = 3;
	    ResultSet rs =null;
	    PreparedStatement ps=null;

	    try {
	        conn = DBConnection.getDBConnection();
	        
	        // Query to fetch all rooms based on preference
	        String query = "SELECT roomNumber, check_in_date, check_out_date FROM rooms WHERE preference = ?";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, preference);
	        rs = ps.executeQuery();
	        
	        // Track available rooms
	        Set<Integer> nonAvailableRoomIds = new HashSet<>();
	        
	        boolean isAProblem = false;
	        // Fetch the current state of all rooms based on preference
	        while (rs.next()) {
	            int roomId = rs.getInt("roomNumber");
	            Date roomCheckIn = Date.valueOf(rs.getString("check_in_date"));
	            Date roomCheckOut = Date.valueOf(rs.getString("check_out_date"));
	            
	            // Check if the room is available during the requested period
	            boolean isNotAvailable = !(
	            	    (check_in_date1.before(roomCheckIn) && check_out_date2.before(roomCheckIn))  // Entirely before reserved period
	            	    || 
	            	    (check_in_date1.after(roomCheckOut) && check_out_date2.after(roomCheckOut))   // Entirely after reserved period
	            	);
	            
	            isAProblem = isAProblem || isNotAvailable;
	            nonAvailableRoomIds.add(roomId);
//	            if (!isNotAvailable) {
//	            	System.out.println("(" + roomCheckIn + "-" + roomCheckOut + ")");
//	            }
	        }
	        
	        // Determine the total number of available rooms
	        int nonAvailableRoomsCount = nonAvailableRoomIds.size();
	        
	        System.out.println("countofrooms(unavailable) "+nonAvailableRoomsCount);
	        System.out.println("isAProblem "+isAProblem);
	        
	        // Check if there are available rooms within the maximum allowed number
	        if(isAProblem)
	        	return false;
	        if (nonAvailableRoomsCount < MAX_ROOMS) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return false;
	}

	@Override
	public boolean processPayment(Payment payment) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
        	connection = DBConnection.getDBConnection();
            
            String transactionId = generateTransactionId();
            payment.setTransactionId(transactionId); // set the transaction ID in the payment bean

            String sql = "INSERT INTO payments (transaction_id, customer_id, bill_amount, payment_mode, card_number, card_holder_name, expiry_date, cvv) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, payment.getTransactionId());
            preparedStatement.setString(2, payment.getCustomerId());
            preparedStatement.setDouble(3, payment.getBillAmount());
            preparedStatement.setString(4, payment.getPaymentMode());
            preparedStatement.setString(5, payment.getCardNumber());
            preparedStatement.setString(6, payment.getCardHolderName());
            preparedStatement.setString(7, payment.getExpiryDate());
            preparedStatement.setString(8, payment.getCvv());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String generateTransactionId() {
        return UUID.randomUUID().toString();
    }


}
	
	 

 


       
	
