<%-- 
    Document   : orderConfirmation
    Created on : 14/05/2022, 12:05:59 PM
    Author     : lindale
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="iotbay.model.*"%>
<%@page import="iotbay.model.dao.DBManager"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Confirmation | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/orderConfirmation.css">
    </head>
    <% 
        Order order = (Order) session.getAttribute("currentOrder");
        Payment payment = (Payment) session.getAttribute("confirmedPayment");
    %>
    
     <body>
        <div class="loggedInMenu">            
            <a href="welcome.jsp" class="button">Main</a>
            <a href="LogoutServlet" class="button" id="logout">Logout</a>
        </div>
        <div class="register">
            <div class="registerContents">
                <h2 id="title"><b>Thank you for your Order!</b></h2>
                <div class="registerFormElement" id="productName">
                    <label>Order ID</label>
                    <div>
                        <input class="border-customized-input" type="text" value="<%= order.getOrderID() %>" readonly>
                    </div>
                </div>
                <div class="registerFormElement" id="productName">
                    <label>Product Name</label>
                    <div>
                        <input class="border-customized-input" type="text" value="<%= order.getProductID() %>" readonly>
                    </div>
                </div>
                <div class="registerFormElement" id="productStock">
                    <label>Order Quantity</label>
                    <div>
                        <input class="border-customized-input" type="text" value="<%= order.getOrderQuantity() %>" readonly>
                    </div>
                </div>                        
                <div class="registerFormElement" id="productPrice">
                    <label>Total Price</label>
                    <div>
                        <input class="border-customized-input" type="text" value="$<%= order.getOrderPrice() %>" readonly>
                    </div>
                </div>
                <div class="registerFormElement" id="productPrice">
                    <label>Date Ordered</label>
                    <div>
                        <input class="border-customized-input" type="text" value="<%= order.getOrderDate() %>" readonly>
                    </div>
                </div>
            </div>
            <div class="registerContents">
                <h2 id="title"><b>Your Payment Details</b></h2>
                <div class="registerFormElement" id="productName">
                    <label>Payment ID</label>
                    <div>
                        <input class="border-customized-input" type="text" value="<%= payment.getPaymentID() %>" readonly>
                    </div>
                </div>
                <div class="registerFormElement" id="productName">
                    <label>Card Number</label>
                    <div>
                        <input class="border-customized-input" type="text" value="<%= payment.getCardNumber() %>" readonly>
                    </div>
                </div>
                <div class="registerFormElement" id="productStock">
                    <label>Card CVC</label>
                    <div>
                        <input class="border-customized-input" type="text" value="<%= payment.getCardCVC() %>" readonly>
                    </div>
                </div>                        
                <div class="registerFormElement" id="productPrice">
                    <label>Card Expiry</label>
                    <div>
                        <input class="border-customized-input" type="text" value="<%= payment.getCardExpiry() %>" readonly>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
