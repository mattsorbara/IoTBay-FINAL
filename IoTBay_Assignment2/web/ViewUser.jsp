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
        String msgUpdate = (session.getAttribute("msgUpdate") != null) ? (String) session.getAttribute("msgUpdate") : "";
        String msgCreate = (session.getAttribute("msgCreate") != null) ? (String) session.getAttribute("msgCreate") : "";
        String msgDelete = (session.getAttribute("msgDelete") != null) ? (String) session.getAttribute("msgDelete") : "";
        String userDeleted = (session.getAttribute("userDeleted") != null) ? (String) session.getAttribute("userDeleted") : "";
    
        session.setAttribute("msgUpdate", "");
        session.setAttribute("msgCreate", "");
        session.setAttribute("msgDelete", "");
        session.setAttribute("userDeleted", "");
    %>
    
    <body action="ViewUserAdmin">
        
        <%
            ArrayList<User> user = (ArrayList<User>) session.getAttribute("user");
        %>

       <div class="topnav">
        <a class="button1" href="HomeAdmin.jsp">Home</a>
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
                    <input type="text" id="inputFirstName" class="searchbox" onkeyup="filterTable()" placeholder="First name" title="Type in a name">
                    <input type="text" id="inputLastName" class="searchbox" onkeyup="filterTable()" placeholder="Last name" title="Type in a name">
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
                            <tr><td colspan="2"><h2>Customers</h2></td></tr>
                            <tr>
                                <th colspan="2">Type</th>
                                <th>Type</th>
                                <th>Email</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Phone number</th>
                                <th>Password</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${customers}" var="c">
                            <tr>
                                <td>
                                    <form method="get" action="UpdateAdminServlet">
                                       <input type="hidden" name="customerEmail" value="${c.getEmail()}" />
                                        <button class="button4" type="confirm">Edit</button> 
                                    </form>
                                </td>
                               <td>Customer</td>
                                    <td>${c.getEmail()}</td>
                                    <td>${c.getFirstName()}</td>
                                    <td>${c.getLastName()}</td>
                                    <td>${c.getPhoneNumber()}</td>
                                    <td>${c.getPassword()}</td>
                            </tr>
                       </c:forEach>
                    </tbody>
                 </table>
                        <table class="UserTable"> <thead>
                            <tr><td colspan="2"><h2>Staff</h2></td></tr>
                            <tr>
                                <th colspan="2">Type</th>
                                <th>Type</th>
                                <th>Email</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Phone number</th>
                                <th>Password</th>
                                <th>Manager</th>
                            </tr>
                        </thead>
                    <tbody>
                        <c:forEach items="${customers}" var="c">
                            <tr>
                                <td>
                                    <form method="get" action="EditUser.jsp">
                                       <input type="hidden" name="staffEmail" value="${s.getEmail()}" />
                                        <button class="button4" type="confirm">Edit</button> 
                                    </form>
                                </td>
                               <td>Staff</td>
                                    <td>${s.getEmail()}</td>
                                    <td>${s.getFirstName()}</td>
                                    <td>${s.getLastName()}</td>
                                    <td>${s.getPhoneNumber()}</td>
                                    <td>${s.getPassword()}</td>
                                    <td>${s.getManager()}</td>
                            </tr>
                       </c:forEach>
                    </tbody>
                 </table>
                </div>
                 <script>
                    function filterUsers() {
                        var input, filterFirstName, filterLastName, filterPhoneNumber, filterType, table, tr, tdFirstName, tdLastName, tdPhoneNumber, tdType, i, txtFirstName;
                        input = document.getElementById("inputFirstName");
                        filterFirstName = input.value.toUpperCase();
                        input = document.getElementById("inputastLName");
                        filterLastName = input.value.toUpperCase();
                        input = document.getElementById("inputPhoneNumber");
                        filterPhoneNumber = input.value.toUpperCase();
                        input = document.getElementById("inputType");
                        filterType = input.value.toUpperCase();
                        table = document.getElementById("UserTable");
                        tr = table.getElementsByTagName("tr");
                        
                        for (i = 0; i < tr.length; i++) {
                            tdType = tr[i].getElementsByTagName("td")[2];
                            tdFirstName = tr[i].getElementsByTagName("td")[4];
                            tdLastName = tr[i].getElementsByTagName("td")[5];
                            tdPhoneNumber = tr[i].getElementsByTagName("td")[6];
                            
                        if (tdFirstName && tdLastName) {
                            txtType = tdType.textContent || tdType.innerText;
                            txtFirstName = tdFirstName.textContent || tdFirstName.innerText;
                            txtLastName = tdLastName.textContent || tdLastName.innerText;
                            txtPhoneNumber = tdPhoneNumber.textContent || tdPhoneNumber.innerText;
                            
                                if (filterType === "CUSTOMER" || filterType === "STAFF") {
                                    if (txtType.toUpperCase().indexOf(filterType) > -1 && txtFirstName.toUpperCase().indexOf(filterFirstName) > -1 && txtLastName.toUpperCase().indexOf(filterLastName) > -1 && txtPhoneNumber.toUpperCase().indexOf(filterPhoneNumber) > -1) {
                                        tr[i].style.display = "";
                                    } 
                                    
                                    else {
                                        tr[i].style.display = "none";
                                    }
                                    }
                                    
                                else {
                                    if (txtFirstName.toUpperCase().indexOf(filterFirstName) > -1 && txtLastName.toUpperCase().indexOf(filterLastName) > -1 && txtPhoneNumber.toUpperCase().indexOf(filterPhoneNumber) > -1) {
                                        tr[i].style.display = "";
                                    } else {
                                        tr[i].style.display = "none";
                                    }
                                }
                            }
                        }
                    }
                </script>                    
            </div>
        </div>
    </body>
</html>