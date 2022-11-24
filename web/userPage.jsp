<%-- 
    Document   : userPage
    Created on : May 31, 2022, 11:31:38 AM
    Author     : lequa
--%>
<%@page import="users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="stylish.css"/>
        <title>User Home</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String vip = "";
            if (loginUser == null || !"US".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.jsp");
                return;
            }
            if (loginUser.getVipsatus() == 1) {
                vip = loginUser.getFullName();
            }
            String notice = (String) session.getAttribute("NOTICE");
            if (notice == null) {
                notice = "";
            }
            String getVip = (String) session.getAttribute("GETVIP");
            if (getVip == null) {
                getVip = "";
            }
        %>
        <div class="userAll">
            <div id="welcomeUser">
                <h1> Welcome <%= vip%> To Home </h1> 
                <h3 id="logOutUs"> <a href="MainController?action=LogoutUs">Logout</a>  </h3>
            </div>
            <div class="tableUs">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Full Name</th>
                            <th>Pass Word</th>
                            <th>Role</th>
                            <th>Phone Number</th>
                            <th>Email</th>
                            <th>Vip Status</th>
                            <th>Balance</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody> 
                    <form action="MainController">
                        <tr>
                            <td>
                                <input type="text" name="fullName" value="<%=loginUser.getFullName()%>" required="" />
                            </td>
                            <td>
                                <input type="text" name="password" value="<%=loginUser.getPassword()%>" required="" />          
                            </td>
                            <td><%= loginUser.getRoleID()%></td>
                            <td>
                                <input type="text" name="phoneNumber" value="<%=loginUser.getPhoneNumber()%>" required="" />    
                            </td>
                            <td>
                                <input type="text" name="email" value=" <%= loginUser.getEmail()%>" required="" />            
                            </td>
                            <td><%= loginUser.getVipsatus()%></td>
                            <td><%= loginUser.getBalance()%></td>
                            <td>
                                <input type="hidden" name="userID" value="<%= loginUser.getUserID()%>" />
                                <input type="submit" name="action" value="UpdateMyself" />
                            </td>
                        </tr>
                    </form>
                    </tbody>
                </table>
                <div id="getVip">
                    <p> Become A vip Member </p>
                    <form action="MainController">
                        <input type="hidden" name="userID" value="<%= loginUser.getUserID()%>" />
                        <input type="hidden" name="balance" value="<%= loginUser.getBalance()%>" />
                        <input type="hidden" name="vipstatus" value="<%= loginUser.getVipsatus()%>" />
                        <input type="hidden" name="useproduct" value="<%= loginUser.getUseproduct()%>" />
                        <input type="text" name="productKey" placeholder="Input Product Key"/>
                        <input type="submit" name="action" value="GetVip" />
                    </form>
                </div>
                <div id="vipResult">
                    <h3>Notification: </h3><%= getVip%><h6> <%= notice%> </h6>
                </div>
                <div id="shopping">
                    <form action="MainController">
                        <h1>Check Your Shopping Iteams </h1>
                        <h1 id="view"> <input type="submit" name="action" value="View" /> </h1>
                    </form>
                    <h1>Or</h1>
                    <h3 id="shoppingPage">Le'st <a href="shopping.jsp">Shopping</a> </h3>
                </div>
            </div>
            <div id="endUserPage">
                </br>
                Any problem or question please contact to us  Phone: 028.73005585 , email: sschcm@fe.edu.vn </br>
                © Powered by FPT University |  CMS |  library |  books24x7
            </div>
            <% session.setAttribute("NOTICE", null);%>
    </body>
</html>
 // url  server global: https://36a6-118-69-233-165.ap.ngrok.io//YenSao