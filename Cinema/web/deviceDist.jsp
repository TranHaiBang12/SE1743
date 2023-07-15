<%-- 
    Document   : cinema
    Created on : Jun 15, 2023, 4:42:57 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css?version=1"/>
        <script src="https://kit.fontawesome.com/8157308f93.js" crossorigin="anonymous"></script>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <link rel="stylesheet" href="swiper-bundle.min.css">
        <style>
            .body{
                padding-left: 80px;
            }

            .insideloc{
                display: flex;
                flex-wrap: wrap;
                border: 2px solid black;
                padding: 20px;
                padding-left:1em;
                border-radius: 12px;
                margin-bottom: 20px;
            }
            button{
                margin-bottom: 20px;
                font-size: 17px;
                padding: 5px;
                width: 80%;
                background-color: red;
                color: white;
            }
            .ttle{
                text-align: center;
                font-size: 30px;
                padding-top: 40px;
            }

            .insideinsideLoc{
                font-size: 20px;
                border: 1px solid black;
                margin-right: 30px;
                margin-bottom: 20px;
                border-radius: 10px;
                cursor: pointer;
                width: 250px;
                height: 50px;
                padding: 10px;
                text-align: center;
            }

            .ttle{
                margin-bottom: 20px;
                padding-top: 20px;
            }

            .insideinsideLocActive{
                font-size: 20px;
                border: 1px solid black;
                padding: 10px;
                margin-right: 30px;
                border-radius: 10px;
                cursor: pointer;
                background-color: black;
                color: white;
                width: 250px;
                height: 50px;
                text-align: center;
            }


            .bigttle{
                text-align: center;
                padding-bottom: 10px;
                font-size: 30px;
                margin-top: 20px;
                margin-bottom: 20px;
                color: red;
                text-shadow: 0px 0px 3px yellow;
                box-shadow: 10px 10px 5px #666666;
            }


            .notDate{
                display: flex;
                flex-direction: column;
                margin-right: 8px;
                margin-bottom: 5px;
            }
            .isDate{
                font-size: 30px;
            }
            .insideSchedule:hover{
                border: 1px solid black;
                border-radius: 15px;
                padding-top: 10px;
                padding-bottom: 10px;
            }

            .ms{
                margin-top: 20px;
                margin-left: 30px;
                margin-right: 0px;
                margin-bottom: 20px;
            }

            .mImg img{
                width: 200px;
                height: 250px;
            }

            .mOvSCHE{
                padding-bottom: 15px;
                border-bottom: 2px solid black;
            }

            .mName{
                font-size: 22px;
                margin-bottom: 15px;
                margin-top: 15px;
                font-weight: bold;
            }

            .mIn4{
                display: flex;
            }

            .insidemNotImg{
                display: flex;
                flex-direction: column;
                flex-wrap: wrap;
            }

            .mTme{
                margin-bottom: 10px;
                display: flex;
                flex-wrap: wrap;
                cursor: pointer;
            }

            .insidemTme{
                margin-right: 10px;
                border: 1px solid black;
                padding: 10px;
            }

            .mFrm{
                margin-bottom: 10px;
                font-weight: bold;
            }

            .mNotImg{
                margin-left: 20px;
                font-size: 20px;
            }

            .ms{
                margin-bottom: 0px;
                padding-bottom: 20px;
            }
            .insideloc li{
                min-width:10em;
                list-style-type: lower-alpha;
            }
            table{
                margin-top: 40px;
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
            
            a{
                text-decoration: none;
                color: black;
            }
            
            .ms{
                font-size: 20px;
                color: red;
            }
            
            .ttle{
                text-align: center;
                font-weight: bold;
                margin-bottom: 20px;
                font-size: 25px;
                padding-top: 40px;
            }
            
            .addE div{
                margin-left: 20px;
            }
            
            .body{
                padding-right: 100px;
            }
            
            .addE {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 20px;
                padding-bottom: 20px;
                font-size: 20px;
            }
            
            .addE img{
                width: 30px;
                cursor: pointer;
            }
            
            a{
                cursor: pointer;
            }
            
            button{
                font-size: 18px;
                border: none;
                background-color: white;
            }
            
            button{
                margin-bottom: 20px;
                font-size: 17px;
                padding: 5px;
                width: 80%;
                background-color: red;
                color: white;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">PHÂN BỐ THIẾT BỊ TẠI CÁC RẠP</div>
            <c:if test = "${requestScope.listCin != null}">
                <div class = "loc">
                    <div class = "ttle">Rạp Chiếu Phim</div>
                    <div class = "insideloc">
                        <c:forEach items = "${requestScope.listCin}" var = "k">
                            <div class = "${k.getCinID()==requestScope.cinID?"insideinsideLocActive":"insideinsideLoc"}" onclick = "pckCin('${k.getCinID()}')">${k.getCinName()}</div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

            <c:if test = "${requestScope.listRoom != null}">
                <div class = "loc">
                    <div class = "ttle">Phòng</div>
                    <div class = "insideloc">
                        <c:forEach items = "${requestScope.listRoom}" var = "k">
                            <div class = "${k.getRoomID()==requestScope.roomID?"insideinsideLocActive":"insideinsideLoc"}" onclick = "pckRoom('${requestScope.cinID}', '${k.getRoomID()}')">${k.getRoomID()}</div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
            <c:if test = "${requestScope.ms != null}">
                <div class = "ms">
                    ${requestScope.ms}
                </div>
            </c:if>
            <c:if test = "${requestScope.listDevice != null && requestScope.ms == null}">
                <table>
                    <tr>
                        <th>ẢNH</th>
                        <th>MÃ THIẾT BỊ</th>
                        <th>LOẠI THIẾT BỊ</th><!-- comment -->
                        <th>GIÁ THIẾT BỊ</th>
                        <th>MÔ TẢ</th>    
                        <th>NGÀY PHÂN BỐ</th>
                        <th>THỜI GIAN PHÂN BỐ</th>
                        <th>BAR CODE</th>
                        <th>TÌNH TRẠNG</th>
                        <th>HÀNH ĐỘNG</th>
                    </tr>
                    <c:forEach items = "${requestScope.listDevice}" var = "i">
                        <tr>
                            <td><img src = "${i.getImg()}"/></td>
                            <td>${i.getDeviceCode()}</td>
                            <td>${i.getTypeName()}</td>
                            <td>${i.getPrice()}</td>
                            <td>${i.getDescript()}</td><!-- comment --> 
                            <td>${i.getDte()}</td>
                            <td>${i.getTme()}</td>
                            <td>${i.getDeviceBarCode()}</td>
                            <td>${i.getStatus()}</td>
                            <td>
                                <a href = "udbc?id=${i.getDeviceBarCode()}"><button type = "button">SỬA</button></a>
                                
                                <a onclick="dlt('${i.getDeviceBarCode()}')"><button type = "button">XÓA</button></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div class = "addE">
                <div>
                    <a href = "adddvd"><img src ="images/plusIcon.png"/></a>
                </div>

            </div>
            </c:if>
        </div>

        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script src="swiper-bundle.min.js"></script>
        <script>
          

        </script>
        <script type = "text/javascript">
            function pckRoom(cin, room) {
                window.location = "dvd?cin=" + cin + "&room=" + room;
            }

            function pckCin(cin) {
                window.location = "dvd?cin=" + cin ;
            }
            
            function dlt(id) {
                if(confirm("Bạn có chắc muốn xóa thiết bị với mã là " + id)) {
                    window.location = "dltdv?id=" + id;
                }
            }

        </script>
    </body>
</html>
