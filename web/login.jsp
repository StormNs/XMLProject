<%-- 
    Document   : login
    Created on : Jun 22, 2017, 11:18:10 AM
    Author     : StormNs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body style="margin: 0px" onload="">
        <jsp:include page="template/header.jsp"/>
        <div style="height: 310px">
            <h1>Welcome to Login</h1>
            <form action="DispatchServlet" method="POST">
                <input type="text" value="" name="txtUsername"/>
                <input type="password" value="" name="txtPassword"/>
                <input type="submit" value="LOGIN" name="btnAction" />
                <!--<img src="/XMLProject/asset/play.png"/>-->
            </form>
            <img src="FileServlet/F:\NetBean_Project\img/wisky/logo-new.png" />
        </div>
        <jsp:include page="template/footer.jsp"/>
    </body>
</html>
