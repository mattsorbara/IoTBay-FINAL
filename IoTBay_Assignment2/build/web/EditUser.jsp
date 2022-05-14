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
         
        <div class="card">
        <h1>Edit profile <span> <%= (update!= null) ? update:"" %> </span> </h1>
        <form method="post" method="get" action="EditUser.jsp">
            <table>
                <tr><td>Email</td><td><input type="text" value="${user.email}" name="Email"></td></tr>
                <tr><td>First Name</td><td><input type="text" value="${user.firstName}" name="FirstName"></td></tr>
                <tr><td>Last Name</td><td><input type="text" value="${user.lastName}" name="LastName"></td></tr>
                <tr><td>Password</td><td><input type="password" value="${user.password}" name="Password"></td></tr>
                <tr><td>Edit</td><td><input type="radio" id="type" value="${user.type}" name="type"> <label for="custoemr">Customer</label><br> <input type="radio" id="type" name="type"><label for="Staff">Staff</label><br></td></tr>
                <tr><td>Phone Number</td><td><input type="text" value="${user.phoneNumber}" name="PhoneNumber"></td></tr>   
            </table>
        </div>
        </form>   
        <div class="edit">
            <div class="editContents">
                <h2 id="title"><b>Edit User Details</b></h2>
                <form class="editForm" action="RegisterServlet" method="post">
                    <div class="registerFormElement" id="name">
                        <label>First Name</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="name" placeholder="Full Name">
                        </div>
                    </div>
                       <div class="registerFormElement" id="email">
                        <label>Email</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="loginFormElement" id="type">
                        <label>Type</label>
                        <div>
                        <input type="radio" id="type" name="customer">
                            <label for="customer">Customer</label>
                       <input type="radio" id="type" name="staff">     
                            <label for="staff">Staff</label>
                    </div>
                    </div> 
                     <div class="registerFormElement" id="phone">
                        <label>Phone Number</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="phone" placeholder="Phone Number">
                        </div>
                    </div>
                    <div class="registerFormElement" id="password">
                        <label>Password</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="password" placeholder="Password">
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
                    String gender = request.getParameter("gender");
                    String unitNumber = request.getParameter("UnitNumber");
                    String streetAddress = request.getParameter("StreetAddress");
                    String city = request.getParameter("City");
                    String state = request.getParameter("State");
                    String postCode = request.getParameter("PostCode");
                    String phoneNumber = request.getParameter("PhoneNumber");
                    user = new User(firstName, lastName, email, password, type, phoneNumber);
                    session.setAttribute("user", user);
                }
            %>               
                
            </div>
          </div>
    </body>
</html>