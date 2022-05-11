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
    </head>
    <body>
        <h1>View your Order Shipment Details</h1>
        <h2> The shipment details linked to your order are shown below. 
            Choose from one of the listed options to make any changes to your shipment details. </h2>
         <div class="infoTable">
             <h3 class="infoTableHeader"> Your Current Shipment Details </h3>
             <br> 
             <h3 class="shipDetails"> Shipment ID: <!-- to be done--> </h3> 
             <br> 
             <h3 class="shipDetails"> Shipment Status: <!-- to be done--> </h3> 
             <br> 
             <h3 class="shipDetails"> Shipment Method: <!-- to be done--> </h3> 
             <br> 
             <h3 class="shipDetails"> Shipment Date:<!-- to be done--> </h3> 
             <br> 
             <h3 class="shipDetails"> Unit Number: <!-- to be done--> </h3> 
             <br> 
             <h3 class="shipDetails"> Street Number: <!-- to be done--> </h3> 
             <br> 
             <h3 class="shipDetails"> Street Name <!-- to be done--> </h3> 
             <br> 
             <h3 class="shipDetails"> Suburb: <!-- to be done--> </h3> 
             <br> 
             <h3 class="shipDetails"> Postcode: <!-- to be done--> </h3> 
       
         </div> 
    <div class="buttons">
                <a href="index.html" class="deleteButton" > Back </a> <!-- do we want this button-->
                <a href="updateShipment.jsp" class="updateButton"> Update </a>
                <a href="deleteShipment.jsp" class="deleteButton" > Delete </a>
                <a href="payment.jsp" class="updateButton" > Go to Payment </a>
    </div>
    
    
    
    </body>
</html>
