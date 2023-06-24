<%-- 
    Document   : viewSche
    Created on : Jun 22, 2023, 6:39:21 PM
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

            .t{
                cursor: pointer;
            }

            .updtick{
                cursor: pointer;
            }

            .addtick{
                cursor: pointer;
            }

            .search{
                padding-top: 20px;
                margin-bottom: 20px;
                text-align: center;
                font-size: 20px;
            }

            .search input{
                font-size: 20px;
            }

            .searchBar input{
                width: 300px;
                height: 20px;
                font-size: 18px;
                cursor: pointer;
            }

            .searchBar {

                margin-top: 20px;
                justify-content: center;
            }

            a{
                text-decoration: none;
                color: black;
            }

            .ms{
                font-size: 25px;
                text-align: center;
                color: red;
                margin-top: 20px;
                padding-bottom: 20px;
            }


        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "search">
                <form action = "viewsche" method = "post">
                    <label for = "searchDate">Nhập ngày:</label>
                    <input type ="date" name ="searchDate" id ="searchDate"/>
                    <input type ="text" hidden name ="id" value ="${requestScope.id}"/>
                    <div class = "searchBar">
                        <input type="submit" name ="key" value="Tìm kiếm..."/>
                    </div>
                    <div class = "searchBar">
                        <a href = "viewsche?id=${requestScope.id}"><input type="button" name ="key" value="Bỏ ngày tìm kiếm"/></a>
                    </div>
                </form>
            </div>
            <div class = "ms">          
                ${requestScope.ms}
            </div>
            <c:if test = "${ms == null}">
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
                            <td id ="" class ="tket">
                                <label id = "tick${i.getScheNo()}" onclick ="direct('tick${i.getScheNo()}')" class = "t">VIEW TICKET</label>
                                /
                                <label id = "sche${i.getScheNo()}" onclick ="directSche('sche${i.getScheNo()}', '${i.isHasSellTick()}')" class = "t">UPDATE SCHEDULE</label>
                                /<!-- comment -->
                                <label id = "delsche${i.getScheNo()}" onclick ="directDlt('delsche${i.getScheNo()}', '${requestScope.id}', '${i.isHasSellTick()}')"  class = "t">DELETE SCHEDULE</label>
                            </td>
                            <c:set var="movName" value="${i.getMovName()}"/>
                            <c:set var="movID" value="${i.getMovID()}"/>
                        </tr>
                    </c:forEach>
                </table>
                <div class = "pagination">
                    <c:if test = "${requestScope.searchDate != null}">
                        <a href ="viewsche?page=${(page - 1) < 1?(1):(page-1)}&id=${requestScope.id}&searchDate=${requestScope.searchDate}"><</a>
                    </c:if>
                    <c:if test = "${requestScope.searchDate == null}">
                        <a href ="viewsche?page=${(page - 1) < 1?(1):(page-1)}&id=${requestScope.id}"><</a>
                    </c:if>
                    <c:forEach begin = "${1}" end = "${totalPage}" var = "i">
                        <c:if test = "${requestScope.searchDate != null}">
                            <a class ="${i == page ? "active":"noActive"}" href ="viewsche?page=${i}&id=${requestScope.id}&searchDate=${requestScope.searchDate}">${i}</a>
                        </c:if>
                        <c:if test = "${requestScope.searchDate == null}">
                            <a class ="${i == page ? "active":"noActive"}" href ="viewsche?page=${i}&id=${requestScope.id}">${i}</a>
                        </c:if>
                    </c:forEach>
                    <c:if test = "${requestScope.searchDate != null}">
                        <a href ="viewsche?page=${(page + 1) > totalPage?(1):(page+1)}&id=${requestScope.id}&searchDate=${requestScope.searchDate}">></a>
                    </c:if>
                    <c:if test = "${requestScope.searchDate == null}">
                        <a href ="viewsche?page=${(page + 1) > totalPage?(1):(page+1)}&id=${requestScope.id}">></a>
                    </c:if>
                </div>
            </c:if>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function direct(id) {
                if (id.includes("tick")) {
                    id = id.replace("tick", "");

                    window.location = "viewtick?id=" + id;


                }
            }

            function directDlt(id, movID, tick) {
                if (id.includes("sche") && String(tick) === "false") {
                    if (id.includes("del")) {
                        if (confirm("Bạn có chắc muốn xóa lịch chiếu với id = " + id)) {
                            id = id.replace("delsche", "");
                            window.location = "dltsche?id=" + id + "&movid=" + movID;
                        }
                    }
                } else {
                    alert("This schedule has already sell ticket, cant delete it");
                }
            }

            function directSche(id, tick) {
                if (id.includes("sche") && String(tick) === "false") {
                    id = id.replace("sche", "");
                    console.log("1");
                    window.location = "updsche?id=" + id;
                } else {
                    alert("This schedule has already sell ticket, cant update it");
                }
            }
        </script>
    </body>
</html>
