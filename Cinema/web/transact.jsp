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
        <<link rel="stylesheet" href="style.css"/>
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
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "uName">Tài Khoản: <span class = "rd">${sessionScope.account.getUserName()}<span></div>
            <c:forEach items = "${requestScope.listOBD}" var = "i">
                <div class = "outsiteO">
                    <div class = "oDate">${i.getDate()}</div>
                    <table>
                        <tr>
                            <th>MÃ HÓA ĐƠN</th>
                            <th>KIỂU THANH TOÁN</th>
                            <th>NGÀY THANH TOÁN</th>
                            <th>THỜI GIAN THANH TOÁN</th>
                            <th>XEM CHI TIẾT</th>
                            <th>TỔNG TIỀN</th>
                        </tr>
                        <c:forEach items = "${i.getO()}" var = "k">
                            <tr>
                                <td>${k.getOrderID()}</td>
                                <td>${k.getPaymentType()}</td><!-- <td></td> -->
                                <td>${k.getPaymentDate()}</td>
                                <td>${k.getPaymentTime()}</td>
                                <td><span class = "rd">${k.getTotalAmount()}đ</span></td>
                                <td><input type ="button" value = "XEM CHI TIẾT"onclick = "in4detail('${k.getOrderID()}')"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:forEach>
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
    
        </script>
    </body>
</html>
