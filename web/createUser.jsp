<%-- 
    Document   : createUser
    Created on : Jun 16, 2022, 9:10:42 AM
    Author     : lequa
--%>

<%@page import="logingoogle.UserGoogleDto"%>
<%@page import="users.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
        <link rel="stylesheet" href="stylish.css"/>
    </head>
    <body>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            UserGoogleDto userGoole = (UserGoogleDto) request.getAttribute("GOOGLE_USER");
            if(userGoole == null){
                
            if (userError == null) {
                userError = new UserError();
            }
        %>
        <div class="createAll">
            <h1 id="userCre">Input User Information</h1>
            <form action="MainController" method="POST">
                <div class="creaFather">
                    <div class="input-group mb-3" id="cra">
                        <span class="input-group-text" id="basic-addon1">User ID</span>
                        <input type="text" name="userID" class="form-control" placeholder="Start With [U]" id="userID">
                    </div>
                    <div class="input-group mb-3" id="crb">
                        <span class="input-group-text" id="basic-addon2">Full Name</span>
                        <input type="text" name="fullName" class="form-control" placeholder="Keep Eye On Leght" id="fullName">
                    </div>
                    <label for="basic-url" class="form-label" id="defaut">Your Default Role</label>
                    <div class="input-group mb-3" id="crc">
                        <input type="text" name="roleID" value="US" readonly=""/></br>
                        Password<input type="password" name="password" value="" required=""/></br>
                        Confirm<input type="password" name="confirm" value="" required=""/>
                        Phone Number<input type="text" name="phoneNumber" value="" required="" id="phoneNumber"/></br>
                        Email <input type="email" name="email" value="" required="" id="email"/></br>
                        <input type="submit" name="action" value="Create" />
                        <input type="reset" value="Reset" />
                        <a href="login.jsp">Cancel</a>
                        <%= userError.getError()%>
                    </div>
                </div>
            </form>
            <div id="errCre">
                <div id="nocc">
                    <p>Notification :</p>
                </div>
                <%= userError.getUserID()%></br>
                <%= userError.getFullName()%></br>
                <%= userError.getConfirm()%></br>
            </div>
        </div>
            <%   
}else{
                %>
                <form action="MainController">
                    
                </form>
    </body>
</html>
