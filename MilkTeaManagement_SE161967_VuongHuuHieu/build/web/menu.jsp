<%-- 
    Document   : menu
    Created on : Jun 21, 2022, 11:13:24 PM
    Author     : ownhi
--%>

<%@page import="sample.products.CategoryDTO"%>
<%@page import="sample.products.ProductDTO"%>
<%@page import="sample.users.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/stylecssmenu.css">
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
        <title>Menu Page</title>
    </head>
    <body>      
        <%
            UserDTO user= (UserDTO) session.getAttribute("LOGIN_USER");
            List<CategoryDTO> listCategory = (List<CategoryDTO>) session.getAttribute("LIST_CATEGORY");
            List<ProductDTO> listProducts = (List<ProductDTO>) session.getAttribute("LIST_SEEALLPRO");

            if (listProducts != null && listProducts.size() > 0 && listCategory != null && listCategory.size() > 0) {
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
                        <li>
                            <a href="MainController?action=SEE+ALL">MENU</a>
                        </li>
                <%
                    for (CategoryDTO category : listCategory) {
                       
                %>                       
                <li>
                    <a href="MainController?action=Category&cateID=<%= category.getCategoryID() %>"> <%= category.getName() %> </a>
                    <form action="MainController">    
                        <input id="categorynav" type="submit" name="action" value="Category" />
                    </form>
                </li>
                
                <%
                    }
                %>
                    </ul>
                <%
                    if(user == null){    
                    %>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="createUser.jsp"><span class="fa fa-user"></span> Sign Up</a></li>
                        <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Sign In</a></li>
                        <li><a href="#"><span onclick="Showtable()" class="fa fa-search"></span>
                                <form action="MainController" id="formsearch">                                    
                                    <input type="text" name="searchpro" placeholder="Search here..." /></br>
                                    <input type="submit" name="action" value="Searchproduct"/>
                                </form>
                            </a></li>
                    </ul>
                <%
                    }
                %>     
                <%
                    if(user != null){
                    %>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="userOrder.jsp"><span class="fa fa-user"></span> Welcome <%= user.getFullName()%> </a></li>
                         <li><form action="MainController">
                                <input id="logout" type="submit" name="action" value="Logout"/>
                            </form></li>
                            <%
                                String search =(String) request.getParameter("searchpro");
                                if(search==null){
                                    search="";
                                }
                            %>
                        <li><a href="#"><span onclick="Showtable()" class="fa fa-search"></span>
                                <form action="MainController" id="formsearch">                                    
                                    <input type="text" name="searchpro" placeholder="Search here..." value="<%= search %>"/></br>
                                    <input type="submit" name="action" value="Searchproduct"/>
                                </form>
                            </a></li>
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
                        <div class="item active" id="menuslider">
                            <h2>
                                <span>MENU</span>
                            </h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Ket thuc header -->
        <!-- container -->
             <div class="viewOrder">
                 <a href="userOrder.jsp"><img src="images/viewcart.png"/></a><br>

             </div>
        <div class="container">
             <%
                 String message= (String) request.getAttribute("MESSAGE");
                 if(message== null){
                     message="";
                 }
             %>
             <div class="message">
                 <h1><%= message %></h1>
             </div>
              
            <!-- product -->
            <div class="row milkteacate">
                <%
                    for (CategoryDTO category : listCategory) {
                        for (ProductDTO product : listProducts) {
                %>
                <%
                    if (product.getCategoryID().equals(category.getCategoryID())) {

                %>

                <div class="col-12 col-md-4 milkteacatedetail">
                    <div class="eachmilktea">
                        <div class="divmilktea">
                            <img src="<%= product.getImg()%>" />
                            <div class="nameofeachdetails">
                                <p> <%= product.getName()%> </p>
                                <p><%= product.getPrice()%> ₫</p>
                           
                                <form action="MainController">
                                    <input type="hidden" name="cmbProduct" value="<%= product %> "/>
                                    <input type="number" min="1" value="1" name="cmbQuantity"/>
                                
                                <input type="submit" name="action" value="Add"/>
                                </form>                                       
                         
                            </div>
                        </div>
                    </div>
                </div>
                <%
                            }
                        }
                    }
                %>




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


        <script>
            function Showtable() {
                if (document.getElementById('formsearch').style.display == "block") {
                    document.getElementById('formsearch').style.display = "none";
                } else {
                    document.getElementById('formsearch').style.display = "block";
                }
            }
        </script>
        <%
            }
        %>
    </body>
</html>
