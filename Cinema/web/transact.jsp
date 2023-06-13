<%-- 
    Document   : Transaction
    Created on : Jun 12, 2023, 5:25:17 PM
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
            <c:forEach items = "${requestScope.listO}" var = "l">
                <option>${l.getId()}. ${l.getLoc()}</option>


            </c:forEach>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
