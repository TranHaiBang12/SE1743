<%-- 
    Document   : student
    Created on : May 22, 2023, 4:33:49 PM
    Author     : acer
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" %>
<%@page import = "java.util.List" %>
<%@page import = "model.Student" %>
<%@page import = "java.text.DateFormat" %>
<%@page import = "java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action = "student">
            <label for = "num">Number of Students:</label>
            <%
                if(request.getAttribute("num") != null) {
            %>
            <input type ="number" id ="num" name ="num" value = "<%=request.getAttribute("num")%>"/>
            <%
                }
   
            %>

            <%
                if(request.getAttribute("num") == null) {
                    
            %>
            <input type ="number" id ="num" name ="num" />
            <%
                }
   
            %>


            <input type ="submit" value ="generate"/>
        </form>
        <%
            if(request.getAttribute("data") != null && request.getAttribute("ms") == null) {
                List<Student> list = new ArrayList<>();
                list = (List<Student>)(request.getAttribute("data"));
                if(list.size() > 0) {
        %>
        <table border ="1px">
            <tr>
                <th>ID</th>
                <th>Name</th><!-- <th></th> -->
                <th>Gender</th><!-- comment -->
                <th>DOB</th>
            </tr>

            <%
                    
                    for(int i = 0; i < list.size(); i++) {
                        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                  
            %>
            <tr>
                <td><%=list.get(i).getId()%></td>
                <td><%=list.get(i).getName()%></td><!-- <th></th> -->
                <td><input type = "checkbox"  disabled="disabled"<%=(list.get(i).isGender())?"checked":""%>/></td><!-- comment -->
                <td><%=formatter.format(list.get(i).getDOB())%></td>
            </tr>
            <%
                    
                    }
                }
            %>
        </table>
        <%
            }
        %>
        <%
            if(request.getAttribute("ms") != null) {
        %>
        <h2><%=request.getAttribute("ms")%></h2>
        <%
            }
        %>
    </body>
</html>
