<%-- 
    Document   : cinema
    Created on : Jun 15, 2023, 4:42:57 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

        <link rel="stylesheet" href="style.css"/>
        <script src="https://kit.fontawesome.com/8157308f93.js" crossorigin="anonymous"></script>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <link rel="stylesheet" href="swiper-bundle.min.css">
        <style>
            .body{
                padding-left: 80px;
            }

            .insideloc{
                display: flex;
                flex-wrap: wrap;
                border: 2px solid black;
                padding: 20px;
                padding-left:1em;
                border-radius: 12px;
                margin-bottom: 20px;
            }

            .ttle{
                text-align: center;
                font-size: 30px;
            }

            .insideinsideLoc{
                font-size: 20px;
                border: 1px solid black;
                margin-right: 30px;
                margin-bottom: 20px;
                border-radius: 10px;
                cursor: pointer;
                width: 250px;
                height: 50px;
                padding: 10px;
                text-align: center;
            }

            .ttle{
                margin-bottom: 20px;
                padding-top: 20px;
            }

            .insideinsideLocActive{
                font-size: 20px;
                border: 1px solid black;
                padding: 10px;
                margin-right: 30px;
                border-radius: 10px;
                cursor: pointer;
                background-color: black;
                color: white;
                width: 250px;
                height: 50px;
                text-align: center;
            }


            .bigttle{
                text-align: center;
                padding-bottom: 10px;
                font-size: 30px;
                margin-top: 20px;
                margin-bottom: 20px;
                color: red;
                text-shadow: 0px 0px 3px yellow;
                box-shadow: 10px 10px 5px #666666;
            }


            .notDate{
                display: flex;
                flex-direction: column;
                margin-right: 8px;
                margin-bottom: 5px;
            }
            .isDate{
                font-size: 30px;
            }
            .insideSchedule:hover{
                border: 1px solid black;
                border-radius: 15px;
                padding-top: 10px;
                padding-bottom: 10px;
            }

            .ms{
                margin-top: 20px;
                margin-left: 30px;
                margin-right: 0px;
                margin-bottom: 20px;
            }

            .mImg img{
                width: 200px;
                height: 250px;
            }

            .mOvSCHE{
                padding-bottom: 15px;
                border-bottom: 2px solid black;
            }

            .mName{
                font-size: 22px;
                margin-bottom: 15px;
                margin-top: 15px;
                font-weight: bold;
            }

            .mIn4{
                display: flex;
            }

            .insidemNotImg{
                display: flex;
                flex-direction: column;
                flex-wrap: wrap;
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

            .mTme{
                margin-bottom: 10px;
                display: flex;
                flex-wrap: wrap;
                cursor: pointer;
            }

            .insidemTme{
                margin-right: 10px;
                border: 1px solid black;
                padding: 10px;
            }

            .mFrm{
                margin-bottom: 10px;
                font-weight: bold;
            }

            .mNotImg{
                margin-left: 20px;
                font-size: 20px;
            }

            .ms{
                margin-bottom: 0px;
                padding-bottom: 20px;
            }
            .insideloc li{
                min-width:10em;
                list-style-type: lower-alpha;
            }
            table{
                margin-top: 40px;
                border: 1px solid black;
                margin-bottom: 20px;
                margin: 0 auto;
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

            a{
                text-decoration: none;
                color: black;
            }

            .ms{
                font-size: 20px;
                color: red;
            }

            .ttle{
                text-align: center;
                font-weight: bold;
                margin-bottom: 20px;
                font-size: 25px;
                padding-top: 20px;
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

            .addE img{
                width: 30px;
                cursor: pointer;
            }

            a{
                cursor: pointer;
            }

            button{
                font-size: 18px;
                border: none;
                background-color: white;
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

            .insideSeat{
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
                cursor: pointer;
                background-color: white;
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

            .breaker{
                flex-basis: 100%;
                height: 0;
            }
            .seat{
                margin-bottom: 20px;
            }

            .btS{
                text-align: center;
            }

            .btS button{
                font-size: 18px;
                padding: 5px;
                background-color: red;
                color: white;
                cursor: pointer;
                border-radius: 12px;

            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">PHÂN BỐ THIẾT BỊ TẠI CÁC RẠP</div>
            <c:if test = "${requestScope.listCin != null}">
                <div class = "loc">
                    <div class = "ttle">Rạp Chiếu Phim</div>
                    <div class = "insideloc">
                        <c:forEach items = "${requestScope.listCin}" var = "k">
                            <div class = "${k.getCinID()==requestScope.cinID?"insideinsideLocActive":"insideinsideLoc"}" onclick = "pckCin('${k.getCinID()}')">${k.getCinName()}</div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

            <c:if test = "${requestScope.listRoom != null}">
                <div class = "loc">
                    <div class = "ttle">Phòng</div>
                    <div class = "insideloc">
                        <c:forEach items = "${requestScope.listRoom}" var = "k">
                            <div class = "${k.getRoomID()==requestScope.roomID?"insideinsideLocActive":"insideinsideLoc"}" onclick = "pckRoom('${requestScope.cinID}', '${k.getRoomID()}')">${k.getRoomID()}</div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
            <c:if test = "${requestScope.ms != null}">
                <div class = "ms">
                    ${requestScope.ms}
                </div>
            </c:if>
            <div class = "ttle">Bố Cục</div>
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

            </div>
            <div class = "screen">
                <img src ="images/screenIcon.png"/>
            </div>
            <div class = "seat">
                <c:forEach items = "${requestScope.tk}" var = "i">
                    <div hidden id = "first${i.getId()}"> ${i.getId()}</div>
                    <c:if test = "${i.getType() == 1}">
                        <div id ="${i.getId()}" class = "insideSeat" onclick = "upd('${i.getCol()}', '${i.getRow()}', '${i.getCinID()}', '${i.getRoomID()}', ${i.getId()})">
                            <input type ="text" hidden/>
                            <div class = "sat">

                                <span>${i.getCol()}</span>
                                <span>${i.getRow()}</span><!-- -->
                            </div>

                        </div>
                    </c:if>
                    <c:if test = "${i.getType() == 2}">
                        <div id ="${i.getId()}" class = "vip" onclick = "upd('${i.getCol()}', '${i.getRow()}', '${i.getCinID()}', '${i.getRoomID()}', ${i.getId()})">
                            <input type ="text" hidden/>
                            <div class = "sat">
                                <span>${i.getCol()}</span>
                                <span>${i.getRow()}</span><!-- -->
                            </div>

                        </div>
                    </c:if>
                    <c:if test = "${i.getType() == 3}">
                        <div  id ="${i.getId()}" class = "spe" onclick = "upd('${i.getCol()}', '${i.getRow()}', '${i.getCinID()}', '${i.getRoomID()}', ${i.getId()})">
                            <input type ="text" hidden/>
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

                    <input type ="submit" hidden value ="${requestScope.room.getNoColSeats() * requestScope.room.getNoRowSeats()}" id = "id2"/>
                </c:forEach>
            </div>
            <div class = "btS">
                <button onclick="sbm()">LƯU</button>
            </div>
            <div class = "ttle">Thiết Bị</div>
            <c:if test = "${requestScope.listDevice != null && requestScope.ms == null}">
                <table>
                    <tr>
                        <th>ẢNH</th>
                        <th>MÃ THIẾT BỊ</th>
                        <th>LOẠI THIẾT BỊ</th><!-- comment -->
                        <th>GIÁ THIẾT BỊ</th>
                        <th>MÔ TẢ</th>    
                        <th>NGÀY PHÂN BỐ</th>
                        <th>THỜI GIAN PHÂN BỐ</th>
                        <th>BAR CODE</th>
                        <th>TÌNH TRẠNG</th>
                        <th>HÀNH ĐỘNG</th>
                    </tr>
                    <c:forEach items = "${requestScope.listDevice}" var = "i">
                        <tr>
                            <td><img src = "${i.getImg()}"/></td>
                            <td>${i.getDeviceCode()}</td>
                            <td>${i.getTypeName()}</td>
                            <td>${i.getPrice()}</td>
                            <td>${i.getDescript()}</td><!-- comment --> 
                            <td>${i.getDte()}</td>
                            <td>${i.getTme()}</td>
                            <td>${i.getDeviceBarCode()}</td>
                            <td>${i.getStatus()}</td>
                            <td>
                                <a href = "udbc?id=${i.getDeviceBarCode()}">SỬA</a>
                                /
                                <a onclick="dlt('${i.getDeviceBarCode()}')">XÓA</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div class = "addE">
                    <div>
                        <a href = "adddvd"><img src ="images/plusIcon.png"/></a>
                    </div>

                </div>
            </c:if>
        </div>

        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script src="swiper-bundle.min.js"></script>
        <script>


        </script>

        <script type="text/javascript" >

            function pckRoom(cin, room) {
                window.location = "rmrp?cin=" + cin + "&room=" + room;
            }

            function pckCin(cin) {
                window.location = "rmrp?cin=" + cin;
            }
            var cnt = 0;
            var seatC = [];

            function upd(col, row, cin, room, id) {
                var t = "";
                var k = 0;
                var cnt = 0;
                var check = 0;
                var tst = "";
                var type = "";
                if (String(document.getElementById(id).className) === "insideSeat") {
                    for (var i = 0; i < seatC.length; i++) {

                        if (String(seatC[i]).includes("ID" + id)) {
                            tst = String(seatC[i]);
                            for (var j = 0; j < tst.length; j++) {
                                if (tst.charAt(j) === "-") {

                                    cnt++;
                                }
                                if (cnt === 3) {
                                    type = tst.substring(j + 1);
                                    break;
                                }

                            }
                            t = "ID" + id + "-" + col + "?" + row + "?"+ cin + "/" + room + "-" + "vip" + "-" + type;
                            if (String(type) !== "vip") {
                                seatC[i] = String(t);
                            } else {
                                seatC.splice(i, 1);
                            }
                            console.log(t);
                            check++;
                        }
                        if (check > 0) {
                            break;
                        }
                    }
                    if (check === 0) {
                        t = "ID" + id + "-"+ col + "?" + row + "?" + cin + "/" + room + "-" + "vip" + "-" + "nm";
                        seatC.push(t);
                    }


                    console.log(String(seatC));
                    document.getElementById(id).className = "vip";

                } else if (String(document.getElementById(id).className) === "vip") {
                    for (var i = 0; i < seatC.length; i++) {
                        if (String(seatC[i]).includes("ID" + id)) {
                            tst = String(seatC[i]);
                            for (var j = 0; j < tst.length; j++) {
                                if (tst.charAt(j) === "-") {
                                    cnt++;
                                }
                                if (cnt === 3) {

                                    type = tst.substring(j + 1);
                                    break;
                                }
                            }
                            t = "ID" + id + "-"+ col + "?" + row + "?" + cin + "/" + room + "-" + "spe" + "-" + type;
                            if (String(type) !== "spe") {
                                seatC[i] = String(t);
                            } else {
                                seatC.splice(i, 1);
                            }
                            console.log(t);
                            check++;
                        }
                        if (check > 0) {
                            break;
                        }
                    }
                    if (check === 0) {
                        t = "ID" + id + "-" + col + "?" + row + "?" + cin + "/" + room + "-" + "spe" + "-" + "vip";
                        seatC.push(t);
                    }
                    console.log(String(seatC));
                    document.getElementById(id).className = "spe";
                } else if (String(document.getElementById(id).className) === "spe") {
                    for (var i = 0; i < seatC.length; i++) {

                        if (String(seatC[i]).includes("ID" + id)) {
                            tst = String(seatC[i]);
                            for (var j = 0; j < tst.length; j++) {
                                if (tst.charAt(j) === "-") {
                                    cnt++;
                                }
                                if (cnt === 3) {
                                    type = tst.substring(j + 1);
                                    break;
                                }
                            }
                            t = "ID" + id + "-"+ col + "?" + row + "?" + cin + "/" + room + "-" + "nm" + "-" + type;
                            if (String(type) !== "nm") {
                                seatC[i] = String(t);
                            } else {
                                seatC.splice(i, 1);
                            }
                            console.log(t);
                            check++;
                        }
                        if (check > 0) {
                            break;
                        }

                    }
                    if (check === 0) {
                        t = "ID" + id + "-" + col + "?" + row + "?" + cin + "/" + room + "-" + "nm" + "-" + "spe";
                        seatC.push(t);
                    }
                    console.log(String(seatC));
                    document.getElementById(id).className = "insideSeat";
                }
            }

            function dlt(id) {
                if (confirm("Bạn có chắc muốn xóa thiết bị với mã là " + id)) {
                    window.location = "dltdv?id=" + id;
                }
            }

            function sbm() {
                let http = new XMLHttpRequest();
                http.open('POST', 'http://localhost:9999/cinema/upds?seatC=' + seatC);
                http.onload = function () {
                    console.log(1);
                }
                http.send(seatC);
                alert("Lưu thành công");
//                $.ajax({
//                    url: 'upds',
//                    type: 'POST',
//                    contentType: 'application/json',
//                    data: JSON.stringify(data),
//                    dataType: 'json',
//                    success: function (data) {
//                        //On ajax success do this
//                        alert(data);
//                    },
//                    error: function (xhr, ajaxOptions, thrownError) {
//                        //On error do this
//                        if (xhr.status == 200) {
//
//                            alert(ajaxOptions);
//                        } else {
//                            alert(xhr.status);
//                            alert(thrownError);
//                        }
//                    }
//                });
            }

        </script>
    </body>
</html>
