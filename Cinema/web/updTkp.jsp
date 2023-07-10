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
        <link rel="stylesheet" href="style.css"/>
        <style>
            .body{
                padding-top: 40px;
            }
            .ttle{
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
                margin-bottom: 20px;
                padding-left: 80px;
            }

            .hinbody1{
                display: flex;
                flex-direction: column;
                padding-left: 80px;
                font-size: 20px;
            }

            .hinbody1 input{
                font-size: 20px;
            }

            .hinbody1 div{
                margin-bottom: 10px;
            }

            .ms{
                font-size: 25px;
                color: red;
                padding-left: 80px;
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

            table{
                margin-top: 20px;
                border: 1px solid black;
            }

            tr{
                text-align: center;
                border: 1px solid black;
            }

            td{
                padding: 20px;
                border: 1px solid black;
            }
            th{
                padding: 20px;
                border: 1px solid black;
            }


            td input{
                width: 80%;
                height: 30px;
                cursor: pointer;
            }
            .addE img{
                width: 30px;
                cursor: pointer;
            }

            .addE div{
                margin-left: 20px;
            }

            .addE {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 20px;
                padding-bottom: 20px;
                font-size: 20px;
            }

            .sb{
                font-size: 16px;
                padding: 5px;
                cursor: pointer;
                color: white;
                background-color: red;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "hinbody">
                <div class = "ttle">Thêm Ca Trực</div>
                <div class = "dteS">
                    <div>Nhân viên: <span class = "rd">${requestScope.e.getLastName()} ${requestScope.e.getFirstName()}</span></div>

                </div>
            </div>
            <c:if test = "${requestScope.ms != null}">
                <div class = "ms">
                    ${requestScope.ms}
                </div>
            </c:if>
            <div class = "hinbody1">
                <form id ="frm" action = "updtkp" method = "post">
                    <div>
                        Mã Nhân Viên: ${requestScope.t.getEmpID()}
                    </div>
                    <input type ="text" hidden name ="empID" value ="${requestScope.t.getEmpID()}"/>
                    <div>
                        Mã Ca Làm: ${requestScope.t.getShiftID()}
                    </div>
                    <input type ="text" hidden name ="id" value ="${requestScope.t.getShiftID()}"/>
                    <input type ="text" hidden name ="date" value ="${requestScope.date}"/>
                    <div>
                        Giờ Vào Làm: <input type ="time" required id ="startWork" name ="startWork" value ="${requestScope.t.getStartWork()}"/>
                    </div><!-- comment -->
                    <div>
                        Giờ Ra Về: <input type ="time" required id ="endWork" name ="endWork" value ="${requestScope.t.getEndWork()}"/>
                    </div>
                    <div>
                        Nghỉ Phép: <input type ="number" required id ="onLeave" min ="0" max ="1" step ="1" name ="onleave" value ="${requestScope.t.getOnLeave()}"/>
                    </div>
                    <div>
                        Ngày: ${requestScope.t.getDateS()}
                    </div>
                    <input class ="sb" type ="button" onclick ="sbmit()" value ="LƯU"/>
                </form>
            </div>



        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function sbmit() {
                var startDate = new Date("2023-06-01");
                var endDate = new Date("2023-06-01");
                var hourStart;
                var hourEnd;
                var minStart;
                var minEnd;
                var startTime = document.getElementById("startWork").value;
                console.log(startDate);
                var endTime = document.getElementById("endWork").value;
                for (var i = 0; i < startTime.length; i++) {
                    if(String(startTime).charAt(i) === ':') {
                        hourStart = String(startTime).substring(0, i);
                        minStart = String(startTime).substring(i + 1, i + 3);
                        break;
                    }
                }
                for (var i = 0; i < endTime.length; i++) {
                    if(String(endTime).charAt(i) === ':') {
                        hourEnd = String(endTime).substring(0, i);
                        minEnd = String(endTime).substring(i + 1, i + 3);
                        break;
                    }
                }
                startDate.setHours(hourStart);
                startDate.setMinutes(minStart);
                
                endDate.setHours(hourEnd);
                endDate.setMinutes(minEnd);
                console.log(startDate + endDate);
                if (startDate.getTime() > endDate.getTime()) {
                    alert("Ngày kết thúc dùng ca làm này phải ở sau ngày bắt đầu");
                } else {
                   document.getElementById("frm").submit();
               }
            }
        </script>
    </body>
</html>
