<%-- 
    Document   : index
    Created on : May 31, 2022, 10:53:25 AM
    Author     : lequa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Login page</title>
        <link rel="stylesheet" href="stylish.css"/>
    </head>
    <body>
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
            String success = (String) request.getAttribute("CREATESUCCESS");
            if (success == null) {
                success = "";
            }
        %>
        <%
            String id = (String) request.getAttribute("id");
            if (id == null) {
                id = "";
            }
            String name = (String) request.getAttribute("name");
            if (name == null) {
                name = "";
            }
            String email = (String) request.getAttribute("email");
            if (email == null) {
                email = "";
            }
        %>
            <div class="topnav">
                <a class="active" href="#home">Home</a>
                <a href="#news">New</a>
                <a href="#contact">Contact</a>
                <a href="#about">About Us</a>
            </div>
            <p id="welcomeLog"> Let's Go To Heaven </p>
            <div class="parentLog">
                <div class="col-md-4" id="childLog">
                    <form action="MainController" method="POST">
                        <div class="mb-3"">
                            <label for="exampleInputName" class="form-label">Full Name</label></br>
                            <input type="text" class="form-control" id="exampleInputName" name="fullName">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword" class="form-label">Password</label></br>
                            <input type="password" class="form-control" id="exampleInputPassword"  aria-describedby="notice" name="password">
                        </div>
                        <div class="mb-3 form-check">
                            <input type="submit" id="logButton" name="action" value="Login"/>
                            <input type="reset" id="resetLog" value="reset"/>
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/MilkTeaManagement/MainController?action=loginGoogle&response_type=code
                               &client_id=1081762529302-9j0c8b6a8g87q99k39375bolng09uvts.apps.googleusercontent.com&approval_prompt=force">Login Google</a>
                                          
                            <h4> First Time In Here  <a href="createUser.jsp">Join Us</a></h4>
                        </div>
                    </form>
                </div>
                <div id="errorLog">
                    <%= id%>
                    <%= name%>
                    <%= email%>
                    <%=error%>
                    <%=success%>
                </div>
                <div class="endLog">
                    <p>This Web Develop By Unprofessional front end don't hope to much better on full screen!</p>
                </div>
            </div>
    </body>
</html>
