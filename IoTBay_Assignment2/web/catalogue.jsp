<%-- 
    Document   : catalogue
    Created on : 4 May 2022, 9:26:23 pm
    Author     : rubabr
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="iotbay.model.Catalogue"%>
<%@page import="iotbay.model.User"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Catalogue | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/catalogue.css">
    </head>
    
    <body action="CatalogueServlet">
        
        <%
            ArrayList<Catalogue> products = (ArrayList<Catalogue>) session.getAttribute("catalogue");
        %>
        
        <jsp:include page="header.jsp"/>
        
        <div class="catalogue">
            <div class="catalogueContents">
                    <h2 id="title">Catalogue</h2>
                    <div class="searchForm">
                        <form class="search" action="CatalogueSearch" method="post">
                            <input class="searchField" type="text" name="query" placeholder="Search Products">
                            <button class="submit" type="submit">Search</button>
                        </form>
                    </div>
                    <hr>
                    <div style="text-align:center; padding:10px">
                        <b>If the user has admin access</b>
                        <p>
                        <a style="color:white; text-decoration: none;" href="addproduct.jsp"><button class="submit" style="width:8%">Add Product</button></a>
                    </div>
                    
                    <%if(products.size() > 0){%>
                    <p><center>Click on a product to view more info</center></p>
                        <%}%>
                    <div class="listProducts">
                        <%if(products.size() > 0){
                            for (Catalogue p: products){ %>
                            
                            <a href="ProductServlet?id=<%=(int)p.getId()%>">
                        <div class="catalogueItem" style="background-color: white; border: 1px solid #000; margin-left: 10px; margin-top: 10px">
                            
                                <img src="<%=p.getImage()%>" style="width: 200px; height: 200px" alt="<%=p.getTitle()%>">
                            
                            <p style="text-align:center;color:black"><%=p.getTitle()%></p>
                            <p style="text-align:center;color:black">$<%=p.getPrice()%>0</p>
                            <% if(p.getStock() > 0){%>
                                <center><a href="OrderCheckoutServlet?id=<%=(int)p.getId()%>"><button class="addtocart">Add To Order</button></a></center>
                            
                            <%}else{%>
                                <center><h4><font color="red">Out of Stock</font></h4></center>
                            <%}%>
                                
                        </div>
                            </a>
                        <%}
                            }else{%>
                            <h2>No products found</h2>
                        <%}%>
                    </div>     
                </div>
            </div> 
        </div>
        </div>
    </body>
</html>
