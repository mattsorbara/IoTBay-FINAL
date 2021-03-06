<%-- 
    Document   : welcome
    Created on : 30 Apr 2022, 9:52:39 am
    Author     : matthewsorbara
--%>
<%@page import="iotbay.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/welcome.css">
    </head>
    
    <%
        User user = (User) session.getAttribute("user");
        if (user.getEmail().equals("guest@guest.com")) {
            System.out.println("here");
            request.getRequestDispatcher("LogoutServlet").include(request, response);
        }
    %>
    
    <% if (!user.getEmail().equals("guest@guest.com")) { %>
    
    <body>
        <div class="loggedInMenu">            
            <a href="welcome.jsp" class="button">Main</a>
            <a href="LogoutServlet" class="button" id="logout">Logout</a>
        </div>

        <div class="welcome">
            <div class="welcomeContents">
                <div class="userInfo">
                    <h5 id="userLabel">You are logged in as:</h5>
                    <h3 id="user"><b>${user.email}</b></h3>
                </div>
                <hr>
                <div class="buttons">
                    <a href="edit.jsp" class="submit">Edit</a>
                </div>
                <div class="buttons">
                    <a href="logSearch.jsp" class="submit">Search User Access Logs</a>
                </div>
                <div class="buttons">
                    <a href="CatalogueServlet" class="submit">Browse Catalogue</a>
                </div>
                <div class="buttons">
                    <a href="orderSearch.jsp" class="submit">Search Order Logs</a>
                </div>                
                <div class="buttons">
                    <a href="shipmentSearch.jsp" class="submit">Search Shipment Logs</a>
                </div>
                <div class="buttons">
                    <a href="paymentSearch.jsp" class="submit">Search Payment Logs</a>
                </div>   
            </div>
        </div>
    </body>
    
    <% } %>
</html>
