<%-- 
    Document   : createUser
    Created on : Jun 21, 2022, 6:59:40 AM
    Author     : ownhi
--%>

<%@page import="sample.users.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
        <link href="css/stylecreateuser.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="main-container">
            <div class="container">
                <h1>Sign Up to OiShi</h1>
                <%
                    UserError userError = (UserError) request.getAttribute("USER_ERROR");
                    if (userError == null) {
                        userError = new UserError();
                    }
                %>
                <form action="MainController">
                    <label>User ID</label> <input type="text" name="userID" required=""/>
                    <%= userError.getUserID()%>
                    <label>Full Name</label><input type="text" name="fullName" required=""/>
                    <%= userError.getFullName()%>
                    <label>Role ID</label><input type="text" name="roleID" value="US" readonly=""/>
                    <label>Email</label><input type="text" name="Email" required=""/>
                    <%= userError.getEmail()%>
                   <label>Address</label><input type="text" name="address" required=""/>
                    <%= userError.getAddress()%>
                    <input type="hidden" name="status" value="1"/>
                    <label>Password</label><input type="password" name="password" required=""/>
                    <label>Confirm</label><input type="password" name="confirmpass" required=""/>
                    <%= userError.getConfirmpass()%></br>


                    <!-- reCAPTCHA -->
                    <div class="g-recaptcha" data-sitekey="6LcFYVEgAAAAAJlgWakyxcperpyV-08I3nmGza6V" data-callback="recaptcha_callback"></div>
                    <input id="createcheck" type="submit" name="action" value="Create" disabled=""/>
                    <input id="reset" type="reset" value="Reset" />
                    <a href="home.jsp">Home</a>
                </form>
            </div>
        </div>

        <script>
            function recaptcha_callback() {
                var checksubmit = document.querySelector('#createcheck');
                checksubmit.removeAttribute('disabled');
                document.getElementById("createcheck").style.color = "#33A551";
            }
        </script>

        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </body>
</html>
