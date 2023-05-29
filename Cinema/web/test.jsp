<%-- 
    Document   : test
    Created on : May 29, 2023, 1:45:51 PM
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
        <c:forEach items = "${requestScope.data}" var = "i">
                    <tr>
                        <td>${i.movID}</td>
                        <td>${i.movName}</td><!-- comment -->
                    </tr>
                </c:forEach>
    </body>
</html>
