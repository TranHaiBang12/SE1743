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
        <<link rel="stylesheet" href="style.css"/>
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
                    <tr>
                        <th>ẢNH</th>
                        <th>MÃ NHÂN VIÊN</th>
                        <th>HỌ</th><!-- comment -->
                        <th>TÊN</th>
                        <th>GIỚI TÍNH</th>
                        <th>VỊ TRÍ CÔNG VIỆC</th>
                        <th>RẠP LÀM VIỆC</th>
                        <th>ACTION</th>
                    </tr>
                    <c:forEach items = "${requestScope.listE}" var = "i">
                        <tr>
                            <td class = "m"><img src = "${i.getImg()}"/></td>
                            <td>${i.getEmpID()}</td>
                            <td>${i.getLastName()}</td>
                            <td>${i.getFirstName()}</td><!-- <td></td> -->
                            <td>${i.getGender()}</td>
                            <td>${i.getPosition()}</td><!-- comment -->
                            <td>${i.getCinName()}</td>
                            <td>
                                <a href = "empdt?id=${i.getEmpID()}">XEM CHI TIẾT</a>

                                /
                                <a href = "emprp?id=${i.getEmpID()}">XEM BÁO CÁO</a>

                                /
                                <span class ="dlt" onclick = "dlt('${i.getEmpID()}')">XÓA</span>
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
    </body>
</html>
