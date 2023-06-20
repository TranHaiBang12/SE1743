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

            .uIn4 div input{
                width: 200px;
                height: 30px;
                background-color: red;
                color: white;
                cursor: pointer;
            }

            #pass{
                background-color: white;
                color: black;
            }

            #t{
                display: none;
            }
            
            #t input{
                cursor: pointer;
                padding-left: 10px;
                margin-top: 15px;
            }
            
            #ms{
                color: red;
                margin-bottom: 15px;
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
                            <div>Tên đăng nhập: <span class = "rd">${requestScope.acc.getUserName()}</span></div>
                            <div>Giới tính: <span class = "rd">${requestScope.acc.getGender()}</span></div>
                            <div>Ngày sinh: <span class = "rd">${requestScope.dob}</span></div>
                            <div>Số điện thoại: <span class = "rd">${requestScope.acc.getPhone()}</span></div>
                            <div>Email: <span class = "rd">${requestScope.acc.getEmail()}</span></div>
                            <div>Thành phố sinh sống: <span class = "rd">${requestScope.acc.getCity()}</span></div>
                            <div>Số lần mua hàng: <span class = "rd">${requestScope.totalOrd}</span></div>
                            <div>Điểm tích lũy: <span class = "rd">${requestScope.point}</span></div>
                            <div>
                                <a href = "transact"><input type ="button" value ="LỊCH SỬ GIAO DỊCH"/></a>
                                <input type ="button" onclick ="check()" value ="UPDATE"/>
                            </div>
                            <div id = "t">
                                    <label id = "ms" hidden></label>
                                    <label for = "pass">Mật khẩu(<span class = "rd">*</span>)</label>
                                    <br/>
                                    <input type ="password" required id ="pass" name ="pass"/>
                                    <br/><!-- comment -->
                                    <input type ="button" value ="Submit" onclick = "checkAcc('${sessionScope.account.getPassword()}')"/>
                            </div>
                        </div>
                        </div>
                        <div id = "footer">
                            <%@include file = "footer.jsp" %>
                        </div>
                        <script type = "text/javascript">
                            function check() {
                                document.getElementById("t").style.display = 'block';
                            }
                            
                            function checkAcc(pass) {
                                console.log(pass);
                                if(String(document.getElementById("pass").value) === pass) {
                                    window.location = "updacc";
                                }
                                else {
                                    document.getElementById("ms").style.display = 'block';
                                    document.getElementById("ms").innerHTML = "Sai mật khẩu, vui lòng thử lại";
                                    document.getElementById("pass").value = "";
                                }
                                
                            }
                        </script>
                        </body>
                        </html>
