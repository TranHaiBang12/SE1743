<%-- 
    Document   : icR
    Created on : Jul 4, 2023, 7:25:31 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <link rel="stylesheet" href="style.css?version=1"/>
        <style>
            .body{
                padding-top: 40px;
            }
            .ttle{
                margin-left: 80px;
                padding-top: 40px;

                font-size: 19px;
            }



            .dteS div{
                margin-right: 15px;
            }

            .rd{
                color: red;
            }

            .Sttle{
                font-weight: bold;
                font-size: 20px;
            }

            .insider1{
                font-size: 20px;
                margin-left: 40px;
                padding-bottom: 10px;
            }

            .insider1 div{
                margin-top: 10px;
                padding-bottom: 10px;
            }

            .ttle{
                padding-top: 20px;
                font-size: 40px;
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


            .SSttle {
                font-size: 20px;
                margin-top: 10px;
                font-weight: bold;
            }

            .dteS{
                text-align: center;
            }

            .monthlyRp{
                display: flex;
                margin-left: 80px;
                flex-wrap: wrap;
                margin-top: 40px;
                border-bottom: 3px solid black;
            }

            .dteS{
                font-size: 20px;
                margin-right: 100px;
            }

            .btN{
                margin-right: 40px;
                width: 300px;
                margin-bottom: 40px;
            }

            .btN button{
                font-size: 18px;
                padding: 10px;
                width: 100%;
                cursor: pointer;
            }

            .hinbody{
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "hinbody">
                <div class = "ttle">Thống Kê Về Nhân Viên</div>
                <c:if test = "${requestScope.check != null}">
                    <div class = "dteS">
                        <div>Nhân viên: <span class = "rd">${requestScope.e.getLastName()} ${requestScope.e.getFirstName()} - </span>Năm <span class = "rd">${requestScope.year}</span></div>

                    </div>
                </c:if>
            </div>
            <c:if test = "${requestScope.check == null}">
                <form action = "emprp" method = "post">
                    <div class = "search">
                        <div>
                            Nhập năm muốn thống kê: <input type ="number" required min ="2000" max ="2023" name ="year"/>
                        </div>
                        <input type ="text" hidden name ="id" value ="${requestScope.id}"/>
                    </div>
                    <br/>
                    <div class = "srchBtn">
                        <input type = "submit" value ="THỐNG KÊ"/>
                    </div>
                </form>
            </c:if>
            <c:if test = "${requestScope.check != null}">


                <div class = "monthlyRp">
                    <c:forEach begin="1" end="12"  var="i">
                        <div class = "btN">
                            <a href = "erpd?id=${requestScope.e.getEmpID()}&month=${i}&year=${requestScope.year}"><button>BÁO CÁO THÁNG ${i}</button></a>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
