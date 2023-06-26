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
        <<link rel="stylesheet" href="style.css"/>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "IN4">
                <div>
                    <img src ="${requestScope.accE.getImg()}">
                </div>
                <div class = "eIN4">
                    <div>Tên đăng nhập: <input type = "text"/></div>
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
                        <div>Quản lý: <span class = "rd">${requestScope.mng.getFirstName() }</span></div>
                        </c:if>
                    <div>Điểm tích lũy: <span class = "rd">${requestScope.point}</span></div>
                    <div>
                        <a href = "transact"><input class ="t" type ="button" value ="DELETE"></a>
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
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
