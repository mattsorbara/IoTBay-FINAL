<%-- 
    Document   : cardPayment
    Created on : 09/05/2022, 8:17:20 AM
    Author     : lindale
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/payment.css">
    </head>
    <body>
        <div class="loggedInMenu">            
            <a href="welcome.jsp" class="button">Main</a>
            <a href="LogoutServlet" class="button" id="logout">Logout</a>
        </div>
        <div class="payment">
            <div class="paymentContents">
                <h2 id="title"><b>Payment</b></h2>
                <form class="paymentForm" action="cardPaymentServlet" method="post">
                    <div class="paymentFormElement" id="cardNumber">
                        <label>Card Number</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="cardNumber" placeholder="0000 0000 0000 0000">
                        </div>
                    </div>
                    <div class="paymentFormElement" id="cardExpiry">
                        <label>Card Expiry</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="cardExpiry" placeholder="00/00">
                        </div>
                    </div>
                    <div class="paymentFormElement" id="cardCVC">
                        <label>Card CVC</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="cardCVC" placeholder="123">
                        </div>
                    </div>
                    <div class="buttons">
                        <button class="submit" type="submit" name="crudPayment" value="false">Pay</button>
                        <button class="submit" type="submit" name="crudPayment" value="create">Pay & Save Payment</button>
                    </div>
                    <div class="buttons">
                        <button class="submit" type="submit" name="crudPayment" value="update">Pay & Update Payment</button>
                        <button class="submit" type="submit" name="crudPayment" value="delete">Pay & Delete Saved Payment</button>
                    </div>
                    <div class="buttons">
                        <a href="home.jsp" class="cancel">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
