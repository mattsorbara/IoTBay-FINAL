<%-- 
    Document   : viewLogs
    Created on : 1 May 2022, 8:03:22 pm
    Author     : matthewsorbara
--%>
<%@page import="java.sql.*" %>
<%@page import="iotbay.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payments Search | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/viewOrders.css">
    </head>

           <%  
                HttpSession current_session = request.getSession();
                ResultSet rows = (ResultSet) current_session.getAttribute("paymentLogRows");
                String requestType = (String) request.getParameter("id");

                String from = (String) current_session.getAttribute("from");
                String to = (String) current_session.getAttribute("to");
                
                User user = (User) current_session.getAttribute("user");
                
//                String orderIDSearch = (String) current_session.getAttribute("orderIDSearch");
            %>
    <body>
        
        <% if ("guest@guest.com".equals(user.getEmail())) { %>
        <jsp:include page="guestHeader.jsp"/>
        
        <% } else { %>
        <jsp:include page="header.jsp"/>
        <% } %>
        
        <div class="log">
            <div class="logContents">
                <h2 id="title"><b>Payment ID Search</b></h2>
                <hr>
                <% if (requestType.equals("2")) {  %>
                <table id="logTable">
                   <tr>
                        <th class="col">Payment ID</th>
                        <th class="col">Email</th>
                        <th class="col">Paid Amount</th>
                        <th class="col">Card Number</th>
                        <th class="col">Card CVC</th>
                        <th class="col">Card Expiry</th>
                        <th class="col">Paid Date</th>                        
                    </tr>
                    <% while(rows.next()){ %>
                        <tr>
                            <td><%=rows.getString(1)%></td>
                            <td><%=rows.getString(3)%></td>
                            <td><%=rows.getString(5)%></td>
                            <td><%=rows.getString(6)%></td>
                            <td><%=rows.getString(7)%></td>
                            <td><%=rows.getString(8)%></td>
                            <td><%=rows.getString(9)%></td>
                            <%}%>
                        </tr>
                
                <% } else { %>
                
                <h5 id="dates">From: <%=from%> | To: <%=to%></h5>
                <table id="logTable">
                    <tr>
                        <th class="col">Payment ID</th>
                        <th class="col">Email</th>
                        <th class="col">Paid Amount</th>
                        <th class="col">Card Number</th>
                        <th class="col">Card CVC</th>
                        <th class="col">Card Expiry</th>
                        <th class="col">Paid Date</th>                        
                    </tr>
                    <% while(rows.next()){ %>
                        <tr>
                            <td><%=rows.getString(1)%></td>
                            <td><%=rows.getString(3)%></td>
                            <td><%=rows.getString(5)%></td>
                            <td><%=rows.getString(6)%></td>
                            <td><%=rows.getString(7)%></td>
                            <td><%=rows.getString(8)%></td>
                            <td><%=rows.getString(9)%></td>
                            <%}%>
                        </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </body>
</html>
