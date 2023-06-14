<%-- 
    Document   : in4OD
    Created on : Jun 14, 2023, 10:14:05 AM
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

            table{
                margin-left: 40px;
                margin-right: 40px;
                margin-top: 20px;
                border: 1px solid black;
            }

            tr{
                text-align: center;
                border: 1px solid black;
            }

            td{
                padding: 20px;
                border: 1px solid black;
            }
            th{
                padding: 20px;
                border: 1px solid black;
            }

            .ttle{
                margin-top: 20px;
                margin-left: 40px;
                margin-right: 40px;
                font-size: 25px;
            }

            td img{
                width: 150px;
                height: 120px;
            }

            .ttAmF {
                margin-left: 40px;
                margin-top: 20px;
                font-size: 20px;
                font-weight: bold;
            }

            .doan {
                margin-left: 200px;
            }

            .ve{
                margin-left: 200px;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "uName">Tài Khoản: <span class = "rd">${sessionScope.account.getUserName()}<span></div>
                        <div class = "in4O">
                            <div class = "ttle">
                                1. Thông Tin Khách Hàng
                            </div>
                            <table>
                                <tr>                                
                                    <th>HỌ</th>
                                    <th>TÊN</th>
                                    <th>SỐ ĐIỆN THOẠI</th>
                                    <th>EMAIL</th>
                                    <th>QUỐC GIA</th>
                                    <th>SỐ NHÀ</th>
                                    <th>QUẬN</th><!-- <th></th> -->
                                    <th>THÀNH PHỐ</th>
                                    <th>KIỂU THANH TOÁN</th><!-- <th></th> -->
                                    <th>NGÀY THANH TOÁN</th>
                                    <th>NƠI THANH TOÁN</th>
                                </tr>

                                <c:forEach items = "${requestScope.listO}" var = "i">
                                    <tr>
                                        <td>${i.getFirstName()}</td>
                                        <td>${i.getLastName()}</td>
                                        <td>${i.getPhone()}</td>
                                        <td>${i.getEmail()}</td>
                                        <td>${i.getCountry()}</td>
                                        <td>${i.getStreet()}</td>
                                        <td>${i.getDistrict()}</td><!-- <th></th> -->
                                        <td>${i.getCity()}</td>
                                        <td>${i.getPaymentType()}</td><!-- <th></th> -->
                                        <td>${i.getPaymentDate()}</td>
                                        <td>${i.getPaymentTime()}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <div><!-- comment -->
                            <div class = "ttle">2. Sản phẩm</div>
                            <div class = "doan">
                                <div class = "ttle">a. Đồ Ăn</div>
                                <table>
                                    <tr>
                                        <th>TÊN</th>
                                        <th>LOẠI SẢN PHẨM</th><!-- comment -->
                                        <th>KHUYẾN MẠI</th><!-- comment -->
                                        <th>GIÁ</th><!-- comment -->
                                        <th>ẢNH</th>
                                        <th>SỐ LƯỢNG</th>
                                        <th>TỔNG</th>
                                    </tr>
                                    <c:set var="cnt" value="1"/>
                                    <c:forEach items = "${requestScope.listOD}" var = "j">
                                        <tr>
                                            <td>${j.getF().getFoodDescript()}</td>
                                            <td>${j.getF().getFoodType()}</td>
                                            <td>${j.getDiscount()}%</td>
                                            <td>${j.getPrice()}đ</td><!-- comment -->
                                            <td><img src="${j.getF().getImg()}"/></td>
                                            <td>${j.getQuantity()}</td>

                                            <td><span class = "rd"><label id = "f${cnt}">${j.getPrice() * j.getQuantity()}</label></span></td>
                                                    <c:set var="cnt" value="${cnt+ 1}"/>
                                        </tr>
                                    </c:forEach>
                                    <input type ="text" hidden value ="${cnt}" id = "numF"/>

                                </table>
                                <div class = "ttAmF">
                                    TỔNG GIÁ: <span class = "rd"><label id = "amF"></label></span>
                                </div>
                            </div>
                            <div class = "ve">
                                <div class = "ttle">b. Vé</div>
                                <table>
                                    <c:forEach items = "${requestScope.listOTD}" var = "k">
                                    </c:forEach>
                                </table>

                            </div>

                            <c:forEach items = "${requestScope.listTC}" var = "m">
                            </c:forEach>

                        </div>
                        <div id = "footer">
                            <%@include file = "footer.jsp" %>
                        </div>
                        <script type = "text/javascript">
                            var t = Number(document.getElementById("numF").value);
                            console.log(t);
                            var value = 0;
                            for (var i = 1; i < t; i++) {
                                value += Number(document.getElementById("f" + i).innerHTML);
                            }
                            document.getElementById("amF").innerHTML = value + "đ";
                        </script>
                        </body>
                        </html>
