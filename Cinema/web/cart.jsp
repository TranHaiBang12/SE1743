<%-- 
    Document   : cart
    Created on : Jun 8, 2023, 12:36:05 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css"/>
        <style>

            .ttle{
                margin-left: 80px;
                margin-right: 60px;
                padding-top: 30px;
                font-size: 30px;
                font-weight: bold;
                margin-bottom: 20px;
                padding-bottom: 10px;
                text-shadow: 10px 10px 5px #666666;
                color: brown
            }

            .numCart {
                margin-left: 80px;
                margin-bottom: 10px;
                font-size: 18px;
            }

            .bld{
                font-weight: bold;
                font-size: 18px;
            }

            .cart {
                display: flex;
                flex-direction: column;
                width: 60%;
                border: 1px solid black;

                margin-right: 20px;
                margin-left: 82px;
                padding-bottom: 25px;
            }

            .choice{
                margin-top: 20px;
                margin-right: 20px;
                margin-left: 20px;
                display: flex;
                justify-content: space-evenly;
                width: 60%;
                border: 1px solid black;
            }

            .choice div{
            }


            .tkEtChoice {
                border-right: 1px solid black;
                padding: 10px;
                font-weight: bold;
                cursor: pointer;
                width: 33%;
                text-align: center;
            }

            .foodChoi {
                padding: 10px;
                font-weight: bold;
                cursor: pointer;
                width: 33%;
                text-align: center;
            }

            .al {
                border-right: 1px solid black;
                padding: 10px;
                font-weight: bold;
                cursor: pointer;
                width: 33%;
                text-align: center;
            }

            #active {
                background-color: black;
                color: white;
            }

            .insideCart {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
                margin-bottom: 20px;
            }

            .imGe {
                display: flex;
                margin-left: 20px;
            }

            .imGe img{
                width: 150px;
                height: 150px;
            }

            .intu{
                display: flex;
                flex-direction: column;
                margin-right: 20px;

            }

            .tkt{
                font-size: 20px;
                font-weight: bold;
                margin-left: 40px;
                display: flex;
                flex-direction: column;
            }

            .cartName {
                margin-bottom: 10px;
            }

            .cartPrice{
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 10px;
            }

            .intu input{
                margin-right: 12px;
                font-size: 20px;
                height: 40px;
                width: 30px;
                border: none;
                cursor: pointer;
                color: black;
                background-color: white;
                margin-bottom: 10px;
            }

            .deleteButton{
                cursor: pointer;
            }

            .containCart {
                display: flex;

                justify-content: flex-start;
                position: relative;
            }

            .totalAmount {
                position: sticky;
                top: 200px;
                right: 0;
                margin-top: 20px;
                margin-right: 20px;
                margin-bottom: 20px;
                border: 1px solid black;
                height: 20%;
                padding-top: 50px;
                padding-right: 20px;
                padding-left: 20px;
                padding-bottom: 50px;
                font-size: 25px;
                font-weight: bold;
            }

            .totalAmount div {
                margin-bottom: 20px;
                text-align: center;
            }
            .tien{
                color:red;
            }

            .totalAmount input{
                color: white;
                background-color: red;
                font-weight: bold;
                width: 100%;
                height: 40px;
            }

            .outTotalAmount{
                position: relative;
                width: 20%;
                height: 1200px;
            }

            .seat{
                color: red;
            }

            *{
                box-sizing: border-box;
            }

            .dltAll{
                margin-left: 20px;
                margin-bottom: 20px;
                margin-top: 20px;
                width: 60%;
                height: 30px;
            }

            .dltAll input{
                width: 100%;
                height: 100%;
                background-color: red;
                color: white;
            }

            .pay input{
                cursor: pointer;
            }

            #ms{
                font-size: 25px;
                padding-left: 20px;
            }

            .cart{
                padding-left: 0px;
            }



        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>

        <div class = "body">
            <div class = "ttle">
                Giỏ hàng của bạn
            </div>
            <c:if test = "${requestScope.listCart != null}">
                <div class = "numCart">
                    Bạn đang có<span class = "bld"> <label id = "nm">${requestScope.totalQuantity}</label> sản phẩm</span> trong giỏ hàng
                </div>
            </c:if>
            <div class = "containCart">

                <div class = "cart">
                    <div class = "choice">
                        <div id = "active" class = "al" onclick = "bActive('active')">ALL</div>
                        <div id ="tkEtChoice" class = "tkEtChoice" onclick = "bActive('tkEtChoice')">VÉ</div>
                        <div id ="foodChoi" class = "foodChoi" onclick = "bActive('foodChoi')">ĐỒ ĂN</div>

                    </div>
                    <div class = "dltAll">
                        <input type ="button" value ="XÓA TẤT CẢ" onclick = "dltAll('${sessionScope.account.getUserName()}')"/>
                    </div>
                    <c:if test = "${requestScope.ms != null}">
                        <span id = "ms">${requestScope.ms}</span>
                    </c:if>
                    <c:if test = "${requestScope.ms == null}">
                        <div id = "doan">
                            <c:forEach items = "${requestScope.listCart}" var = "i">
                                <div id ="list${i.getFood().getProductCode()}" class = "myCart">

                                    <div class = "insideCart">
                                        <div class = "imGe">
                                            <img src = "${i.getFood().getImg()}">

                                            <div class = "tkt">${i.getFood().getFoodDescript()}</div>
                                        </div>
                                        <div class = "intu">

                                            <div class = "cartPrice"><label id ="price${i.getFood().getProductCode()}">${i.getFood().getPriceNS() * i.getQuantity()}</label><span class = "donvi">đ</div>
                                            <div>
                                                <input type ="submit" name ="cartButton" onclick = "a('${i.getFood().getProductCode()}', '+', '${sessionScope.account.getUserName()}', '${i.getQuantity()}', '${i.getFood().getPriceNS()}', '${i.getFood().getDiscount()}')" id ="increaseButton" value = "+"/>
                                                <input type ="button" name ="cartButton" id ="${i.getFood().getProductCode()}" value = "${i.getQuantity()}"/>
                                                <input type ="submit" name ="cartButton" onclick = "a('${i.getFood().getProductCode()}', '-', '${sessionScope.account.getUserName()}', '${i.getQuantity()}', '${i.getFood().getPriceNS()}', '${i.getFood().getDiscount()}')" id ="decreaseButton" value = "-"/>
                                            </div>
                                            <div class = "deleteButton" onclick = "dlt('${i.getFood().getProductCode()}', '${i.getQuantity()}', '${sessionScope.account.getUserName()}', '${i.getFood().getPrice()}', '${i.getFood().getDiscount()}')">
                                                Xóa
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div id = "ve">
                            <c:forEach items = "${requestScope.listTicket}" var = "t">
                                <div id ="list${t.getTicket().getProductCode()}${t.getSeat()}" class = "myCart">

                                    <div class = "insideCart">
                                        <div class = "imGe">
                                            <img src = "images/cinemaTicket.jpg">
                                            <div class = "tkt">
                                                <div class = "cartName">${t.getTicket().getMovie().getMovName()}</div>
                                                <div class ="cartSeat">Ghế: <span class = "seat">${t.getSeat()}</span></div>
                                                <div>Phòng: <span class = "seat">${t.getTicket().getRoomID()}</span></div>
                                            </div>
                                        </div>

                                        <div class = "intu">

                                            <div class = "cartPrice"><label id ="price${t.getTicket().getProductCode()}">${t.getTicket().getPriceNS()}</label><span class = "donvi">đ</div>
                                            <div>
                                                <input type ="button" name ="cartButton" value = "1"/>
                                            </div>

                                            <div class = "deleteButton" onclick = "dlt('${t.getTicket().getProductCode()}', '${t.getSeat()}', '${sessionScope.account.getUserName()}', '${t.getTicket().getPrice()}', '${t.getTicket().getDiscount()}')">
                                                Xóa
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
                <div class = "outTotalAmount">
                    <div class = "totalAmount">
                        <div>Thông tin đơn hàng</div>
                        <div>Tổng tiền: <span class = "tien"><label id = "ttAm">${requestScope.totalAmount!=null?(requestScope.totalAmount):0}</label>đ</span></div>
                        <div class = "pay"><input type = "submit" value = "THANH TOÁN" onclick = "pay('${sessionScope.account.getUserName()}')"></div>
                    </div>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            var t = "";
            var dltA = 0;
            if (String(document.getElementById("ms").innerHTML) !== null) {
                if (String(document.getElementById("ms").innerHTML) !== "") {
                    alert(String(document.getElementById("ms").innerHTML));
                }
            }



            function dltAll(user) {
                dltA = 1;

                if (String(getCookie(user)) !== "" && String(getCookie(user)) !== "false") {
                    var ans = confirm("Do you want to delete all your cart items ?");
                    if (String(ans) === 'true') {
                        setCookie(user, "", 365);
                        document.getElementById("doan").style.display = 'none';
                        document.getElementById("ve").style.display = 'none';
                        console.log(document.getElementById("doan").style.display + " " + document.getElementById("ve").style.display);
                        document.getElementById("nm").innerHTML = 0;
                        document.getElementById("ttAm").innerHTML = 0;
                    } else if (String(ans) === 'false') {

                    }
                } else {
                    alert("Giỏ hàng hiện đang trống");
                }

            }

            function dlt(id, more, user, price, discount) {
                console.log(id);
                console.log(getCookie(user));
                var value = getCookie(user);
                if (Number(discount) !== 0) {
                    let b = discount * 100;
                    let c = Math.round(b);
                    discount = c / 100;
                }
                if (id.includes("FD")) {
                    if (value.includes(id)) {
                        value = value.replace("/" + id + "p" + Number(document.getElementById(id).value) + "p" + price + "p" + discount, "");
                        console.log("t" + value);
                        console.log("/" + id + "p" + Number(document.getElementById(id).value) + "p" + price + "p" + discount);
                        setCookie(user, value, 365);
                        console.log("1");
                    }
                    document.getElementById("nm").innerHTML -= (Number(more));
                    document.getElementById("list" + id).style.display = 'none';
                    document.getElementById("ttAm").innerHTML = Number(document.getElementById("ttAm").innerHTML) - (Number(price) * Number(document.getElementById(id).value));
                } else {
                    if (value.includes(id)) {
                        value = value.replace("/" + id + "p" + more + "p" + price + "d" + discount, "");
                        console.log(value);
                        console.log("/" + id + "p" + more + "p" + price + "d" + discount)
                        setCookie(user, value, 365);
                        console.log("1");
                    }
                    document.getElementById("nm").innerHTML--;
                    document.getElementById("list" + id + more).style.display = 'none';
                    document.getElementById("ttAm").innerHTML = Number(document.getElementById("ttAm").innerHTML) - Number(price);
                }


            }

            function a(cartNumber, op, user, quantity, price, discount) {
                console.log(Number(price));
                console.log(Number(discount));

                let b = discount * 100;
                let c = Math.round(b);
                discount = c / 100;
                console.log(cartNumber + " " + op + " " + user + " " + quantity + " " + price);
                console.log(document.getElementById(cartNumber));
                var value = getCookie(user);
                if (String(op) === String('+')) {
                    document.getElementById(cartNumber).value++;
                    var t = cartNumber + "p" + document.getElementById(cartNumber).value;
                    console.log(document.getElementById(cartNumber).value)
                    if (value.includes(cartNumber + "p" + quantity))
                        value = value.replace(cartNumber + "p" + quantity, t);
                    else {
                        value = value.replace(cartNumber + "p" + (Number(document.getElementById(cartNumber).value - 1)), t);
                    }
                    console.log(document.getElementById(cartNumber).value)
                    document.getElementById("price" + cartNumber).innerHTML = Number(document.getElementById("price" + cartNumber).innerHTML) + (Number(price));
                    document.getElementById("ttAm").innerHTML = Number(document.getElementById("ttAm").innerHTML) + (Number(price));
                    setCookie(user, value, 365);
                    document.getElementById("nm").innerHTML++;

                } else if (String(op) === String('-') && document.getElementById(cartNumber).value > 0) {
                    document.getElementById(cartNumber).value--;
                    var t = cartNumber + "p" + document.getElementById(cartNumber).value;
                    if (value.includes(cartNumber + "p" + quantity))
                        value = value.replace(cartNumber + "p" + quantity, t);
                    else {
                        value = value.replace(cartNumber + "p" + (Number(document.getElementById(cartNumber).value) + 1), t);
                    }
                    console.log(document.getElementById(cartNumber).value);
                    document.getElementById("price" + cartNumber).innerHTML = Number(document.getElementById("price" + cartNumber).innerHTML) - (Number(price));
                    document.getElementById("ttAm").innerHTML = Number(document.getElementById("ttAm").innerHTML) - (Number(price));
                    setCookie(user, value, 365);
                    document.getElementById("nm").innerHTML--;
                }
            }

            function setCookie(cname, cvalue, exdays) {
                var d = new Date();
                d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
                var expires = "expires=" + d.toUTCString();
                document.cookie = cname + "=" + cvalue + "; " + expires;
            }

            function bActive(id) {
                if (dltA === 0) {
                    if (id === "tkEtChoice") {
                        document.getElementById(id).style.color = 'white';
                        document.getElementById(id).style.backgroundColor = 'black';
                        document.getElementById("active").style.color = 'black';
                        document.getElementById("active").style.backgroundColor = 'white';
                        document.getElementById("foodChoi").style.color = 'black';
                        document.getElementById("foodChoi").style.backgroundColor = 'white';
                        document.getElementById("ve").style.display = 'block';
                        document.getElementById("doan").style.display = 'none';
                    } else if (id === "foodChoi") {
                        document.getElementById(id).style.color = 'white';
                        document.getElementById(id).style.backgroundColor = 'black';
                        document.getElementById("active").style.color = 'black';
                        document.getElementById("active").style.backgroundColor = 'white';
                        document.getElementById("tkEtChoice").style.color = 'black';
                        document.getElementById("tkEtChoice").style.backgroundColor = 'white';
                        document.getElementById("doan").style.display = 'block';
                        document.getElementById("ve").style.display = 'none';
                    } else {
                        document.getElementById(id).style.color = 'white';
                        document.getElementById(id).style.backgroundColor = 'black';
                        document.getElementById("foodChoi").style.color = 'black';
                        document.getElementById("foodChoi").style.backgroundColor = 'white';
                        document.getElementById("tkEtChoice").style.color = 'black';
                        document.getElementById("tkEtChoice").style.backgroundColor = 'white';
                        document.getElementById("doan").style.display = 'block';
                        document.getElementById("ve").style.display = 'block';
                    }
                } else {
                    if (id === "tkEtChoice") {
                        document.getElementById(id).style.color = 'white';
                        document.getElementById(id).style.backgroundColor = 'black';
                        document.getElementById("active").style.color = 'black';
                        document.getElementById("active").style.backgroundColor = 'white';
                        document.getElementById("foodChoi").style.color = 'black';
                        document.getElementById("foodChoi").style.backgroundColor = 'white';

                    } else if (id === "foodChoi") {
                        document.getElementById(id).style.color = 'white';
                        document.getElementById(id).style.backgroundColor = 'black';
                        document.getElementById("active").style.color = 'black';
                        document.getElementById("active").style.backgroundColor = 'white';
                        document.getElementById("tkEtChoice").style.color = 'black';
                        document.getElementById("tkEtChoice").style.backgroundColor = 'white';

                    } else {
                        document.getElementById(id).style.color = 'white';
                        document.getElementById(id).style.backgroundColor = 'black';
                        document.getElementById("foodChoi").style.color = 'black';
                        document.getElementById("foodChoi").style.backgroundColor = 'white';
                        document.getElementById("tkEtChoice").style.color = 'black';
                        document.getElementById("tkEtChoice").style.backgroundColor = 'white';

                    }
                }
            }

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

            function pay(user) {
                console.log(user);
                console.log(getCookie(user));
                if (getCookie(user) !== "" && String(getCookie(user)) !== "false") {
                    window.location = "pay";
                } else {
                    alert("Giỏ hàng hiện đang trống");
                }
            }

        </script>
    </body>
</html>
