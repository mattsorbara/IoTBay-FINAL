<%-- 
    Document   : viewLogs
    Created on : 1 May 2022, 8:03:22 pm
    Author     : matthewsorbara
--%>
<%@page import="iotbay.model.*"%>
<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Logs | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/viewLogs.css">
    </head>

           <%  
                HttpSession current_session = request.getSession();
                ResultSet rows = (ResultSet)current_session.getAttribute("userLogRows");

                String from = (String)current_session.getAttribute("from");
                String to = (String)current_session.getAttribute("to");
                User user = (User) current_session.getAttribute("user");

            %>
        <% if ("guest@guest.com".equals(user.getEmail())) { %>
        <jsp:include page="guestHeader.jsp"/>
        
        <% } else { %>
        <jsp:include page="header.jsp"/>
        <% } %>
            
    <body>
        <div class="log">
            <div class="logContents">
                <h2 id="title"><b>User Access Logs</b></h2>
                <hr>
                <h5 id="dates">From: <%=from%> | To: <%=to%></h5>
                <table id="logTable">
                    <tr>
                        <th class="col">Email</th>
                        <th class="col">Login Time</th>
                        <th class="col">Logout Time</th>
                    </tr>
                    <% while(rows.next()){ %>
                        <tr>
                            <td><%=rows.getString(2)%></td>
                            <td><%=rows.getString(3)%></td>
                            <td><%=rows.getString(4)%></td>
                        </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </body>
</html>
