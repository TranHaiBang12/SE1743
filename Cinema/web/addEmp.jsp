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
            <form>
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
            </form> 
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
