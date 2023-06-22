<%-- 
    Document   : addSche
    Created on : Jun 21, 2023, 10:24:35 AM
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
            }

            table{
                text-align: center;
                margin-top: 20px;
                border: 1px solid black;
                margin-bottom: 20px;
                margin: 0 auto;
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

            .pagination{
                text-align: center;
                padding-bottom: 15px;
                margin-top: 20px;
            }

            .pagination a{
                text-decoration: none;

            }

            .noActive{
                text-decoration: none;
                color: black;
                border: 1px solid black;
                padding: 5px;
                border-radius: 18px;
            }

            .active{
                text-decoration: none;
                border: 1px solid black;
                padding: 5px;
                color: white;
                border-radius: 18px;
                background-color: black;
            }

            input {
                margin-top :10px;
                margin-bottom: 10px;
                width: 500px;
                height: 30px;
                border: 1px solid black;
                border-radius: 10px;
                font-size: 20px;
                padding-left: 10px;
            }

            select{
                margin-top :10px;
                margin-bottom: 10px;
                width: 500px;
                height: 30px;
                border: 1px solid black;
                border-radius: 10px;
                font-size: 20px;
                padding-left: 10px;
            }

            label{
                font-size: 20px;
            }

            .sbmit input{
                background-color: red;
                color: white;
                cursor: pointer;
            }

            .sbmit{
            }
            
            .ms{
                margin-left: 450px;
                color: red;
            }
            .frm{
                margin-left: 450px;
            }
            
            .tket{
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div><!-- comment -->
        <div class = "body">
            <table>
                <tr>
                    <th>MÃ LỊCH CHIẾU</th>
                    <th>NGÀY CHIẾU</th>
                    <th>GIỜ CHIẾU</th>
                    <th>PHIM</th><!-- comment -->
                    <th>KIỂU CHIẾU</th><!-- comment -->
                    <th>RẠP</th>
                    <th>PHÒNG</th>
                    <th>NGÀY CHIẾU XONG</th>
                    <th>GIỜ CHIẾU XONG</th>
                    <th>VÉ</th>
                </tr>
                <c:forEach items = "${requestScope.s}" var = "i">
                    <tr>
                        <td>${i.getScheNo()}</td>
                        <td>${i.getStart()}</td>
                        <td>${i.getStartTim()}</td>
                        <td>${i.getMovName()}</td>
                        <td>${i.getFormName()}</td>
                        <td>${i.getCinName()}</td>
                        <td>${i.getRoomID()}</td>
                        <td>${i.getEnd()}</td>
                        <td>${i.getEndTim()}</td>
                        <td id ="" class ="tket">${i.isHasTick() == true?"UPDATE TICKET":"ADD TICKET"}</td>
                        <c:set var="movName" value="${i.getMovName()}"/>
                        <c:set var="movID" value="${i.getMovID()}"/>
                    </tr>
                </c:forEach>
            </table>
            <div class = "pagination">
                <a href ="addsche?page=${(page - 1) < 1?(1):(page-1)}&id=${requestScope.id}"><</a>
                <c:forEach begin = "${1}" end = "${totalPage}" var = "i">
                    <a class ="${i == page ? "active":"noActive"}" href ="addsche?page=${i}&id=${requestScope.id}">${i}</a>
                </c:forEach>
                <a href ="addsche?page=${(page + 1) > totalPage?(1):(page+1)}&id=${requestScope.id}">></a>
            </div>
            <div class = "ms">
                <h2>${requestScope.ms}</h2>
            </div>
            <div class = "frm">
                <form id ="frm" action = "addsche" method = "post">
                    <c:if test = "${room != null}">
                        <div>
                            <label for = "scheNo">Nhập mã lịch chiếu: </label>
                            <input type ="text" required id ="scheNo" name ="scheNo"/>
                        </div>
                    </c:if>

                    <c:if test = "${room != null}">
                        <div>
                            <label for ="startDate">Nhập ngày chiếu: </label>
                            <input type ="date"required id ="startDate" name ="startDate"/>
                        </div>
                    </c:if>

                    <c:if test = "${room != null}">
                        <div>
                            <label for ="startTime">Nhập thời gian chiếu: </label>
                            <input type ="time" required id ="startTime" name ="startTime"/>
                        </div>
                    </c:if>

                    <div>
                        <label for ="movName">Phim: </label>
                        <input type ="text" id ="movName" name ="movName" readonly value = "${movName}"/>
                        <input type ="text" id ="movID" name ="movID" readonly hidden value = "${movID}"/>
                    </div>

                    <c:if test = "${form != null}">
                        <div>
                            <label for ="form">Kiểu chiếu: </label>
                            <select name ="form">
                                <c:forEach items = "${requestScope.form}" var = "j">
                                    <option value = "${j.getId()}">${j.getFormName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <input type ="text" hidden value ="${requestScope.id}" name ="id"/>
                    <input type ="text" hidden value ="${requestScope.page}" name ="page"/>
                    <input type ="text" hidden value ="0" id ="check" name ="check"/>
                    <div>
                        <label for ="cin">Rạp: </label>
                        <select id ="ciN" onchange ="cnge()" name ="cin">
                            <c:forEach items = "${requestScope.cin}" var = "j">
                                <option ${requestScope.cinPick == j.getCinID()?"selected":""} id ="${j.getCinID()}" value = "${j.getCinID()}">${j.getCinName()}</option>

                            </c:forEach>
                        </select>
                    </div>



                    <c:if test = "${room != null}">
                        <div>
                            <label for ="room">Phòng: </label>
                            <select name = "room">
                                <c:forEach items = "${requestScope.room}" var = "k">
                                    <option value = "${k.getRoomID()}">${k.getRoomID()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>

                    <div class = "sbmit">
                        <input type ="submit" value ="ADD"/>
                    </div>
                </form>

            </div>

        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function cnge() {
                var selectedValue = document.getElementById("ciN").selectedIndex;
                document.getElementById("check").value = 1;
                document.getElementById("frm").submit();
            }
        </script>
    </body>
</html>
