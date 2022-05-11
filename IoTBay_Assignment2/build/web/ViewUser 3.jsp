<%-- 
    Document   : ViewUser
    Created on : 08/05/2022, 3:34:42 PM
    Author     : saniyakhanna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View User Records</title>
        <link rel="stylesheet" href="static/css/ViewUser.css">
    </head>
    <%
        // get confirmation and error messages from session
        String msgUpdate = (session.getAttribute("msgUpdate") != null) ? (String) session.getAttribute("msgUpdate") : "";
        String msgCreate = (session.getAttribute("msgCreate") != null) ? (String) session.getAttribute("msgCreate") : "";
        String msgDelete = (session.getAttribute("msgDelete") != null) ? (String) session.getAttribute("msgDelete") : "";
        String userDeleted = (session.getAttribute("userDeleted") != null) ? (String) session.getAttribute("userDeleted") : "";
        // clear confirmation and error messages so does not show again
        session.setAttribute("msgUpdate", "");
        session.setAttribute("msgCreate", "");
        session.setAttribute("msgDelete", "");
        session.setAttribute("userDeleted", "");
    %>
    <body>
       <div class="topnav">
        <a class="button1" href="Home.jsp">Home</a>
        <a class="button1" href="Login.jsp">Logout</a> 
        <a class="button1" href="ViewUser.jsp">View Users</a>
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
                    <input type="text" id="inputFName" class="searchbox" onkeyup="filterTable()" placeholder="First name" title="Type in a name">
                    <input type="text" id="inputLName" class="searchbox" onkeyup="filterTable()" placeholder="Last name.." title="Type in a name">
                    <input type="text" id="inputPhone" class="searchbox" onkeyup="filterTable()" placeholder="Phone number" title="Type in a name">
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
                    <table id="CTable">
                        <thead>
                            <tr><td colspan="2"><h2>Customers</h2></td></tr>
                            <tr>
                                <th colspan="2">Update</th>
                                <th>Type</th>
                                <th>Email</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Phone number</th>
                                <th>Password</th>
                            </tr>
                        </thead>
                    </table>
                        <table id="STable"> <thead>
                            <tr><td colspan="2"><h2>Staff</h2></td></tr>
                            <tr>
                                <th colspan="2">Update</th>
                                <th>Type</th>
                                <th>Email</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Phone number</th>
                                <th>Password</th>
                                <th>Manager</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>