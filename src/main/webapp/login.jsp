<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <title>LOGIN</title>
      <style><%@include file="/WEB-INF/login.css"%></style>
</head>
<body>
  <div class="login-box">
    <h2>LOGIN</h2>
      <% String successPage=(String)request.getAttribute("successPage");
    if(successPage!=null && !successPage.isEmpty()) {  %>
    <p style="color:green"><%=successPage %> </p>
    <% 
    	} 
    %>
    <form action="login" method="post">
    <div class="textbox">
        <label for="CustomerId">User ID:</label>
        <input type="text" id="CustomerId" name="CustomerId" required><br>
        </div>
      <div class="textbox">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
         </div>
        <input type="submit" value="Login" class="login-button">
    </form>
       <% String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <p class="error-message"><%= errorMessage %></p>
        <% } %>
 </div>
    
</body>
</html>

