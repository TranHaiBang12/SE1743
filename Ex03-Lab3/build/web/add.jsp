<%-- 
    Document   : add
    Created on : May 29, 2023, 8:55:18 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form>
            <label for = "name">Name:</label>
            <input type ="text" id ="name" name ="name"/>
            
            <br/><!-- comment -->
            Gender:
            <input type ="radio" id ="male" name ="gender" value = "0"/>
            <label for = "male">Male</label>
            <input type ="radio" id ="female" name ="gender" value = "1"/>
            <label for = "female">Female</label>
            
            <br/>
            
            <label for = "date">DOB:</label>
            <input type ="date" id ="date" name ="date"/>
            
            <br/><!-- comment -->
            
            <input type ="submit" value ="Create"/>
            <h2><%=request.getAttribute("ms")%></h2>
        </form>
            
    </body>
</html>
