<%-- 
    Document   : mvRp
    Created on : Jun 28, 2023, 4:10:11 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css"/>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <style>
            .ttle{
                text-align: center;
                padding-top: 20px;
                font-size: 27px;
                font-weight: bold;
                margin-bottom: 40px;
            }

            .search{
                display: flex;
                justify-content: space-around;
                font-size: 18px;
            }

            .search input {
                font-size: 18px;
            }

            .srchBtn{
                text-align: center;
                margin-top: 20px;
                padding-bottom: 30px;
            }

            .srchBtn input{
                font-size: 18px;
                padding-top: 5px;
                padding-bottom: 5px;
                padding-right: 12px;
                padding-left: 12px;
                cursor: pointer;
            }

            .ms{
                text-align: center;
                font-size: 20px;
                color: red;
            }

            .msT{
                text-align: center;
                font-size: 20px;
                color: red;
                margin-top: 20px;
            }

            .mvIN4{
                display: flex;
                flex-direction: column;
                margin-top: 20px;
                margin-left: 40px;
                background-color: white;
                padding-bottom: 20px;
                border-bottom: 3px solid black;
            }

            .imGE img{
                width: 100%;
                height: 100%;
            }

            .imGE{
                width: 250px;
                height: 300px;
            }

            .in4 {
                display: flex;
                flex-direction: column;
                margin-left: 80px;
            }

            .mName{
                font-size: 25px;
                font-weight: bold;
            }

            .blk {
                font-weight: bold;
            }

            .rte {
                margin-right: 80px;
            }

            .in4 div{
                margin-bottom: 10px;
            }

            .mName {
                font-size: 20px;
            }

            .oIn4{
                font-size: 20px;
            }

            .btn{
                margin-top: 20px;
            }

            .btn input{
                font-size: 18px;
                padding: 5px;
                color: white;
                background-color: red;
                cursor: pointer;
            }

            .dteS{
                display: flex;
                justify-content: center;
                font-size: 19px;
            }

            .dteS div{
                margin-right: 15px;
            }

            .choice{
                margin-right: 20px;
                margin-left: 20px;
                display: flex;
                justify-content: space-evenly;
                width: 60%;
                border: 1px solid black;
            }

            .choice div{
            }


            .tkEtChoice {
                border-right: 1px solid black;
                padding: 10px;
                font-weight: bold;
                cursor: pointer;
                width: 50%;
                text-align: center;
            }

            .foodChoi {
                padding: 10px;
                font-weight: bold;
                cursor: pointer;
                width: 50%;
                text-align: center;
            }

            .outsiteO{
                display: flex;
                flex-direction: column;
                margin-left: 80px;
                margin-right: 80px;
            }


            .oDate{
                font-size: 25px;
                font-weight: bold;
            }

            .uName{
                font-size: 40px;
                font-weight: bold;
                text-align: center;
                padding-top: 20px;
                margin-bottom: 20px;
            }

            .rd{
                color: red;
            }



            #tt{
                display: none;
            }

            .ms{
                text-align: center;
                margin-top: 40px;
                padding-bottom: 40px;
                font-size: 30px;
                color: red;
            }

            .dteS{
                display: flex;
                justify-content: center;
                font-size: 19px;
            }

            .dteS div{
                margin-right: 15px;
            }

            .rd{
                color: red;
            }

            .Sttle{
                font-weight: bold;
                font-size: 20px;
                margin-left: 40px;
                margin-top: 20px;
            }

            .mvIN4{
                display: flex;
                margin-top: 20px;
                margin-left: 40px;
                background-color: white;
                padding-bottom: 20px;
            }

            .imGE img{
                width: 100%;
                height: 100%;
            }

            .imGE{
                width: 250px;
                height: 300px;
            }

            .in4 {
                display: flex;
                flex-direction: column;
                margin-left: 80px;
            }

            .mName{
                font-size: 25px;
                font-weight: bold;
            }

            .blk {
                font-weight: bold;
            }

            .rte {
                margin-right: 80px;
            }

            .in4 div{
                margin-bottom: 10px;
            }

            .mName {
                font-size: 20px;
            }

            .oIn4{
                font-size: 20px;
            }

            .rd{
                color: red;
            }

            table{

                width: 100%;
                margin-top: 20px;
                border: 1px solid black;
                margin-bottom: 20px;
                margin: 0 auto;
                margin-left: 20px;
                margin-right: 50px;
            }

            body{
                overflow-x: hidden;
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
                padding-top: 20px;
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

            td a{
                text-decoration: none;
                color: black;
            }

        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">THỐNG KÊ VỀ THIẾT BỊ</div>
            <c:if test = "${requestScope.check == null}">
                <form action = "dvrp" method = "post">
                    <div class = "search">
                        <div>
                            Ngày bắt đầu: <input type ="date" name ="start"/>
                        </div>

                        <div>
                            Ngày kết thúc: <input type ="date" name ="end"/>
                        </div>
                    </div>
                    <br/>
                    <div class = "srchBtn">
                        <input type = "submit" value ="THỐNG KÊ"/>
                    </div>
                </form>
            </c:if>
            <c:if test = "${requestScope.check != null}">
                <div class = "dteS">
                    <div>Ngày: <span class = "rd">${requestScope.start}</span></div>
                    <div>
                        -
                    </div>
                    <div>Ngày: <span class = "rd">${requestScope.end}</span></div>
                </div>
                <div class = "TKET">
                    <div class = "Sttle">1. TỔNG QUÁT (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "mvIN4">
                        <div class = "in4">
                            <div class = "oIn4">
                                <div>
                                    <div>Tổng chi phí: <span class = "rd">${requestScope.TID1.getNo() + requestScope.TID2.getNo()}đ</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class = "TKET">
                    <div class = "Sttle">2. NHẬP THIẾT BỊ (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "mvIN4">
                        <div class = "in4">
                            <div class = "oIn4">
                                <div>
                                    <div>
                                        Số thiết bị nhập vào: <span class = "rd">${requestScope.numD}</span>
                                    </div>
                                    <div>${requestScope.TID1.getdS()}: <span class = "rd">${requestScope.TID1.getNo()}đ</span>, chiếm <span class = "rd">${requestScope.TID1.getPc()}%</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
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
                                            <a href = "udbc?id=${i.getDeviceBarCode()}">SỬA</a>
                                            /
                                            <a onclick="dlt('${i.getDeviceBarCode()}')">XÓA</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                </div>


                <div class = "TKET">
                    <div class = "Sttle">3. SỬA CHỮA THIẾT BỊ (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "mvIN4">
                        <div class = "in4">
                            <div class = "oIn4">
                                <div>
                                    <div>
                                        Số thiết bị sửa chữa: <span class = "rd">${requestScope.numE}</span>
                                    </div>
                                    <div>${requestScope.TID2.getdS()}: <span class = "rd">${requestScope.TID2.getNo()}đ</span>, chiếm <span class = "rd">${requestScope.TID2.getPc()}%</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <table>
                                <tr>
                                    <th>ẢNH</th>
                                    <th>MÃ THIẾT BỊ</th>
                                    <th>RẠP</th><!-- comment -->
                                    <th>PHÒNG</th>
                                    <th>NGÀY PHÁT HIỆN</th>    
                                    <th>THỜI GIAN PHÁT HIỆN</th>  
                                    <th>NGÀY SỬA</th>  
                                    <th>THỜI GIAN SỬA</th>  
                                    <th>CHI PHÍ</th>
                                    <th>BARCODE</th>
                                    <th>TÌNH TRẠNG</th>
                                    <th>HÀNH ĐỘNG</th>
                                </tr>
                                <c:forEach items = "${requestScope.listE}" var = "i">
                                    <tr>
                                        <td><img src = "${i.getImg()}"/></td>
                                        <td>${i.getDeviceCode()}</td>
                                        <td>${i.getCinName()}</td>
                                        <td>${i.getRoomID()}</td>
                                        <td>${i.getDteDetected()}</td>
                                        <td>${i.getTmeDetected()}</td>
                                        <td>${i.getDteFixed()}</td>
                                        <td>${i.getTmeFixed()}</td>
                                        <td>${i.getCostIncured()}</td>
                                        <td>${i.getDeviceBarCode()}</td>
                                        <td><span class = "rd">${i.getStatus()}</span></td>
                                        <td>
                                            <a  href = "udbc?id=${i.getDeviceBarCode()}">SỬA</a>
                                            /
                                            <a href = "#">XÓA</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>


            </c:if>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function in4detail(id) {
                window.location = "info?id=" + id;
            }

            function bActive(id) {
                if (id === "tkEtChoice") {
                    document.getElementById(id).style.color = 'white';
                    document.getElementById(id).style.backgroundColor = 'black';
                    document.getElementById("foodChoi").style.color = 'black';
                    document.getElementById("foodChoi").style.backgroundColor = 'white';
                    document.getElementById("web").style.display = 'block';
                    document.getElementById("tt").style.display = 'none';
                } else if (id === "foodChoi") {
                    document.getElementById(id).style.color = 'white';
                    document.getElementById(id).style.backgroundColor = 'black';

                    document.getElementById("tkEtChoice").style.color = 'black';
                    document.getElementById("tkEtChoice").style.backgroundColor = 'white';
                    document.getElementById("tt").style.display = 'block';
                    document.getElementById("web").style.display = 'none';
                } else {
                    document.getElementById(id).style.color = 'white';
                    document.getElementById(id).style.backgroundColor = 'black';
                    document.getElementById("foodChoi").style.color = 'black';
                    document.getElementById("foodChoi").style.backgroundColor = 'white';
                    document.getElementById("web").style.display = 'block';
                    document.getElementById("tt").style.display = 'none';
                }
            }

        </script>
    </body>
</html>
