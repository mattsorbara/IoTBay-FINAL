<%-- 
    Document   : findShipment
    Created on : 14/05/2022, 11:09:21 PM
    Author     : tanya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--

-->
<html>
    <head>
        <title>Shipment Management</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="static/css/searchShipment.css"/>
    </head>
   <% 
        String shipmentViewError = (String) session.getAttribute("shipmentViewError");
     /*   User user = (User) session.getAttribute("user"); - this always causes a run error*/
    %>
    <body>
        <div class="loggedInMenu">            
            <a href="welcome.jsp" class="button">Main</a>
            <a href="LogoutServlet" class="button" id="logout">Logout</a>
        </div>

        <br>
        <br>
        <br>
        
            <div class="SMhomeContents">
               <div class="trackOrderID"> 
                    <h3 id="title">Search for your shipment here:</h3> 
                    <br>
                   <!-- order id input will come here--> 
                  <!--" method="post"> this will be made into a form later-->
                   
                   <form action="searchShipmentServlet" method="post" id="shipSearchForm">
                
                    <div>
                        <label>From:</label>
                        <input type="datetime-local" name="from" value="2021-04-01T00:00">
                        <label>To:</label>
                        <input type="datetime-local" name="to" value="2022-05-01T00:00">
                        <button  value="1" type="submit" class="searchbutton">Search by Date</button>
                    </div><br/>
                
                    <div>
                        <label>Shipment ID:</label>
                        <input type="text" name="shipmentIDSearch">
                        <button  value="2" type="submit" class="searchbutton">Search by Shipment ID</button>
                    </div>
                </form>
                  
                </div>
               
            </div>
        <button class="backbutton" href=""> Back </button>
    </body>
</html>

