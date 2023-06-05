<%-- 
    Document   : pickSeat
    Created on : Jun 5, 2023, 2:21:24 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <<link rel="stylesheet" href="style.css"/>
        <style>
            .body{
                background-color: white;
                border-bottom: 2px dashed red;
                padding-bottom: 50px;
                padding-top: 15px;
            }
            
            .ttle{
                text-align: center;
                font-size: 30px;
                margin-bottom: 25px;
            }
            
            .urSelection{
                text-align: center;
                font-size: 20px;
                margin-bottom: 25px;
            }
            
            .mvName {
                color: red;
            }
            
            .fName {
                color: red;
            }
            
            .scheIn4{
                text-align: center;
            }
            
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <h3 class = "ttle">CHỌN GHẾ NGỒI</h3>
            <div class = "urSelection">
                BẠN ĐÃ CHỌN: <span class = "mvName">${requestScope.movName}, </span> <span class = "fName">${requestScope.formName}</span>
            </div>
            <div class = "scheIn4">
                PHÒNG CHIẾU: <span class = "scheRoom">${requestScope.sche.getRoomID()}</span>, TẦNG <span>${requestScope.room.getFloor()}</span>
                <br/>
                SUẤT CHIẾU: <span class = "scheTime">${requestScope.sche.getStartTim()}</span> - <span>${requestScope.day} </span><span>${requestScope.dateFormat}</span>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
