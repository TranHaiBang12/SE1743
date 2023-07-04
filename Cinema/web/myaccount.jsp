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
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                margin: 0 auto;
                font-size: 20px;
                margin-left: 550px;
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
                                <div>Tên đăng nhập: <span class = "rd">${requestScope.acc.getUserName()}</span></div>
                                <div>Giới tính: <span class = "rd">${requestScope.acc.getGender()}</span></div>
                                <div>Ngày sinh: <span class = "rd">${requestScope.dob}</span></div>
                                <div>Số điện thoại: <span class = "rd">${requestScope.acc.getPhone()}</span></div>
                                <div>Email: <span class = "rd">${requestScope.acc.getEmail()}</span></div>
                                <div>Thành phố sinh sống: <span class = "rd">${requestScope.acc.getCity()}</span></div>
                                <div>Số lần mua hàng: <span class = "rd">${requestScope.totalOrd}</span></div>
                                <div>Điểm tích lũy: <span class = "rd">${requestScope.point}</span></div>
                                <div>
                                    <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                                        <a href = "transact?acc=${requestScope.acc.getUserName()}"><input type ="button" value ="LỊCH SỬ GIAO DỊCH"/></a>
                                        </c:if>
                                        <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() != 3}">
                                        <a href = "transact"><input type ="button" value ="LỊCH SỬ GIAO DỊCH"/></a>
                                        </c:if>
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
                        </c:if>
                        <c:if test = "${requestScope.accE != null}">
                            <div class = "IN4">
                                <div>
                                    <img src ="${requestScope.accE.getImg()}">
                                </div>
                                <div class = "eIN4">
                                    <div>Tên đăng nhập: <span class = "rd">${requestScope.accE.getUsername()}</span></div>
                                    <div>Họ: <span class = "rd">${requestScope.accE.getLastName()}</span></div>
                                    <div>Tên: <span class = "rd">${requestScope.accE.getFirstName()}</span></div>
                                    <div>Giới tính: <span class = "rd">${requestScope.accE.getGender()}</span></div>
                                    <div>CCCD: <span class = "rd">${requestScope.accE.getCccd()}</span></div>
                                    <div>Ngày sinh: <span class = "rd">${requestScope.dob}</span></div>
                                    <div>Số điện thoại: <span class = "rd">${requestScope.accE.getPhone()}</span></div>
                                    <div>Email: <span class = "rd">${requestScope.accE.getEmail()}</span></div>
                                    <div>Địa chỉ: <span class = "rd">${requestScope.accE.getAddress()}</span></div>
                                    <div>Ngày vào làm: <span class = "rd">${requestScope.hiredDate}</span></div>
                                    <div>Vị trí: <span class = "rd">${requestScope.accE.getPosition()}</span></div>
                                    <div>Rạp làm việc: <span class = "rd">${requestScope.cin.getCinName()}</span></div>
                                    <div>Số lần mua hàng: <span class = "rd">${requestScope.totalOrd}</span></div>
                                        <c:if test = "${requestScope.mng != null}">
                                        <div>Quản lý: <span class = "rd">${requestScope.mng.getFirstName()}</span></div>
                                        </c:if>
                                    <div>Điểm tích lũy: <span class = "rd">${requestScope.point}</span></div>
                                    <div>
                                        <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                                            <a href = "transact?acc=${requestScope.accE.getUsername()}"><input class ="t" type ="button" value ="LỊCH SỬ GIAO DỊCH"/></a>
                                            </c:if>
                                            <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() != 3}">
                                            <a href = "transact><input class ="t" type ="button" value ="LỊCH SỬ GIAO DỊCH"/></a>
                                        </c:if>
                                        <input type ="button" class ="t" onclick ="check()" value ="UPDATE"/>
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
                            </c:if>
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
                                if (String(document.getElementById("pass").value) === pass) {
                                    window.location = "updacc";
                                } else {
                                    document.getElementById("ms").style.display = 'block';
                                    document.getElementById("ms").innerHTML = "Sai mật khẩu, vui lòng thử lại";
                                    document.getElementById("pass").value = "";
                                }

                            }
                        </script>
                        </body>
                        </html>
