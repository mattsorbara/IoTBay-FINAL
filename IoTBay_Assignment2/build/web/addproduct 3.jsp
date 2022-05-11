<%-- 
    Document   : catalogue
    Created on : 4 May 2022, 9:26:23 pm
    Author     : rubabr
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="iotbay.model.User"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Catalogue | IoT Bay</title>
        <link rel="stylesheet" type="text/css" href="static/css/addproduct.css">
    </head>
    
    <body action="CatalogueServlet">
        
        <jsp:include page="header.jsp"/>
        
         <%
            String error = (String) session.getAttribute("error");
          %>
        
        <div class="addProduct">
            <div class="addProductContents">
                <h2 id="title"><b>Add Product</b></h2>
                <div class="buttons" style="width:100%; display: inline-grid; justify-content: center;">
                    <button class="submit" type="submit" onclick="history.back()">Back</button>&nbsp;
                </div>
                <form class="addProductForm" action="AddProductServlet" method="post">
                    <h4><font color="red"><%=(error != null ? error : "")%></font></h4>
                    <div class="addProductElement" id="Product ID">
                        <label>Product ID</label>
                        <div>
                            <input onkeydown="javascript: return ['Backspace','Delete','ArrowLeft','ArrowRight'].includes(event.code) ? true : !isNaN(Number(event.key)) && event.code!=='Space'" class="border-customized-input" type="number" required="true" name="id" placeholder="Enter the product identifier">
                        </div>
                    </div>
                    
                    <div class="addProductElement" id="Product Name">
                        <label>Product Name</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="title" placeholder="Product Title">
                        </div>
                    </div>
                    
                    <div class="addProductElement" id="price">
                        <label>Price</label>
                        <div>
                            <input onkeydown="javascript: return ['Backspace','Delete','ArrowLeft','ArrowRight'].includes(event.code) ? true : !isNaN(Number(event.key)) && event.code!=='Space'" class="border-customized-input" type="number" required="true" name="price" placeholder="Product Price">
                        </div>
                    </div>
                    
                    <div class="addProductElement" id="description">
                        <label>Description</label>
                        <div>
                            <input class="border-customized-input" type="text" "required="true" name="description" placeholder="Product Description">
                        </div>
                    </div>
                    
                    <div class="addProductElement" id="image">
                        <label>Image</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="image" placeholder="Enter Image URL">
                        </div>
                    </div>
                    
                    <div class="addProductElement" id="stock">
                        <label>Stock Level</label>
                        <div>
                            <input onkeydown="javascript: return ['Backspace','Delete','ArrowLeft','ArrowRight'].includes(event.code) ? true : !isNaN(Number(event.key)) && event.code!=='Space'" class="border-customized-input" type="number" required="true" name="stock" placeholder="Stock Level">
                        </div>
                    </div>
                    
                    <div class="addProductElement" id="type">
                        <label>Type of Product</label>
                        <div>
                            <input class="border-customized-input" type="text" required="true" name="type" placeholder="Product Type">
                        </div>
                    </div>
                    
                    <div class="buttons">
                        <button class="submit" type="submit">Add Product</button>
                    </div>
                    
                </form>
            </div>
        </div>
        
    </body>
</html>
