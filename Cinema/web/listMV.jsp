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
        </style>
        <link rel="stylesheet" href="style.css"/>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">
                DANH SÁCH PHIM
            </div>
            <div class = "ms">
                ${requestScope.ms}
            </div>
            <table>
                <tr>
                    <th>ẢNH</th>
                    <th>MÃ</th><!-- comment -->
                    <th>TÊN</th>
                    <th>NGÀY CÔNG CHIẾU</th>
                    <th>THỜI LƯỢNG</th>
                    <th>NGÔN NGỮ</th>
                    <th>XUẤT XỨ</th>
                    <th>TÌNH TRẠNG</th>
                    <th>STUDIO</th>
                    <th>HÀNH ĐỘNG</th>
                </tr>
                <c:forEach items = "${requestScope.list}" var = "i">
                    <tr>
                        <td class = "k"><img src = "${i.getImg()}"/></td>
                        <td class = "blk">${i.getMovID()}</td>
                        <td class = "blk">${i.getMovName()}</td><!-- <td></td> -->
                        <td>${i.getStartDateS()}</td>
                        <td>${i.getTime()}</td>
                        <td>${i.getLanguage()}</td>
                        <td>${i.getOrigin()}</td>
                        <td>${i.getStatus()}</td><!-- <td></td> -->
                        <td>${i.getStudio()}</td>
                        <td>
                            <button type = "button" onclick =  "upd('${i.movID}')">UPDATE</button>
                            <a href = "detail?id=${i.movID}"><button type = "button">DETAIL</button></a>
                            <button type = "button" onclick =  "dlt('${i.movID}', '${i.getMovName()}')">DELETE</button>
                            <a href = "viewsche?id=${i.movID}"><button type = "button">SCHEDULE</button></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class = "addE">
                <div>
                    <a href = "addmov"><img src ="images/plusIcon.png"/></a>
                </div>

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
