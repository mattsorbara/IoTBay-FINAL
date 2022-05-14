<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log Search | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/logSearch.css">
    </head>

    <body>
        <div class="loggedInMenu">            
            <a href="welcome.jsp" class="button">Main</a>
            <a href="LogoutServlet" class="button" id="logout">Logout</a>
        </div>
        <div class="log">
            <div class="logContents">
                <h2 id="title"><b>Order Search</b></h2>
                <hr>
                <form action="OrderSearchServlet" method="post" id="orderSearchForm">
                    <label>From:</label>
                    <input type="datetime-local" name="from" value="2022-05-01T00:00">
                    <label>To:</label>
                    <input type="datetime-local" name="to" value="2022-06-01T00:00">
                    <label>Order ID:</label>
                    <input type="text" name="orderIDSearch">
<!--                    <label>Payment ID:</label>
                    <input type="text" name="paymentIDSearch" value="2022-06-01T00:00">
                    <label>Shipment ID:</label>
                    <input type="text" name="shipmentIDSearch" value="2022-05-01T00:00">-->
                    <button type="submit" class="submit">Search</button>
                </form>
            </div>
        </div>

    </body>


</html>
