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
        <<link rel="stylesheet" href="style.css?version=1"/>
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
                width: 900px;
            }

            .abtSeat{
                width: 70%;
            }

            .tket {
                text-align: center;
                width: 20%;
                color: white;
                border: 1px solid black;
                background-color: black;
                font-size: 20px;
                padding-top: 10px;
            }

            .tket img{
                width: 90%;
                margin-top: 20px;
                margin-left: 10px;
                margin-right: 10px;
            }

            .tket a{
                text-decoration: none;
                color: white;
            }

            .ko{
                display: flex;
                flex-direction: column;
                margin-left: 270px;
            }

            .ghe {
                margin-top: 40px;
                margin-bottom: 40px;
            }

            .qtt{
                margin-top: 40px;
                margin-bottom: 40px;
            }

            .sum{
                margin-top: 40px;
                margin-bottom: 40px;
            }

            .crt input{
                width: 80%;
                padding: 12px;
                font-size: 15px;
                cursor: pointer;
            }

            .insideSeatRed{
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
                background-color: red;
                cursor: pointer;
                color: white;
            }

            .vipRed{
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
                background-color: red;
                cursor: pointer;
                color: white;
            }

            .speRed{
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
                background-color: red;
                cursor: pointer;
                color: white;
            }

            .msT{
                color: red;
                font-size: 25px;
                text-align: center;
                padding-top: 20px;
            }

            a{
                text-decoration: none;
                color: red;
            }

            .screen{
                width: 80%;
                text-align: center;
                margin: 0 auto;
                margin-top: 20px;
            }

            .screen img{
                width: 100%;
            }

            .seatInstruct{
                width: 90%;
                margin: 0 auto;
            }

            .frm{
                display: flex;
                flex-direction: column;
                margin-left: 40px;
                margin-top: 20px;
                font-size: 20px;
            }

            .imgE{
                width: 50px;
                height: 50px;
                margin-right: 20px;
            }

            .insideForm{
                display: flex;
                flex-direction: column;
                justify-content: flex-start;
            }

            .insideinsideform{
                display: flex;
                align-items: center;

            }

            .insideForm input{
                width: 72%;
                margin-top: 15px;
                margin-bottom: 15px;
                height: 25px;
                padding-left: 10px;
                font-size: 20px;
            }

            .insideinsideform label{
                margin-right: 100px;
                font-weight: bold;
            }



            .sbMIT{
                color: white;
                background-color: red;
                cursor: pointer;
            }


            .insideTinsideform input{
                width: 61%;
            }

            .insideTinsideform label{
                margin-right: 14px;
            }

            .insideTinsideform{
                display: flex;
                align-items: center;
            }

            .ms{
                margin-top: 20px;
                font-size: 20px;
                color: red;
                margin-right: 300px;
                margin-left: 400px;
            }



        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <h3 class = "ttle">VÉ</h3>
            <div class = "urSelection">
                PHIM: <span class = "mvName">${requestScope.movName}, </span> <span class = "fName">${requestScope.formName}</span>
            </div>
            <div class = "scheIn4">
                PHÒNG CHIẾU: <span class = "scheRoom">${requestScope.sche.getRoomID()}</span>, TẦNG <span>${requestScope.room.getFloor()}</span>
                <br/>
                RẠP: <span class = "scheRoom">${requestScope.cin.getCinName()}</span>
                <br/>
                SUẤT CHIẾU: <span class = "scheTime">${requestScope.sche.getStartTim()}</span> - <span>${requestScope.day} </span><span>${requestScope.dateFormat}</span>
            </div>
            <c:if test = "${msT == null}">
                <div class = "ko">
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
                            <div>
                                <img class = "imgE" src ="images/redSeat.png"><!-- comment -->
                                <span> Đã Bán</span>
                            </div>
                        </div>
                        <div class = "screen">
                            <img src ="images/screenIcon.png"/>
                        </div>
                        <div class = "seat">
                            <c:forEach items = "${requestScope.tk}" var = "i">
                                <div hidden id = "first${i.getID()}"> ${i.getID()}</div>
                                <c:if test = "${i.getSeatType() == 1}">
                                    <div id ="${i.getID()}" class = "insideSeat" >
                                        <input type ="text" hidden/>
                                        <div class = "sat">

                                            <span>${i.getCol()}</span>
                                            <span>${i.getRow()}</span><!-- -->
                                        </div>

                                    </div>
                                </c:if>
                                <c:if test = "${i.getSeatType() == 2}">
                                    <div id ="${i.getID()}" class = "vip"  >
                                        <input type ="text" hidden/>
                                        <div class = "sat">
                                            <span>${i.getCol()}</span>
                                            <span>${i.getRow()}</span><!-- -->
                                        </div>

                                    </div>
                                </c:if>
                                <c:if test = "${i.getSeatType() == 3}">
                                    <div  id ="${i.getID()}" class = "spe">
                                        <input type ="text" hidden/>
                                        <div class = "sat">
                                            <span>${i.getCol()}</span>
                                            <span>${i.getRow()}</span><!-- -->
                                        </div>
                                    </div>
                                </c:if>
                                <div class = "${((i.getID() % requestScope.room.getNoColSeats()== 0) )?"breaker":""}">

                                    <!--
                                    <c:if test = "${((i.getID() % requestScope.room.getNoColSeats()== 0) || ((i.getID() - 1) % requestScope.room.getNoColSeats()== 0) && i.getID() != 1)}">
                                        <p class = "breaker">T</p>
                                    </c:if>
                                    -->
                                </div>
                                <input type ="submit" hidden value ="${requestScope.room.getNoColSeats()}" id = "id1"/>
                                <input type ="submit" hidden value ="${requestScope.room.getNoColSeats() * requestScope.room.getNoRowSeats()}" id = "id2"/>
                            </c:forEach>
                        </div>
                    </div>
                    <div class = "ms">
                        ${requestScope.ms}
                    </div>
                    <div class = "frm">
                        <form action = "updtick" method = "post">
                            <div class = "insideForm">
                                <div class = "insideinsideform">
                                    <label id ="nm">Ghế Thường: </label>
                                    <img class = "imgE" src = "images/whiteSeat.png">
                                </div>
                                <div class = "insideTinsideform">
                                    <label>Giá(.000đ): &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type ="number"  class="tk_in4" required  min="0" max ="1000" id ="nm" name ="nm" placeholder = "${requestScope.nm_price}"/>
                                </div>
                                <div class = "insideTinsideform">
                                    <label>Khuyến Mại(%): </label><input type="number"  class="tk_in4" min ="0" step="0.01" required name = "nm_dc" placeholder = "${requestScope.nm_dc}">
                                </div>
                            </div>

                            <div class = "insideForm">
                                <div class = "insideinsideform">
                                    <label id ="vp">Ghế VIP: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                    <img class = "imgE" src = "images/blueSeat.png">
                                </div>
                                <div class = "insideTinsideform">
                                    <label>Giá(.000đ): &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type ="number"  class="tk_in4" required min="0" max ="1000" id ="vp" name ="vp" placeholder = "${requestScope.vp_price}"/>
                                </div>
                                <div class = "insideTinsideform">
                                    <label>Khuyến Mại(%): </label><input type="number"  class="tk_in4" min ="0" step="0.01" required name = "vp_dc" placeholder = "${requestScope.vp_dc}">
                                </div>
                            </div>
                            <div class = "insideForm">
                                <div class = "insideinsideform">
                                    <label id ="vt">Ghế Đôi: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                    <img class = "imgE" src = "images/pinkSeat.png">
                                </div>
                                <div class = "insideTinsideform">
                                    <label>Giá(.000đ): &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type ="number" required  class="tk_in4" min="0" max ="1000" id ="vt" name ="vt" placeholder = "${requestScope.vt_price}"/>
                                </div>
                                <div class = "insideTinsideform">
                                    <label>Khuyến Mại(%): </label><input type="number" class="tk_in4" min ="0" step="0.01" required name = "vt_dc" placeholder = "${requestScope.vt_dc}">
                                </div>
                            </div>
                            <div class = "insideForm">
                                <input type ="submit" class ="sbMIT" value ="UPDATE"/>
                            </div>
                            <input type ="text" hidden value ="${requestScope.id}" name = "id"/>
                        </form>
                    </div>
                </div>
            </c:if>
            <c:if test = "${msT != null}">
                <div class = "msT">
                    ${requestScope.msT}
                </div>
                <div class = "msT">
                    <a href = "addtick?id=${requestScope.id}">Bấm vào đây để thêm vé </a>
                </div>
            </c:if>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>

        <script type="text/javascript">


        </script>
    </body>
</html>
