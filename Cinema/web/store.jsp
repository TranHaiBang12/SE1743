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

            .cart img{
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
                <div class = "cart">
                    <a href = "cart"><img src ="images/shoppingCartIcon.png"/><a/>
                </div>
            </div>
            <div class = "item">
                <c:forEach items = "${requestScope.listPerPage}" var = "i">
                    <div class = "insideItem">
                        <div><img src = "${i.getImg()}"></div>
                        <div class = "name">
                            ${i.getFoodDescript()}                  
                        </div>
                        <div>
                            <span class = "newPrice">${i.getPrice()}<span class = "donvi">đ<span></span>
                                    <c:if test = "${i.getDiscount() != 0}">
                                        <span>${i.getprice()} / ${i.getDiscount()}</span>
                                    </c:if>

                                    </div>
                                    <div class = "cart">
                                        <input type ="submit" value ="Add to cart" onclick = "cart('${i.getProductCode()}', '${sessionScope.account.getUserName()}')"/>
                                    </div>

                                    </div>
                                    <div class = "${(i.getId() % 3 == 0)?"breaker":""}">

                                    </div>
                                </c:forEach>
                                </div>
                                <div class = "pagination">
                                    <a href ="store?page=${(page - 1) < 1?(1):(page-1)}"><</a>
                                    <c:forEach begin = "${1}" end = "${totalPage}" var = "i">
                                        <a class ="${i == page ? "active":"noActive"}" href ="store?page=${i}">${i}</a>
                                    </c:forEach>
                                    <a href ="store?page=${(page + 1) > totalPage?(1):(page+1)}">></a>
                                </div>

                                </div>
                                <div id = "footer">
                                    <%@include file = "footer.jsp" %>
                                </div>
                                <script>
                                    var t = "/";
                                    function setCookie(name, value, days) {
                                        var expires = "";
                                        if (days) {
                                            var date = new Date();
                                            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                                            expires = "; expires=" + date.toUTCString();
                                        }
                                        document.cookie = name + "=" + (value || "") + expires;
                                    }
                                    function cart(id, username) {
                                        console.log(id);
                                        if (getCookie(username) === null) {
                                            t = "";
                                        } else {
                                            t = String(getCookie(username));
                                        }
                                        if (getCookie(username) === null) {
                                            setCookie(username, id, 365);
                                            console.log("1");
                                        } else {
                                            if (!t.includes(id)) {
                                                console.log("2");
                                                t = t + "/";
                                                t = t + id;
                                                t = t + "p";
                                                t = t + "1";
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
