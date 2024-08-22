<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>HOME</title>
      <style><%@include file="/WEB-INF/home.css"%></style>
</head>
<body>
    <h2>Welcome Administrator -${userId }</h2>
      
    <ul>
        <li><a href="homeAdmin">Home</a></li>
        <li><a href="reservation.jsp">Reservation</a></li>
        <li><a href="billing.jsp">Billing</a></li>
        <li><a href="history.jsp">History</a></li>
         <li><a href="roomStatus.jsp">Room Status</a></li>
         <li><a href="roomStatus.jsp">Pay Bill</a></li>
        <li><a href="contact.jsp">Contact Support</a></li>
        <li><a href="index.jsp">Logout</a></li>
    </ul>
    

</body>
</html>
