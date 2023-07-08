<%-- 
    Document   : event
    Created on : Jul 8, 2023, 2:35:10 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css"/>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <style>
            .ttle{
                text-shadow: 0px 0px 3px yellow, 0px -5px 5px red;
                margin-left: 80px;
                padding-top: 20px;
                text-align: center;
                font-size: 30px;
                border-top: 2px solid black;
            }
            
            .body{
                padding-top: 20px;
            }
            
            #header{
                
            }

            .date{
                font-size: 18px;
            }

            .list{
                
                margin-top: 40px;
                display: flex;
                flex-wrap: wrap;
                margin-left: 80px;
                padding-bottom: 40px;
                border-bottom: 2px solid black;
            }

            .ilist{
                display: flex;
                flex-direction: column;
                margin-right: 100px;
                cursor: pointer;
            }

            .date{
                display: flex;
                align-items: center;
            }

            .date div{
                margin-right: 20px;
            }

            .date img{
                width: 30px;
            }
            
            .imge img{
                width: 100%;
                height: 100%;
                border: 2px solid black;
          
            }
            
            .imge{
                width: 265px;
                height: 235px;
                margin-bottom: 20px;
                box-shadow: 3px 3px grey;
            }
            
            
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">ƯU ĐÃI</div>
            <c:if test = "${requestScope.ms != null}">
                <div class = "ms">
                    ${requestScope.ms}
                </div>
            </c:if>
            <c:if test = "${requestScope.list != null}">
                <div class = "list">
                    <c:forEach items = "${requestScope.list}" var = "i">
                        <div class = "ilist" onclick = "dt('${i.getEventCode()}')">
                            <div class = "imge">
                                <img src ="${i.getImg()}"/>

                            </div>
                            <div class = "date">
                                <div>
                                    <img src ="images/calendarIcon.png"/>
                                </div>
                                <div>
                                    ${i.getStartS()}
                                </div>
                                <div>
                                    -
                                </div>
                                <div>
                                    ${i.getEndS()}
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function dt(id) {
                window.location = "edt?id=" + id;
            }
        </script>
    </body>
</html>
