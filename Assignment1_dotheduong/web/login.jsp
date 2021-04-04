<%-- 
    Document   : login
    Created on : Jan 5, 2021, 11:26:05 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login JSP Page</title>
        <link rel="stylesheet" href="CSS\style.css">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="331506458814-k60q43d16pbfk6c6ejs2cmjm0mkm3isi.apps.googleusercontent.com">
    </head>
    <body>
        <h1>HANA SHOP</h1>  
        <form action="MainController" method="POST">
            User ID:<input type="text" name="txtUserID"/>
            <%=request.getAttribute("errorUserID")==null?"" : request.getAttribute("errorUserID")%>
            <c:if test="${requestScope.errorUserID!=null && not empty requestScope.errorUserID}">
                <h1>${requestScope.errorUserID}</h1>
            </c:if>
            </br>
            Password: <input type="password" name="txtPassword"/>
            <%=request.getAttribute("errorUserPassword")==null?"" : request.getAttribute("errorUserPassword")%>
             <c:if test="${requestScope.errorUserPassword!=null && not empty requestScope.errorUserPassword}">
                <h1>${requestScope.errorUserPassword}</h1>
            </c:if>
            </br>
            <input type="submit" name="btnAction" value="Login"/>
            <input type="reset" value="Reset"/>
        </form>
        <a href="MainController?btnAction=Search">Shopping Page</a>
        <div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
        <script>
            function onSignIn(googleUser) {
                // Useful data for your client-side scripts:
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                    console.log('User signed out.');
                });
                var profile = googleUser.getBasicProfile();
                window.location.href = 'MainController?btnAction=LoginGmail&txtUserID=' + profile.getId() + '&txtEmail=' + profile.getEmail() + '&txtFullName=' + profile.getName();
            }
        </script>
    </body>
</html>
