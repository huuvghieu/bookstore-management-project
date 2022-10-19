<%-- 
    Document   : login
    Created on : Jun 10, 2022, 10:55:23 PM
    Author     : ownhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/stylelogin.css">
    </head>
    <body>
        <div class="main-container">
            <div class="container">
                <h1>Welcome To Oishi MilkTea</h1>
               
                <form action="MainController" method="POST">
                    <label> User ID </label>
                    <input class="submitinput" type="text" name="userID" required="" />
                    <label> Password </label>
                    <input class="submitinput" type="password" name="password" required="" /><br>

                    <!-- ReCapcha -->
                    <div class="g-recaptcha" data-sitekey="6LcFYVEgAAAAAJlgWakyxcperpyV-08I3nmGza6V" data-callback="recaptcha_callback"></div>
                    <input  id="submit" type="submit" name="action" value="Login" disabled=""/>
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/MilkTeaManagement_SE161967_VuongHuuHieu/login-google&response_type=code
    &client_id=580870250573-u7pg2d7dbdeqe4lnhr96703ltiouks4h.apps.googleusercontent.com&approval_prompt=force">Login with Google</a>                     
                    <a   href="createUser.jsp">Sign Up</a>
                    <input  id="reset" type="reset" value="Reset"/>
                    <a href="home.jsp">Home</a>
                </form>
               
            </div>
        </div>
            <script>
                    function recaptcha_callback() {
                        var checksubmit = document.querySelector('#submit');
                        checksubmit.removeAttribute('disabled');
                        document.getElementById("submit").style.color = "#33A551";
                    }
                </script>

                <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </body>
    <%
        String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }
    %>

    <div id="error"><%= error%></div>
</html>
