<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<head>
    <title>User Registration</title>
       <style><%@include file="/WEB-INF/register.css"%></style>
</head>
<body>
 
    <div class="register-box">
     <h2>Hotel Elite</h2>
        <h3>REGISTER USER</h3>
        <form action="register" method="post">
            <div class="textbox">
                <label for="CustomerName">Customer Name:</label>
                <input type="text" id="CustomerName" name="CustomerName"  maxlength="50" required >
            </div>
            <div class="textbox">
                <label for="Email">Email:</label>
                <input type="text" id="Email" name="Email" required>
            </div>
               <div class="textbox">
                <label for="Address">Address:</label>
                <input type="text" id="Address" name="Address" required>
            </div>
                    <div class="textbox">
            <label for="CountryCode">Country Code:</label>
            <select id="CountryCode" name="CountryCode" required>
                <option value="+91">+91 (India)</option>
                <option value="+44">+44 (UK)</option>
                <option value="+1">+1 (US)</option>
               
            </select>
        </div>
           
               <div class="textbox">
                <label for="MobileNo">Mobile No:</label>
                <input type="text" id="MobileNo" name="MobileNo" pattern="\d{10}" title="10 digit mobile number" placeholder="10 digit mobile number" required>
            </div>
               <div class="textbox">
                <label for="CustomerId">CustomerId:</label>
                <input type="text" id="CustomerId" name="CustomerId" minlength="5" maxlength="20"  required>
            </div>
            <div class="textbox">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password"  pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,30}" title="Must contain at least one number, one uppercase, one lowercase letter, and one special character. Min 8 and max 30 characters." required>
            </div>
                <div class="textbox">
            <label for="confirm-password">Confirm Password:</label>
            <input type="password" id="confirm-password" name="confirm-password" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,30}" title="Must contain at least one number, one uppercase, one lowercase letter, and one special character. Min 8 and max 30 characters." required>
        </div>
            <input type="submit" value="Register" class="register-button">
        </form>
        <% String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <p class="error-message"><%= errorMessage %></p>
        <% } %>
        
        
        
  
    </div>
</body>
</html>