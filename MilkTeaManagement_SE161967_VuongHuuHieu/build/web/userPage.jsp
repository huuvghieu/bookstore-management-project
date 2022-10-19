<%-- 
    Document   : userInfo
    Created on : Jun 29, 2022, 2:39:05 PM
    Author     : ownhi
--%>

<%@page import="sample.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User's Information Page</title>
        <link rel="stylesheet" href="css/styleuserInfor.css">
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"US".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <div class="main-container">
            <div class="container">
                <h1>Welcome <%= loginUser.getFullName() %> </h1>
                <div id="maindetail">
                    <label>User ID</label>
                    <p> <%= loginUser.getUserID() %> </p>
                    <label>Password</label>
                    <p> *** </p>
                    <label>Full Name</label>
                    <p> <%= loginUser.getFullName() %> </p>
                    <label>Email</label>
                    <p> <%= loginUser.getEmail() %> </p>
                    <label>Address</label>
                    <p> <%= loginUser.getAddress() %> </p>
                    <label>Role ID</label>
                    <p> <%= loginUser.getRoleID() %> </p>
                    <a href="userOrder.jsp">Back my order!</a>
                    <a href="home.jsp">Home</a>
                </div>
            </div>
        </div>
    </body>
    <%
        String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }
    %>

    <%= error%>
</html>
