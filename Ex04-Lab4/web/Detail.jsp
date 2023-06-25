<%-- 
    Document   : Detail
    Created on : Jun 14, 2023, 11:14:41 AM
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
        <c:set var="conditionVariable" value="true"/>
        <c:forEach var="i" items="${pageContext.request.cookies}" > 
            <c:if test = "${i.name == 'pass' || i.name == 'user'|| i.name == 'rm'|| requestScope.ms != null}">
                <c:if test="${conditionVariable eq 'true'}">
                        <h1>Hello ${sessionScope.account.getUsername()}</h1>
                        <h1>Hello ${sessionScope.account.getPassword()}</h1>
                        <h1>Hello ${sessionScope.account.getDisplayname()}</h1>

                        <c:set var="conditionVariable" value="false"/>
                </c:if> 
            </c:if>

        </c:forEach>
        <c:if test="${conditionVariable eq 'true'}">

            <%
                response.sendRedirect("login.jsp");
            %>


        </c:if> 
    </body>
</html>
