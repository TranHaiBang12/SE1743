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
        <link rel="stylesheet" href="style.css?version=1"/>
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
            
            button{
                margin-bottom: 20px;
                font-size: 17px;
                padding: 5px;
                width: 80%;
                background-color: red;
                color: white;
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
            
            .Sttle{
                margin-bottom: 20px;
            }
            
            th{
                border: none;
            }
                    
            td{
                border: none;
            }
            
            td{
                border-bottom: 1px solid black;
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
                <tr class = "ttr">
                    <th class = "first">TÀI KHOẢN</th>
                    <th>SỐ LẦN MUA HÀNG</th>
                    <th>SỐ LẦN MUA HÀNG ONL</th>                   
                    <th>SỐ LẦN MUA HÀNG OFF</th>
                    <th>TỈ LỆ MUA HÀNG ONL</th>
                    <th>TỈ LỆ MUA HÀNG OFF</th>
                    <th>ĐIỂM</th>                 
                    <th>ĐIỂM ĐÃ DÙNG</th>
                    <th>TỈ LỆ DÙNG ĐIỂM</th>
                    <th>SỐ LẦN BÌNH LUẬN</th>
                    <th>ACTIVE</th>
                    <th class = "last">HÀNH ĐỘNG</th>
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
                        <td>${i.getActive()}</td>
                        <td>
                            <a href="acc?user=${i.getUserName()}"><button>XEM CHI TIẾT</button></a>
                            
                            <c:if test = "${i.getActive() == 1}">
                                <a  onclick="act('${i.getUserName()}', '${i.getActive()}')"><button>ẨN</button></a>
                            </c:if>
                            <c:if test = "${i.getActive() == 0}">
                                <a  onclick="act('${i.getUserName()}', '${i.getActive()}')"><button>BỎ ẨN</button></a>
                            </c:if>
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
        <script type="text/javascript">
            function act(id, stt) {
                if (confirm("Bạn có muốn thay đổi trạng thái ẩn của tài khoản này")) {
                    if (Number(stt) === 1) {
                        window.location = "deact?user=" + id + "&stt=" + 0;
                    }
                    else if (Number(stt) === 0) {
                        window.location = "deact?user=" + id + "&stt=" + 1;
                    }
                }
            }
        </script>
    </body>
</html>
