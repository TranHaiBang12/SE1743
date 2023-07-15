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
        <<link rel="stylesheet" href="style.css?version=1"/>
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

            .IN4 img{
                width: 470px;
                height: 600px;
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

            #upd {
                display: none;
            }

            #cngPass {
                display: none;
            }

            input {
                font-size: 20px;
            }

            select {
                font-size: 20px;
            }

            .k{
                margin-left: 10px;
            }

            .ms{
                margin-top: 20px;
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
            <div class = "uName">Tài Khoản: <span class = "rd">${requestScope.accE.getUsername()}<span></div>
                        <div id ="IN4" class = "IN4">
                            <div>
                                <img src ="${requestScope.accE.getImg()}">
                            </div>

                            <div id ="eIN4" class = "eIN4">
                                <div>Mã nhân viên: <span class = "rd">${requestScope.accE.getEmpID()}</span></div>
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
                                <div>Lương: <span class ="rd">${requestScope.salary}</span></div>
                                <div>Vai trò trong hệ thống: <span class ="rd">${requestScope.accE.getRoleName()}</span></div>
                                    <c:if test = "${requestScope.mng != null}">
                                    <div>Quản lý: <span class = "rd">${requestScope.mng.getFirstName()}</span></div>
                                    </c:if>
                                <div>Điểm tích lũy: <span class = "rd">${requestScope.point}</span></div>
                                <div>
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
                        </div> 
                        <div id = "upd">

                            <div class = "IN4">
                                <div>
                                    <img src ="${requestScope.accE.getImg()}">
                                </div>
                                <div class = "eIN4">

                                    <div class = "ms">${requestScope.ms}</div>
                                    <form id ="frm" action = "empdt" method = "post">
                                        <div>Mã nhân viên: 

                                            <input type ="text" required readonly value ="${requestScope.accE.getEmpID()}" name ="empID"/>
                                        </div>
                                        <div>Tên đăng nhập: 

                                            <input type ="text" readonly value ="${requestScope.accE.getUsername()}" name ="user"/>
                                        </div>
                                        <div>Họ: 
                                            <input type ="text" required value ="${requestScope.accE.getLastName()}" name ="ln"/>
                                        </div>
                                        <div>Tên: 
                                            <input type ="text" required value ="${requestScope.accE.getFirstName()}" name ="fn"/>
                                        </div>
                                        <div>Giới tính: 
                                            Nam<input type ="radio" class ="k" name ="gen" value = "1" ${requestScope.accE.getGender().equals("Nam") ?"checked":""}/>
                                            Nữ<input type ="radio" class ="k" name ="gen" value = "0" ${requestScope.accE.getGender().equals("Nữ") ?"checked":""}/>
                                            Khác<input type ="radio" class ="k" name ="gen" value = "-1" ${requestScope.gen == -1 ?"checked":""}/>
                                        </div>
                                        <input type ="text" hidden id ="check" name ="check" value ="${requestScope.check != null?(requestScope.check):0}"/>
                                        <input type ="text" hidden id ="check1" name ="check1" value ="0"/>
                                        <input type ="text" hidden id ="id" name ="id" value ="${requestScope.id}"/>
                                        <div>CCCD:
                                            <input type ="text" required value ="${requestScope.accE.getCccd()}" name ="cccd"/>
                                        </div>
                                        <div>Ngày sinh: 
                                            <input type ="date" required name ="dob"/>
                                        </div>
                                        <div>Số điện thoại:
                                            <input type ="text" required value ="${requestScope.accE.getPhone()}" name ="sdt"/>
                                        </div>
                                        <div>Email: 
                                            <input type ="text" required value ="${requestScope.accE.getEmail()}" name ="email"/>
                                        </div>
                                        <div>Địa chỉ: 
                                            <input type ="text" required value ="${requestScope.accE.getAddress()}" name ="address"/>
                                        </div>
                                        <div>Rạp làm việc:
                                            <select name = "cin" id ="ciN" onchange ="cnge()">
                                                <c:forEach items = "${requestScope.allCin}" var = "i">
                                                    <option value ="${i.getCinID()}" ${requestScope.cin.getCinID() == i.getCinID()?"selected":""}>${i.getCinName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div>Ngày vào làm:
                                            <input type ="date" required name ="hiredDate"/>
                                        </div>
                                        <div>Vị trí:

                                            <select onchange ="cnge()" name ="position">
                                                <c:forEach items = "${requestScope.allP}" var = "i">
                                                    <c:if test = "${requestScope.mn != null}">
                                                        <option value ="${i}" ${(i.equals(requestScope.mn))?"selected":""}>${i}</option>
                                                    </c:if>
                                                    <c:if test = "${requestScope.mn == null}">

                                                        <option value ="${i}" ${(requestScope.accE.getPosition().equals(i))?"selected":""}>${i}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <c:if test = "${requestScope.mng != null || requestScope.mn != null}">
                                            <c:if test = "${requestScope.mn == 'Nhân Viên'}">
                                                <div>Quản lý:
                                                    <select name = "mng">
                                                        <c:forEach items = "${requestScope.allM}" var = "i">
                                                            <option value ="${i.getEmpID()}" ${(requestScope.mng != null && requestScope.mn != null && requestScope.mn.equals("Nhân Viên") && requestScope.mng.getEmpID() == i.getEmpID())?"selected":""}>${i.getFirstName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </c:if>
                                            <c:if test = "${requestScope.mn != 'Nhân Viên'}">
                                                <input hidden type ="text" name ="mngD" value ="0"/>
                                            </c:if> 
                                        </c:if>
                                        <div>Ảnh:
                                            <input type ="text" name ="img" value ="${requestScope.accE.getImg()}"/>
                                        </div>
                                        <div>Lương(.000đ):
                                            <input type ="number" name ="salary" min ="0" value ="${requestScope.salary}"/>
                                        </div><!-- comment -->
                                        <div>Vai trò trong hệ thống:
                                            <select name ="role">
                                                <c:forEach items = "${requestScope.allR}" var = "i">
                                                    <option value ="${i.getRole()}" ${requestScope.accE.getRole() == i.getRole()?"selected":""}>${i.getRoleName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div><input type ="button" class ="t" value ="Thay đổi mật khẩu" onclick = "changeP()"/></div>
                                        <div id = "cngPass">
                                            <div>Nhập mật khẩu mới: <span class = "rd"><input type = "password" id ="pass" name ="pass"/></span></div><!-- comment -->
                                            <div>Nhập lại mật khẩu mới: <span class = "rd"><input type = "password" id ="repass" name ="repass" /></span></div>
                                        </div>
                                        <div>
                                            <input type ="submit" class ="t"  value ="SAVE"/>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        </div>

                        <div id = "footer">
                            <%@include file = "footer.jsp" %>
                        </div>
                        <script type = "text/javascript">
                            function cnge() {
                                var selectedValue = document.getElementById("ciN").selectedIndex;
                                document.getElementById("check").value = 1;
                                document.getElementById("check1").value = 1;
                                console.log(document.getElementById("check").value);
                                console.log(document.getElementById("check1").value);
                                document.getElementById("frm").submit();
                            }

                            function check() {
                                document.getElementById("t").style.display = 'block';
                            }
                            console.log(document.getElementById("check").value);

                            if (Number(document.getElementById("check").value) === 1) {
                                document.getElementById("upd").style.display = 'block';
                                document.getElementById("IN4").style.display = 'none';
                            }
                            function checkAcc(pass) {
                                console.log(pass);
                                if (String(document.getElementById("pass").value) === pass) {

                                    document.getElementById("upd").style.display = 'block';
                                    document.getElementById("IN4").style.display = 'none';
                                } else {
                                    document.getElementById("ms").style.display = 'block';
                                    document.getElementById("ms").innerHTML = "Sai mật khẩu, vui lòng thử lại";
                                    document.getElementById("pass").value = "";
                                }

                            }

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
