<%-- 
    Document   : EditUser
    Created on : 10/05/2022, 3:58:44 PM
    Author     : saniyakhanna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/EditUser.css">
    </head>
     <body>
       <div class="topnav">
            <a class="button1" href="Home.jsp">Home</a>
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
                        <input type="radio" id="type" name="type">
                            <label for="customer">Customer</label>
                       <input type="radio" id="type" name="type">     
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
            </div>
          </div>
    </body>
</html>