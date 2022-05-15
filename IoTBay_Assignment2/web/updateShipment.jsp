<%-- 
    Document   : updateShipment
    Created on : 10/05/2022, 11:09:38 AM
    Author     : tanya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>IoTBay | Update Shipment Details</title>
        
        <link rel="stylesheet" href="static/updateShipment.css"/>
    </head>
    
     <body>
        <div class="update">
            <div class="updateContents">
                <h2><b>Update Shipment Details</b></h2>
                <br>
                <form class="updateShipForm" action="UpdateShipmentServlet" method="post">
                    
                    
                    <div class="updateShipForm" >
                    <label for="shipmethod"> Shipment Method </label>
                    
                    <select name="shipmethod" id="shipmethod">
                    <option value="standard"> Standard Delivery </option>
                    <option value="express"> Express Delivery </option>
                    <option value="premium"> Premium Delivery </option>
                    </select>
                   
                    </div>
                    <div class="updateShipForm" id="unitno">
                        <label>Unit Number</label>
                        <div>
                            <input class="border-customized-input" type="text"  name="unitno" placeholder="Unit Number">
                        </div>
                    </div>
                    
                    <div class="updateShipForm" id="streetno">
                        <label>Street Number</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="streetno" placeholder="Street Number ">
                        </div>
                    </div>
                    
                    <div class="updateShipForm" id="streetname">
                        <label>Street Name</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="streetname" placeholder="Street Name">
                        </div>
                    </div>
                    <div class="updateShipForm" id="suburb">
                        <label>Suburb</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="suburb" placeholder="Suburb">
                        </div>
                    </div>
                    
                    <div class="updateShipForm" id="postcode">
                        <label>Postcode</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="postcode" placeholder="Postcode">
                        </div>
                    </div>
                    <div class="buttons">
                        <a href="viewShipment.jsp" class="cancel">Cancel</a>
                        
                        <button href="viewShipment.jsp" class="updatebutton" type ="submit" > Update </button> 
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
