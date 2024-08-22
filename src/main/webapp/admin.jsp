<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title> Admin Login</title>
      <style><%@include file="/WEB-INF/login.css"%></style>
</head>
<body>
  <div class="login-box">
    <h2>ADMIN LOGIN</h2>
    <form action="admin" method="post">
    <div class="textbox">
        <label for="userId">User ID:</label>
        <input type="text" id="userId" name="userId" required><br>
        </div>
      <div class="textbox">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
         </div>
        <input type="submit" value="Login" class="login-button">
    </form>
    <% String errorMessage=(String)request.getAttribute("errorMessage");
    if(errorMessage!=null && !errorMessage.isEmpty()) {  %>
    <p style="color:red"><%=errorMessage %> </p>
    <% 
    	} 
    %>
 </div>
    
</body>
</html>

