<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
       <style><%@include file="/WEB-INF/register.css"%></style>

    <title>Payment Page</title>
    <script type="text/javascript">
        function validatePayment() {
            var cardNumber = document.getElementById("CardNumber").value;
            var cardHolderName = document.getElementById("CardHolderName").value;
            var expiryDate = document.getElementById("ExpiryDate").value;
            var cvv = document.getElementById("CVV").value;

            if (cardNumber.length !== 16) {
                alert("Card number must be 16 digits.");
                return false;
            }
            if (cardHolderName.length < 10) {
                alert("Card holder name must be at least 10 characters.");
                return false;
            }
            if (!/^\d{2}\/\d{2}$/.test(expiryDate)) {
                alert("Expiry date must be in MM/YY format.");
                return false;
            }
            if (cvv.length !== 3) {
                alert("CVV must be 3 digits.");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
<div class=register-box>
	<h1>Payment Page</h1>
    <h2>Enter the details below to complete the payment:</h2>
    
    <form action="payment" method="post" onsubmit="return validatePayment();">
        <p>CustomerId: <input type="text" id="customerId" name="CustomerId" > </p>
        <p>Bill Amount: <input type="text" id="billAmount" name="BillAmount" > </p>
        <p>Mode of Payment: 
            <select name="PaymentMode">
                <option value="Debit">Debit</option>
                <option value="Credit">Credit</option>
            </select>
        </p>
        <p>Card Number: <input type="text" id="CardNumber" name="CardNumber" maxlength="16"></p>
        <p>Card Holder Name: <input type="text" id="CardHolderName" name="CardHolderName" minlength="10"></p>
        <p>Expiry Date: <input type="text" id="ExpiryDate" name="ExpiryDate" placeholder="MM/YY"></p>
        <p>CVV: <input type="text" id="CVV" name="CVV" maxlength="3"></p>
        <button type="submit" class="login-button">Make Payment</button>
    </form>
    <button onclick="window.location.href='home.jsp'">Back to Home</button>

    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p style="color:red;"><%= errorMessage %></p>
    <%
        }
    %>
    </div>
</body>
</html>

