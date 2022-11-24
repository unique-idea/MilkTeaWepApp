<%-- 
    Document   : viewCart
    Created on : Jun 21, 2022, 10:57:51 AM
    Author     : lequa
--%>

<%@page import="users.UserDTO"%>
<%@page import="products.Product"%>
<%@page import="orders.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Items</title>
        <link rel="stylesheet" href="stylish.css"/>
    </head>
    <body>
        <div id="viewAll">
            <h1>Your Lovely Milk Tea:</h1>
            <%
                UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                String invalue = "";
                String payment = (String) session.getAttribute("PAYMENTSUCCESS");
                if(payment == null){
                    payment = "";
                }
                String notEngough = (String) session.getAttribute("NOTENOUGHT");
                if (notEngough == null) {
                    notEngough = "";
                }
                String noCart = (String) session.getAttribute("NOCART");
                if (noCart == null) {
                    noCart = "";
                }
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    payment = "";
            %>
            <div id="tableShopping">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Detail</th>
                            <th>Quantity</th>
                            <th>Cost</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count = 1;
                            float total = 0;
                            for (Product pd : cart.getCart().values()) {
                                total += pd.getPrice() * pd.getCurrentQuantity();
                        %>
                    <form action="MainController">
                        <tr>
                            <td><%= count++%></td>
                            <td><%= pd.getName()%></td>
                            <td><%= pd.getPrice()%></td>
                            <td><%= pd.getDetail()%></td>
                            <td>
                                <input type="number" name="quantity" value="<%= pd.getCurrentQuantity()%>" required="" min="1" >
                            </td>
                            <td>
                                <%= pd.getPrice() * pd.getCurrentQuantity()%> 
                            </td>
                            <td>
                                <input type="hidden" name="productID" value="<%= pd.getProductID()%>"/>
                                <input type="submit" name="action" value="Remove"/>
                            </td>
                            <td>
                                <input type="submit" name="action" value="Edit"/>
                            </td>
                        </tr>
                    </form>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
            <div id="total">
                <h1> Total: <%= total%> $ </h1>
                <form action="MainController">
                    <input type="hidden" name="fullName" value="<%= loginUser.getFullName()%>">
                    <input type="hidden" name="phone" value="<%= loginUser.getPhoneNumber()%>">
                    <input type="hidden" name="email" value="<%= loginUser.getEmail()%>">
                    <input type="hidden" name="cost" value="<%= total%>" >
                    <input type="hidden" name="customerID" value="<%= loginUser.getUserID()%>">
                    <input type="submit" name="action" value="checkOut"/>
                </form>                           
            </div>
                    
            <%
                } else {
                     if (payment == "") {
                            invalue = "Not bying yet!";
                            }else{
                                invalue = "";
                           }
                }
            %>
            <div id="endCart">
                <%= invalue%> <div id="addMore">
                    <%= payment %>
                    <%= notEngough %>
                    <a href="shopping.jsp">Add more</a> </div>
                     <% session.setAttribute("NOTENOUGHT", null);%>
            </div>
        </div>
    </body>
</html>


forgot password => email .phone


- giam truc tiep % len san pham => (10%, 20%, 30%)  Key: rd10pt
- giam gia ship % => (30%, 50%, 70%) Key: fr30pt
- free ship  Key fr100pt
- extra tich luy => (x1.5, x2, x3) Key: ex15cu
- giam truc tiep len tong gia (120 over 1000)   Key: rd120ov1000
Culmulaitive:
Use direct: 100 point per 1
Change voucherSystem: ship 30% => 600 point, 50% => 1000 point, 70% => 1500 point, x1,5 => 500 point, x2 => 700 point, x3 => 1000 point,
50 over 500 => 1500 point, 15 each 100 => 2000 point