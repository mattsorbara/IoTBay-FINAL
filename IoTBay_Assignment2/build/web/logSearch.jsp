<%@page import="iotbay.model.User"%>
<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log Search | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/logSearch.css">
    </head>
    <% 
        User user = (User) session.getAttribute("user");
        String logErr = (String) session.getAttribute("logSearchError");
        session.setAttribute("logSearchError", "");
    %>
    
    
    <body>
        <% if ("guest@guest.com".equals(user.getEmail())) { %>
        <jsp:include page="guestHeader.jsp"/>
        
        <% } else { %>
        <jsp:include page="header.jsp"/>
        <% } %>
        <div class="log">
            <div class="logContents">
                <h2 id="title"><b>User Access Logs</b></h2>
                <hr>
                <p style="color: red; text-align: center; margin-bottom: 0"><%=logErr != null ? logErr : ""%></p>
                <form action="LogServlet" method="post" id="logForm">
                    <label>From:</label>
                    <input type="datetime-local" name="from" value="2022-05-01T00:00">
                    <label>To:</label>
                    <input type="datetime-local" name="to" value="2022-06-01T00:00">
                    <button type="submit" class="submit">Search</button>
                </form>

            </div>
        </div>

    </body>


</html>
