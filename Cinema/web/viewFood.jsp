<%-- 
    Document   : viewFood
    Created on : Jun 25, 2023, 9:56:00 AM
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
                text-align: center;
            }
            
            h2{
                padding-top: 20px;
                font-size: 30px;
                margin-bottom: 30px;
            }
            
            .fIn4 img{
                width: 800px;
                height: 600px;
            }
            
            .morefIn4{
                padding-bottom: 20px;
            }
            
            .morefIn4 div{
                margin-top: 20px;
                font-size: 20px;
                font-weight: bold;
            }
            
            .morefIn4 input{
                width: 50%;
                height: 30px;
                font-size: 20px;
                cursor: pointer;
                color: white;
                background-color: red;
            }
            
            .rd{
                color: red;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div><!-- comment -->
        <div class = "body">
            <div class = "fIn4">
                <div>
                    <h2><span  class = "rd">${requestScope.f.getFoodDescript()}</span></h2>
                </div>
                <div>
                    <img src ="${requestScope.f.getImg()}"/>
                </div>
            </div>
            <div class = "morefIn4">
                <div>
                    Thể Loại: <span  class = "rd">${requestScope.f.getTypeName()}</span>
                </div>
                <div>
                    Khuyến Mại: <span class = "rd">${requestScope.f.getDiscount()}%</span>
                </div>
                <div>
                    Giá: <span  class = "rd">${requestScope.f.getPrice()}đ</span>
                </div>
                <div>
                    Tình Trạng: <span  class = "rd">${requestScope.f.getStatus()}</span>
                </div>
                <div>
                    <a href = "updf?id=${requestScope.id}"><input type = "button" value = "UPDATE"/></a>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
