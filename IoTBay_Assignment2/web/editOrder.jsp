<%-- 
    Document   : register
    Created on : 29 Apr 2022, 5:39:23 pm
    Author     : matthewsorbara
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="iotbay.model.*"%>
<%@page import="iotbay.model.dao.DBManager"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Checkout | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/editOrder.css">
    </head>
    <% 
        Catalogue product = (Catalogue) session.getAttribute("product");
        String orderError = (String) session.getAttribute("orderError");
        Order order = (Order) session.getAttribute("targetOrder");
    %>
    
     <body>
        <div class="register">
            <div class="registerContents">
                <h2 id="title"><b>Order Summary</b></h2>
                <p style="color: red; text-align: center; margin-bottom: 0">${orderError}</p>
                <form class="registerForm" action="EditOrderServlet" method="post">
                    <div class="registerFormElement" id="productName">
                        <label>Product Name</label>
                        <div>
                            <input class="border-customized-input" type="text" name="productName" value="<%= product.getTitle() %>" readonly>
                        </div>
                    </div>
                    <div class="registerFormElement" id="productPrice">
                        <label>Product Price</label>
                        <div>
                            <input class="border-customized-input" type="text" name="productPrice" value="$<%= product.getPrice() %>" readonly>
                        </div>
                    </div>
                    <div class="registerFormElement" id="productStock">
                        <label>Stock</label>
                        <div>
                            <input class="border-customized-input" type="text" name="productStock" value="<%= product.getStock() %>" readonly>
                        </div>
                    </div>
                    <div class="registerFormElement" id="quantity">
                        <label>Quantity</label>
                        <div> 
                            <input class="border-customized-input" type="text" required="true" name="quantity" value="<%= order.getOrderQuantity() %>">
                        </div>
                    </div>
                    <div class="buttons">
                        <button class="submit" type="submit">Update Order</button>
                    </div>
                </form>
                <form action="SubmitOrderServlet" method="post">
                    <div class="buttons">
                        <button name="id" value="<%= order.getOrderID() %>" class="submit">Submit Order</button>
                    </div>
                </form>    
            </div>
        </div>
    </body>
</html>
