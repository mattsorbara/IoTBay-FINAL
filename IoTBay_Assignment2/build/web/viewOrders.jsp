<%-- 
    Document   : viewLogs
    Created on : 1 May 2022, 8:03:22 pm
    Author     : matthewsorbara
--%>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders Search | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/viewLogs.css">
    </head>

           <%  
                HttpSession current_session = request.getSession();
                ResultSet rows = (ResultSet) current_session.getAttribute("orderLogRows");

                String from = (String) current_session.getAttribute("from");
                String to = (String) current_session.getAttribute("to");
                String orderIDSearch = (String) current_session.getAttribute("orderIDSearch");
                
            %>
    <body>
        <div class="loggedInMenu">            
            <a href="welcome.jsp" class="button">Main</a>
            <a href="LogoutServlet" class="button" id="logout">Logout</a>
        </div>
        <div class="log">
            <div class="logContents">
                <h2 id="title"><b>Order ID Search</b></h2>
                <hr>
                <% if (orderIDSearch != null) {  %>
                <h5 id="dates">Order ID: <%=orderIDSearch%></h5>
                <table id="logTable">
                    <tr>
                        <th class="col">OrderID</th>
                        <th class="col">User Email</th>
                        <th class="col">Order Price</th>
                        <th class="col">Order Quantity</th>
                        <th class="col">Order Date</th>
                        <th class="col">Order Status</th>
                    </tr>
                    <% while(rows.next()){ %>
                        <tr>
                            <td><%=rows.getString(2)%></td>
                            <td><%=rows.getString(3)%></td>
                            <td><%=rows.getString(4)%></td>
                        </tr>
                    <% } %>
                
                <% } else { %>
                
                <h5 id="dates">From: <%=from%> | To: <%=to%></h5>
                <table id="logTable">
                    <tr>
                        <th class="col">Email</th>
                        <th class="col">Login Time</th>
                        <th class="col">Logout Time</th>
                    </tr>
                </table>
                    <% while(rows.next()){ %>
                        <tr>
                            <td><%=rows.getString(2)%></td>
                            <td><%=rows.getString(3)%></td>
                            <td><%=rows.getString(4)%></td>
                        </tr>
                    <%      } 
                        }
                    %>
                </table>

            </div>
        </div>
    </body>
</html>
