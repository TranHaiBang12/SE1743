<%-- 
    Document   : device
    Created on : Jul 5, 2023, 6:59:23 PM
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

                width: 100%;
                margin-top: 20px;
                border: 1px solid black;
                margin-bottom: 20px;
                margin: 0 auto;
                margin-left: 20px;
                margin-right: 50px;
            }

            body{
                overflow-x: hidden;
            }

            button{
                margin-bottom: 20px;
                font-size: 17px;
                padding: 5px;
                width: 80%;
                background-color: red;
                color: white;
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

            td img{
                width: 100px;
            }

            .ttle{
                text-align: center;
                font-weight: bold;
                margin-bottom: 20px;
                font-size: 25px;
                padding-top: 40px;
            }

            .body{
                padding-left: 70px;
                padding-right: 120px;
            }

            .addE img{
                width: 30px;
                cursor: pointer;
            }

            .addE div{
                margin-left: 20px;
            }

            .addE {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 20px;
                padding-bottom: 20px;
                font-size: 20px;
            }

            td a{
                text-decoration: none;
                color: black;
            }

            .rd{
                color: red;
            }
            button{
                margin-bottom: 20px;
                font-size: 17px;
                padding: 5px;
                width: 80%;
                background-color: red;
                color: white;
            }
            
            .ms{
                padding-top: 40px;
                padding-bottom: 40px;
                color: red;
                font-size: 30px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div><!-- comment -->
        <div class = "body">
            <div class = "ttle">Danh Sách Lỗi</div>
            <c:if test = "${requestScope.ms != null}">
                <div class = "ms">
                    ${requestScope.ms}
                </div>
            </c:if>
            <c:if test = "${requestScope.ms == null}">
                <table>
                    <tr>
                        <th>ẢNH</th>
                        <th>MÃ THIẾT BỊ</th>
                        <th>RẠP</th><!-- comment -->
                        <th>PHÒNG</th>
                        <th>NGÀY PHÁT HIỆN</th>    
                        <th>THỜI GIAN PHÁT HIỆN</th>  
                        <th>NGÀY SỬA</th>  
                        <th>THỜI GIAN SỬA</th>  
                        <th>CHI PHÍ</th>
                        <th>BARCODE</th>
                        <th>TÌNH TRẠNG</th>
                        <th>HÀNH ĐỘNG</th>
                    </tr>
                    <c:forEach items = "${requestScope.listE}" var = "i">
                        <tr>
                            <td><img src = "${i.getImg()}"/></td>
                            <td>${i.getDeviceCode()}</td>
                            <td>${i.getCinName()}</td>
                            <td>${i.getRoomID()}</td>
                            <td>${i.getDteDetected()}</td>
                            <td>${i.getTmeDetected()}</td>
                            <td>${i.getDteFixed()}</td>
                            <td>${i.getTmeFixed()}</td>
                            <td>${i.getCostIncured()}</td>
                            <td>${i.getDeviceBarCode()}</td>
                            <td><span class = "rd">${i.getStatus()}</span></td>
                            <td>
                                <a  href = "udbc?id=${i.getDeviceBarCode()}"><button type = "button">SỬA</button></a>

                                <a><button type = "button" onclick = "dlt('${i.getDeviceBarCode()}', '${i.getDteDetected()}', '${i.getTmeDetected()}')">XÓA</button></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function dlt(id, day, time) {
                if (confirm("Bạn có chắc muốn xóa bản ghi lỗi của thiết bị " + id + " vào ngày " + day + " giờ " + time)) {
                    window.location = "dde?id=" + id + "&date=" + day + "&time=" + time;
                }
            }
        </script>
    </body>
</html>
