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
        <<link rel="stylesheet" href="style.css"/>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "loc">
                <c:forEach items = "${requestScope.listLoc}" var = "i">
                </c:forEach>
            </div>
        </div>
        <div id = "footer">
                <%@include file = "footer.jsp" %>
        </div>
    </body>
    </html>
