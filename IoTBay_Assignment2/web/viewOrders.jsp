<%@page import="java.sql.*" %>
<%@page import="iotbay.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders Search | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/viewOrders.css">
    </head>

           <%  
                HttpSession current_session = request.getSession();
                ResultSet rows = (ResultSet) current_session.getAttribute("orderLogRows");
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
                <h2 id="title"><b>Order ID Search</b></h2>
                <hr>
                <% if (requestType.equals("2")) {  %>
                <table id="logTable">
                   <tr>
                        <th class="col">OrderID</th>
                        <th class="col">Email</th>
                        <th class="col">Amount</th>
                        <th class="col">Quantity</th>
                        <th class="col">Date</th>
                        <th class="col">Status</th>
                    </tr>
                    <% while(rows.next()){ %>
                        <tr>
                            <td><%=rows.getString(1)%></td>
                            <td><%=rows.getString(2)%></td>
                            <td><%=rows.getString(4)%></td>
                            <td><%=rows.getString(5)%></td>
                            <td><%=rows.getString(6)%></td>
                            <td><%=rows.getString(7)%></td>
                            <% if (rows.getString(7).equals("SAVED")) {%>
                            <td><a href="EditOrderServlet?id=<%=rows.getString(1)%>"><button class="submit">Edit Order</button></a></td>
                            <td/>
                            <%}%>
                            <% if (rows.getString(7).equals("SUBMITTED") || rows.getString(7).equals("SAVED")){%>
                            <td/>
                            <form action="CancelOrderServlet" method="post">
                                <td><button name="id" value="<%=rows.getString(1)%>" class="cancel">Cancel Order</button></a></td>
                            </form>
                            <%}%>
                        </tr>
                    <% } %>
                
                <% } else { %>
                
                <h5 id="dates">From: <%=from%> | To: <%=to%></h5>
                <table id="logTable">
                    <tr>
                        <th class="col">OrderID</th>
                        <th class="col">Email</th>
                        <th class="col">Amount</th>
                        <th class="col">Quantity</th>
                        <th class="col">Date</th>
                        <th class="col">Status</th>
                    </tr>
                    <% while(rows.next()){ %>
                        <tr>
                            <td><%=rows.getString(1)%></td>
                            <td><%=rows.getString(2)%></td>
                            <td><%=rows.getString(4)%></td>
                            <td><%=rows.getString(5)%></td>
                            <td><%=rows.getString(6)%></td>
                            <td><%=rows.getString(7)%></td>
                            <% if (rows.getString(7).equals("SAVED")) {%>
                            <td><a href="EditOrderServlet?id=<%=rows.getString(1)%>"><button class="submit">Edit Order</button></a></td>
                            <td/>
                            <%}%>
                            <% if (rows.getString(7).equals("SUBMITTED") || rows.getString(7).equals("SAVED") || rows.getString(7).equals("SHIPPED")){%>
                            <form action="CancelOrderServlet" method="post">
                                <td><button name="id" value="<%=rows.getString(1)%>" class="cancel">Cancel Order</button></a></td>
                            </form>
                            <%}%>
                        </tr>
                    <%      } 
                        }
                    %>
                </table>
            </div>
        </div>
        <div id="invisDiv"/>
    </body>
</html>
