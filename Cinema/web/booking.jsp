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
                margin-top: 20px;
                margin-left: 30px;
                margin-right: 0px;
                margin-bottom: 20px;
            }
            
            .insideLocation{
                margin-right: 45px;
                cursor: pointer;
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
                height:500px;
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
                Schedule
            </div>
            <div class = "schedule">
                <c:forEach items = "${requestScope.date}" var = "i">
                    <div class = "insideSchedule">
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
                    <div class = "insideLocation">
                        <div>${i}</div>
                    </div>
                </c:forEach>
            </div>
            <div class = "form">
                <c:forEach items = "${requestScope.form}" var = "i">
                    <div class = "insideForm">
                        <div>${i}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
