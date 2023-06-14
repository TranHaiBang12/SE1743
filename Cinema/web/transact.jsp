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
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <c:forEach items = "${requestScope.listOBD}" var = "i">
                <div class = "outsiteO">
                    <div class = "oDate">${i.getDate()}</div>
                    <table>
                        <tr>
                            <th>MÃ HÓA ĐƠN</th>
                            <th>KIỂU THANH TOÁN</th>
                            <th>NGÀY THANH TOÁN</th>
                            <th>THỜI GIAN THANH TOÁN</th>
                        </tr>
                        <c:forEach items = "${i.getO()}" var = "k">
                                <tr>
                                    <td>${k.getOrderID()}</td>
                                    <td>${k.getPaymentType()}</td><!-- <td></td> -->
                                    <td>${k.getPaymentDate()}</td>
                                    <td>${k.getPaymentTime()}</td>
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
            function bActive(id) {
                if (id === "tkEtChoice") {
                    document.getElementById(id).style.color = 'white';
                    document.getElementById(id).style.backgroundColor = 'black';
                    document.getElementById("foodChoi").style.color = 'black';
                    document.getElementById("foodChoi").style.backgroundColor = 'white';
                    document.getElementById("ve").style.display = 'block';
                    document.getElementById("doan").style.display = 'none';
                } else if (id === "foodChoi") {
                    document.getElementById(id).style.color = 'white';
                    document.getElementById(id).style.backgroundColor = 'black';

                    document.getElementById("tkEtChoice").style.color = 'black';
                    document.getElementById("tkEtChoice").style.backgroundColor = 'white';
                    document.getElementById("doan").style.display = 'block';
                    document.getElementById("ve").style.display = 'none';
                } else {
                    document.getElementById(id).style.color = 'white';
                    document.getElementById(id).style.backgroundColor = 'black';
                    document.getElementById("foodChoi").style.color = 'black';
                    document.getElementById("foodChoi").style.backgroundColor = 'white';
                    document.getElementById("tkEtChoice").style.color = 'black';
                    document.getElementById("tkEtChoice").style.backgroundColor = 'white';
                    document.getElementById("doan").style.display = 'block';
                    document.getElementById("ve").style.display = 'block';
                }
            }
        </script>
    </body>
</html>
