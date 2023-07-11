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
                justify-content: space-between;
                align-items: center;
                padding-left: 80px;

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
                margin-bottom: 15px;

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
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "hinbody">
                <div class = "ttle">Ca Trực Tháng ${requestScope.month} Năm ${requestScope.year}</div>
                <div class = "dteS">
                    <div>Nhân viên: <span class = "rd">${requestScope.e.getLastName()} ${requestScope.e.getFirstName()}</span></div>

                </div>
            </div>
            <c:if test = "${requestScope.ms != null}">
                <div class = "ms">
                    ${requestScope.ms}
                </div>
            </c:if>
            <c:if test = "${requestScope.list != null}">
                <div class = "hinbody1">
                    <table>
                        <tr>
                            <th>MÃ CA LÀM</th>
                            <th>MÃ NHÂN VIÊN</th>
                            <th>GIỜ BẮT ĐẦU CA LÀM</th>
                            <th>GIỜ KẾT THÚC CA LÀM</th>
                            <th>NGÀY BẮT ĐẦU</th>
                            <th>NGÀY KẾT THÚC</th>
                            <th>HÀNH ĐỘNG</th>
                        </tr>
                        <c:forEach items = "${requestScope.list}" var = "k">
                            <tr>
                                <td>${k.getShiftID()}</td>
                                <td>${k.getEmpID()}</td><!-- <td></td> -->
                                <td>${k.getStartShift()}</td>
                                <td>${k.getEndShift()}</td>
                                <td>${k.getStartDateS()}</td>
                                <td>${k.getEndDateS()}</td>
                                <td>
                                    <a href="updsh?id=${k.getShiftID()}"><input type ="button" value = "SỬA"/></a>
                                    <a href="#"><input type ="button" onclick ="dlt(${k.getShiftID()})" value = "XÓA"/></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class = "addE">
                    <div>
                        <a href = "addsh?id=${requestScope.e.getEmpID()}"><img src ="images/plusIcon.png"/></a>
                    </div>

                </div>
            </c:if>
            <c:if test = "${requestScope.list == null}">
                <div class = "addE">
                    <div>
                        <a href = "addsh?id=${requestScope.e.getEmpID()}"><img src ="images/plusIcon.png"/></a>
                    </div>

                </div>
            </c:if>

        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function dlt(id) {
                if(confirm("Bạn có chắc muốn xóa ca làm với id = " + id)) {
                    window.location = "dltsh?id=" + id;
                }
            }
        </script>
    </body>
</html>
