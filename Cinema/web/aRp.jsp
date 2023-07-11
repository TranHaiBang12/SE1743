<%-- 
    Document   : aRp
    Created on : Jul 4, 2023, 2:23:33 PM
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
            table{
                margin-top: 20px;
                border: 1px solid black;
                margin-bottom: 20px;
                margin-left: 20px;
                margin-right: 20px;
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

            .Sttle{
                margin-left: 20px;
                font-weight: bold;
                font-size: 20px;
            }

            a{
                text-decoration: none;
                color: black;
            }

            .insider1{
                font-size: 20px;
                margin-left: 40px;
            }

            .insider1 div{
                margin-top: 10px;
                padding-bottom: 10px;
            }
            
            .SSttle {
                font-size: 20px;
                margin-top: 10px;
                font-weight: bold;
                margin-left: 40px;
                color: deeppink;
            }
            
            .Sttle{
                padding-top: 40px;
            }
            
            .body{
                padding-left: 70px;
                padding-right: 80px;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "Sttle">1. DANH SÁCH </div>
            <table>
                <tr>
                    <th>TÀI KHOẢN</th>
                    <th>SỐ LẦN MUA HÀNG</th>
                    <th>SỐ LẦN MUA HÀNG ONL</th>                   
                    <th>SỐ LẦN MUA HÀNG OFF</th>
                    <th>TỈ LỆ MUA HÀNG ONL</th>
                    <th>TỈ LỆ MUA HÀNG OFF</th>
                    <th>ĐIỂM</th>                 
                    <th>ĐIỂM ĐÃ DÙNG</th>
                    <th>TỈ LỆ DÙNG ĐIỂM</th>
                    <th>SỐ LẦN BÌNH LUẬN</th>
                    <th>HÀNH ĐỘNG</th>
                </tr>
                <c:forEach items = "${requestScope.list}" var = "i">
                    <tr>
                        <td>${i.getUserName()}</td>
                        <td>${i.getNumBuy()}</td>
                        <td>${i.getNumBuyOnl()}</td>
                        <td>${i.getNumBuyOff()}</td><!-- comment -->
                        <td>${i.getPcOnl()}%</td>
                        <td>${i.getPcOff()}%</td>
                        <td>${i.getPoint()}</td>
                        <td>${i.getPointUse()}</td>
                        <td>${i.getPcPointUse()}%</td><!-- comment -->
                        <td>${i.getNumRate()}</td>
                        <td>
                            <a href="acc?user=${i.getUserName()}">XEM CHI TIẾT</a>
                            /
                            <a href = "#">XÓA</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class = "Sttle">2. THỐNG KÊ </div>
            <div class = "SSttle">a. Tỉ lệ thành phố:</div>
            <div class = "insider1">
                <c:forEach items = "${requestScope.listTID}" var = "t">
                    <div>
                        + ${t.getdS()}: <span class = "blk">${t.getNo()}</span>, chiếm <span class = "blk">${t.getPc()}%</span>

                    </div>
                </c:forEach>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
