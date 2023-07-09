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
                RẠP: <span class = "scheRoom">${requestScope.cin.getCinName()}</span>
                <br/>
                SUẤT CHIẾU: <span class = "scheTime">${requestScope.sche.getStartTim()}</span> - <span>${requestScope.day} </span><span>${requestScope.dateFormat}</span>
            </div>
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
                                <div id ="${i.getID()}" class = "${i.getStat().equals("Buy")?"insideSeatRed":"insideSeat"}" onclick = "pckSeat('${i.getID()}', '${i.getProductCode()}', '${i.getSeatType()}', '${i.getCol()}', '${i.getRow()}', '${i.getPrice()}', '${i.getDiscount()}')">
                                    <input type ="text" id ="${i.getProductCode()}${i.getCol()}${i.getRow()}" value ="${i.getID()}p${i.getProductCode()}p${i.getSeatType()}p${i.getCol()}p${i.getRow()}p${i.getPrice()}p${i.getDiscount()}" hidden/>
                                    <div class = "sat">

                                        <span>${i.getCol()}</span>
                                        <span>${i.getRow()}</span><!-- -->
                                    </div>

                                </div>
                            </c:if>
                            <c:if test = "${i.getSeatType() == 2}">
                                <div id ="${i.getID()}" class = "${i.getStat().equals("Buy")?"vipRed":"vip"}"  onclick = "pckSeat('${i.getID()}', '${i.getProductCode()}', '${i.getSeatType()}', '${i.getCol()}', '${i.getRow()}', '${i.getPrice()}', '${i.getDiscount()}')">
                                    <input type ="text" id ="${i.getProductCode()}${i.getCol()}${i.getRow()}" value ="${i.getID()}p${i.getProductCode()}p${i.getSeatType()}p${i.getCol()}p${i.getRow()}p${i.getPrice()}p${i.getDiscount()}" hidden/>
                                    <div class = "sat">
                                        <span>${i.getCol()}</span>
                                        <span>${i.getRow()}</span><!-- -->
                                    </div>

                                </div>
                            </c:if>
                            <c:if test = "${i.getSeatType() == 3}">
                                <div  id ="${i.getID()}" class = "${i.getStat().equals("Buy")?"speRed":"spe"}" onclick = "pckSeat('${i.getID()}', '${i.getProductCode()}', '${i.getSeatType()}', '${i.getCol()}', '${i.getRow()}', '${i.getPrice()}', '${i.getDiscount()}')">
                                    <input type ="text" id ="${i.getProductCode()}${i.getCol()}${i.getRow()}" value ="${i.getID()}p${i.getProductCode()}p${i.getSeatType()}p${i.getCol()}p${i.getRow()}p${i.getPrice()}p${i.getDiscount()}" hidden/>
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
                            <input type ="text" id ="USer" hidden value ="${sessionScope.account.getUserName()}"/>
                            <input type ="submit" hidden value ="${requestScope.room.getNoColSeats()}" id = "id1"/>
                            <input type ="submit" hidden value ="${requestScope.room.getNoColSeats() * requestScope.room.getNoRowSeats()}" id = "id2"/>
                        </c:forEach>
                    </div>
                </div>
                <div class = "tket">
                    <a href = "movie">CHỌN LẠI PHIM</a>
                    <img src = "${requestScope.mov.getImg()}"><!-- comment -->
                    <div  class = "ghe">Ghế chọn mua:<span id ="ghe"></span></div>
                    <div  class ="qtt">Số lượng: <span id ="qtt"></span></div>
                    <div class = "sum">Tổng: <span id ="sum" ></span></div>
                    <div class = "crt"><input type = "button" onclick ="cart('${sessionScope.account.getUserName()}')" value = "Add to cart"/></div>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>

        <script type="text/javascript">
            var t = "";
            var idP = "";
            var cnt = 0;
            var sum = 0;
            function pckSeat(id, code, type, col, row, price, dc) {
                console.log(price);
                let color = document.getElementById(id).style.backgroundColor;
                if (color !== 'green') {
                    if (document.getElementById(id).className !== "insideSeatRed" && document.getElementById(id).className !== "vipRed" && document.getElementById(id).className !== "speRed") {
                        document.getElementById(id).style.backgroundColor = 'green';
                        document.getElementById(id).style.color = 'white';
                        t += " ";
                        t += col;
                        t += row;
                        idP += "/";
                        idP += code;
                        idP += "p";
                        idP += col;
                        idP += row;
                        idP += "p";
                        idP += price;
                        idP += "d";
                        idP += dc;
                        document.getElementById("ghe").innerHTML = t;
                        document.getElementById("qtt").innerHTML = ++cnt;
                        if (type === "1") {
                            sum += (Number(price) - Number(price) * Number(dc));
                            document.getElementById("sum").innerHTML = sum;
                        } else if (type === "2") {
                            sum += (Number(price) - Number(price) * Number(dc));
                            document.getElementById("sum").innerHTML = sum;
                        } else if (type === "3") {
                            sum += (Number(price) - Number(price) * Number(dc));
                            document.getElementById("sum").innerHTML = sum;
                        }
                    }
                } else if (color === "green") {
                    if (t.includes(String(col + row))) {
                        t = t.replace(String(col + row), "");
                        cnt--;
                        if (idP.includes(String("/" + code + "p" + col + row + "p" + price + "d" + dc)))
                            idP = idP.replace(String("/" + code + "p" + col + row  + "p" + price + "d" + dc), "");
                        if (type === "1") {
                            sum -= (Number(price) - Number(price) * Number(dc));
                        } else if (type === "2") {
                            sum -= (Number(price) - Number(price) * Number(dc));
                        } else if (type === "3") {
                            sum -= (Number(price) - Number(price) * Number(dc));
                        }
                        document.getElementById("ghe").innerHTML = t;
                        document.getElementById("qtt").innerHTML = cnt;
                        document.getElementById("sum").innerHTML = sum;
                    }

                    if (type === "1") {
                        document.getElementById(id).style.backgroundColor = 'white';
                        document.getElementById(id).style.color = 'black';
                    } else if (type === "2") {
                        document.getElementById(id).style.backgroundColor = 'blue';
                        document.getElementById(id).style.color = 'white';
                    } else if (type === "3") {
                        document.getElementById(id).style.backgroundColor = 'pink';
                        document.getElementById(id).style.color = 'white';
                    }

                }

                console.log(idP);

            }



            function setCookie(name, value, days) {
                var expires = "";
                if (days) {
                    var date = new Date();
                    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                    expires = "; expires=" + date.toUTCString();
                }
                document.cookie = name + "=" + (value || "") + expires;
            }
// Hàm lấy Cookie
            function getCookie(name) {
                var cookieName = name + "=";
                var docCookie = document.cookie;
                var cookieStart;
                var end;

                if (docCookie.length > 0) {
                    cookieStart = docCookie.indexOf(cookieName);
                    if (cookieStart != -1) {
                        cookieStart = cookieStart + cookieName.length;
                        end = docCookie.indexOf(";", cookieStart);
                        if (end == -1) {
                            end = docCookie.length;
                        }
                        return unescape(docCookie.substring(cookieStart, end));
                    }
                }
                return false;
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
            console.log(document.getElementById("USer").value);
            //displaySeatInCart("thbang");
            var user = String(document.getElementById("USer").value);
            displaySeatInCart(user);

            function displaySeatInCart(user) {
                var seatInCart = [];
                let m = 0;
                for (var i = 0; i < getCookie(user).length; i++) {
                    if (getCookie(user).charAt(i) === "/") {
                        if (getCookie(user).substring(i + 1, i + 3) === "TK") {
                            seatInCart[m] = getCookie(user).substring(i + 1, i + 10);
                            m++;
                        }
                    }
                }

                for (var i = 0; i < seatInCart.length; i++) {
                    var str = seatInCart[i].substring(0, 6) + seatInCart[i].substring(7);
                    var k = 0;
                    var rm;
                    var id = "", code = "", type = "", col = "", row = "", price = "", discount = "";
                    var str2 = String(document.getElementById(str).value);
                    console.log(str2);
                    for (var j = 0; j < str2.length; j++) {
                        if (str2.charAt(j) === "p" && k === 0) {
                            id = str2.substring(0, j);
                            k++;
                        } else if (str2.charAt(j) === "p" && k === 1) {
                            code = str2.substring(j - 6, j);
                            rm = j;
                            k++;
                        } else if (str2.charAt(j) === "p" && k === 2) {
                            type = str2.substring(rm + 1, j);
                            rm = j;
                            k++;
                        } else if (str2.charAt(j) === "p" && k === 3) {
                            col = str2.substring(rm + 1, j);
                            rm = j;
                            k++;
                        } else if (str2.charAt(j) === "p" && k === 4) {
                            row = str2.charAt(rm + 1, j);
                            rm = j;
                            k++;
                        }
                        else if (str2.charAt(j) === "p" && k === 5) {
                            price = str2.substring(rm + 1, j);
                            console.log(price);
                            console.log("n" + (rm + 1));
                            console.log("e" + j);
                            rm = j;
                            k++;
                        }
                        else if (j === str2.length - 1) {
                            discount = str2.substring(rm + 1);
                            rm = j;
                            k++;
                        }
                    }
                    console.log(price);
                    pckSeat(id, code, type, col, row, price, discount);
                    
                }

            }

            function cart(user) {
                if(user === null || user === "" || user === undefined) {
                    window.location = "login";
                }
                //console.log(user);
                var ckie = getCookie(user);
                ckie = String(ckie);
                console.log(ckie)

                var seatInCart = [];
                let m = 0;
                for (var i = 0; i < idP.length; i++) {
                    if (idP.charAt(i) === "/") {
                        if (idP.substring(i + 1, i + 3) === "TK") {
                            seatInCart[m] = idP.substring(i + 1, i + 10);
                            m++;
                        }
                    }
                }
                for (var i = 0; i < seatInCart.length; i++) {
                    if (!ckie.includes(String(seatInCart[i]))) {
                        ckie += "/";
                        ckie += String(seatInCart[i]);
                    }
                }
                console.log(ckie);
                setCookie(user, ckie, 365);
            }
        </script>
    </body>
</html>
