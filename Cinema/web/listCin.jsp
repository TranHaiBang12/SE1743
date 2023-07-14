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
                DANH SÁCH RẠP
            </div>
            <div class = "ms">
                ${requestScope.ms}
            </div>
            <table>
                <tr>
                    <th>MÃ</th><!-- comment -->
                    <th>TÊN</th>
                    <th>LOẠI</th>
                    <th>THÀNH PHỐ</th>
                    <th>PHỐ</th>
                    <th>ĐỊA CHỈ</th>
                    <th>SỐ PHÒNG</th>
                    <th>FAX</th>
                    <th>HOTLINE</th>
                    <th>TÌNH TRẠNG</th>
                    <th>HÀNH ĐỘNG</th>
                </tr>
                <c:forEach items = "${requestScope.list}" var = "i">
                    <tr>
                        <td class = "blk">${i.getCinID()}</td>
                        <td class = "blk">${i.getCinName()}</td><!-- <td></td> -->
                        <td>${i.getCtypeName()}</td>
                        <td>${i.getCity()}</td>
                        <td>${i.getStreet()}</td>
                        <td>${i.getAddress()}</td>
                        <td>${i.getNoRoom()}</td><!-- <td></td> -->
                        <td>${i.getFax()}</td>
                        <td>${i.getHotline()}</td>
                        <td>${i.getStatus()}</td>
                        <td>
                            <button type = "button" onclick =  "upd('${i.getCinID()}')">UPDATE</button>
                            <c:if test = "${i.getStatus() == 'Đang hoạt động'}">
                                <button type = "button" onclick =  "dlt('${i.getCinID()}', '${i.getStatus()}', '${i.getCinName()}')">CLOSE</button>
                            </c:if>
                            <c:if test = "${i.getStatus() == 'Đóng cửa'}">
                                <button type = "button" onclick =  "dlt('${i.getCinID()}', '${i.getStatus()}', '${i.getCinName()}')">OPEN</button>
                            </c:if>
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


            function dlt(id, stt, name) {

                if (String(stt) === "Đóng cửa") {
                    if (confirm("thay đổi trạng thái của rạp " + name + " thành mở cửa")) {
                        window.location = "occ?id=" + id + "&stt=" + 1;
                        console.log(1);
                    }
                } else if (String(stt) === "Đang hoạt động") {
                    if (confirm("thay đổi trạng thái của rạp " + name + " thành đóng cửa")) {
                        window.location = "occ?id=" + id + "&stt=" + 0;
                        console.log(0);
                    }
                }

            }


            function upd(id) {
                window.location = "updcin?id=" + id;
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
