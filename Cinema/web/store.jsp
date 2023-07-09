<%-- 
    Document   : store
    Created on : Jun 7, 2023, 9:02:33 AM
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
            .pagination{
                text-align: center;
                padding-bottom: 15px;
            }

            .pagination a{
                text-decoration: none;
            }

            .noActive{
                text-decoration: none;
                color: black;
                border: 1px solid black;
                padding: 5px;
                border-radius: 18px;
            }

            .active{
                text-decoration: none;
                border: 1px solid black;
                padding: 5px;
                color: white;
                border-radius: 18px;
                background-color: black;
            }

            .active a{
                color: white;
            }

            .item{
                display: flex;
                flex-direction: row;
                flex-wrap: wrap;
                text-align: center;
                justify-content: center;
            }

            .insideItem img{
                width: 295px;
                height: 300px;
            }

            .insideItem{
                display: flex;
                flex-direction: column;
                width: 300px;
                border: 2px solid #d7ded9;
                margin-right: 20px;
                margin-bottom: 20px;
                margin-top: 20px;
            }

            .breaker {
                flex-basis: 100%;
                height: 0;
            }

            .cart input{
                height: 30px;
                width: 90%;
                border: 1px solid black;
                border-radius: 10px;
                color: white;
                background-color: black;
                cursor: pointer;
                margin-bottom: 10px;
            }

            .insideItem div{
                margin-top: 20px;
            }

            .name{
                font-size: 20px;
                font-weight: bold;
            }

            .newPrice{
                font-size: 17px;
                color: red;
            }

            .donvi{
                text-decoration: underline;
            }

            .ttle{
                display: flex;
                justify-content: space-between;
                align-items: center;
                border-bottom: 3px solid black;
                padding-top: 20px;
            }

            .cart2 img{
                height: 40px;
                width:40px;
                margin-right: 20px;
                border: 1px solid black;
                border-radius: 12px;
                padding: 5px;
                background: orange;
                cursor: pointer;
            }

            .prD{
                margin-left: 10px;
                font-size: 30px;
            }



            .cart2 {
                width: 40px;
                margin-right: 20px;
            }

            .cart2 a{
                width: 100%;
                height: 100%;
            }

            .cart2 img{
                width: 100%;
                height: 100%;
            }

            .searchBar img{
                width: 25px;
                height: 25px;
                border: 1px solid black;
                margin-right: 8px;
                border-radius: 5px;
                cursor: pointer;
            }

            .searchBar input{
                width: 300px;
                height: 20px;
                font-size: 18px;
                padding: 10px;
            }

            .searchBar {
                display: flex;
                align-items: center;
                margin-right: 10px;
            }

            .slt{
                margin-left: 10px;
                font-size: 18px;
            }

            .slt select {
                font-size: 18px;
            }

            .search{
                display: flex;
                justify-content: space-between;
                margin-top: 15px;
            }

            h2{
                text-align: center;
                color: red;
                font-size:25px;
                margin-top: 30px;
                padding-bottom: 30px;
            }

            .oldPrice {
                margin-left: 20px;
                text-decoration: line-through;
                color: red;
            }

        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">
                <div class = "prD">
                    Products
                </div>
                <div class = "cart2">
                    <img onclick="crt()" src ="images/shoppingCartIcon.png"/>
                </div>
            </div>
            <div>
                <form class = "search" id = "srch" action = "store" method = "post">
                    <div class = "slt">
                        <label>Thể loại: </label>
                        <select onchange ="cnaGE()"  class = "type" id = "type" name = "type">
                            <option value = "" ${requestScope.type==""?"selected":""}>All</option>
                            <option value = "RC" ${requestScope.type=="RC"?"selected":""}>Cơm</option>
                            <option value = "PC" ${requestScope.type=="PC"?"selected":""}>Bỏng Ngô</option>
                            <option value = "HB" ${requestScope.type=="HB"?"selected":""}>Hamburger</option>
                            <option value = "SL" ${requestScope.type=="SL"?"selected":""}>Salad</option>
                            <option value = "KC" ${requestScope.type=="KC"?"selected":""}>KFC</option>
                            <option value = "SN" ${requestScope.type=="SN"?"selected":""}>Snack</option>
                            <option value = "DR" ${requestScope.type=="DR"?"selected":""}>Đồ Uống</option>
                        </select>
                    </div>
                    <div class = "searchBar">
                        <img onclick ="cnaGE()" src = "images/searchIcon.png"/>
                        <input type="text" name ="key" placeholder="Tìm kiếm"/>
                    </div>
                    <input type ="submit" value ="s"/>
                </form>
            </div>
            <c:if test = "${requestScope.ms == null}">
                <div class = "item">
                    <c:forEach items = "${requestScope.listPerPage}" var = "i">
                        <div class = "insideItem">
                            <div><img src = "${i.getImg()}"></div>
                            <div class = "name">
                                ${i.getFoodDescript()}                  
                            </div>
                            <div>
                                <span class = "newPrice">${i.getPrice() - i.getPrice() * i.getDiscount()}<span class = "donvi">đ</span></span>
                                <c:if test = "${i.getDiscount() != 0}">
                                    <span class = "oldPrice">${i.getPrice()}<span class = "donvi">đ</span></span>
                                </c:if>

                            </div>
                            <div class = "cart">
                                <input type ="submit" value ="Add to cart" onclick = "cart('${i.getProductCode()}', '${i.getPrice()}', '${i.getDiscount()}', '${sessionScope.account.getUserName()}')"/>
                            </div>

                            <c:if test = "${sessionScope.account.getRole() == 3}">
                                <div class = "cart">
                                    <a href = "viewf?id=${i.getProductCode()}"><input type ="button" value ="View"/></a>
                                </div>
                            </c:if>

                        </div>
                        <div class = "${(i.getId() % 3 == 0)?"breaker":""}">

                        </div>
                    </c:forEach>
                </div>
                <div class = "pagination">
                    <a href ="store?page=${(page - 1) < 1?(1):(page-1)}&type=${requestScope.type != null ?requestScope.type:""}&key=${requestScope.key?requestScope.key:""}"><</a>
                    <c:forEach begin = "${1}" end = "${totalPage}" var = "i">
                        <a class ="${i == page ? "active":"noActive"}" href ="store?page=${i}&type=${requestScope.type}&key=${requestScope.key}">${i}</a>
                    </c:forEach>
                    <a href ="store?page=${(page + 1) > totalPage?(1):(page+1)}&type=${requestScope.type != null ?requestScope.type:""}&key=${requestScope.key?requestScope.key:""}">></a>
                </div>
            </c:if>
            <c:if test = "${requestScope.ms != null}">
                <h2>${requestScope.ms}</h2>
                <h2><a href = "store">Bấm vào đây để về cửa hàng</a></h2>
            </c:if>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script>
            var t = "";

            console.log(t);
            function cnaGE() {

                document.getElementById("srch").submit();
            }
            function crt() {
                window.location = "cart";
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
            function cart(id, price, discount, username) {
                if (getCookie(username) !== null) {
                    t = getCookie(username);
                } else {
                    t = "";
                }

                if (getCookie(username) === null) {
                    setCookie(username, id, 365);
                    console.log("1");
                } else {
                    if (!String(t).includes(id)) {
                        console.log("2");
                        t = t + "/";
                        t = t + id;
                        t = t + "p";
                        t = t + "1";
                        t = t + "p";
                        t = t + price;
                        t = t + "p";
                        t = t + discount;
                    }
                    console.log(t);
                    setCookie(username, t, 365);
                }
                console.log(getCookie(username));
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
        </script>
    </body>
</html>
