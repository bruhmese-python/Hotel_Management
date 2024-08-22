<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.hotel.bean.Booking" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/register.css"%></style>
</head>
<body>
 <div class="login-box">
  <h1>Admin - Access Booking History</h1>
    <form action="history" method="post">
    <div class="history-box">
        <label for="userId">Enter UserID:</label>
        <input type="text" id="userId" name="userId" required>
       </div>
        <button type="submit" class="login-button">View History </button>
    </form>

    <%
        List<Booking> bookingList = (List<Booking>) request.getAttribute("bookingList");
        if (bookingList != null && !bookingList.isEmpty()) {
    %>
    <table border="1">
        <tr>
            <th>Reservation ID</th>
            <th>Check-in Date</th>
            <th>Check-out Date</th>
            <th>Room Number</th>
            <th>Bill</th>
            <th>Booking Date</th>
        </tr>
        <%
            for (Booking booking : bookingList) {
        %>
        <tr>
            <td><%= booking.getReservationId() %></td>
            <td><%= booking.getCheckInDate() %></td>
            <td><%= booking.getCheckOutDate() %></td>
            <td><%= booking.getRoomNumber() %></td>
            <td><%= booking.getBill() %></td>
            <td><%= booking.getBookingDate() %></td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }
    %>
    
    <% 
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) { 
    %>
        <p class="error-message"><%= errorMessage %></p>
    <% } %>
  
 </div>
</body>
</html>