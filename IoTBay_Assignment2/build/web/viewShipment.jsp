<%-- 
    Document   : trackOrder
    Created on : 09/05/2022, 2:23:33 PM
    Author     : tanya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>IoTBay | View Shipment Details</title>
        
        <link rel="stylesheet" href="static/css/viewShipment.css"/>
        
        
        <!-- calling everything --> 
        
       
        -->
    </head>
    <% 
        Savedshipment savedShipment = (Savedshipment) session.getAttribute("savedShipment");
        Order currentOrder = (Order) session.getAttribute("currentOrder");
        String anythingSaved = (String) session.getAttribute("anythingSaved");
        User currentUser = (User) session.getAttribute("user");
    %>
    <body>
        <div class="loggedInMenu">            
            <a href="welcome.jsp" class="button">Main</a>
            <a href="LogoutServlet" class="button" id="logout">Logout</a>
        </div>
        <h1>View your Order Shipment Details</h1>
        <h2> The shipment details linked to your order are shown below. 
            Choose from one of the listed options to make any changes to your shipment details. </h2>
        <% 
                    if (!"guest@guest.com".equals(currentUser.getUserEmail())){
                %> 
         
            <form action="ViewShipmentServlet" method="post">
                
                <div class="infoTable">
             <h3 class="infoTableHeader"> Your Current Shipment Details </h3> 
             <br> 
             <h3 class="shipDetails"> Shipment ID: </h3> <td><%= savedShipment.getShipmentID() %></td>
             <br> 
             <h3 class="shipDetails"> Shipment Status: Processing </h3> 
             <br> 
             <h3 class="shipDetails"> Shipment Method: </h3> <td><%= savedShipment.getshipMethod() %></td>
             <br> 
             <h3 class="shipDetails"> Shipment Date:<!-- to be done--> </h3> <td><%= savedShipment.getshipDate() %></td>
             <br> 
             <h3 class="shipDetails"> Unit Number: <!-- to be done--> </h3> <td><%= savedShipment.unitno() %></td>
             <br> 
             <h3 class="shipDetails"> Street Number: <!-- to be done--> </h3> <td><%= savedShipment.streetno() %></td>
             <br> 
             <h3 class="shipDetails"> Street Name <!-- to be done--> </h3> <td><%= savedShipment.streetname() %></td>
             <br> 
             <h3 class="shipDetails"> Suburb: <!-- to be done--> </h3> <td><%= savedShipment.suburb() %></td>
             <br> 
             <h3 class="shipDetails"> Postcode: <!-- to be done--> </h3> <td><%= savedShipment.postcode() %></td>
       
         </div> 
       }
        
    <div class="buttons">
                <a href="index.html" class="deleteButton" > Back </a> 
                
                <a href="updateShipment.jsp" class="updateButton"> Update </a>
                
                <a href="deleteShipment.jsp" action= "DeleteShipmentServlet" class="deleteButton" > Delete </a>
                
                <a href="payment.jsp" class="updateButton" > Go to Payment </a>
    </div>
    
    
    
    </body>
</html>
