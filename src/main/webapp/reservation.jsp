 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<head>
    <title>User Registration</title>
       
       <style><%@include file="/WEB-INF/register.css"%></style>
    
     
</head>



<body>
    <div class="register-box">
     
        <h2>Register</h2>
        <form action="reservation" method="post">
            <div class="textbox">
                <label for="check_in_date">check_in_date:</label>
                <input type="Date" id="check_in_date" name="check_in_date" pattern="\d{4}-\d{2}-\d{2}"  required >
            </div>
            <div class="textbox">
                <label for="check_out_date">check_out_date:</label>
                <input type="Date" id="check_out_date" name="check_out_date" pattern="\d{4}-\d{2}-\d{2}" required>
  </div>
                    <div class="textbox">
            <label for="room_Preference">preferences :</label>
            <select id="room_Preference" name="room_Preference" required>
                <option value="AC">AC</option>
                <option value="NON-AC">NON-AC</option>
               
                <!-- Add other country codes as needed -->
            </select>
        </div>
           
               <div class="textbox">
                <label for="name">name :</label>
                <input type="text" id="name" name="name" required>
            </div>
               <div class="textbox">
                <label for="contact">contact:</label>
                <input type="text" id="contact" name="contact"   required>
            </div>
            <input type="submit" value="Caluclate Price" class="register-button">
        
        
           <h2> Total Price: ${totalPrice}</h2>
             <% String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <p class="error-message"><%= errorMessage %></p>
        <% } %>
   <% String acknowledgmentMessage=(String)request.getAttribute("acknowledgmentMessage");
    if(acknowledgmentMessage!=null && !acknowledgmentMessage.isEmpty()) {  %>
    <p style="color:green"><%=acknowledgmentMessage %> </p>
    <% 
    	} 
    %>
    </form>
    </div>
</body>
</html>