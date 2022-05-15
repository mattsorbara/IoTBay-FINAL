<%-- 
    Document   : UserRecord
    Created on : 08/05/2022, 4:50:43 PM
    Author     : saniyakhanna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New User | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/CreateUser.css">
    </head>
     <body>
       <div class="topnav">
            <a class="button1" href="HomeAdmin.jsp">Home</a>
<<<<<<< Updated upstream
            <a class="button1" href="login.jsp">Logout</a> 
            <a class="button1" href="ViewUser.jsp">View Users</a>
=======
            <a class="button1" href="LogoutServlet">Logout</a> 
            <a class="button1" href="ViewUserAdminServlet">View Users</a>
>>>>>>> Stashed changes
            <a class="button1" href="CreateUser.jsp">Create New Users</a>
        </div>
        <div class="login">
            <div class="loginContents">
                <h2 id="title"><b>Create New User</b></h2>
                <form class="loginForm" action="RegisterAdminServlet" method="post">
                    <div class="loginFormElement" id="name">
                        <label>Full Name</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="name" placeholder="Full Name">
                        </div>
                    </div>
                    <div class="loginFormElement" id="email">
                        <label>Email</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="loginFormElement" id="type">
                        <label>Type</label>
                       <div>
                       <input type="radio" id="type" name="type" value="customer">
                              <label for="customer">Customer</label>
                       <input type="radio" id="type" name="type" value="staff">     
                               <label for="staff">Staff</label>
                    </div>
                    </div>    
                    <div class="loginFormElement" id="password">
                        <label>Temporary Password</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="password" placeholder="Temporary Password">
                        </div>
                    </div> 
                     <div class="loginFormElement" id="phone">
                        <label>Phone Number</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="phone" placeholder="Phone Number">
                        </div>
                    </div>
                    <div class="buttons">
                        <a href="HomeAdmin.jsp" class="cancel">Cancel</a>
                        <button class="button2" type="submit">Create</button>
                    </div>
                </form>
            </div>
            </div>          
    </body>
</html>