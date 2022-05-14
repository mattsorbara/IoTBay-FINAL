<%-- 
    Document   : payment
    Created on : 07/05/2022, 9:03:22 AM
    Author     : lindale
--%>

<%@page import="iotbay.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/payment.css">
    </head>
    <% 
        Savedpayment savedPayment = (Savedpayment) session.getAttribute("savedPayment");
        Order currentOrder = (Order) session.getAttribute("currentOrder");
    %>
    <body>
        <div class="loggedInMenu">            
            <a href="welcome.jsp" class="button">Main</a>
            <a href="LogoutServlet" class="button" id="logout">Logout</a>
        </div>
        <div class="payment">
            <div class="paymentContents">
                <h2 id="title"><b>Payment Summary</b></h2>
                <form class="paymentForm" action="PaymentServlet" method="post">
<!--                    <div class="paymentFormElement" id="orderID">
                        <label>Order ID</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="orderID">
                            <input class="border-customized-input" type="text" required="true" name="paymentID" value="${order.orderID}" readonly>
                        </div>
                    </div>-->
                    <div class="paymentFormElement" id="amount">
                        <label>Amount</label>
                        <div>
                            <!--<input class="border-customized-input" type="double" required="true" name="amount">-->
                            <input class="border-customized-input" type="double" required="true" name="paymentID" placeholder="${currentOrder.getOrderPrice()}" readonly>
                        </div>
                    </div>
                    <div class="paymentFormElement" id="paymentMethod">
                        <label>Payment Method</label>
                        <div>
                            <!--<input class="border-customized-input" type="text" required="true" name="paymentMethod">-->
                            <select name="paymentMethod">
                                <option value="card">Card</option>
                                <% 
                                if (!"guest@guest.com".equals(currentOrder.getUserEmail())){
                                %>
                                <option value="savedCard">Saved Payment</option>
                                <%}%>
                            </select>
                        </div>
                    </div>
                    <div class="buttons">
                        <button class="submit" type="submit">Pay / Update SP</button>
                        <a href="home.jsp" class="cancel">Cancel</a>
                    </div>
                </form><br><br>
                <% 
                    if (!"guest@guest.com".equals(currentOrder.getUserEmail())){
                %>                     
                <h2 id="title"><b>Saved Payment</b></h2><br
                <table id="paymentTable" align="center">
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>CC Number</th>
                            <th>CVC</th>
                            <th>Expiry Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr> 
                            <td><%= savedPayment.getEmail() %></td>
                            <td><%= savedPayment.getCardNumber() %></td>
                            <td><%= savedPayment.getCardCVC() %></td>
                            <td><%= savedPayment.getCardExpiry() %></td>
                        </tr>
                    </tbody>
                </table>
                <%}%>
            </div>
        </div>
    </body>
</html>
