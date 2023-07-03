<%-- 
    Document   : tkRp
    Created on : Jul 2, 2023, 2:19:02 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <link rel="stylesheet" href="style.css"/>
        <style>
            .ttle{
                text-align: center;
                padding-top: 20px;
                font-size: 27px;
                font-weight: bold;
                margin-bottom: 40px;
            }

            .dteS{
                display: flex;
                justify-content: center;
                font-size: 19px;
            }

            .dteS div{
                margin-right: 15px;
            }

            .rd{
                color: red;
            }

            .Sttle{
                font-weight: bold;
                font-size: 20px;
                margin-left: 40px;
                margin-top: 20px;
            }

            .mvIN4{
                display: flex;
                margin-top: 20px;
                margin-left: 40px;
                background-color: white;
                padding-bottom: 20px;
            }

            .imGE img{
                width: 100%;
                height: 100%;
            }

            .imGE{
                width: 250px;
                height: 300px;
            }

            .in4 {
                display: flex;
                flex-direction: column;
                margin-left: 80px;
            }

            .mName{
                font-size: 25px;
                font-weight: bold;
            }

            .blk {
                font-weight: bold;
            }

            .rte {
                margin-right: 80px;
            }

            .in4 div{
                margin-bottom: 10px;
            }

            .mName {
                font-size: 20px;
            }

            .oIn4{
                font-size: 20px;
            }

            .btn{
                margin-top: 20px;
            }

            .btn input{
                font-size: 18px;
                padding: 5px;
                color: white;
                background-color: red;
                cursor: pointer;
            }

            .btS button{
                font-size: 18px;
                padding: 5px;
                background-color: red;
                color: white;
                cursor: pointer;
            }

            .insider1{
                font-size: 20px;
                margin-left: 80px;
                margin-bottom: 10px
            }

            .insider1 div{
                margin-top: 10px;
                margin-bottom: 10px;
            }

            .TKET{
                margin-top: 20px;
                margin-left: 40px;
                background-color: white;
                padding-bottom: 20px;
                border-bottom: 3px solid black;
            }

            .SSttle {
                font-size: 20px;
                margin-top: 10px;
                font-weight: bold;
                margin-left: 40px;
                color: deeppink;
            }
            
            .ttype2{
                margin-left: 120px;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">THỐNG KÊ VỀ VÉ</div>
            <div class = "dteS">
                <div>Ngày: <span class = "rd">${requestScope.start}</span></div>
                <div>
                    -
                </div>
                <div>Ngày: <span class = "rd">${requestScope.end}</span></div>
            </div><!-- comment -->
            <div class = "TKET">
                <div class = "Sttle">1. TỔNG QUÁT (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                <div class = "mvIN4">
                    <div class = "imGE"><img src = "images/cinemaTicket.jpg"></div>
                    <div class = "in4">
                        <div class = "mName">
                            <div>${requestScope.m.getMovName()}</div>
                        </div>
                        <div. class = "oIn4">
                            <div>
                                <div>Tổng số vé được bán: <span class = "blk">${requestScope.numTick}</span>, chiếm <span  class = "blk">${requestScope.pcNumTick}%</span> tổng số vé được bán từ trước đến nay</div><!-- <div></div> -->
                            </div>


                        </div.>
                    </div>
                </div>

            </div>

            <div class = "TKET">
                <div class = "Sttle">2. CHI TIẾT (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                <div class = "SSttle">a. Các loại vé:</div>
                <div class = "insider1">

                    <c:forEach items = "${requestScope.listTID}" var = "t">
                        <div>
                            + Vé ${t.getdS()}: <span class = "blk">${t.getNo()}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${t.getPc()}%</span></span>
                        </div>
                    </c:forEach>
                </div>

                <div class = "SSttle">b. Tỉ lệ các ngày được bán:</div>
                <div class = "insider1">

                    <c:forEach items = "${requestScope.listTID3}" var = "t">
                        <div>
                            + ${t.getdS()}: <span class = "blk">${t.getNo()}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${t.getPc()}%</span></span>
                        </div>
                        <c:forEach items = "${t.getTkd()}" var = "p">
                            <div class = "ttype2">
                                * Vé ${p.getdS()}: <span class = "blk">${p.getNo()}</span>, <span class = "rte">chiếm: <span class = "blk">${p.getPc()}%</span> số vé của ngày này</span>
                            </div>
                        </c:forEach>
                    </c:forEach>
                </div>
                
                <div class = "SSttle">c. Tỉ lệ các khung giờ được bán:</div>
                <div class = "insider1">

                    <c:forEach items = "${requestScope.listTID4}" var = "t">
                        <div>
                            + <span class = "blk">${t.getdS()}</span>: <span class = "blk">${t.getNo()}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${t.getPc()}%</span></span>
                        </div>
                        <c:forEach items = "${t.getTkd()}" var = "p">
                            <div class = "ttype2">
                                *  <span class = "blk">${p.getdS()}</span>: <span class = "blk">${p.getNo()}</span>, <span class = "rte">chiếm <span class = "blk">${p.getPc()}%</span> số vé của khung giờ này</span>
                            </div>
                        </c:forEach>
                    </c:forEach>
                </div>
                
                <div class = "SSttle">d. Tỉ lệ vé bán giữa các rạp:</div>
                <div class = "insider1">

                    <c:forEach items = "${requestScope.listTID5}" var = "t">
                        <div>
                            + <span class = "blk">${t.getdS()}</span>: <span class = "blk">${t.getNo()}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${t.getPc()}%</span></span>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
