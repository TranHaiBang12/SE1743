<%-- 
    Document   : payment
    Created on : Jun 12, 2023, 1:36:32 PM
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
            .cart {
                display: flex;
                flex-direction: column;
                width: 60%;
                border: 1px solid black;

                margin-right: 20px;
                margin-left: 129px;
                padding-bottom: 25px;
            }

            .choice{
                margin-top: 20px;
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
                width: 33%;
                text-align: center;
            }

            .foodChoi {
                padding: 10px;
                font-weight: bold;
                cursor: pointer;
                width: 33%;
                text-align: center;
            }

            .al {
                border-right: 1px solid black;
                padding: 10px;
                font-weight: bold;
                cursor: pointer;
                width: 33%;
                text-align: center;
            }

            #active {
                background-color: black;
                color: white;
            }

            .insideCart {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
                margin-bottom: 20px;
            }

            .imGe {
                display: flex;
                margin-left: 20px;
            }

            .imGe img{
                width: 150px;
                height: 150px;
            }

            .intu{
                display: flex;
                flex-direction: column;
                margin-right: 20px;

            }

            .tkt{
                font-size: 20px;
                font-weight: bold;
                margin-left: 40px;
                display: flex;
                flex-direction: column;
            }

            .cartName {
                margin-bottom: 10px;
            }

            .cartPrice{
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 10px;
            }

            .intu input{
                margin-right: 12px;
                font-size: 20px;
                height: 40px;
                width: 30px;
                border: none;
                cursor: pointer;
                color: black;
                background-color: white;
                margin-bottom: 10px;
            }



            .seeCart input{
                width: 40%;
                height: 25px;
                background-color: red;
                color: white;
                font-weight: bold;
                cursor: pointer;
                margin-bottom: 10px;
            }

            #crt {
                display: none;
            }

            .ttle {
                text-align: center;
                padding-top: 20px;
                color: black;
                font-size: 30px;
                font-weight: bold;
            }

            .in4 {
                padding-left: 30%;
                padding-right: 30%;
            }

            .frm{
                display: flex;
                flex-direction: column;
            }

            .vnpay {
                width: 20%;
            }


            .frm input {
                margin-top :10px;
                margin-bottom: 10px;
                width: 500px;
                height: 30px;
                border: 1px solid black;
                border-radius: 10px;
                font-size: 20px;
                padding-left: 10px;
            }

            .frm div{
                margin-top :10px;
                margin-bottom: 10px;
            }

            .frm{
                font-size: 20px;
            }

            .ten {
                display: flex;
                align-items: center;
            }

            .ten input {
                width: 170px;
                margin-right: 20px;
                margin-left: 20px;
            }

            .pay{
                padding-left: 30%;
                padding-right: 30%;
            }

            .pay input{
                width: 40%;
                height: 25px;
                background-color: red;
                color: white;
                font-weight: bold;
                cursor: pointer;
                margin-top: 20px;

            }

            .payoo{
                width: 0%;
            }

            .frm img{
                width: 100px;
            }

            .pmType {
                display: flex;
                align-items: center;
            }

            .pmType div {
                display: flex;
                align-items: center;
                margin-right: 20px;
            }

            .pmType input {
                margin-right: 20px;
                height: 20px;
                cursor: pointer;
            }



            .pmType div {
                width: 25%;
            }

            .cart {
                padding-left: 0px;
                padding-right: 0px;
            }

            .rd{
                color: red;
            }
            
            .part{
                font-weight: bold;
            }
            
            .ms{
                font-size: 30px;
                color: red;
                margin-top: 10px;
            }

        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <form action = "pay" method = "post">
                <div class = "in4">

                    <div class = "ttle">THANH TOÁN</div>
                    <div class = "ms">${requestScope.ms}</div>
                    <div class = "frm">
                        <div class = "part">1. Chi tiết thông tin</div>
                        <label for = "email">Địa chỉ email(<span class = "rd">*</span>)</label>
                        <input type ="text" required id ="email" name ="email"/>

                        <div class = "ten">
                            <label for = "fName">Họ(<span class = "rd">*</span>)</label>
                            <input type ="text" required id ="fName" name ="fName"/>


                            <label for = "lName">Tên(<span class = "rd">*</span>)</label>
                            <input type ="text" required id ="lName" name ="lName"/>
                        </div>

                        <label for = "sdt">Số điện thoại(<span class = "rd">*</span>)</label>
                        <input type ="text" required id ="sdt" name ="sdt"/>


                        <label for = "cntry">Quốc gia(<span class = "rd">*</span>)</label>
                        <input type ="text" required id ="cntry" name ="cntry"/>


                        <label for = "city">Thành phố(<span class = "rd">*</span>)</label>
                        <input type ="text" required id ="city" name ="city"/>


                        <label for = "dist">Quận, huyện(<span class = "rd">*</span>)</label>
                        <input type ="text" required id ="dist" name ="dist"/>


                        <label for = "strt">Số nhà, đường(<span class = "rd">*</span>)</label>
                        <input type ="text" required id ="strt" name ="strt"/>


                        <div class = "part">2. Hình thức thanh toán</div>
                        <div class = "pmType">
                            <div>
                                <input class ="vnpay" type ="radio" name ="pm" value = "0"/>
                                <img src ="images/icon-vnpay.png"/>
                            </div>
                            <div>
                                <input class ="payoo" type ="radio" name ="pm" value = "1"/>
                                <img src ="images/icon-payoo.png"/>
                            </div>
                        </div>
                    </div>
                    <div class = "seeCart"><input type = "button" value = "XEM GIỎ HÀNG" onclick = "seeCart()"/></div>
                </div>
                <div class = "cart" id = "crt">
                    <div class = "choice">
                        <div id = "active" class = "al" onclick = "bActive('active')">ALL</div>
                        <div id ="tkEtChoice" class = "tkEtChoice" onclick = "bActive('tkEtChoice')">VÉ</div>
                        <div id ="foodChoi" class = "foodChoi" onclick = "bActive('foodChoi')">ĐỒ ĂN</div>

                    </div>

                    <c:if test = "${requestScope.ms != null}">
                        <span>${requestScope.ms}</span>
                    </c:if>
                    <c:if test = "${requestScope.ms == null}">
                        <div id = "doan">
                            <c:forEach items = "${requestScope.listCart}" var = "i">
                                <div id ="list${i.getFood().getProductCode()}" class = "myCart">

                                    <div class = "insideCart">
                                        <div class = "imGe">
                                            <img src = "${i.getFood().getImg()}">

                                            <div class = "tkt">${i.getFood().getFoodDescript()}</div>
                                        </div>
                                        <div class = "intu">

                                            <div class = "cartPrice"><label id ="price${i.getFood().getProductCode()}">${i.getFood().getPrice() * i.getQuantity()}</label><span class = "donvi">đ</div>
                                            <div>
                                                <input type ="button" name ="cartButton" id ="${i.getFood().getProductCode()}" value = "${i.getQuantity()}"/>
                                            </div>

                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div id = "ve">
                            <c:forEach items = "${requestScope.listTicket}" var = "t">
                                <div id ="list${t.getTicket().getProductCode()}${t.getSeat()}" class = "myCart">

                                    <div class = "insideCart">
                                        <div class = "imGe">
                                            <img src = "images/cinemaTicket.jpg">
                                            <div class = "tkt">
                                                <div class = "cartName">${t.getTicket().getMovie().getMovName()}</div>
                                                <div class ="cartSeat">Ghế: <span class = "seat">${t.getSeat()}</span></div>
                                            </div>
                                        </div>

                                        <div class = "intu">

                                            <div class = "cartPrice"><label id ="price${t.getTicket().getProductCode()}">${t.getTicket().getPrice()}</label><span class = "donvi">đ</div>
                                            <div>
                                                <input type ="button" name ="cartButton" value = "1"/>
                                            </div>

                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
                <div class ="pay"><input type = "submit" value = "THANH TOÁN"/></div>
            </form>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type ="text/javascript">
            function seeCart() {
                if (String(document.getElementById("crt").style.display) === 'none') {
                    document.getElementById("crt").style.display = 'block';
                    console.log("1");
                } else if (String(document.getElementById("crt").style.display) === 'block') {
                    document.getElementById("crt").style.display = 'none';
                    console.log("2");
                } else {
                    document.getElementById("crt").style.display = 'block';
                }
            }

            function bActive(id) {
                if (id === "tkEtChoice") {
                    document.getElementById(id).style.color = 'white';
                    document.getElementById(id).style.backgroundColor = 'black';
                    document.getElementById("active").style.color = 'black';
                    document.getElementById("active").style.backgroundColor = 'white';
                    document.getElementById("foodChoi").style.color = 'black';
                    document.getElementById("foodChoi").style.backgroundColor = 'white';
                    document.getElementById("ve").style.display = 'block';
                    document.getElementById("doan").style.display = 'none';
                } else if (id === "foodChoi") {
                    document.getElementById(id).style.color = 'white';
                    document.getElementById(id).style.backgroundColor = 'black';
                    document.getElementById("active").style.color = 'black';
                    document.getElementById("active").style.backgroundColor = 'white';
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
