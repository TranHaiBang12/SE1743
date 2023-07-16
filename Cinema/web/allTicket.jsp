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
        <<link rel="stylesheet" href="style.css?version=1"/>
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

            .search{
                padding-top: 40px;
            }

            table{
                margin-left: 80px;
                margin-right: 80px;
            }

            .ttle{
                text-align: center;
                padding-top: 40px;
                font-size: 27px;
                font-weight: bold;
                text-shadow: 10px 10px 5px #666666;
                color: brown
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

            button{
                margin-bottom: 20px;
                font-size: 17px;
                padding: 5px;
                width: 80%;
                background-color: red;
                color: white;
            }


            .ms a{
                color: red;
            }

            table{
                margin-top: 20px;
                margin: 0 auto;
                border: 2px solid black;
                border-radius: 12px;
                border-spacing: 0px;
            }

            tr{
                border-bottom: 2px solid black;
            }

            td{
                padding: 20px;
            }
            th{
                padding: 20px;
                border-bottom: 2px solid black;
            }

            .th{

            }

            .ttr{
                background-color: crimson;
                color: white;
            }

            tr{
                border-bottom: 2px solid black;
            }

            .m img{
                width: 100px;
            }

            td{
                border-bottom: 1px solid black;
            }

            td a{
                text-decoration: none;
                color: black;
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

            .dlt{
                cursor: pointer;
            }

            .dteS{
                display: flex;
                justify-content: center;
                font-size: 19px;
                margin-bottom: 20px;
            }

            .dteS div{
                margin-right: 15px;
            }

            .blk{
                font-weight: bold;
            }

            .rd{
                color: red;
            }

            .ms{
                font-weight: bold;
                color: red;
                font-size: 25px;
                text-align: center;
            }

            .ttle{
                text-align: center;
                padding-top: 40px;
                font-size: 27px;
                font-weight: bold;
                margin-bottom: 40px;
                text-shadow: 10px 10px 5px #666666;
                color: brown
            }
            .body{
                padding-top: 40px;
                padding-left: 90px;
                padding-right: 110px;
            }

            table{
                width: 100%;

            }

            .k{
                padding: 0px;
                width: 150px;
                height: 270px;
            }
            .k img{
                width: 100%;
                height: 100%;
            }


            .blk{
                font-weight: bold;
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

            button{
                margin-bottom: 20px;
                font-size: 15px;
                padding: 5px;
                width: 80%;
                border-radius: 12px;
                cursor: pointer;
                background-color: red;
                color: #faebee;
            }

            button:hover{
                color:white;
                background-color: red;
            }

            .ms{
                margin-bottom: 20px;
            }

            th{
                padding-left: 20px;
                margin-left: 0px;
                text-align: left;
            }

            td{
                text-align: left;
                font-size: 18px;
                padding-left: 20px;
            }

            tr:hover{
                background-color: rgba(70,70,70,0.2);
            }

            .ttr:hover{
                background-color: crimson;

            }

            .ttr{

                border: 1px solid crimson;
                border-radius: 12px;
                padding: 5px;
                background-color: crimson;
            }

            .first{
                border-left: 1px solid crimson;
                border-radius: 12px 0px 0px 0px;
            }

            .last{
                border-right: 1px solid crimson;
                border-radius: 0px 12px 0px 0px;
            }

            th{
                border: none;
                border-bottom: 1px solid black;
            }

            td{
                border: none;
                border-bottom: 1px solid black;
            }

            .ms{
                margin-bottom: 0px;
                padding-bottom: 20px;
            }

            select{
                font-size: 20px;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">
                DANH SÁCH VÉ

            </div>

            <c:if test = "${ms != null}">
                <div class = "ms">          
                    ${requestScope.ms}

                </div>
           
            </c:if>
            <table>
                <tr class = "ttr">             
                    <th class = "first">MÃ VÉ</th>
                    <th>MÃ LỊCH CHIẾU</th>
                    <th>LOẠI</th>
                    <th>GIÁ</th>
                    <!-- comment -->
                    <th>KHUYẾN MẠI</th>
                    <th>TÌNH TRẠNG</th>
                    <th class = "last">HÀNH ĐỘNG</th>
                </tr>

                <c:forEach items = "${requestScope.list}" var = "i">
                    <tr>
                        <td>${i.getProductCode()}</td>
                        <td>${i.getScheNo()}</td>

                        <td>${i.getType()}</td>
                        <td>${i.getPrice()}</td>
                        <td>${i.getDiscount()}</td>
                        <td>${i.getStatus()}</td>

                        <td id ="" class ="tket">

                            <a href = "upd1t?id=${i.getProductCode()}&sd=${i.getScheNo()}"><button type ="button" class = "t">UPDATE</button></a>
                            <!-- comment -->
                            <button type ="button" onclick ="dlt('${i.getProductCode()}')" class = "t">DELETE</button>
                        </td>

                    </tr>
                </c:forEach>
            </table>
            <div class = "pagination">
                <c:if test = "${requestScope.searchDate != null}">
                    <c:if test = "${requestScope.mov != null}">
                        <a href ="allsche?page=${(page - 1) < 1?(1):(page-1)}&searchDate=${requestScope.searchDate}&id=${requestScope.id}"><</a>
                    </c:if>
                    <c:if test = "${requestScope.mov == null}">
                        <a href ="allsche?page=${(page - 1) < 1?(1):(page-1)}&searchDate=${requestScope.searchDate}"><</a>
                    </c:if>
                </c:if>
                <c:if test = "${requestScope.searchDate == null}">
                    <c:if test = "${requestScope.mov != null}">
                        <a href ="allt?page=${(page - 1) < 1?(1):(page-1)}&id=${requestScope.id}"><</a>
                    </c:if>
                    <c:if test = "${requestScope.mov == null}">
                        <a href ="allt?page=${(page - 1) < 1?(1):(page-1)}"><</a>
                    </c:if>
                </c:if>
                <c:forEach begin = "${1}" end = "${totalPage}" var = "i">
                    <c:if test = "${requestScope.searchDate != null}">
                        <c:if test = "${requestScope.mov != null}">
                            <a class ="${i == page ? "active":"noActive"}" href ="allt?page=${i}&searchDate=${requestScope.searchDate}&id=${requestScope.id}">${i}</a>
                        </c:if>
                        <c:if test = "${requestScope.mov == null}">
                            <a class ="${i == page ? "active":"noActive"}" href ="allt?page=${i}&searchDate=${requestScope.searchDate}">${i}</a>
                        </c:if>
                    </c:if>
                    <c:if test = "${requestScope.searchDate == null}">
                        <c:if test = "${requestScope.mov != null}">
                            <a class ="${i == page ? "active":"noActive"}" href ="allt?page=${i}&id=${requestScope.id}">${i}</a>
                        </c:if>
                        <c:if test = "${requestScope.mov == null}">
                            <a class ="${i == page ? "active":"noActive"}" href ="allt?page=${i}">${i}</a>
                        </c:if>
                    </c:if>
                </c:forEach>
                <c:if test = "${requestScope.searchDate != null}">
                    <c:if test = "${requestScope.mov != null}">
                        <a href ="allt?page=${(page + 1) > totalPage?(1):(page+1)}&searchDate=${requestScope.searchDate}&id=${requestScope.id}">></a>
                    </c:if>
                    <c:if test = "${requestScope.mov == null}">
                        <a href ="allt?page=${(page + 1) > totalPage?(1):(page+1)}&searchDate=${requestScope.searchDate}">></a>
                    </c:if>
                </c:if>
                <c:if test = "${requestScope.searchDate == null}">
                    <c:if test = "${requestScope.mov != null}">
                        <a href ="allt?page=${(page + 1) > totalPage?(1):(page+1)}&id=${requestScope.id}">></a>
                    </c:if>
                    <c:if test = "${requestScope.mov == null}">
                        <a href ="allt?page=${(page + 1) > totalPage?(1):(page+1)}">></a>
                    </c:if>
                </c:if>
            </div>

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

            function dlt(id) {
                if (confirm("Bạn có muốn xóa vé với mã = " + id)) {
                    window.location = "dlt1t?id=" + id;
                }
            }

            function directDlt(id, movID, tick) {
                if (id.includes("sche") && String(tick) === "false") {
                    if (id.includes("del")) {
                        if (confirm("Bạn có chắc muốn xóa lịch chiếu với id = " + id + ". Việc này đồng thời sẽ xóa tất cả vé của lịch chiếu này")) {
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
