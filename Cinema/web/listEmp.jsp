<%-- 
    Document   : listEmp
    Created on : Jun 26, 2023, 3:36:03 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <<link rel="stylesheet" href="style.css?version=1"/>
        <style>

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
            
            button{
                margin-bottom: 20px;
                font-size: 17px;
                padding: 5px;
                width: 80%;
                background-color: red;
                color: white;
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
            
            td{
                border: none;
                border-bottom: 1px solid black;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">.
            <c:if test = "${requestScope.start != null && requestScope.end != null}">
                <div class = "dteS">
                    <div><span class = "blk">Ngày: </span><span class = "rd">${requestScope.start}</span></div>
                    <div>
                        -
                    </div>
                    <div><span class = "blk">Ngày: </span><span class = "rd">${requestScope.end}</span></div>
                </div>
                <div class = "dteS">
                    <span class = "blk">Rạp:</span> <span class = "rd">${requestScope.cin.getCinName()}</span>
                </div>
            </c:if>
            <c:if test = "${requestScope.ms != null}">
                <div class = "ms">
                    ${requestScope.ms}
                </div>
            </c:if>
            <c:if test = "${requestScope.listE != null}">
                <div class = "ttle">DANH SÁCH NHÂN VIÊN</div>
                <table>
                    <tr class = "ttr">
                        <th class = "first">MÃ NHÂN VIÊN</th>
                        <th>HỌ</th><!-- comment -->
                        <th>TÊN</th>
                        <th>GIỚI TÍNH</th>
                        <th>VỊ TRÍ CÔNG VIỆC</th>
                        <th>RẠP LÀM VIỆC</th>
                        <th>TÌNH TRẠNG</th>
                        <th class = "last">ACTION</th>
                    </tr>
                    <c:forEach items = "${requestScope.listE}" var = "i">
                        <tr>
                            <td>${i.getEmpID()}</td>
                            <td>${i.getLastName()}</td>
                            <td>${i.getFirstName()}</td><!-- <td></td> -->
                            <td>${i.getGender()}</td>
                            <td>${i.getPosition()}</td><!-- comment -->
                            <td>${i.getCinName()}</td>
                            <td>${i.getActive()}</td>
                            <td>
                                <a href = "empdt?id=${i.getEmpID()}"><button type ="button">XEM CHI TIẾT</button></a>

                                
                                <a href = "emprp?id=${i.getEmpID()}"><button type ="button">XEM BÁO CÁO</button></a>

                                
                                <c:if test = "${i.getActive() == 1}">
                                <a  onclick="act('${i.getEmpID()}', '${i.getActive()}')"><button>ẨN</button></a>
                            </c:if>
                            <c:if test = "${i.getActive() == 0}">
                                <a  onclick="act('${i.getEmpID()}', '${i.getActive()}')"><button>BỎ ẨN</button></a>
                            </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <div class = "addE">
                <div>
                    <a href = "addemp"><img src ="images/plusIcon.png"/></a>
                </div>

            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function dlt(id) {
                if (confirm("Bạn có chắc muốn xóa nhân viên với id = " + id)) {
                    window.location = "dele?id=" + id;
                }
            }
        </script>
        <script type="text/javascript">

            
            function act(id, stt) {
                if (confirm("Bạn có muốn thay đổi trạng thái ẩn của tài khoản này")) {
                    if (Number(stt) === 1) {
                        window.location = "deact?id=" + id + "&stt=" + 0;
                    }
                    else if (Number(stt) === 0) {
                        window.location = "deact?id=" + id + "&stt=" + 1;
                    }
                }
            }
        </script>
    </body>
</html>
