<%-- 
    Document   : login.jsp
    Created on : May 29, 2023, 7:47:38 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ĐĂNG NHẬP</title>
    
        <link rel="stylesheet" href="style.css"/>
        <style>
            .body{
                background-color: white;
                margin-top: 0px;
            }

            .loginForm label{
                font-weight: bold;
            }

            .loginForm{
                padding: 25px;
                font-size: 18px;
            }

            .loginTitle {
                background-color: red;
                color: white;
                padding: 15px;
                text-align: center;
                width: 540px;
                display: flex;
                margin-left: 10px;
            }

            .loginTitle a{
                margin-left: 15px;
                margin-right: 65px;
                font-weight: bold;
                text-decoration: none;
                color: white;
            }

            .dn {
                text-decoration: none;
                position: relative;
            }

            .dn:after {
                content: '';
                width: 100%;
                position: absolute;
                left: 0;
                bottom: -17px;
                border-width: 0 0 5px;
                border-style: solid;
            }

            .loginForm input {
                width: 500px;
                height: 25px;
                margin-top: 10px;
                margin-bottom: 10px;
            }

            #logBtn{
                background-color: red;
                color: white;
                font-weight: bold;
                padding-top: 15px;
                padding-bottom: 30px;
                border: 1px solid black;
                border-radius: 15px;
                cursor: pointer;
            }
            
            .header {
            }
            
            .body .logo img{
                width: 250px;
                height: 250px;
            }

            .body{
                width: 100%;
                margin-top: 0px;
                display: flex;
                justify-content: space-between;
                border-top: 5px dotted red;
                border-bottom: 5px dotted red;
            }
            
            
            
            .logn {
                padding-top: 30px;
                padding-bottom: 30px;
            }




        </style>
    </head>
    <body>
        <div class = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "logn">
                <div class = "loginTitle">
                    <a href = "login" class = "dn">ĐĂNG NHẬP</a>
                    <a href = "signup">ĐĂNG KÝ</a>
                </div>
                <div class ="loginForm">
                    <form action = "login" method = "post">
                            <label for = "user">Email hoặc số điện thoại</label>
                            <br/><!-- comment -->
                            <input type ="text" id ="user" name ="user" placeholder ="Email hoặc số điện thoại"/>

                            <br/><!-- comment -->

                            <label for = "pass">Mật khẩu</label>
                            <br/><!-- comment -->
                            <input type ="password" id ="pass" name ="pass" placeholder ="Mật khẩu"/>

                            <br/>

                            <input id ="logBtn" type ="submit" value ="ĐĂNG NHẬP"/>
                    </form>
                </div>
            </div>
            <div class = "logo">
                <img src = "images/logoCinema.png">
            </div>
        </div>
        <div class = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
