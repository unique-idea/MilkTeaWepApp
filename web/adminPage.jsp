<%-- 
    Document   : adminPage
    Created on : May 31, 2022, 11:32:15 AM
    Author     : lequa
--%>

<%@page import="java.util.List"%>
<%@page import="users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Home</title>
        <link rel="stylesheet" href="stylish.css"/>

    </head>
    <body>

        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "NONE";
            }
        %>
        <div class="adAll">
            <h1 id="welcomeBack">Welcome Back:</h1>
            <h1 id="nameAd"> <%= loginUser.getFullName()%> </h1>
            <h1> Your Own Work Still Below</h1> 
            <h1 id="logoutAd">
                <a href="MainController?action=LogoutAd">Logout</a> 
            </h1>
            <div id="usManage">
                <h1 id="usc"> <a href="userManager.jsp">User Manager</a> </h1>
            </div>
            <div id="pdManage">
                <h1 id="pdc"><a href="productManager.jsp">Product Manager</a></h1>
            </div>
        </div>
        <div class="bodyAd">
            <div id="errAd">
                <h1> Notification Error: <%= error%> </h1>
            </div>
            <h3 id="ownWork">Your Own Work: </h3> <p> </br> - Fixed some problem: False </br> - Update new function: In work
                </br> - Checking manager: Done </br>- Checking user: Done - Check Out: Failed</p></br>
            <h3 id="valid">Valid Function:</h3>
            <p>- Update User: ID, Balance, Vip status, Use Product </br>- Search User</br>- Delete User</br>-Clock Error </p>
            <h3 id="newF">New Function: </h3>
            <p>-Edit Cart Online: Unavailable</br>-Recap cha: Unavailable</br>- Log j4: Unavailable</br>- Loging google: In completing</br>- Check Out: Available</br>- Payment: Unavailable
                </br>- Resolve rubix: In Work</p>
            <div class="right">
                <p id="ads">ADS </br> This Page Just Spend More Time So it look like better</br>Please Contact To Us: @fpt.edu </br>
                    © Powered by FPT University |  CMS |  library |  books24x7<p>
            </div>
        </div>
    </body>
</html>
