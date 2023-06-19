<%-- 
    Document   : myaccount
    Created on : Jun 19, 2023, 11:59:39 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <<link rel="stylesheet" href="style.css"/>
        <style>
            .uName{
                font-size: 40px;
                font-weight: bold;
                text-align: center;
                padding-top: 20px;
                margin-bottom: 20px;
            }

            .rd{
                color: red;
            }

            .uIn4 {
                text-align: center;
                font-size: 20px;
            }

            .uIn4 div{
                margin-top: 15px;
            }

            .a{
                width: 200px;
                height: 30px;
                background-color: red;
                color: white;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "uName">Tài Khoản: <span class = "rd">${sessionScope.account.getUserName()}<span></div>
                        <div class="uIn4">
                            <form action = "updacc" method = "post">
                                <div>Tên đăng nhập: <span class = "rd"><input type = "text" required placeholder = "${requestScope.acc.getUserName()}"/></span></div>
                                <div>Giới tính: <span class = "rd"><input type = "text" required placeholder = "${requestScope.acc.getGender()}"/></span></div>
                                <div>Ngày sinh: <span class = "rd"><input type = "text" required placeholder = "${requestScope.acc.getDob()}"/></span></div>
                                <div>Số điện thoại: <span class = "rd"><input type = "text" required placeholder = "${requestScope.acc.getPhone()}"/></span></div>
                                <div>Email: <span class = "rd"><input type = "text" required placeholder = "${requestScope.acc.getEmail()}"/></span></div>
                                <div>Thành phố sinh sống: <span class = "rd"><input type = "text" required placeholder = "${requestScope.acc.getCity()}"/></span></div>
                                <div>
                                    <a href="updacc"><input type ="submit" class ="a"  value ="UPDATE"/></a>
                                </div>
                            </form>
                        </div>
                        </div>
                        <div id = "footer">
                            <%@include file = "footer.jsp" %>
                        </div>
                        </body>
                        </html>
