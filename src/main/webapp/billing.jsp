<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Charges Lookup</title>
      <style><%@include file="/WEB-INF/register.css"%></style>
</head>
<body>
 <div class="register-box">
    <h2>Billing Details</h2>
      
    <form action="billing" method="post">
       <div class="textbox">
     
        <label for="BillingId">Customer ID:</label>
        <input type="text" id="BillingId" name="BillingId" required>
        </div>
  
        <input type="submit" value="Get Charges"  class="login-button">
    </form>

     
  <%@ page import="com.hotel.bean.*" %>
    
    <%
        Billing billing = (Billing) request.getAttribute("billing");
        String customerId = (String) request.getAttribute("BillingId");
        if (customerId != null) {
    %>
        <h4>Customer Charges</h4>
        <%
            if (billing != null) {
        %>
        <div class=textbox>
           <p>Customer ID: <%= billing.getBillingId() %></p>
            <p>Total Charges: <%= billing.getTotalCharges() %></p>
            <p>Room Charges: <%= billing.getRoomCharges() %></p>
            <p>Additional Charges: <%= billing.getAdditionalServices() %></p>
            
            </div>
        <%
            } else {
                %>
     
            <p>No charges found for the provided customer ID: <%= customerId %></p>
        <%
            }
        %>
    <%
        }
        
    %>
    
        <% String errorMessage2=(String)request.getAttribute("errorMessage2");
    if(errorMessage2!=null && !errorMessage2.isEmpty()) {  %>
    <p style="color:red"><%=errorMessage2 %> </p>
    <% 
    	} 
    %>
        </div>
</body>
</html>
 