<%-- 
    Document   : EditUser
    Created on : 10/05/2022, 3:58:44 PM
    Author     : saniyakhanna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="iotbay.model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/EditUser.css">
    </head>
     <body>
        <%
            User user = (User)session.getAttribute("user");
            String update = request.getParameter("update");
        %>
       <div class="topnav">
            <a class="button1" href="HomeAdmin.jsp">Home</a>
            <a class="button1" href="Login.jsp">Logout</a> 
            <a class="button1" href="ViewUser.jsp">View Users</a>
            <a class="button1" href="CreateUser.jsp">Create New Users</a>
        </div>
  
        <div class="edit">
            <div class="editContents">
                <h2 id="title"><b>Edit User Details</b></h2>
                <form class="editForm" action="RegisterServlet" method="post">
                    <div class="registerFormElement" id="name">
                        <label>First Name</label>
                        <div>
                            <input class="border-customized-input" type="text" value="${user.firstName}" required="true" name="firstName" placeholder="First Name">
                        </div>
                    </div>
                     <div class="registerFormElement" id="name">
                        <label>Last Name</label>
                        <div>
                            <input class="border-customized-input" type="text" value="${user.LastName}" required="true" name="lastName" placeholder="Last Name">
                        </div>
                    </div>
                       <div class="registerFormElement" id="email">
                        <label>Email</label>
                        <div>
                            <input class="border-customized-input" type="text" value="${user.email}" required="true" name="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="loginFormElement" id="type">
                        <label>Type</label>
                        <div>
                        <input type="radio"  value="${user.type}" id="type" name="customer">
                            <label for="customer">Customer</label>
                       <input type="radio" id="type" name="staff">     
                            <label for="staff">Staff</label>
                    </div>
                    </div> 
                     <div class="registerFormElement" id="phone">
                        <label>Phone Number</label>
                        <div>
                            <input class="border-customized-input" type="text"  value="${user.phoneNumber}" required="true" name="phone" placeholder="Phone Number">
                        </div>
                    </div>
                    <div class="registerFormElement" id="password">
                        <label>Password</label>
                        <div>
                            <input class="border-customized-input" type="text"  value="${user.password}"required="true" name="password" placeholder="Password">
                        </div>
                    </div>
                     <div class="buttons">
                        <a href="ViewUser.jsp" class="button4">Cancel</a>
                        <a href="Confirm.jsp" class="button4">Confirm</a>
                        <a href="Activate.jsp" class="button4">Activate User</a>
                        <a href="Deactivate.jsp" class="button4">Deactivate User</a>
                        <a href="Delete.jsp" class="button4">Delete User</a>
                    </div>
               </form>
                
             <%
                if(update != null) {
                    String firstName = request.getParameter("FirstName");
                    String lastName = request.getParameter("LastName");
                    String email = request.getParameter("Email");
                    String password = request.getParameter("Password");
                    String type = request.getParameter("Type");
                    String phoneNumber = request.getParameter("PhoneNumber");
                    session.setAttribute("user", user);
                }
            %>               
                
            </div>
          </div>
    </body>
</html>

