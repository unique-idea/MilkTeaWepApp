<%-- 
    Document   : UserManager
    Created on : Jul 4, 2022, 9:55:16 AM
    Author     : lequa
--%>

<%@page import="java.util.List"%>
<%@page import="users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="stylish.css"/>
        <title>User Manager</title>
    </head>
    <body>
        <%
            String error = (String) session.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
            String deleteSuccess = (String) session.getAttribute("DELETESUCCSESS");
            if (deleteSuccess == null) {
                deleteSuccess = "";
            }
            String updateSuccess = (String) session.getAttribute("UPDATESUCCESS");
            if (updateSuccess == null) {
                updateSuccess = "";
            }

            String updateFail = (String) session.getAttribute("UPDATEFAIL");
            if (updateFail == null) {
                updateFail = "";
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <div class="usermnAll">
            <div id="welcomeUsMn">
                <h1>Let's See How Customer Going!<h1/>
                    <h3 id="gobackA"> <a href="adminPage.jsp">Go Back</a> </h3>
                    <form action="MainController" method="POST">
                        <input type="search" name="search" value="<%= search%>">              
                        <input type="submit" name="action" value="Search"/>
                    </form>
            </div>
            <div id="errUs">
                <%= error%>
                <%= deleteSuccess%>
                <%= updateSuccess%>
                <%= updateFail%>
            </div>
            <%
                List<UserDTO> listUser = (List<UserDTO>) request.getAttribute("LIST_USER");
                if (listUser != null && listUser.size() > 0) {
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>User ID</th>
                        <th>Full Name</th>
                        <th>Pass Word</th>
                        <th>Role ID</th>
                        <th>Phone Number</th>
                        <th>Email</th>
                        <th>Vip Status</th>
                        <th>Balance</th>
                        <th>Use Product</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (UserDTO user : listUser) {
                    %>
                <form action="MainController">
                    <tr>
                        <td> <%= count++%> </td>
                        <td> 
                            <input type="text" name="userID" value="<%= user.getUserID()%>" required=""/>
                        </td>
                        <td><%= user.getFullName()%> </td>
                        <td>
                            <%= user.getPassword()%>
                        </td>
                        <td> 
                            <input type="text" name="roleID" value="<%= user.getRoleID()%>" required="" />
                        </td>
                        <td><%= user.getPhoneNumber()%></td>
                        <td><%= user.getEmail()%></td>
                        <td>
                            <input type="text" name="vipstatus" value="<%= user.getVipsatus()%>" required="" />
                        </td>
                        <td>
                            <input type="text" name="balance" value="<%= user.getBalance()%>" required="" />
                        </td>
                        <td><%= user.getUseproduct()%></td>
                        <!--delete function-->
                        <td>
                            <a href="MainController?action=Delete&userID=<%= user.getUserID()%>&fullName=<%= user.getFullName()%>&search=<%= search%>">Delete</a>
                        </td>
                        <!--update function-->
                        <td> 
                            <input type="hidden" name="fullName" value="<%= user.getFullName()%>" />
                            <input type="submit" name="action" value="Update" />
                            <input type="hidden" name="search" value="<%= search%>" />
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
        <div class="endUs">
            <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" id="troll"> Nothings Importance Don't Click</a> 
        </div>
        <%
            session.setAttribute("ERROR", null);
            session.setAttribute("DELETESUCCSESS", null);
            session.setAttribute("UPDATESUCCESS", null);
            session.setAttribute("UPDATEFAIL", null);
        %>
    </body>
</html>
