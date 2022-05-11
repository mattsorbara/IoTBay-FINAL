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
        <title>Product Catalogue | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/addproduct.css">
    </head>
    
    <body action="EditProductServlet">
        
        <jsp:include page="header.jsp"/>
        
        <%
            Catalogue product = (Catalogue) session.getAttribute("product");
            String error = (String) session.getAttribute("error");
        %>
        
        <div class="addProduct">
            <div class="addProductContents">
                <div class="buttons" style="width:100%; display: inline-grid; justify-content: center;">
                    <button class="submit" type="submit" onclick="history.back()">Back</button>&nbsp;
                </div>
                <center><h4><font color="red"><%=(error != null ? error : "")%></font></h4></center>
                <div style="display: flex">
                        <img class="productImage" src="<%=product.getImage()%>" alt="<%=product.getTitle()%>">
                </div>   
                <h2 id="title"><b>Edit Product: <%=product.getTitle()%></b></h2>
                <form class="addProductForm" action="EditProductServlet?id=<%=product.getId()%>" method="post">
                    
                    <div class="addProductElement" id="stock">
                        <label>Stock Level</label>
                        <div>
                            <input class="border-customized-input" type="number" required="true" name="stock" value="<%=product.getStock()%>" placeholder="Current Stock Level: <%=product.getStock()%>">
                        </div>
                    </div>
                    
                    <div class="addProductElement" id="price">
                        <label>Price</label>
                        <div>
                            <input class="border-customized-input" type="number" required="true" name="price" value="<%=product.getPrice()%>0" placeholder="Current Product Price: <%=product.getPrice()%>0">
                        </div>
                    </div>
                    
                    <div class="buttons">
                        <button class="submit" type="submit">Edit Product</button>
                    </div>
                    
                </form>
            </div>
        </div>
        
    </body>
</html>
