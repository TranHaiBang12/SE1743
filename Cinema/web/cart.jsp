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
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">
                Giỏ hàng của bạn
            </div>
            <div class = "cart">
                <c:if test = "${requestScope.ms != null}">
                    <span>${requestScope.ms}</span>
                </c:if>
                <c:if test = "${requestScope.ms == null}">
                    <c:forEach items = "${requestScope.listCart}" var = "i">
                        <div class = "insideCart">
                            <div class = "imGe">
                                <img src = "${i.getFood().getImg()}">
                            </div>
                            <div class = "intu">
                                <div class = "cartName">${i.getFood().getFoodDescript()}</div>
                                <div class = "cartPrice">${i.getFood().getPrice()}</div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
