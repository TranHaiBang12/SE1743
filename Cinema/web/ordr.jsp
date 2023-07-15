<%-- 
    Document   : Transaction
    Created on : Jun 12, 2023, 5:25:17 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <<link rel="stylesheet" href="style.css?version=1"/>
        <style>
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
            
            .body{
                padding-top: 40px;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
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
                        <!--
                        private String orderID;
                private String userName;
                private String firstName;
                private String lastName;
                private String phone;
                private String email;
                private String country;
                private String street;
                private String district;
                private String city;
                private String paymentType;
                private Date paymentDate;
                private Time paymentTime;
                        <div class = "trsct">
                            <div class = "ve"></div>
                            <div class = "doan"></div>
                        </div>
                        -->

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
