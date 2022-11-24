<%-- 
    Document   : createPd
    Created on : Jul 5, 2022, 9:29:02 AM
    Author     : lequa
--%>

<%@page import="products.ProductError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="stylish.css"/>
        <title>Create Pd</title>
    </head>
    <body>
        <%
            ProductError pdError = (ProductError) request.getAttribute("PD_ERROR");
            if (pdError == null) {
                pdError = new ProductError();
            }
            String createPdFail = (String) session.getAttribute("CREATEPDFAIL");
            if (createPdFail == null) {
                createPdFail = "";
            }
        %>
        <div class="createPdall">
            <h1 id="welcomePdcr">Input New Product Information!</h1>
            <div id="createPdmain">
                <form action="MainController" method="POST">
                    Product ID</br><input type="text" name="productID" value="" requried=""/></br>
                    New Name</br><input type="text" name="name" value="" requried=""/></br>
                    Price</br> <input type="text" name="price" value="" required=""/></br>
                    Detail</br><input type="text" name="detail" value="" required=""/></br>
                    Current Quantity</br><input type="text" name="currentQuantity" value="" required=""/></br>
                    <input type="submit" name="action" value="CreatePd" />
                    <input type="reset" value="Reset" /></br>
                    <h3 id="cancelPd"> <a href="productManager.jsp">Cancel</a> </h3>
                </form>
            </div>
            </br></br>
            <div id="errCrePd">
                <p>Notification: </p>
                <%= pdError.getProductID()%></br>
                <%= pdError.getName()%></br>
                <%= pdError.getError()%></br>
            </div>
            <%= createPdFail%>
        </div>
    </body>
</html>
