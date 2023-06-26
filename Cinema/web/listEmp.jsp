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
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">.
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
                            <a href = "#">SỬA</a>
                            /
                            <a href = "#">XÓA</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class = "addE">
                <div>
                    <a href = "addemp"><img src ="images/plusIcon.png"/></a>
                </div>
      
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
