<%-- 
    Document   : ViewUser
    Created on : 08/05/2022, 3:34:42 PM
    Author     : saniyakhanna
--%>

<%@ page import="java.sql.*" %>
<%@page import="iotbay.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>




<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View User Records</title>
        <link rel="stylesheet" href="static/css/ViewUser.css">
    </head>
    <%
        String msgUpdate = (session.getAttribute("msgUpdate") != null) ? (String) session.getAttribute("msgUpdate") : "";
        String msgCreate = (session.getAttribute("msgCreate") != null) ? (String) session.getAttribute("msgCreate") : "";
        String msgDelete = (session.getAttribute("msgDelete") != null) ? (String) session.getAttribute("msgDelete") : "";
        String userDeleted = (session.getAttribute("userDeleted") != null) ? (String) session.getAttribute("userDeleted") : "";
    
        session.setAttribute("msgUpdate", "");
        session.setAttribute("msgCreate", "");
        session.setAttribute("msgDelete", "");
        session.setAttribute("userDeleted", "");
        
       
        HttpSession current_session = request.getSession();
        ArrayList<User> rows = (ArrayList<User>)current_session.getAttribute("users");

     %>


    
    <body action="ViewUserAdmin">

       <div class="topnav">
        <a class="button1" href="HomeAdmin.jsp">Home</a>
<<<<<<< Updated upstream
        <a class="button1" href="Login.jsp">Logout</a> 
        <a class="button1" href="ViewUser.jsp">View Users</a>
=======
        <a class="button1" href="LogoutServlet">Logout</a> 
        <a class="button1" href="ViewUserAdminServlet">View Users</a>
>>>>>>> Stashed changes
        <a class="button1" href="CreateUser.jsp">Create New Users</a>
        </div>
       </div> 
        <div class="column1">
            <div class="view">
                <div>
                    <h1> View User Record </h1>
                    <p class ="success" align="center"><%= (msgUpdate != null) ? msgUpdate : ""%></p>
                    <p class ="success" align="center"><%= (msgCreate != null) ? msgCreate : ""%></p>
                    <p class ="success" align="center"><%= (msgDelete != null) ? msgDelete : ""%></p>
                    <p class="error" align="center">${userDeleteErr}</p>
                </div>
                <div>
                    <input type="text" id="inputFullName" class="searchbox" onkeyup="filterTable()" placeholder="Full name" title="Type in a name">
                    <input type="text" id="inputPhoneNumber" class="searchbox" onkeyup="filterTable()" placeholder="Phone number" title="Type in number">
                  <select id="inputType" onchange="filterTable()" >
                        <option value="all">View All</option>
                        <option value="customer">Only Customers</option>
                        <option value="staff">Only Staff</option>
                    </select>
                    <form class="inline" method="get" action="CreateUser.jsp">
                        <input type="hidden" name="userType" value="user" />
                        <button class="button2" type="submit">Add New User</button>
                    </form>
                </div>
                
                <div class="tablewrap">
                    <table class="UserTable">
                        <thead>
                            <tr><td colspan="2"><h2>Users</h2></td></tr>
                            <tr>                            
                                <th>Email</th>
                                <th>User Type</th>
                                <th>Full Name</th>
                                <th>Phone number</th>
                                <th>Password</th>
                                <th>Edit User Details</th>
                                    
                            </tr>   
                          </thead>
                    <tbody>
                        <% for (User u : rows) {%>
                        <tr>
                            <td><%=u.getEmail()%></td>
                            <td><%=u.getUserType()%></td>
                            <td><%=u.getName()%></td>
                            <td><%=u.getPhone()%></td>
                            <td><%=u.getPassword()%></td>
                            <td><form method="post" action="EditUser.jsp"><button type="submit" name="adminSubmit" value="<%=u.getEmail()%>">Edit User</button></form></td>
                        </tr>
                            <% } %>
        </tbody>
 </table>
                </div>
            </div>
        </div>
    </body>
</html>