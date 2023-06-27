<%-- 
    Document   : addEmp
    Created on : Jun 26, 2023, 8:20:22 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css"/>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <style>
            #ms{
                color: red;
                margin-bottom: 15px;
            }

            .IN4 {
                display: flex;
                flex-direction: row;
                justify-content: flex-start;
                margin-left: 100px;


            }

            .IN4 img{
                width: 470px;
                height: 600px;
            }

            .eIN4 {
                padding-top: 20px;
                font-size: 20px;
                margin-left: 80px;
                padding-bottom: 10px;
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
            <div class = "IN4">
                <c:if test = "${requestScope.img != null}">
                    <div>
                        <img src ="${requestScope.img}">
                    </div>
                </c:if>
                <div class = "eIN4">

                    <div class = "ms">${requestScope.ms}</div>
                    <form id ="frm" action = "addemp" method = "post">
                        <c:if test = "${requestScope.empID != null}">
                            <div>Mã nhân viên: 

                                <input type ="text" required value ="${requestScope.empID}" name ="user"/>
                            </div>
                        </c:if>

                        <div>Tên đăng nhập: 

                            <input type ="text" required value ="${requestScope.user}" name ="user"/>
                        </div>
                        <div>Họ: 
                            <input type ="text" required value ="${requestScope.ln}" name ="ln"/>
                        </div>
                        <div>Tên: 
                            <input type ="text" required value ="${requestScope.fn}" name ="fn"/>
                        </div>
                        <div>Giới tính: 
                            Nam<input type ="radio" class ="k" name ="gen" value = "1" ${requestScope.gen.equals("Nam") ?"checked":""}/>
                            Nữ<input type ="radio" class ="k" name ="gen" value = "0" ${requestScope.gen.equals("Nữ") ?"checked":""}/>
                            Khác<input type ="radio" class ="k" name ="gen" value = "-1" ${requestScope.gen.equals("Khác") ?"checked":""}/>
                        </div>
                        <input type ="text" hidden id ="check" name ="check" value ="${ requestScope.check != null?(requestScope.check):0}"/>
                        <input type ="text" hidden id ="check1" name ="check1" value ="0"/>
                        <input type ="text" hidden id ="id" name ="id" value ="${requestScope.id}"/>
                        <div>CCCD:
                            <input type ="text" required value ="${requestScope.cccd}" name ="cccd"/>
                        </div>
                        <div>Ngày sinh: 
                            <input type ="date" required name ="dob"/>
                        </div>
                        <div>Số điện thoại:
                            <input type ="text" required value ="${requestScope.sdt}" name ="sdt"/>
                        </div>
                        <div>Email: 
                            <input type ="text" required value ="${requestScope.email}" name ="email"/>
                        </div>
                        <div>Địa chỉ: 
                            <input type ="text" required value ="${requestScope.address}" name ="address"/>
                        </div>
                        <div>Rạp làm việc:
                            <select name = "cin" id ="ciN" onchange ="cnge()">
                                <c:forEach items = "${requestScope.allCin}" var = "i">
                                    <option value ="${i.getCinID()}" ${(requestScope.cin_raw != null && requestScope.cin_raw == i.getCinID())?"selected":""}>${i.getCinName()}</option>
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

                                        <option value ="${i}" ${(requestScope.position.equals(i))?"selected":""}>${i}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <c:if test = "${requestScope.mng != null || requestScope.mn != null}">
                            <c:if test = "${requestScope.mn == 'Nhân Viên'}">
                                <div>Quản lý:
                                    <select name = "mng">
                                        <c:forEach items = "${requestScope.allM}" var = "i">
                                            <option value ="${i.getEmpID()}" ${(requestScope.mngID_raw != null && requestScope.mn != null && requestScope.mn.equals("Nhân Viên") && requestScope.mngID_raw == i.getEmpID())?"selected":""}>${i.getFirstName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                            <c:if test = "${requestScope.mn != 'Nhân Viên'}">
                                <input hidden type ="text" name ="mngD" value ="0"/>
                            </c:if> 
                        </c:if>
                        <div>Ảnh:
                            <input type ="text" name ="img" value ="${requestScope.img}"/>
                        </div>
                        <div>Lương(.000đ):
                            <input type ="number" name ="salary" min ="0" value ="${requestScope.salary}"/>
                        </div><!-- comment -->
                        <div>Vai trò trong hệ thống:
                            <select name ="role">
                                <c:forEach items = "${requestScope.allR}" var = "i">
                                    <option value ="${i.getRole()}" ${(requestScope.role_raw!= null && requestScope.role_raw == i.getRole())?"selected":""}>${i.getRoleName()}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <c:if test = "${requestScope.mp != null}">
                            <div>Mật khẩu:
                                <input type ="text" readonly value ="${requestScope.pass}" name ="sdt"/>
                            </div>
                        </c:if>
                        <c:if test = "${requestScope.mp == null}">
                            <div>Nhập mật khẩu mới: <span class = "rd"><input type = "password" required id ="pass" name ="pass"/></span></div><!-- comment -->
                            <div>Nhập lại mật khẩu mới: <span class = "rd"><input type = "password" required id ="repass" name ="repass"/></span></div>
                        </c:if>
                        <c:if test = "${requestScope.mp == null}">
                            <div>
                                <input type ="submit" class ="t"  value ="ADD"/>
                            </div>
                        </c:if>
                        <c:if test = "${requestScope.mp != null}">
                            <div>
                                <a href = "addemp"><input type ="button" class ="t"  value ="ADD MORE"/></a>
                            </div>
                        </c:if>
                    </form>
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

            if (Number(document.getElementById("check").value) === 1) {
                document.getElementById("upd").style.display = 'block';
                document.getElementById("IN4").style.display = 'none';
            }
        </script>
    </body>
</html>
