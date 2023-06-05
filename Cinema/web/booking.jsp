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
        <<link rel="stylesheet" href="style.css"/>
        <style>
            .body{
                background-color: blanchedalmond;
                border-bottom: 2px dashed red;
                padding-bottom: 10px;
                padding-top: 15px;
            }

            .bodyTitle{
                color: black;
                font-size: 40px;
                border-bottom: 1px solid black;
                padding: 15px;
                text-align: center;
            }

            .schedule{
                margin-top: 15px;
                display: flex;
                flex-wrap: wrap;
                border-bottom: 3px solid black;
            }

            .insideSchedule{
                display: flex;
                width: 80px;
                height: 40px;
                margin-left: 20px;
                margin-right: 0px;
                margin-bottom: 10px;
                justify-content: center;
                align-items: center;
                cursor: pointer;
            }
            .notDate{
                display: flex;
                flex-direction: column;
                margin-right: 8px;
                margin-bottom: 5px;
            }
            .isDate{
                font-size: 30px;
            }
            .insideSchedule:hover{
                border: 1px solid black;
                border-radius: 15px;
                padding-top: 10px;
                padding-bottom: 10px;
            }

            .location{
                display: flex;
                border-bottom: 3px solid black;
                flex-wrap: wrap;

            }

            .insideLocation{
                margin-right: 45px;
                cursor: pointer;
                margin-top: 30px;
                margin-left: 30px;
                padding: 10px;
                margin-bottom: 30px;
            }

            .movie{
                display: flex;
                flex-direction: column;
                justify-content: center;
            }

            .movName{
                text-align: center;
                margin-top: 20px;
                margin-bottom: 20px;
                font-size: 30px;
                font-weight: bold;
            }

            .movImg{
                text-align: center;
            }

            .movImg img{
                width: 500px;
                height:550px;
            }

            .form{
                display: flex;

            }

            .insideForm{
                margin-right: 45px;
                cursor: pointer;
                margin-top: 20px;
                margin-left: 30px;
                margin-right: 0px;
                margin-bottom: 20px;
                border: 3px solid black;
                padding: 5px;
                border-radius: 10px;
                padding: 10px;
            }
            
            .insideFormActive{
                margin-right: 45px;
                cursor: pointer;
                margin-top: 20px;
                margin-left: 30px;
                margin-right: 0px;
                margin-bottom: 20px;
                border: 3px solid black;
                padding: 5px;
                border-radius: 10px;
                border: 1px solid black;
                background-color: black;
                color: white;
                border-radius: 10px;
                padding: 10px;
            }
            
         

            .insideScheduleActive{
                display: flex;
                width: 80px;
                height: 40px;
                margin-left: 20px;
                margin-right: 0px;
                margin-bottom: 10px;
                justify-content: center;
                align-items: center;
                cursor: pointer;
                border: 1px solid black;
                border-radius: 15px;
                padding-top: 10px;
                padding-bottom: 10px;

            }

            .insideLocationActive{
                margin-right: 45px;
                cursor: pointer;
                margin-top: 30px;
                margin-left: 30px;

                margin-bottom: 30px;
                border: 1px solid black;
                background-color: black;
                color: white;
                border-radius: 10px;
                padding: 10px;
            }

            .ms{
                margin-top: 20px;
                margin-left: 30px;
                margin-right: 0px;
                margin-bottom: 20px;
            }

        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "movie">
                <div class = "movName">${requestScope.movie.getMovName()}</div>
                <div class = "movImg"><img src = "${requestScope.movie.getImg()}"></div>                
            </div>
            <div class="bodyTitle">
                Lịch Chiếu
            </div>
            <div class = "schedule">
                <c:forEach items = "${requestScope.date}" var = "i">
                    <div class="${requestScope.schePick==i.getId()?"insideScheduleActive":"insideSchedule"}" onclick = "pick('${requestScope.id}', '${i.getId()}', '${requestScope.loPick}', '${requestScope.formPick}')">
                        
                        <div class = "notDate">
                            <span>${i.getMonth()}</span><!-- comment -->
                            <span>${i.getDay()}</span>
                        </div>
                        <div class = "isDate">${i.getDate()}</div>
                    </div>
                </c:forEach>
            </div>
            <div class = "location">
                <c:forEach items = "${requestScope.city}" var = "i">
                    <div class="${requestScope.loPick==i.getId()?"insideLocationActive":"insideLocation"}" onclick = "pick('${requestScope.id}', '${requestScope.schePick}', '${i.getId()}', '${requestScope.formPick}')" >
                        <div>${i.getLoc()}</div>
                    </div>
                </c:forEach>
            </div>
            <%
       
                if(request.getAttribute("ms") != null) {
            %>
            <h3 class = "ms">${requestScope.ms}</h3>
            <%    
               }
                else {
            %>
            <div class = "form">
                <c:forEach items = "${requestScope.form}" var = "i">
                    <div class="${requestScope.formPick==i.getId()?"insideFormActive":"insideForm"}" onclick = "pick('${requestScope.id}', '${requestScope.schePick}', '${requestScope.loPick}', '${i.getId()}')">
                        <div>${i.getFormName()}</div>
                    </div>
                </c:forEach>
            </div>
            <%
                }
            %>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function pick(id, sche, lo, form) {
                window.location = "booking?id=" + id + "&schePick=" + sche + "&loPick=" + lo + "&formPick=" + form;
            }
        </script><!-- comment -->
    </body>
</html>
