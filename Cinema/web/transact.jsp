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
                margin-top: 20px;
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
                font-size: 25px;
                margin-left: 80px;
            }

            .uName{
                padding-top: 50px;
            }

            .choice{
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
                border-left: 1px solid black;
                border-radius: 12px 0px 0px 0px;
            }

            .last{
                border-right: 1px solid black;
                border-radius: 0px 12px 0px 0px;
            }

            th{
                border: none;
                border-bottom: 1px solid black;
            }

            td{
                border: none;
                border-bottom: 1px solid black;
            }
            
            .oDate{
                margin-bottom: 20px;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
                  <div class = "uName">Tài Khoản: <span class = "rd">${sessionScope.account.getUserName()}<span></div>
                      <div class = "choice">
                          <div id ="tkEtChoice" class = "tkEtChoice" onclick = "bActive('tkEtChoice')">MUA QUA WEB</div>
                          <div id ="foodChoi" class = "foodChoi" onclick = "bActive('foodChoi')">MUA TRỰC TIẾP</div>

                      </div>
                      <div id ="web">
                          <c:if test = "${ms != null}">
                              <div class = "ms">
                                  ${requestScope.ms}
                              </div>
                          </c:if>
                          <c:if test = "${listOBD != null}">

                              <c:forEach items = "${requestScope.listOBD}" var = "i">
                                  <div class = "outsiteO">
                                      <div class = "oDate">${i.getDate()}</div>
                                      <table>
                                          <tr class = "ttr">
                                              <th class = "first">MÃ HÓA ĐƠN</th>
                                              <th>KIỂU THANH TOÁN</th>
                                              <th>NGÀY THANH TOÁN</th>
                                              <th>THỜI GIAN THANH TOÁN</th>
                                              <th>TỔNG TIỀN</th>
                                              <th class = "last">XEM CHI TIẾT</th>
                                          </tr>
                                          <c:forEach items = "${i.getO()}" var = "k">
                                              <tr>
                                                  <td>${k.getOrderID()}</td>
                                                  <td>${k.getPaymentType()}</td><!-- <td></td> -->
                                                  <td>${k.getPaymentDate()}</td>
                                                  <td>${k.getPaymentTime()}</td>
                                                  <td><span class = "rd">${k.getTotalAmount()}đ</span></td>
                                                  <td><button type ="button" value = "XEM CHI TIẾT"onclick = "in4detail('${k.getOrderID()}')">XEM CHI TIẾT</button></td>
                                              </tr>
                                          </c:forEach>
                                      </table>
                                  </div>
                              </c:forEach>
                          </c:if>
                      </div>
                      <div id = "tt">
                          <c:if test = "${msOFF != null}">
                              <div class = "ms">
                                  ${requestScope.msOFF}
                              </div>
                          </c:if>
                          <c:if test = "${listOFBD != null}">
                              <c:forEach items = "${requestScope.listOFBD}" var = "j">
                                  <div class = "outsiteO">
                                      <div class = "oDate">${j.getDate()}</div>
                                      <table>
                                          <tr class = "ttr">
                                              <th class = "first">MÃ HÓA ĐƠN</th>
                                              <th>KIỂU THANH TOÁN</th>
                                              <th>NGÀY THANH TOÁN</th>
                                              <th>THỜI GIAN THANH TOÁN</th>
                                              <th>TỔNG TIỀN</th>
                                              <th class = "last">XEM CHI TIẾT</th>
                                          </tr>


                                          <c:forEach items = "${j.getOf()}" var = "q">
                                              <tr>
                                                  <td>${q.getOrderID()}</td>
                                                  <td>${q.getPaymentType()}</td>
                                                  <td>${q.getPaymentDate()}</td>
                                                  <td>${q.getPaymentTime()}</td>
                                                  <td><span class = "rd">${q.getTotalAmount()}đ</span></td>
                                                  <td><button type ="button" value = "XEM CHI TIẾT"onclick = "in4detail('${q.getOrderID()}')">XEM CHI TIẾT</button></td>
                                              </tr>
                                          </c:forEach>

                                      </table>
                                  </div>
                              </c:forEach>
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
