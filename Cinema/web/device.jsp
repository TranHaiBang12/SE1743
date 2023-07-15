<%-- 
    Document   : device
    Created on : Jul 5, 2023, 6:59:23 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css?version=1"/>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <style>
            table{
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
            
            td img{
                width: 100px;
            }
            
            .ttle{
                text-align: center;
                font-weight: bold;
                margin-bottom: 20px;
                font-size: 25px;
                padding-top: 40px;
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
            
            td a{
                text-decoration: none;
                color: black;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div><!-- comment -->
        <div class = "body">
            <div class = "ttle">DANH SÁCH THIẾT BỊ NHẬP VỀ</div>
            <table>
                <tr>
                    <th>ẢNH</th>
                    <th>MÃ THIẾT BỊ</th>
                    <th>LOẠI THIẾT BỊ</th><!-- comment -->
                    <th>GIÁ THIẾT BỊ</th>
                    <th>MÔ TẢ</th>                   
                    <th>HÀNH ĐỘNG</th>
                </tr>
                <c:forEach items = "${requestScope.listDI}" var = "i">
                    <tr>
                        <td><img src = "${i.getImg()}"/></td>
                        <td>${i.getDeviceCode()}</td>
                        <td>${i.getTypeName()}</td>
                        <td>${i.getPrice()}</td>
                        <td>${i.getDescript()}</td><!-- comment -->                      
                        <td>
                            <a href = "upddv?id=${i.getDeviceCode()}"><button type = "button">SỬA</button></a>
                            
                            <a href = "#"><button type = "button" onclick = "dltD('${i.getDeviceCode()}')">XÓA</button></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class = "addE">
                <div>
                    <a href = "adddv"><img src ="images/plusIcon.png"/></a>
                </div>

            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function dltD(id) {
                if(confirm("Bạn có chắc muốn xóa tất cả thiết bị với mã là " + id)) {
                    window.location = "ddvi?id=" + id;
                }
            }
        </script>
    </body>
</html>
