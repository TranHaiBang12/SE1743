<%-- 
    Document   : booking
    Created on : Jun 1, 2023, 1:24:22 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items = "${requestScope.date}" var = "i">
            <h2>${i}</h2>
        </c:forEach>
        <c:forEach items = "${requestScope.city}" var = "i">
            <h2>${i}</h2>
        </c:forEach>
    </body>
</html>
