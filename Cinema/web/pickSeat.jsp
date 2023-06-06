<%-- 
    Document   : pickSeat
    Created on : Jun 5, 2023, 2:21:24 PM
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
            .body{
                background-color: white;
                border-bottom: 2px dashed red;
                padding-bottom: 50px;
                padding-top: 15px;
            }

            .ttle{
                text-align: center;
                font-size: 30px;
                margin-bottom: 25px;
            }

            .urSelection{
                text-align: center;
                font-size: 20px;
                margin-bottom: 25px;
            }

            .mvName {
                color: red;
            }

            .fName {
                color: red;
            }

            .scheIn4{
                text-align: center;
            }

            .seat{
                display: flex;
                margin-top: 20px;
                flex-wrap: wrap;
                margin-left: 33%;
                margin-right: 20%;
            }

            .seat hr {
                flex-basis: 100%;
                height: 0;
                margin: 0;
                border: 0;
            }

            .insideSeat{
                display: flex;
                justify-content: space-around;
                flex-direction: row;
                margin-bottom: 10px;
                font-size: 20px;

                margin-right: 30px;
                margin-bottom: 20px;

                border: 1px solid black;
                padding: 10px;
                border-radius: 10px;
                font-size: 15px;
            }

            .sat{
                display: flex;
            }

            .breaker{
                flex-basis: 100%;
                height: 0;
            }
            
            .vip{
                display: flex;
                justify-content: space-around;
                flex-direction: row;
                margin-bottom: 10px;
                font-size: 20px;

                margin-right: 30px;
                margin-bottom: 20px;

                border: 1px solid black;
                padding: 10px;
                border-radius: 10px;
                font-size: 15px;
                background-color: blue;
            }
            
            .spe{
                display: flex;
                justify-content: space-around;
                flex-direction: row;
                margin-bottom: 10px;
                font-size: 20px;

                margin-right: 30px;
                margin-bottom: 20px;

                border: 1px solid black;
                padding: 10px;
                border-radius: 10px;
                font-size: 15px;
                background-color: pink;
            }




        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <h3 class = "ttle">CHỌN GHẾ NGỒI</h3>
            <div class = "urSelection">
                BẠN ĐÃ CHỌN: <span class = "mvName">${requestScope.movName}, </span> <span class = "fName">${requestScope.formName}</span>
            </div>
            <div class = "scheIn4">
                PHÒNG CHIẾU: <span class = "scheRoom">${requestScope.sche.getRoomID()}</span>, TẦNG <span>${requestScope.room.getFloor()}</span>
                <br/>
                SUẤT CHIẾU: <span class = "scheTime">${requestScope.sche.getStartTim()}</span> - <span>${requestScope.day} </span><span>${requestScope.dateFormat}</span>
            </div>
            <div>
                <div class = "seat">
                    <c:forEach items = "${requestScope.rs}" var = "i">
                        <c:if test = "${i.getType() == 1}">
                            <div class = "insideSeat">

                                <div class = "sat">
                                    <div>${i.getCol()}</div>
                                    <div>${i.getRow()}</div><!-- -->
                                </div>

         
                            </div>
                        </c:if>
                        <c:if test = "${i.getType() == 2}">
                            <div class = "vip">

                                <div class = "sat">
                                    <div>${i.getCol()}</div>
                                    <div>${i.getRow()}</div><!-- -->
                                </div>

                 
                            </div>
                        </c:if>
                        <c:if test = "${i.getType() == 3}">
                            <div class = "spe">

                                <div class = "sat">
                                    <div>${i.getCol()}</div>
                                    <div>${i.getRow()}</div><!-- -->
                                </div>

                            </div>
                        </c:if>
                        <div class = "${((i.getId() % requestScope.room.getNoColSeats()== 0) )?"breaker":""}">

                            <!--
                            <c:if test = "${((i.getId() % requestScope.room.getNoColSeats()== 0) || ((i.getId() - 1) % requestScope.room.getNoColSeats()== 0) && i.getId() != 1)}">
                                <p class = "breaker">T</p>
                            </c:if>
                            -->
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
