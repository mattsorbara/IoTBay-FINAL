<%-- 
    Document   : register
    Created on : 29 Apr 2022, 5:39:23 pm
    Author     : matthewsorbara
--%>

<%@page import="iotbay.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/edit.css">
    </head>
     <body>
        <%
            User user = (User) session.getAttribute("user");
            String updated = (String) session.getAttribute("updated");
        %>
        <div class="loggedInMenu">            
            <a href="welcome.jsp" class="button">Main</a>
            <a href="LogoutServlet" class="button" id="logout">Logout</a>
        </div>
        <div class="edit">
            <div class="editContents">
            
                <h2 id="title"><b>Update Details</b></h2>
                <hr>
                <h4 class="message"><span class="message"><%=(updated != null ? updated : "")%></span></h4>
                <form class="editForm" action="UpdateServlet" method="post">
                    <div class="editFormElement" id="name">
                        <label><b>Full Name</b></label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="name" value="${user.name}">
                        </div>
                    </div>
                    <div class="editFormElement" id="email">
                        <label><b>Email:</b> ${user.email}</label>
                        <input type="hidden" name="email" value="${user.email}">
                    </div>
                    <div class="editFormElement" id="password">
                        <label><b>Password</b></label>
                        <div>
                            <input class="border-customized-input" type="password" required="true" name="password" value="${user.password}">
                        </div>
                    </div>
                    <div class="editFormElement" id="phone">
                        <label><b>Phone</b></label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="phone" value="${user.phone}">
                        </div>
                    </div>
                    <div class="editFormElement" id="type">
                        <label><b>User Type:</b> ${user.userType}</label>
                        <input type="hidden" name="type" value="${user.userType}">
                    </div>
                    <div class="buttons">
                        <a href="DeleteServlet" class="cancel">Cancel Registration</a>
                        <button class="submit" type="submit">Update User</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
