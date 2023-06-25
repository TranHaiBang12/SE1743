<%-- 
    Document   : rq4
    Created on : Jun 19, 2023, 9:14:23 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    </head>
    <body>
    <c:forEach items = "${requestScope.emp}" var = "i">
        ${i.getAddress().getStreet()} ${i.getAddress().getCity()} ${i.getAddress().getZIP()} ${i.getName().getFirst()} ${i.getName().getLast()}
        <br/>
    </c:forEach>
</body>
</html>
