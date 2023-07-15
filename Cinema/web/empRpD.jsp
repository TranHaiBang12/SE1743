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
                padding-left: 80px;
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
                margin-bottom: 20px;
            }
            .btS{
                padding-right: 115px;

            }
            .btS button{
                font-size: 18px;
                padding: 5px;
                background-color: red;
                color: white;
                width: 100%;
                height: 100%;
                cursor: pointer;
            }
            
            .m1{
                margin-left: 40px;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "hinbody">
                <div class = "ttle">Báo Cáo Tháng ${requestScope.month} Năm ${requestScope.year}</div>
                <div class = "dteS">
                    <div>Nhân viên: <span class = "rd">${requestScope.e.getLastName()} ${requestScope.e.getFirstName()}</span></div>

                </div>
            </div>
            <div class = "hinbody">
                <div class = "SSttle">
                    1. Ca Trực
                </div>
                <div class = "btS">
                    <a href = "emps?id=${requestScope.e.getEmpID()}&month=${requestScope.month}&year=${requestScope.year}"><button>XEM CHI TIẾT</button></a>
                </div>

            </div>
            <div class = "hinbody">
                <div class = "SSttle">
                    2. Chấm Công
                </div>
                <div class = "btS">
                    <a href = "emptp?id=${requestScope.e.getEmpID()}&month=${requestScope.month}&year=${requestScope.year}"><button>XEM CHI TIẾT</button></a>
                </div>
                <!-- comment -->
            </div>
            <div class = "SSttle">
                3. Thống Kê Chấm Công
                <div class = "insider1">
                    <div>
                        - Số ngày làm việc: <span class = "rd">${requestScope.numDateWork}</span>
                    </div>
                    <div class = "m1">
                        + Số ngày đến đúng giờ: <span class = "rd">${requestScope.numDateCR}</span>
                    </div>
                    <div class = "m1">
                        + Số ngày đến muộn: <span class = "rd">${requestScope.numDateCL}</span>
                    </div>
                    <div class = "m1">
                        + Số ngày làm OT: <span class = "rd">${requestScope.numDateOT}</span>
                    </div>
                    <div class = "m1">
                        + Số ngày về đúng giờ: <span class = "rd">${requestScope.numDateRR}</span>
                    </div>
                    <div>
                        - Số giờ làm việc: <span class = "rd">${requestScope.numHourWork}</span>
                    </div>
                    <div>
                        - Số ngày nghỉ: <span class = "rd">${requestScope.numDateOff}</span>
                    </div><!-- comment -->
                    <div>
                        - Số ngày nghỉ có phép: <span class = "rd">${requestScope.numDateOffHasP}</span>
                    </div>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
