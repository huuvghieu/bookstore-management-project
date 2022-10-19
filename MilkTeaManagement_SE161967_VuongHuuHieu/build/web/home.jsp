<%-- 
    Document   : home
    Created on : Jun 14, 2022, 1:51:22 PM
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
        <link rel="stylesheet" href="css/stylecss.css">
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
        <title>OiShi MilkTea</title>
    </head>
    <body>     
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
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
                    <a class="navbar-brand" href="home.jsp">HOME</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li><a  href="#ABOUTUS">ABOUT US</a></li>
                        <li><a  href="MainController?action=SEE+ALL">MENU</a ></li>
                        <li><a  href="#CONTACT">CONTACT</a></li>
                    </ul>
                    <%
                        if (user == null) {
                    %>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="createUser.jsp"><span class="fa fa-user"></span> Sign Up</a></li>
                        <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Sign In</a></li>
                    </ul>
                    <%
                        }
                    %>

                    <%
                        if (user != null) {
                    %>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="userOrder.jsp"><span class="fa fa-user"></span> Welcome <%= user.getFullName()%> </a></li>
                        <li><form action="MainController">
                                <input id="logout" type="submit" name="action" value="Logout"/>
                            </form></li>
                    </ul>
                    <%
                        }
                    %>
                </div>
            </div>
        </nav> <!--ket thuc navbar -->
        <div class="row">
            <div class="col-md-12">
                <!--Bắt đầu slider-->
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="item active">
                            <img id="slider1" src="images/slider01.jpg" alt="Oishimilktea">
                        </div>
                    </div>
                </div>
            </div>
            <!-- Ket thuc header -->
            <!-- container -->
            <div class="container">
                <div class="row bestseller">
                    <div>
                        <h2 class="bestsellerh2">
                            <span>Best Seller</span>
                        </h2>
                    </div>
                </div>

                <div class="row bestsellerdetail">
                    <div class="col-md-3 outsidedetails">
                        <div class="eachdetails">
                            <div class="divdetail">
                                <img src="images/olong.png"/>
                                <div class="nameofeachdetails">
                                    <a href="MainController?action=SEE+ALL"> Oolong Milk Tea</a>
                                    <p>40.000 ₫</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 outsidedetails">
                        <div class="eachdetails">
                            <div class="divdetail">
                                <img src="images/matcha.png"/>
                                <div class="nameofeachdetails">                                 
                                    <a href="MainController?action=SEE+ALL">Matcha Milk Tea</a>
                                    <p>40.000 ₫</p>                                 
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="col-md-3 outsidedetails">
                        <div class="eachdetails">
                            <div class="divdetail">
                                <img src="images/creme-brulee-late.png"/>
                                <div class="nameofeachdetails ">
                                    <a href="MainController?action=SEE+ALL">Creme Brulee Latte</a>
                                    <p>45.000 ₫</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 outsidedetails">
                        <div class="eachdetails">
                            <div class="divdetail">
                                <img src="images/Trà-Bí-Đao-2.png"/>
                                <div class="nameofeachdetails">
                                    <a href="MainController?action=SEE+ALL">Winter Melon Tea</a>
                                    <p>32.000 ₫</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <form action="MainController" id="seeall">
                    <input id="inputseeall" type="submit" name="action" value="SEE ALL"/>
                </form>  

            </div>
        </div>
        <div id="ABOUTUS" class="containershop">
            <div class="row aboutus">
                <div>
                    <h2 class="aboutush2">
                        <span>ABOUT US</span>
                    </h2>
                </div>
            </div>

            <div class="row aboutus2">

                <div class="col-md-6 aboutuscontent">
                    <img src="images/Hình-CH.jpg"/>
                </div>

                <div class="col-md-6">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d40435.786841164605!2d106.77305502294195!3d10.834638619009457!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xb752b24b379bae5e!2sFPT%20University%20HCMC!5e0!3m2!1sen!2s!4v1655738910367!5m2!1sen!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>

            </div>
        </div>


        <div id="CONTACT" class="footer">
            <div class="row">
                <div>
                    <p>Email : hieuvhse161967@fpt.edu.vn</p>
                    <p>Address: Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Thành phố Hồ Chí Minh</p>
                    <h5>&copy; Copyright 2022. OiShiMilkTea.VN</h5>
                </div>
            </div>

        </div>

    </body>
</html>
