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
                padding-top: 25px;
                margin-left: 20px;
                font-size: 30px;
                font-weight: bold;
                border-bottom: 3px solid black;
                margin-bottom: 20px;
                padding-bottom: 10px;
            }

            .numCart {
                margin-left: 20px;
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
                margin-top: 20px;
                margin-right: 20px;
                margin-left: 20px;
                padding-bottom: 25px;
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

            .cartName{
                font-size: 20px;
                font-weight: bold;
                margin-left: 40px;
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
                    <c:if test = "${requestScope.ms != null}">
                        <span>${requestScope.ms}</span>
                    </c:if>
                    <c:if test = "${requestScope.ms == null}">

                        <c:forEach items = "${requestScope.listCart}" var = "i">
                            <div class = "myCart">

                                <div class = "insideCart">
                                    <div class = "imGe">
                                        <img src = "${i.getFood().getImg()}">
                                        <div class = "cartName">${i.getFood().getFoodDescript()}</div>
                                    </div>
                                    <div class = "intu">

                                        <div class = "cartPrice"><label id ="price${i.getFood().getProductCode()}">${i.getFood().getPrice() * i.getQuantity()}</label><span class = "donvi">đ</div>
                                        <div>
                                            <input type ="submit" name ="cartButton" onclick = "a('${i.getFood().getProductCode()}', '+', '${sessionScope.account.getUserName()}', '${i.getQuantity()}', '${i.getFood().getPrice()}')" id ="increaseButton" value = "+"/>
                                            <input type ="button" name ="cartButton" id ="${i.getFood().getProductCode()}" value = "${i.getQuantity()}"/>
                                            <input type ="submit" name ="cartButton" onclick = "a('${i.getFood().getProductCode()}', '-', '${sessionScope.account.getUserName()}', '${i.getQuantity()}', '${i.getFood().getPrice()}')" id ="decreaseButton" value = "-"/>
                                        </div>
                                        <div class = "deleteButton">
                                            Xóa
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                <div class = "totalAmount">
                    <div>Thông tin đơn hàng</div>
                    <div>Tổng tiền: </div>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script>


            function a(cartNumber, op, user, quantity, price) {
                var value = getCookie(user);
                if (String(op) === String('+')) {
                    document.getElementById(cartNumber).value++;
                    var t = cartNumber + "p" + document.getElementById(cartNumber).value;
                    console.log(t);
                    if (value.includes(cartNumber + "p" + quantity))
                        value = value.replace(cartNumber + "p" + quantity, t);
                    else {
                        value = value.replace(cartNumber + "p" + (Number(document.getElementById(cartNumber).value - 1)), t);
                    }
                    document.getElementById("price" + cartNumber).innerHTML = Number(document.getElementById("price" + cartNumber).innerHTML) + Number(price);
                    console.log(value);
                    setCookie(user, value, 365);
                    document.getElementById("nm").innerHTML++;

                } else if (String(op) === String('-') && document.getElementById(cartNumber).value > 0) {
                    document.getElementById(cartNumber).value--;
                    var t = cartNumber + "p" + document.getElementById(cartNumber).value;
                    console.log(t);
                    if (value.includes(cartNumber + "p" + quantity))
                        value = value.replace(cartNumber + "p" + quantity, t);
                    else {
                        value = value.replace(cartNumber + "p" + (Number(document.getElementById(cartNumber).value) + 1), t);
                    }
                    document.getElementById("price" + cartNumber).innerHTML = Number(document.getElementById("price" + cartNumber).innerHTML) - Number(price);
                    console.log(value);
                    setCookie(user, value, 365);
                    document.getElementById("nm").innerHTML--;
                }
                console.log(getCookie(user));
            }

            function setCookie(cname, cvalue, exdays) {
                var d = new Date();
                d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
                var expires = "expires=" + d.toUTCString();
                document.cookie = cname + "=" + cvalue + "; " + expires;
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

        </script>
    </body>
</html>
