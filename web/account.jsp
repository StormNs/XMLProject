<%-- 
    Document   : login
    Created on : Jun 22, 2017, 11:18:10 AM
    Author     : StormNs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            .Form{
                position: relative;
                background: white;
                width: 30%;
                margin: 0 auto;
                padding: 20px;
                margin-bottom: 10px;
                text-align: center;
                transition-duration: 1s;
            }
            .lgIn-field{
                width: 82%;
                height: 37px;
                border: none;
                background-color: #F2F2F2;
                padding: 0% 2% 0% 3%;
            }
            .lgIn-btn{
                width: 87%;
                border: none;
                height: 37px;
                background-color: #77AAAD;
                color:white;
            }
            .lgIn-btn:hover{
                background-color: #218380;
            }
            .regis{
                font-size: 84%;
                color:#b7b0b0;
            }
            ::-webkit-input-placeholder {
                color: #b7b0b0;
            }
            .snUpLink{
                color:#218380;
                text-decoration: none;
                cursor: pointer;
            }
            .sUpbackgr{
                background-color:#566270;
            }
            .icon-lgIn{
                margin-bottom: 20px;
                text-align: center;
                width: 101%;
                color: #58C9B9;
            }
            .rotate-icon{
                cursor: pointer;
                transition-duration: 0.5s;
                text-decoration: none;
                color: #58C9B9;
            }
            .rotate-icon:hover{
                transform: rotate(90deg);
            }
            .hid-form{
                display: none;
            }

            .alert {
                padding: 20px;
                background-color: #f44336;
                color: white;
                width: 78%;
                margin: 0px auto;

            }
            .success {
                padding: 20px;
                background-color: #58ef5d;
                color: white;
                width: 78%;
                margin: 0px auto;

            }
            .btn_close{
                cursor: pointer;
                float: right;
                font-weight: bold;
            }
        </style>

    </head>
    <body style="margin: 0px; margin-top:20px" class="sUpbackgr">
        <div style="display: none;">

            <jsp:include page="template/header.jsp"/>
        </div>
        <div>
            <div class="icon-lgIn">
                <a class="fa fa-play-circle brand-icon rotate-icon" href="main.jsp"
                   style="font-size:74px !important;"></a>
            </div>
            <div class="Form" id="LogIn_F">
                <form action="DispatchServlet" method="POST">
                    <p> <input class="lgIn-field" required="true" type="text" value="" 
                               name="txtUsername" placeholder="Username or Email"/></p>
                    <p> <input class="lgIn-field" required="true" type="password" value=""
                               name="txtPassword" placeholder="Password"/></p>
                    <p><input class="lgIn-btn" type="submit" 
                              value="LOGIN" name="btnAction" /> </p>
                    <p class="regis">Not registered? 
                        <a class="snUpLink" 
                           onclick="SignUp()">Create an account</a></p>
                </form>
                <div class="success hid-form" id="success">
                    <span class="btn_close" onclick="this.parentElement.style.display = 'none'">&times;</span>
                    <div id="success_message">Sign Up Successed</div>
                </div>

            </div>
            <div class="Form hid-form" id="SignUp_F">
                <form action="DispatchServlet" method="POST">
                    <p> <input class="lgIn-field" required="true" type="text" value="" 
                               name="txtsgnUpUsername" placeholder="Username"/></p>
                    <p> <input class="lgIn-field" required="true" type="password" value="" 
                               name="txtsgnPassword" placeholder="Password"/></p>
                    <p> <input class="lgIn-field" required="true" type="email" value=""
                               name="txtsgnEmail" placeholder="Email"/></p>
                    <p><input class="lgIn-btn" type="submit"
                              value="REGISTER" name="btnAction" /> </p>
                    <p class="regis"><a onclick="LogIn()" class="snUpLink">Already have an account?</a></p>
                </form>
                <div class="alert hid-form" id="error">
                    <span class="btn_close" onclick="this.parentElement.style.display = 'none'">&times;</span>
                    <div id="error_message">Username or Email already existed!</div>
                </div>
            </div>

        </div>

        <script>

            document.addEventListener("DOMContentLoaded", function (event) {
                checkResult();

            })
            function LogIn() {
                var logI = document.getElementById('LogIn_F');
                var sign = document.getElementById('SignUp_F');
                logI.className = logI.className.replace("hid-form", "");
                sign.className += " hid-form";
            }

            function SignUp() {
                var logI = document.getElementById('LogIn_F');
                var sign = document.getElementById('SignUp_F');
                logI.className += " hid-form";
                sign.className = sign.className.replace("hid-form", "");
            }

            function checkResult() {
                var result = "" + '${requestScope.Result}';
                var error = document.getElementById('error_message');
                var eContain = document.getElementById('error');
                var success = document.getElementById('success');
                var success_mess = document.getElementById('success_message');
                if (result === "existed") {
                    error.innerHTML = "Username or Email already existed!";
                    eContain.className = eContain.className.replace("hid-form", " ");
                    SignUp();
                } else if (result === "invalidField") {
                    error.innerHTML = "Error";
                    eContain.className = eContain.className.replace("hid-form", " ");
                    SignUp();
                } else if (result === "successed") {
                    LogIn();
                    success_mess.innerHTML = "Successed";
                    success.className = success.className.replace("alert", " success")
                    success.className = success.className.replace("hid-form", " ");
                } else if (result === "Wrong_field") {
                    LogIn();
                    success_mess.innerHTML = "Wrong username/email or password";
                    success.className = success.className.replace("success", " alert")
                    success.className = success.className.replace("hid-form", " ");
                }
            }
        </script>

        <!--<img src="FileServlet/F:\NetBean_Project\img/wisky/logo-new.png" />-->
        <%--<jsp:include page="template/footer.jsp"/>--%>
    </body>
</html>
