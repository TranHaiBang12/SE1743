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

            table{
                margin-top: 20px;
                border: 1px solid black;
                margin-bottom: 20px;
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
            .oDate{
                font-size: 25px;
                font-weight: bold;
            }

            td input{
                width: 80%;
                height: 30px;
                cursor: pointer;
                margin-bottom: 10px;
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

            .choice{
                margin-right: 20px;
                margin-left: 80px;
                margin-bottom: 20px;
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
                background-color: black;
                color: white;
            }

            .foodChoi {
                padding: 10px;
                font-weight: bold;
                cursor: pointer;
                width: 50%;
                text-align: center;
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

        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">THỐNG KÊ VỀ PHIM</div>
            <form action = "orl" method = "post">
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
            <div class = "choice">
                <div id ="tkEtChoice" class = "tkEtChoice" onclick = "bActive('tkEtChoice')">MUA QUA WEB</div>
                <div id ="foodChoi" class = "foodChoi" onclick = "bActive('foodChoi')">MUA TRỰC TIẾP</div>

            </div>
            <div id ="web">
                <c:if test = "${requestScope.msONL == null}">
                    <c:forEach items = "${requestScope.listOBD}" var = "i">
                        <div class = "outsiteO">
                            <div class = "oDate">${i.getDate()}</div>
                            <table>
                                <tr>
                                    <th>MÃ HÓA ĐƠN</th>
                                    <th>KIỂU THANH TOÁN</th>
                                    <th>NGÀY THANH TOÁN</th>
                                    <th>THỜI GIAN THANH TOÁN</th>
                                    <th>TỔNG TIỀN</th>
                                    <th>HÀNH ĐỘNG</th>
                                </tr>
                                <c:forEach items = "${i.getO()}" var = "k">
                                    <tr>
                                        <td>${k.getOrderID()}</td>
                                        <td>${k.getPaymentType()}</td><!-- <td></td> -->
                                        <td>${k.getPaymentDate()}</td>
                                        <td>${k.getPaymentTime()}</td>
                                        <td><span class = "rd">${k.getTotalAmount()}đ</span></td>
                                        <td>
                                            <input type ="button" value = "XEM CHI TIẾT"onclick = "in4detail('${k.getOrderID()}')"/>

                                            <input type ="button" value = "XÓA"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test = "${requestScope.msONL != null}">
                    <div class = "ms">
                        ${requestScope.msONL}
                    </div>
                </c:if>
            </div>
            <div id = "tt">
                <c:if test = "${requestScope.msOFF == null}">
                    <c:forEach items = "${requestScope.listOFBD}" var = "j">
                        <div class = "outsiteO">
                            <div class = "oDate">${j.getDate()}</div>
                            <table>
                                <tr>
                                    <th>MÃ HÓA ĐƠN</th>
                                    <th>KIỂU THANH TOÁN</th>
                                    <th>NGÀY THANH TOÁN</th>
                                    <th>THỜI GIAN THANH TOÁN</th>
                                    <th>TỔNG TIỀN</th>
                                    <th>XEM CHI TIẾT</th>
                                </tr>


                                <c:forEach items = "${j.getOf()}" var = "q">
                                    <tr>
                                        <td>${q.getOrderID()}</td>
                                        <td>${q.getPaymentType()}</td>
                                        <td>${q.getPaymentDate()}</td>
                                        <td>${q.getPaymentTime()}</td>
                                        <td><span class = "rd">${q.getTotalAmount()}đ</span></td>
                                        <td><input type ="button" value = "XEM CHI TIẾT"onclick = "in4detail('${q.getOrderID()}')"/></td>
                                    </tr>
                                </c:forEach>

                            </table>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test = "${requestScope.msOFF != null}">
                    <div class = "ms">
                        ${requestScope.msOFF}
                    </div>
                </c:if>
            </div>
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
