<%-- 
    Document   : EditUser
    Created on : 10/05/2022, 3:58:44 PM
    Author     : saniyakhanna
--%>

<%@page import="iotbay.model.dao.DBManager"%>
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
            DBManager manager = (DBManager) session.getAttribute("manager");
            System.out.println(request.getParameter("adminSubmit"));
            String retrievedEmail = request.getParameter("adminSubmit");
            User user = manager.findUserEmail(retrievedEmail);
            session.setAttribute("targetUser", user);
            String update = request.getParameter("update");
            System.out.println(user.getEmail());
        %>
       <div class="topnav">
            <a class="button1" href="HomeAdmin.jsp">Home</a>
            <a class="button1" href="login.jsp">Logout</a> 
            <a class="button1" href="ViewUser.jsp">View Users</a>
            <a class="button1" href="CreateUser.jsp">Create New Users</a>
        </div>
  
        <div class="edit">
            <div class="editContents">
                <h2 id="title"><b>Edit User Details</b></h2>
                <h4 class="message"><span class="message"><%=(update != null ? update : "")%></span></h4>
                <form class="editForm" action="UpdateAdminServlet" method="post">
                    <div class="registerFormElement" id="name">
                        <label>Full Name</label>
                        <div>
                            <input class="border-customized-input" type="text" value="<%=user.getName()%>" required="true" name="name" placeholder="Full Name">
                        </div>
                    </div>
                       <div class="registerFormElement" id="email">
                        <label>Email</label>
                        <div>
                            <input class="border-customized-input" type="text" value="<%=user.getEmail()%>" required="true" name="email" placeholder="Email">
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
                     <div class="registerFormElement" id="phone">
                        <label>Phone Number</label>
                        <div>
                            <input class="border-customized-input" type="text"  value="<%=user.getPhone()%>" required="true" name="phone" placeholder="Phone Number">
                        </div>
                    </div>
                    <div class="registerFormElement" id="password">
                        <label>Password</label>
                        <div>
                            <input class="border-customized-input" type="text"  value="<%=user.getPassword()%>"required="true" name="password" placeholder="Password">
                        </div>
                    </div>
                     <div class="buttons">
                        <a href="ViewUser.jsp" class="button4">Cancel</a>
                        <a class="button4"><button type="submit" class="button4">Confirm</button></a>
                        <a href="ActivateAdminServlet" class="button4">Activate User</a>
                        <a href="DeactivateAdminServlet" class="button4">Deactivate User</a>
                        <a href="DeleteAdminServlet" class="button4">Delete User</a>
                    </div>
               </form>                
            </div>
          </div>
    </body>
</html>

