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
        Catalogue product = (Catalogue) session.getAttribute("product");
        User user = (User) session.getAttribute("user");
    %>
    
     <body>
        <% if ("guest@guest.com".equals(user.getEmail())) { %>
        <jsp:include page="guestHeader.jsp"/>
        
        <% } else { %>
        <jsp:include page="header.jsp"/>
        <% } %>
        <div class="register">
            <div class="registerContents">
                <h2 id="title"><b>Order Summary</b></h2>
                <div class="registerFormElement" id="productName">
                    <label>Order ID</label>
                    <div>
                        <input class="border-customized-input" type="text" value="<%= order.getOrderID() %>" readonly>
                    </div>
                </div>
                <div class="registerFormElement" id="productName">
                    <label>Product Name</label>
                    <div>
                        <input class="border-customized-input" type="text" value="<%= product.getTitle() %>" readonly>
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
            <div class="registerContents">
                <div style="margin-left: 33%">
                    <form action="SubmitOrderServlet" method="post">
                        <button name="id" value="<%= order.getOrderID() %>" class="submit">Submit Order</button>
                    </form>
                    <a href="catalogue.jsp"><button class="submit">Save Order</button></a>
                </div>
            </div>
        </div>
    </body>
</html>
