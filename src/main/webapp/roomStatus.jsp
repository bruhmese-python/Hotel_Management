<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room Availability</title>
    <style><%@include file="/WEB-INF/register.css"%></style>
</head>
<body>
<div class="register-box">
    <h2>Room Availability</h2>
    <form action="roomStatus" method="post">
     <div class="textbox">
        Check-in Date: <input type="date" name="check_in" required><br>
        </div>
         <div class="textbox">
        Check-out Date: <input type="date" name="check_out" required><br>
        </div>
         <div class="textbox">
        Room Type:
        <select name="preference">
            <option value="AC">AC</option>
            <option value="non-AC">Non-AC</option>
        </select><br>
         </div>
        <input type="submit" value="Check Availability" class="login-button">
    </form>
    </div>
        <h3>Status:</h3>
        
            <% String status = (String) request.getAttribute("status");
        if (status != null && !status.isEmpty()) { %>
            <p style="color: green;"><%= status %></p>
        <% } %>
         <% String status1 = (String) request.getAttribute("status1");
        if (status1 != null && !status1.isEmpty()) { %>
            <p style="color: red;"><%= status1 %></p>
        <% } %>
