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
                padding-top: 40px;
                margin-bottom: 20px;
            }

            .rd{
                color: red;
            }

            .uIn4 {
                margin-left: 500px;
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

            .IN4 {
                display: flex;
                flex-direction: row;
                justify-content: flex-start;
                margin-left: 100px;
                ;

            }

            .eIN4 {
                font-size: 20px;
                margin-left: 50px;
            }

            .eIN4 div{
                margin-bottom: 20px;
            }

            input {
                font-size: 20px;
            }

            .t{
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
                        <c:if test = "${requestScope.acc != null}">
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
                        </c:if>
                        <c:if test = "${requestScope.accE != null}">

                            <div class = "IN4">
                                <div>
                                    <img src ="${requestScope.accE.getImg()}">
                                </div>
                                <div class = "eIN4">

                                    <div class = "ms">${requestScope.ms}</div>
                                    <form action = "updacc" method = "post">
                                        <div>Tên đăng nhập: 

                                            <input type ="text" required readonly value ="${requestScope.accE.getUsername()}" name ="user"/>
                                        </div>
                                        <div>Họ: 
                                            <input type ="text" required placeholder ="${requestScope.accE.getLastName()}" name ="ln"/>
                                        </div>
                                        <div>Tên: 
                                            <input type ="text" required placeholder ="${requestScope.accE.getFirstName()}" name ="fn"/>
                                        </div>
                                        <div>Giới tính: 
                                            Nam<input type ="radio" name ="gen" value = "1" ${requestScope.accE.getGender().equals("Nam") ?"checked":""}/>
                                            Nữ<input type ="radio" name ="gen" value = "0" ${requestScope.accE.getGender().equals("Nữ") ?"checked":""}/>
                                            Khác<input type ="radio" name ="gen" value = "-1" ${requestScope.gen == -1 ?"checked":""}/>
                                        </div>
                                        <div>CCCD:
                                            <input type ="text" required placeholder ="${requestScope.accE.getCccd()}" name ="cccd"/>
                                        </div>
                                        <div>Ngày sinh: 
                                            <input type ="date" required name ="dob"/>
                                        </div>
                                        <div>Số điện thoại:
                                            <input type ="text" required placeholder ="${requestScope.accE.getPhone()}" name ="sdt"/>
                                        </div>
                                        <div>Email: 
                                            <input type ="text" required placeholder ="${requestScope.accE.getEmail()}" name ="email"/>
                                        </div>
                                        <div>Địa chỉ: 
                                            <input type ="text" required placeholder ="${requestScope.accE.getAddress()}" name ="address"/>
                                        </div>

                                        <div><input type ="button" class ="a" value ="Thay đổi mật khẩu" onclick = "changeP()"/></div>
                                        <div id = "cngPass">
                                            <div>Nhập mật khẩu mới: <span class = "rd"><input type = "password" id ="pass" name ="pass"/></span></div><!-- comment -->
                                            <div>Nhập lại mật khẩu mới: <span class = "rd"><input type = "password" id ="repass" name ="repass" /></span></div>
                                        </div>
                                        <div>
                                            <input type ="submit" class ="a"  value ="UPDATE"/>
                                        </div>
                                    </form>
                                </div>
                            </c:if>
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
                                } else {
                                    document.getElementById("cngPass").style.display = 'block';
                                    document.getElementById("pass").required = true;
                                    document.getElementById("repass").required = true;
                                }
                            }
                        </script>
                        </body>
                        </html>
