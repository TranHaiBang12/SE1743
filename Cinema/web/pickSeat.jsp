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
            html{
                background-color: red;
            }

            body{
                width: 100%;
            }

            .header{
                width: 100%;
            }

            .footer{
                width: 100%;
            }

            .body{
                background-color: white;
                border-bottom: 2px dashed red;
                padding-bottom: 50px;
                padding-top: 15px;
                width: 100%;
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
                justify-content: center;
                align-items: center;
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
                font-size: 20px;
                align-items: center;
                margin-left: 20px;
                margin-right: 20px;
                margin-top: 10px;
                margin-bottom: 10px;
                border: 1px solid black;
                padding: 10px;
                border-radius: 10px;
                font-size: 15px;
                cursor: pointer;
                background-color: white;
                color: black;
            }

            .sat{
                display: flex;
                width: 100%;
                text-align: center;
                overflow-x: hidden;
                align-items: center;
            }

            .sat div{
                width: 50%;
                overflow-x: hidden;
            }

            .breaker{
                flex-basis: 100%;
                height: 0;
            }

            .vip{
                display: flex;
                justify-content: space-around;
                flex-direction: row;
                font-size: 20px;

                margin-left: 20px;
                margin-right: 20px;
                margin-top: 10px;
                margin-bottom: 10px;
                border: 1px solid black;
                padding: 10px;
                border-radius: 10px;
                font-size: 15px;
                background-color: blue;
                cursor: pointer;
                color: white;
            }

            .spe{
                display: flex;
                justify-content: space-around;
                flex-direction: row;
                font-size: 20px;

                margin-left: 20px;
                margin-right: 20px;
                margin-top: 10px;
                margin-bottom: 10px;
                border: 1px solid black;
                padding: 10px;
                border-radius: 10px;
                font-size: 15px;
                background-color: pink;
                cursor: pointer;
                color: white;
            }

            .abtSeat {
                display: flex;
                flex-direction: column;
                border: 2px solid black;
                margin-top: 20px;
                margin-left: 40px;
                margin-right: 40px;
            }

            .instruc{
                margin-top: 10px;
                margin-left: 20px;
                font-size:20px;
            }

            .instruc p{
                margin-top: 5px;
            }

            .seatInstruct{
                display: flex;

                align-items: center;
                justify-content: center;
            }

            .seatInstruct div{
                display: flex;

                align-items: center;
                justify-content: center;
                margin-right: 40px;
            }
            .seatInstruct img{
                width: 50px;
                height: 50px;
                margin-right: 20px;
            }

            .screen {
                margin-top: 30px;
                text-align: center;
                margin-bottom: 20px;
            }
            .screen img{
                width: 1000px;
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
            <div class = "abtSeat">
                <div class = "instruc">
                    <p>Để chọn ghế vui lòng chọn ghế ưa thích theo icon</p>
                    <p>Click tiếp vào ghế đã chọn để xoá lựa chọn</p>
                </div>
                <div class ="seatInstruct">
                    <div>
                        <img class = "imgE" src = "images/blueSeat.png">
                        <span>Ghế VIP</span>
                    </div>
                    <div>
                        <img class = "imgE" src = "images/whiteSeat.png">
                        <span>Ghế Thường</span>
                    </div>
                    <div>
                        <img class = "imgE" src = "images/pinkSeat.png">
                        <span> Ghế Đôi</span>
                    </div>
                    <div>
                        <img class = "imgE" src = "images/greenSeat.png">
                        <span> Ghế Đã Chọn</span>
                    </div>
                </div>
                <div class = "screen">
                    <img src ="images/screenIcon.png"/>
                </div>
                <div class = "seat">
                    <c:forEach items = "${requestScope.rs}" var = "i">
                        <div hidden id = "first${i.getId()}"> ${i.getId()}</div>
                        <c:if test = "${i.getType() == 1}">
                            <div id ="${i.getId()}" class = "insideSeat" onclick = "pckSeat('${i.getId()}')">

                                <div class = "sat">
                                    <span>${i.getCol()}</span>
                                    <span>${i.getRow()}</span><!-- -->
                                </div>


                            </div>
                        </c:if>
                        <c:if test = "${i.getType() == 2}">
                            <div id ="${i.getId()}" class = "vip">

                                <div class = "sat">
                                    <span>${i.getCol()}</span>
                                    <span>${i.getRow()}</span><!-- -->
                                </div>


                            </div>
                        </c:if>
                        <c:if test = "${i.getType() == 3}">
                            <div  id ="${i.getId()}"class = "spe">

                                <div class = "sat">
                                    <span>${i.getCol()}</span>
                                    <span>${i.getRow()}</span><!-- -->
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
                        <input type ="submit" hidden value ="${requestScope.room.getNoColSeats()}" id = "id1"/>
                        <input type ="submit" hidden value ="${requestScope.room.getNoColSeats() * requestScope.room.getNoRowSeats()}" id = "id2"/>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>

        <script type="text/javascript">
            function pckSeat(id) {
                if (document.getElementById(id).style.color !== 'green') {
                    document.getElementById(id).style.backgroundColor = 'green';
                    document.getElementById(id).style.color = 'white';
                }

                else if(document.getElementById(id).style.backgroundColor === "green") {
                    document.getElementById(id).style.backgroundColor = 'white';
                    document.getElementById(id).style.color = 'black';
                    console.log("1");
                    
                }
                console.log(document.getElementById(id).style.backgroundColor);
                console.log(document.getElementById(id).style.backgroundColor === "green");

//                if(String(document.getElementById(id).style.backgroundColor) === "white") {
//                    document.getElementById(id).style.backgroundColor = 'green';
//                    document.getElementById(id).style.color = 'white';
//                }
//                else if(String(document.getElementById(id).style.backgroundColor) === "green") {
//                    document.getElementById(id).style.backgroundColor = 'white';
//                    document.getElementById(id).style.color = 'black';
//                }
            }

            for (var i = 1; i <= document.getElementById("id2").value; i++) {
                let k = String(i);
                const element = document.getElementById(k);
                let t = 100 / document.getElementById("id1").value * 4;
                element.style.width = t + "px";
                element.style.height = "auto";
            }
            for (var i = 1; i <= document.getElementById("id2").value; i++) {
                if ((i - 1) % document.getElementById("id1").value === 0) {
                    var first = "first" + String(i);
                    document.getElementById(first).innerHTML = "HÀNG " + String(Math.floor(i / document.getElementById("id1").value) + 1);
                    document.getElementById(first).style.display = "block";
                }

            }
        </script>
    </body>
</html>
