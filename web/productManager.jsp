<%-- 
    Document   : productManager
    Created on : Jul 4, 2022, 9:58:36 AM
    Author     : lequa
--%>

<%@page import="java.util.List"%>
<%@page import="users.UserDTO"%>
<%@page import="products.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="stylish.css"/>
        <title>PD Manager</title>
    </head>
    <body>
        <%
            String deleteSuccess = (String) session.getAttribute("DELTESUCCESS");
            if (deleteSuccess == null) {
                deleteSuccess = "";
            }
            String error = (String) session.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
            String upPd = (String) session.getAttribute("UPPD");
            if (upPd == null) {
                upPd = "";
            }
            String createPd = (String) session.getAttribute("CREATEPD");
            if (createPd == null) {
                createPd = "";
            }
            String searchPd = request.getParameter("searchPd");
            if (searchPd == null) {
                searchPd = "";
            }

        %>
        <div id="pdmAll">
            <div id="welcomePdMn">
                <h1>Let's See How Product Going </h1>
                <h3 id="gobackU">  <a href="adminPage.jsp">Go Back</a> </h3>  
                <form action="MainController" method="POST">
                    <input type="search" name="searchPd" value="<%= searchPd%>">              
                    <input type="submit" name="action" value="SearchPd"/>
                </form>
            </div>
            <div id="errPd">
                <%= error%>
                <%= deleteSuccess%>
                <%= upPd%>
                <%= createPd%>
            </div>
            <%
                List<Product> listPd = (List<Product>) request.getAttribute("LIST_PRODUCT");
                if (listPd != null && listPd.size() > 0) {
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Detail</th>
                        <th>Current Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (Product pd : listPd) {
                    %>
                <form action="MainController">
                    <tr>
                        <td> <%= count++%> </td>
                        <td> <%= pd.getProductID()%> </td>
                        <td>
                            <input type="text" name="name" value="<%= pd.getName()%>" required="" /> 
                        </td>
                        <td>
                            <input type="text" name="price" value="<%= pd.getPrice()%>" required="" />
                        </td>
                        <td> 
                            <input type="text" name="detail" value="<%= pd.getDetail()%>" required="" />
                        </td>
                        <td>
                            <input type="text" name="currentQuantity" value="<%= pd.getCurrentQuantity()%>" required="" />
                        </td>
                        <!--delete function-->
                        <td>
                            <a href="MainController?action=DeletePd&productID=<%= pd.getProductID()%>&search=<%= searchPd%>">Delete</a>
                        </td>
                        <!--update function-->
                        <td>
                            <input type="hidden" name="productID" value="<%= pd.getProductID()%>" />
                            <input type="hidden" name="search" value="<%= searchPd%>" />
                            <input type="submit" name="action" value="UpdatePd" />
                        </td>
                    </tr>
                </form>

                <%
                        }
                    }
                %>

                </tbody>
            </table>
        </div>
        <%
            session.setAttribute("ERROR", null);
            session.setAttribute("DELTESUCCESS", null);
            session.setAttribute("UPPD", null);
            session.setAttribute("CREATEPD", null);
        %>
        <div id="createPd">
            </br><a href="createPd.jsp">Create New Product</a>
        </div>
    </body>
</html>
