<%-- 
    Document   : shopping
    Created on : Jun 16, 2022, 11:14:23 AM
    Author     : lequa
--%>

<%@page import="products.Product"%>
<%@page import="java.util.List"%>
<%@page import="users.UserDTO"%>
<%@page import="users.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="stylish.css"/>
        <title>Shopping</title>
    </head>
    <body>
        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message == null) {
                message = "";
            }
        %>
        <div id="shoppingAll">
            <div id="buying"><h1>Hello Customer Come To Shopping Let The Fun Beging</h1>
                <h1 id="gobackU2"> <a href="userPage.jsp">Go Back</a>   </h1> 
                <form action="MainController" method="POST">            
                    <input type="submit" name="action" value="showAll"/>
                    <input type="submit" name="action" value="View" />
                </form>
            </div>
            <%
                List<Product> listProduct = (List<Product>) request.getAttribute("LIST_PRODUCTUS");
                if (listProduct != null && listProduct.size() > 0) {
            %>

            <table border="5">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Detail</th>
                        <th>Price</th>
                        <th>Current Quantity</th>
                        <th>Add Number</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (Product pd : listProduct) {
                    %>
                <form action="MainController">
                    <tr>
                        <td> <%= count++%></td>
                        <td><%= pd.getName()%></td>
                        <td><%= pd.getDetail()%></td>
                        <td><%= pd.getPrice()%></td
                        <td></td>
                        <td><%= pd.getCurrentQuantity()%></td>
                        <td>
                            <select name="AddNumber">
                                <option value="1">1 Cup</option>
                                <option value="2">2 Cups</option>
                                <option value="3">3 Cups</option>
                                <option value="4">4 Cups</option>
                                <option value="5">5 Cups</option>
                                <option value="10">10 Cups</option>
                            </select>
                        </td>
                        <td>
                            <input type="hidden" name="name" value="<%= pd.getName()%>" />
                            <input type="hidden" name="price" value="<%= pd.getPrice()%>" />
                            <input type="hidden" name="detail" value="<%= pd.getDetail()%>" />
                            <input type="hidden" name="currentQuantity" value="<%= pd.getCurrentQuantity()%>" />
                             <input type="hidden" name="productID" value="<%= pd.getProductID()%>" />
                            <input type="submit" name="action" value="Add"/>
                        </td>
                    </tr>
                </form>
                <%
                        }
                    }
                %>
                </tbody>
            </table> 
                <h1 id="message"><a> <%= message%> </a></h1>
        </div>
    </body>
</html>
