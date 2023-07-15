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
        <link rel="stylesheet" href="style.css?version=1"/>
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
            
            td{
                border: none;
                border-bottom: 1px solid black;
            }
            
            table{
                margin-top: 20px;
                margin: 0 auto;
                border: 2px solid black;
                border-radius: 12px;
                border-spacing: 0px;
            }

            tr{
                border-bottom: 2px solid black;
            }

            td{
                padding: 20px;
            }
            th{
                padding: 20px;
                border-bottom: 2px solid black;
            }

            .th{

            }

            .ttr{
                background-color: crimson;
                color: white;
            }

            tr{
                border-bottom: 2px solid black;
            }

            .m img{
                width: 100px;
            }

            td{
                border-bottom: 1px solid black;
            }

            td a{
                text-decoration: none;
                color: black;
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

            .dlt{
                cursor: pointer;
            }

            .dteS{
                display: flex;
                justify-content: center;
                font-size: 19px;
                margin-bottom: 20px;
            }

            .dteS div{
                margin-right: 15px;
            }

            .blk{
                font-weight: bold;
            }

            .rd{
                color: red;
            }

            .ms{
                font-weight: bold;
                color: red;
                font-size: 25px;
                text-align: center;
            }

            .ttle{
                text-align: center;
                padding-top: 40px;
                font-size: 27px;
                font-weight: bold;
                margin-bottom: 40px;
                text-shadow: 10px 10px 5px #666666;
                color: brown
            }
            .body{
                padding-top: 40px;
                padding-left: 90px;
                padding-right: 110px;
            }

            table{
                width: 100%;

            }

            .k{
                padding: 0px;
                width: 150px;
                height: 270px;
            }
            .k img{
                width: 100%;
                height: 100%;
            }


            .blk{
                font-weight: bold;
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

            button{
                margin-bottom: 20px;
                font-size: 15px;
                padding: 5px;
                width: 80%;
                border-radius: 12px;
                cursor: pointer;
                background-color: red;
                color: #faebee;
            }

            button:hover{
                color:white;
                background-color: red;
            }

            .ms{
                margin-bottom: 20px;
            }

            th{
                padding-left: 20px;
                margin-left: 0px;
                text-align: left;
            }

            td{
                text-align: left;
                font-size: 18px;
                padding-left: 20px;
            }

            tr:hover{
                background-color: rgba(70,70,70,0.2);
            }

            .ttr:hover{
                background-color: crimson;

            }

            .ttr{

                border: 1px solid crimson;
                border-radius: 12px;
                padding: 5px;
                background-color: crimson;
            }

            .first{
                border-left: 1px solid crimson;
                border-radius: 12px 0px 0px 0px;
            }

            .last{
                border-right: 1px solid crimson;
                border-radius: 0px 12px 0px 0px;
            }
            
            th{
                border: none;
                border-bottom: 1px solid black;
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
                    <tr class = "ttr">
                        <th class = "first">MÃ THIẾT BỊ</th>
                        <th>RẠP</th><!-- comment -->
                        <th>PHÒNG</th>
                        <th>NGÀY PHÁT HIỆN</th>    
                        <th>THỜI GIAN PHÁT HIỆN</th>  
                        <th>NGÀY SỬA</th>  
                        <th>THỜI GIAN SỬA</th>  
                        <th>CHI PHÍ</th>
                        <th>BARCODE</th>
                        <th>TÌNH TRẠNG</th>
                        <th class = "last">HÀNH ĐỘNG</th>
                    </tr>
                    <c:forEach items = "${requestScope.listE}" var = "i">
                        <tr>
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
