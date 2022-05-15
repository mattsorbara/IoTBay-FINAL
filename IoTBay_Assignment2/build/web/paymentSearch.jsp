<%@ page import="java.sql.*" %>
<%@page import="iotbay.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log Search | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/orderSearch.css">
    </head>
    <% 
        String paymentViewError = (String) session.getAttribute("paymentViewError");
        User user = (User) session.getAttribute("user");
    %>
    <body>
        <div class="loggedInMenu">            
            <a href="welcome.jsp" class="button">Main</a>
            <a href="LogoutServlet" class="button" id="logout">Logout</a>
        </div>
        <div class="log">
            <div class="logContents">
                <h2 id="title"><b>Payment Search</b></h2>
                <p style="color: red; text-align: center; margin-bottom: 0">${paymentViewError}</p>
                <hr>
                <form action="PaymentSearchServlet" method="post" id="orderSearchForm">
                <% 
                    if (!"guest@guest.com".equals(user.getEmail())){
                            %>
                    <div class="searchAttribute">
                        <label>From:</label>
                        <input type="datetime-local" name="from" value="2022-05-01T00:00">
                        <label>To:</label>
                        <input type="datetime-local" name="to" value="2022-06-01T00:00">
                        <button name="id" value="1" type="submit" class="submit">Search by Date</button>
                    </div><br/>
                <%
                    }
                %>
                    <div class="searchAttribute">
                        <label>Payment ID:</label>
                        <input type="text" name="paymentIDSearch">
                        <button name="id" value="2" type="submit" class="submit">Search by Payment ID</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
