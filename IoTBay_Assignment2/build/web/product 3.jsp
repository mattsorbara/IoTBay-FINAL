<%-- 
    Document   : catalogue
    Created on : 4 May 2022, 9:26:23 pm
    Author     : rubabr
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="iotbay.model.Catalogue"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/product.css">
    </head>
    
    <script>
    function checkDelete(id) {
      if (confirm("Are you sure you want to delete?") === true) {
        window.location.replace("ProductDeleteServlet?id="+id);
      }
    }
    </script>
            
    <body action="ProductServlet">
        
        <%
            Catalogue product = (Catalogue) session.getAttribute("product");
        %>
        
        <jsp:include page="header.jsp"/>
            
            <div class="product">
                <div class="productContents">
              
                    <h2 id="title"><%=product.getTitle()%></h2>
                    <hr>
                    <div style="text-align:center; padding:10px">
                        <b>If the user has admin access</b>
                        <p>
                        <a  style="color:white; text-decoration: none;" href="EditProductServlet?id=<%=product.getId()%>"><button class="submit">Edit Product</button></a>
                        <button class="submit" onclick="checkDelete(<%=product.getId()%>)">Delete Product</button>
                    </div>
                    
                    <h5 id="title">Product ID: <%=product.getId()%></h5>
                    <h5 id="title">Product Type: <%=product.getType()%></h5>
                    
                    <div style="display: flex">
                        <img class="productImage" src="<%=product.getImage()%>" alt="<%=product.getTitle()%>">
                    </div>     
                    <div class="productDetails">
                        <b>$<%=product.getPrice()%>0</b>
                        <p>
                        <b>Stock Level: <%=product.getStock()%></b>
                    
                    <% if(product.getStock() > 0){%>
                    </div>
                        <center><a href="/AddToOrderServlet?id=<%=product.getId()%>"><button class="submit">Add To Order</button></a></center>
                    <hr>
                    <%}else{%>
                    <h4><font color="red">Out of Stock</font></h4>
                    <%}%>
                    <div class="productDescription">
                        <p><%=product.getDescription()%></p>
                        
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
