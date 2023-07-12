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
                border: 1px solid black;
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
            }
        </style>
        <link rel="stylesheet" href="style.css"/>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <table>
                <tr>
                    <th>IMG</th>
                    <th>ID</th><!-- comment -->
                    <th>NAME</th>
                    <th>STARTDATE</th>
                    <th>TIME</th>
                    <th>LANGUAGE</th>
                    <th>ORIGIN</th>
                    <th>STATUS</th>
                    <th>STUDIO</th>
                    <th>ACTION</th>
                </tr>
                <c:forEach items = "${requestScope.list}" var = "i">
                    <tr>
                        <td><img src = "${i.getImg()}"/></td>
                        <td>${i.getMovID()}</td>
                        <td>${i.getMovName()}</td><!-- <td></td> -->
                        <td>${i.getStartDateS()}</td>
                        <td>${i.getTime()}</td>
                        <td>${i.getLanguage()}</td>
                        <td>${i.getOrigin()}</td>
                        <td>${i.getStatus()}</td><!-- <td></td> -->
                        <td>${i.getStudio()}</td>
                        <td>
                            <a href = "#" onclick = "upd('${i.movID}')">UPDATE</a>

                            /
                            <a class ="lnk" href="addmov">ADD</a>

                            /
                            <span class ="dlt" onclick =  "dlt('${i.movID}', '${i.getMovName()}')">DELETE</span>
                        </td>
                    </tr>
                </c:forEach>
            </table>
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
