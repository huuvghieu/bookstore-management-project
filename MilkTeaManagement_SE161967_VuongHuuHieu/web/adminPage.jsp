<%-- 
    Document   : adminPage
    Created on : Jun 11, 2022, 3:17:38 PM
    Author     : ownhi
--%>

<%@page import="java.util.List"%>
<%@page import="sample.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleadmin.css">
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>

        <!--Header-->
        <div class="row" id="header">

            <div class="col-md-6 " id="headerleft">                  
                <div id="divphone"><span class="fa fa-phone-square"></span> 0923 81 73 67 </div>
                <div><span class="fa fa-envelope"></span> hieuvhse161967@fpt.edu.vn</div>
            </div>
            <div class="col-md-6 " id="headerright">
                <a href="https://www.facebook.com/hieu.vuong.7967/"><span class="fab fa-facebook-square"></span></a>
                <a id="insta" href="https://www.instagram.com/henxaoxaott/"><span class="fab fa-instagram-square"></span></a>
            </div>
        </div>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li><form action="MainController">
                                <input oninput="searchByName(this)" type="text" name="search" placeholder="User's name"/>
                                <input id="search" type="submit" name="action" value="Search" />
                            </form></li>

                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li><form action="MainController">
                                <input id="logout" type="submit" name="action" value="Logout"/>
                            </form></li>

                    </ul>
                </div>
            </div>
        </nav> <!--ket thuc navbar -->
        <div class="row">
            <div class="col-md-12">
                <!--Bắt đầu slider-->
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="item active" id="adminslider">
                            <h2>
                                <span><%= loginUser.getFullName()%></span>
                            </h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            <!-- Ket thuc header -->
            <!-- container -->
 
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
                            <th>Role ID</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Password</th>
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
                            <td><%= count++%></td>
                            <td>
                                <input type="text" name="userID" value="<%= user.getUserID()%>" readonly=""/>
                            </td>
                            <td>
                                <input type="text" name="fullName" value="<%= user.getFullName()%>" required=""/>
                            </td>
                            <td>
                                <input type="text" name="roleID" value="<%= user.getRoleID()%>" required=""/>
                            </td>
                            <td>
                                <input type="text" name="Email" value="<%= user.getEmail()%>" required=""/>
                            </td>
                            <td>
                                <input type="text" name="address" value="<%= user.getAddress()%>" required=""/>
                            </td>
 
                            <td><%= user.getPassword()%></td>
                            <!--delete-->
                            <td>
                                <a href="MainController?search=<%= search%>&action=Delete&userID=<%= user.getUserID()%>">Delete </a>
                            </td>
                            <!--update-->
                            <td>
                                <input type="submit" name="action" value="Update"/>
                                <input type="hidden" name="search" value="<%= search%>"/>
                            </td>
                        </tr>
                    </form>

                        
                    
                    <%
                        }
                    %>
                    </tbody>
                </table>
                <%
                    String error = (String) request.getAttribute("ERROR");
                    if (error == null) {
                        error = "";
                    }
                %>
                <%= error%>
                <%
                    }
                %>



            <div id="CONTACT" class="footer">
                <div class="row">
                    <div>
                        <p>Email : hieuvhse161967@fpt.edu.vn</p>
                        <p>Address: Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Thành phố Hồ Chí Minh</p>
                        <h5>&copy; Copyright 2022. OiShiMilkTea.VN</h5>
                    </div>
                </div>

            </div>
                
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script>
    </body>
</html>
