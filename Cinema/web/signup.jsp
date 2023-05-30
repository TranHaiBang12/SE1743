<%-- 
    Document   : signup
    Created on : May 30, 2023, 9:23:00 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ĐĂNG KÝ</title>
    
        <<link rel="stylesheet" href="style.css"/>
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

            .dk {
                text-decoration: none;
                position: relative;
            }

            .dk:after {
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
            
            #rule{
                height:15px;
                width:20px;
                margin-right:10px;
                padding: 0px;
            }
            
            #nam{
                height:15px;
                width:20px;
                margin-top: 2px;
                margin-bottom: 12px;
                margin-right:10px;
                padding: 0px;
            }
            
            #nu{
                height:15px;
                width:20px;
                margin-right:10px;
                margin-top: 2px;
                margin-bottom: 12px;
                padding: 0px;
            }
            
            #khac{
                height:15px;
                width:20px;
                margin-right:10px;
                margin-top: 2px;
                margin-bottom: 12px;
                padding: 0px;
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
                    <a href = "login">ĐĂNG NHẬP</a>
                    <a href = "signup" class = "dk">ĐĂNG KÝ</a>
                </div>
                <div class ="loginForm">
                    <form action = "signup" method = "post">
                        <label for = "name">Tên tài khoản <span style="color:red">*</span></label>
                            <br/><!-- comment -->
                            <input type ="text" id ="name" name ="name" placeholder ="Tên tài khoản"/>

                            <br/><!-- comment -->
                            
                            <label for = "gender">Giới tính <span style="color:red">*</span>:</label>
                            <label for ="nam">Nam</label>
                            <input type ="radio" id ="nam" name ="gender" value ="1"/>
                            <label for ="nam">Nữ</label>
                            <input type ="radio" id ="nu" name ="gender" value ="0"/>
                            <label for ="nam">Khác</label>
                            <input type ="radio" id ="khac" checked name ="gender" value ="2"/>

                            <br/><!-- comment -->

                            <label for = "sdt">Số điện thoại <span style="color:red">*</span></label>
                            <br/><!-- comment -->
                            <input type ="text" id ="sdt" name ="sdt" placeholder ="Số điện thoại"/>

                            <br/>
                            
                            <label for = "email">Email <span style="color:red">*</span></label>
                            <br/><!-- comment -->
                            <input type ="text" id ="email" name ="email" placeholder ="Email"/>

                            <br/>
                            
                            <label for = "pass">Mật khẩu <span style="color:red">*</span></label>
                            <br/><!-- comment -->
                            <input type ="password" id ="pass" name ="pass" placeholder ="Mật khẩu"/>

                            <br/>
                            
                            <label for = "dob">Ngày sinh <span style="color:red">*</span></label>
                            <br/>
                            <input type ="date" id ="dob" name ="dob" placeholder ="Ngày sinh"/>
                            <br/>
                            
                            <label for = "area">Khu vực <span style="color:red">*</span></label>
                            <br/><!-- comment -->
                            <input type ="text" id ="area" name ="area" placeholder ="Khu vực"/>

                            <br/>
                            
                            <input type ="checkbox" checked id ="rule" name ="rule"><label for = "rule">Tôi đồng ý với Điều Khoản Sử Dụng </label><span style="color:red">*</span>
                            <br/>

                            <input id ="logBtn" type ="submit" value ="ĐĂNG KÝ"/>
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
