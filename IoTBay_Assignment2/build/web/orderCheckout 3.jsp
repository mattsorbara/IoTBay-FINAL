<%-- 
    Document   : register
    Created on : 29 Apr 2022, 5:39:23 pm
    Author     : matthewsorbara
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="iotbay.model.Catalogue"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/orderSummary.css">
    </head>
    <% 
        
        Catalogue product = (Catalogue) session.getAttribute("testProduct");
    
    %>
    
     <body>
        <div class="register">
            <div class="registerContents">
                <h2 id="title"><b>Order Summary</b></h2>
                <form class="registerForm">
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
                            <input class="border-customized-input" type="text" name="quantity" value="<%= product.getStock() %>" readonly>
                        </div>
                    </div>
                    <div class="registerFormElement" id="Quantity">
                        <label>Quantity</label>
                        <div> 
                            <input class="border-customized-input" type="text" required="true" name="quantity" placeholder="Quantity">
                        </div>
                    </div>
                    <div class="buttons">
                        <a href="home.jsp" class="cancel">Cancel</a>
                        <button class="submit" type="submit">Proceed to Shipment</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
