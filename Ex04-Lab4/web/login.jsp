<%-- 
    Document   : login
    Created on : Jun 14, 2023, 11:14:13 AM
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
            <c:if test = "${i.name == 'rm'}">
                <c:set var="rm" value="${i.value}"/>
            </c:if>
            <c:if test = "${i.name == 'user'}">
                <c:set var="user" value="${i.value}"/>
            </c:if>
            <c:if test = "${i.name == 'pass'}">
                <c:set var="pass" value="${i.value}"/>
            </c:if>
        </c:forEach>
        <form action = "login" method = "post">
            <label for = "user">Username:</label><!-- comment -->
            <input type ="text" id ="user" name ="user" value = "${user}"/>

            <br/><!-- comment -->

            <label for = "pass">Password:</label><!-- comment -->
            <input type ="password" id ="pass" name ="pass" value = "${pass}"/>

            <br/><!-- comment -->


            <input type ="checkbox" name ="rm" value ="1" ${rm == 1?"checked":""}/>Remember me

            <br/>

            <input type ="submit" value ="Login"/>
        </form>
    </body>
</html>
