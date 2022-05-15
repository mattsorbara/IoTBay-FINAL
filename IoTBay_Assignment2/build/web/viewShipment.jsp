<%-- 
    Document   : trackOrder
    Created on : 09/05/2022, 2:23:33 PM
    Author     : tanya
--%>

<%@page import="iotbay.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>IoTBay | Shipment Details</title>
        <link rel="stylesheet" href="static/css/viewShipment.css"/>
        
    </head>
        
     

     <% 
        Savedshipment savedShipment = (Savedshipment) session.getAttribute("savedShipment");
        Order currentOrder = (Order) session.getAttribute("currentOrder");
        String anythingSaved = (String) session.getAttribute("anythingSavedShipment");
    %>
    
    
    <body>
        
        
        <div class="loggedInMenu">            
        
            <a href="welcome.jsp" class="updateButton">Main</a>
           
            <a href="LogoutServlet" class="deleteButton" id="logout">Logout</a>
        </div>
        
        <h1>View your Order Shipment Details: </h1>
        <h2> The shipment details linked to your order are shown below. 
            Choose from one of the listed options to make any changes to your shipment details. </h2>
        
        
        <div class="shipment">
            <div class="shipmentcontents">
                <h2 id="title"><b>Shipment Summary</b></h2> 
                <form class="shipmentForm" action="updateShipment.jsp" method="post">
             
                   
                    <div class="shipmentFormElement" id="shipmentMethod">
                        <label>Shipment Option</label>
                        <div>
                            <!--<input class="border-customized-input" type="text" required="true" name="paymentMethod">-->
                            <select name="savedshipment">
                                <option value="false">New Shipment</option>
                                <% 
                                if (!"guest@guest.com".equals(currentOrder.getUserEmail())) {
                                    if (anythingSaved.equals("true")) {
                                %>
                                <option value="true">Saved Shipment</option>
                                <%   }
                               }%>
                            </select>
                        </div>
                    </div>
                            
                    <div class="buttons">
                  <!--THIS NOT WORKING -->   <button href="updateShipment.jsp" class="updateButton" type="submit">Update Shipment</button>
                        <button href="home.jsp" class="deleteButton">Cancel</button>
                    </div>
            </div> 
        
                </form><br><br>
                <% 
                    if (!"guest@guest.com".equals(currentOrder.getUserEmail())){
                %>   
                
                    <div class="shipmentcontents">
                     
                <h2 id="title"><b>Saved Shipment Details</b></h2><br>
                <table id="ShipmentTable" align="center">
                   
                        <tr>
                            <th>Shipment Method</th>
                            <td><%= savedShipment.getShipmethod() %></td>
                        </tr>
                        <tr>
                            <th>Unit Number</th>
                            <td><%= savedShipment.getUnitno() %></td>
                        </tr> 
                        <tr>
                            <th>Street Number</th>
                            <td><%= savedShipment.getStreetno() %></td>
                        </tr> 
                        <tr>
                            <th>Street Name</th>
                            <td><%= savedShipment.getStreetname() %></td>
                        </tr>
                        <tr>
                            <th>Suburb</th>
                            <td><%= savedShipment.getSuburb() %></td>
                        </tr> 
                        <tr>
                            <th>Postcode</th>
                            <td><%= savedShipment.getPostcode() %></td>
                        </tr> 
                </table>
                         <%  }
                                %>
                </div> 
              </div> 
            
       
                <div class="buttons">
                <a href="index.html" class="updateButton" > Back </a> 
                
                
              <a href="deleteShipment.jsp" action= "DeleteShipmentServlet" class="deleteButton" > Delete </a>
                
                <a href="payment.jsp" class="updateButton" > Go to Payment </a>
    </div>
    
    </body>
</html>
