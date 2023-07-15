<%-- 
    Document   : listMV
    Created on : Jul 12, 2023, 10:54:39 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <style>
            td img{
                width: 100px;
            }
            table{
                margin-top: 20px;
                border: 2px solid black;
                margin: 0 auto;
            }

            tr{
                text-align: center;
            }

            td{
                padding: 20px;
                border: 2px solid black;
            }
            th{
                padding: 20px;
                border: 2px solid black;
            }

            .m img{
                width: 100px;
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
                height: 200px;
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
                font-size: 17px;
                padding: 5px;
                width: 80%;
                background-color: red;
                color: white;
            }

            .ms{
                margin-bottom: 20px;
            }

            td{
                font-size: 18px;
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
            
            .rd{
                color: crimson;
            }

            .active a{
                color: white;
            }
        </style>
        <link rel="stylesheet" href="style.css?version=1"/>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">
                DANH SÁCH ĐỒ ĂN
            </div>
            <div class = "ms">
                ${requestScope.ms}
            </div>
            <table>
                <tr>
                    <th>ẢNH</th>
                    <th>MÃ</th><!-- comment -->
                    <th>TÊN</th>
                    <th>LOẠI</th>

                    <th>GIÁ</th>
                    <th>KHUYẾN MẠI</th>
                    <th>TÌNH TRẠNG</th>
                    <th>KHÔNG TIẾP TỤC</th>
                    <th>HÀNH ĐỘNG</th>
                </tr>
                <c:forEach items = "${requestScope.listPerPage}" var = "i">
                    <tr>
                        <td class = "k"><img src = "${i.getImg()}"/></td>
                        <td class = "blk">${i.getProductCode()}</td>
                        <td class = "blk">${i.getFoodDescript()}</td><!-- <td></td> -->
                        <td>${i.getTypeName()}</td>
                        <td>${i.getStatus()}</td>
                        <td>${i.getDiscontinued()}</td>
                        <td class = "rd">${i.getPrice()}đ</td>
                        <td class = "rd">${i.getDiscount()}%</td><!-- <td></td> -->
                        <td>
                            <a href = "updf?id=${i.getProductCode()}"><button type = "button">UPDATE</button></a>
                            <a href = "viewf?id=${i.getProductCode()}"><button type ="button">VIEW</button></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class = "pagination">
                <a href ="listfd?page=${(page - 1) < 1?(1):(page-1)}&type=${requestScope.type != null ?requestScope.type:""}&key=${requestScope.key?requestScope.key:""}"><</a>
                <c:forEach begin = "${1}" end = "${totalPage}" var = "i">
                    <a class ="${i == page ? "active":"noActive"}" href ="listfd?page=${i}&type=${requestScope.type}&key=${requestScope.key}">${i}</a>
                </c:forEach>
                <a href ="listfd?page=${(page + 1) > totalPage?(1):(page+1)}&type=${requestScope.type != null ?requestScope.type:""}&key=${requestScope.key?requestScope.key:""}">></a>
            </div>
     
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script>


            function dlt(id, name) {
                if (confirm("Bạn có chắc muốn xóa bộ phim " + name + ". Việc này sẽ xóa cả vé cũng như toàn bộ lịch chiếu của bộ phim này")) {
                    window.location = "dltmv?id=" + id;
                }
            }


            function upd(id) {
                window.location = "update?id=" + id;
            }

            function createNewDir() {
                if (a <= 5) {
                    // First create a DIV element.
                    var txtNewInputBox = document.createElement('div');

                    // Then add the content (a new input box) of the element.
                    txtNewInputBox.innerHTML = "<input type='text' id='newInputBox' name = 'dir'>";

                    // Finally put it where it is supposed to appear.
                    document.getElementById("newdir").appendChild(txtNewInputBox);
                    a++;
                } else {
                    alert("Lượng đạo diễn tối đa bạn có thể thêm là 6");
                }
            }

            function createNewStar() {
                if (b <= 5) {
                    // First create a DIV element.
                    var txtNewInputBox = document.createElement('div');


                    // Then add the content (a new input box) of the element.
                    txtNewInputBox.innerHTML = "<input type='text' id='newInputBox' name = 'star'>";

                    // Finally put it where it is supposed to appear.
                    document.getElementById("newstar").appendChild(txtNewInputBox);
                    b++;
                } else {
                    alert("Lượng đạo diễn tối đa bạn có thể thêm là 6");
                }
            }

            function createNewGenre() {
                c++;
                document.getElementById("gN").value = c;
                console.log(c);
                document.getElementById("slt" + c).style.display = 'block';
            }

        </script>
    </body>
</html>
