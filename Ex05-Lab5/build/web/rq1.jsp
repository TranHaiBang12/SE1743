<%-- 
    Document   : rq1
    Created on : Jun 19, 2023, 9:14:07 AM
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
    <c:forEach items = "${requestScope.names}" var = "i">
        ${i}
        <br/>
    </c:forEach>
</body>
</html>
