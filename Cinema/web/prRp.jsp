<%-- 
    Document   : prRp
    Created on : Jul 2, 2023, 2:08:46 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <<link rel="stylesheet" href="style.css"/>
        <style>
            .ttle{
                text-align: center;
                padding-top: 20px;
                font-size: 27px;
                font-weight: bold;
                margin-bottom: 40px;
            }

            .search{
                display: flex;
                justify-content: space-around;
                font-size: 18px;
            }

            .search input {
                font-size: 18px;
            }

            .srchBtn{
                text-align: center;
                margin-top: 20px;
                padding-bottom: 30px;
            }

            .srchBtn input{
                font-size: 18px;
                padding-top: 5px;
                padding-bottom: 5px;
                padding-right: 12px;
                padding-left: 12px;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">THỐNG KÊ VỀ PHIM</div>
            <form action = "rpp" method = "post">
                <div class = "search">
                    <div>
                        Ngày bắt đầu: <input type ="date" name ="start"/>
                    </div>

                    <div>
                        Ngày kết thúc: <input type ="date" name ="end"/>
                    </div>
                </div>
                <input type ="text" name ="type" hidden value ="${requestScope.type}"/>
                <br/>
                <div class = "srchBtn">
                    <input type = "submit" value ="THỐNG KÊ"/>
                </div>
            </form>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
