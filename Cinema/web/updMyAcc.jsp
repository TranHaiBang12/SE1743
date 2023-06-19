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
                margin-top: 10px;
            }

            input {
                margin-left: 5px;
            }
            #cngPass{
                display: none;
            }
            
            .ms{
                margin-top: 10px;
                margin-bottom: 10px;
                font-size: 20px;
                color: red;
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
                            <div class = "ms">${requestScope.ms}</div>
                            <form action = "updacc" method = "post">
                                <label id = "gen">Giới tính: </label>
                                Nam<input type ="radio" name ="gen" value = "1" ${requestScope.gen == 1 ?"checked":""}/>
                                Nữ<input type ="radio" name ="gen" value = "0" ${requestScope.gen == 0 ?"checked":""}/>
                                Khác<input type ="radio" name ="gen" value = "-1" ${requestScope.gen == -1 ?"checked":""}/>
                                <div>Ngày sinh: <span class = "rd"><input type = "date" name ="dob" required placeholder = "${requestScope.dob}"/></span></div>
                                <div>Số điện thoại: <span class = "rd"><input type = "text" name ="sdt" required placeholder = "${requestScope.acc.getPhone()}"/></span></div>
                                <div>Email: <span class = "rd"><input type = "text" required name ="email" placeholder = "${requestScope.acc.getEmail()}"/></span></div>
                                <div>Thành phố sinh sống: <span class = "rd"><input type = "text" name ="city" required placeholder = "${requestScope.acc.getCity()}"/></span></div>
                                <div><input type ="button" class ="a" value ="Thay đổi mật khẩu" onclick = "changeP()"/></div>
                                <div id = "cngPass">
                                    <div>Nhập mật khẩu mới: <span class = "rd"><input type = "password" id ="pass" name ="pass"/></span></div><!-- comment -->
                                    <div>Nhập lại mật khẩu mới: <span class = "rd"><input type = "password" id ="repass" name ="repass" /></span></div>
                                </div>
                                <div>
                                    <a href="updacc"><input type ="submit" class ="a"  value ="UPDATE"/></a>
                                </div>
                            </form>
                        </div>
                        </div>
                        <div id = "footer">
                            <%@include file = "footer.jsp" %>
                        </div>
                        <script type = "text/javascript">
                            function changeP() {
                                if (String(document.getElementById("cngPass").style.display) === 'none') {
                                    document.getElementById("cngPass").style.display = 'block';
                                    document.getElementById("pass").required = true;
                                    document.getElementById("repass").required = true;
                                } else if (String(document.getElementById("cngPass").style.display) === 'block') {
                                    document.getElementById("cngPass").style.display = 'none';
                                    document.getElementById("pass").required = false;
                                    document.getElementById("repass").required = false;
                                }
                                else {
                                    document.getElementById("cngPass").style.display = 'block';
                                    document.getElementById("pass").required = true;
                                    document.getElementById("repass").required = true;
                                }
                            }
                        </script>
                        </body>
                        </html>
