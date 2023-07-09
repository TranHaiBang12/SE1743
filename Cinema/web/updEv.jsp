<%-- 
    Document   : event
    Created on : Jul 8, 2023, 2:35:10 PM
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
                text-shadow: 0px 0px 3px yellow, 0px -5px 5px red;
                margin-left: 80px;
                padding-top: 20px;
                text-align: center;
                font-size: 30px;
                border-top: 2px solid black;
            }

            .Sttle{
                font-size: 20px;
                color: crimson;
                margin-bottom: 20px;
            }

            .body{
                width: 100%;
                overflow-x: hidden;
                padding-top: 20px;
                padding-right: 40px;
            }

            #header{

            }

            .date{
                font-size: 18px;
            }

            .detail{

                margin-top: 40px;
                display: flex;
                flex-direction: column;
                flex-wrap: wrap;
                margin-left: 80px;
                padding-bottom: 40px;
                border-bottom: 2px solid black;
            }

            .event{
                display: flex;
            }

            .dleIMG img{
                width: 100%;
                height: 100%;
            }

            .dleIMG{
                width: 351.5px;
                height: 491.11px;
            }
            .blk{
                font-weight: bold;
            }

            .prt{

                margin-bottom: 20px;
            }

            .in4{
                margin-left: 20px;
                font-size: 20px;
                padding-right: 80px;
            }
            input{
                font-size: 20px;
            }
            select{
                font-size:20px;
                margin-bottom: 10px;
            }
            .m1{
                margin-left: 40px;
            }

            .k{
                width: 1000px;
            }

            .btS{
                margin-top: 20px;
            }

            .btS button{
                font-size: 18px;
                padding: 5px;
                background-color: red;
                color: white;
                cursor: pointer;
            }

            .plI{
                display: flex;
            }

            .imGe{
                width: 25px;
                height: 25px;
                cursor: pointer;
                margin-left: 10px;
            }

            .imGe img{
                width: 100%;
                height: 100%;
            }

            .pin4{
                display: flex;
                align-items: center;
            }

            .IMGE img{
                width: 100%;
                height: 100%;
            }

            .IMGE{
                width: 165px;
                height: 170px;
                margin-right: 20px;
            }

            .nme{
                font-size: 25px;
                font-weight: bold;
            }

            .oIN4{
                display: flex;
                flex-direction: column;
            }

            #dc{
                margin-bottom: 10px;
            }
            
            .ms{
                color: red;
                font-weight: bold;
                margin-bottom: 10px;
            }

        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">ƯU ĐÃI</div>
            <c:if test = "${requestScope.ms != null}">

            </c:if>
            <c:if test = "${requestScope.e != null}">
                <div class = "detail">
                    <div class = "Sttle">${requestScope.e.getEventName()}</div>
                    <form id ="frm" action = "updev" method = "post">
                        <div class = "event">
                            <div class = "dleIMG">
                                <img src ="${requestScope.e.getImg()}"/>
                            </div>

                            <input type ="text" hidden id ="checkDc" name ="checkDc" value ="${requestScope.checkDc != null ?(requestScope.checkDc):"0"}"/>
                            <input type ="text" hidden id ="checkApply" name ="checkApply" value ="${requestScope.checkApply != null ?(requestScope.checkApply):"0"}"/>
                            <input type ="text" hidden name ="id" value ="${requestScope.id}"/>
                            <div class = "in4">
                                <div class = "ms">
                                    ${requestScope.ms}
                                </div>
                                <div class = "prt">
                                    <span class = "blk">Mã ưu đãi:</span> ${requestScope.id}
                                </div>
                                <div class = "prt">
                                    <c:if test = "${requestScope.startU == null && requestScope.endU == null}">
                                        <span class = "blk">1. Thời gian áp dụng:</span> Từ <input type = "date" required="" name = "startDate"/> đến hết <input type = "date" required name = "endDate"/>
                                    </c:if>
                                    <c:if test = "${requestScope.startU != null && requestScope.endU != null}">
                                        <span class = "blk">1. Thời gian áp dụng:</span> Từ ${requestScope.startU} đến hết ${requestScope.endU}
                                    </c:if>
                                </div>
                                <div class = "prt">
                                    <div class = "blk">2. Nội dung ưu đãi:</div>
                                    <div class = "m1"><input class ="k" type = "text" name = "content" value = "${requestScope.e.getEventContent()}"/></div>
                                </div>
                                <div class = "prt">
                                    <div class = "blk">3. Kiểu ưu đãi:</div>
                                    <div class = "plI">
                                        <div class = "m1">
                                            <select name = "dctype" id ="dctype" onchange = "sbmitDcType()">
                                                <c:forEach items = "${requestScope.type}" var = "k">
                                                    <option value = "${k.getEventCode()}" ${k.getEventCode() == requestScope.evType?"selected":""}>${k.getOrderID()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>


                                    </div>
                                    <c:if test = "${requestScope.pc != null}">
                                        <div class = "m1">
                                            Giảm giá: <input type ="number" required="false" id ="dc" step ="0.05" min ="0" max ="1" name ="dc" value ="${requestScope.discount}"/>
                                        </div>
                                    </c:if>
                                    <c:if test = "${requestScope.pc == null}">
                                        <div class = "m1">
                                            Giảm giá: <input type ="number" required="false" disabled="true" id ="dc" step ="0.05" min ="0" max ="1" name ="dc" value ="${requestScope.discount}"/>
                                        </div>
                                    </c:if>
                                    <c:if test = "${requestScope.f != null}">
                                        <c:forEach items = "${requestScope.f}" var = "i">
                                            <div class = "m1">
                                                <div class = "pin4">

                                                    <div class = "oIN4">

                                                        <div>
                                                            Mã sản phẩm tặng kèm: 
                                                            <select name ="fCode" id = "fCode" required="false">
                                                                <c:forEach items = "${requestScope.allF}" var = "k">
                                                                    <option value ="${k.getProductCode()}" ${k.getProductCode().equals(i.getProductCode())?"selected":""}>${k.getProductCode()}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test = "${requestScope.f != null}">
                                        <div class = "m1">
                                            Số ngày tối đa có thể đổi quà: <input type ="number" required="false" id ="dte" step ="1" min ="0" max ="7" name ="dte" value ="${requestScope.maxDate}"/>
                                        </div>
                                    </c:if>
                       
                                    <div>

                                    </div>
                                </div>
                                <div class = "prt">
                                    <div class = "blk">4. Kiểu tác động:</div>
                                    <div class = "plI">
                                        <div class = "m1">
                                            <select name = "applytype" id ="applytype" required="false" onchange = "sbmitApplyType()">
                                                <c:forEach items = "${requestScope.listTypeA}" var = "k">
                                                    <option value = "${k}" ${k.equals(requestScope.typeApply)?"selected":""}>${k}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <c:if test = "${requestScope.movID != null}">
                                        <div class = "m1">
                                            <select id ="mov" required="false" name = "mov">
                                                <c:forEach items = "${requestScope.mov}" var = "k">
                                                    <option value = "${k.getMovID()}" ${k.getMovID() == movID?"selected":""}>${k.getMovName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </c:if>
                                    <c:if test = "${requestScope.movID == null}">
                                        <div class = "m1">
                                            <select id ="mov" required="false" name = "mov" disabled="">
                                                <c:forEach items = "${requestScope.mov}" var = "k">
                                                    <option value = "${k.getMovID()}" ${k.getMovID() == movID?"selected":""}>${k.getMovName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </c:if>
                                    <c:if test = "${requestScope.price != null}">
                                        <div class = "m1">
                                            Áp dụng với hóa đơn trên: <input required type ="number" id ="price" value ="${requestScope.price}" name ="price"/>
                                        </div>
                                        <div class = "m1">
                                            Áp dụng với: 
                                            <select name = "otype" id = "otype" required>
                                                <c:forEach items = "${requestScope.otype}" var = "i">
                                                    <c:if test = "${requestScope.e.getType() == 'TK'}">
                                                        <option value = "${i}" ${i.equals("Vé")?"selected":""}>${i}</option>
                                                    </c:if>
                                                    <c:if test = "${requestScope.e.getType() == 'FD'}">
                                                        <option value = "${i}" ${i.equals("Đồ Ăn")?"selected":""}>${i}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </c:if>
                                    <c:if test = "${requestScope.price == null}">
                                        <div class = "m1">
                                            Áp dụng với hóa đơn trên: <input type ="number" id ="price" disabled="" value ="${requestScope.price}" name ="price"/>
                                        </div>
                                        <div class = "m1">
                                            Áp dụng với: 
                                            <select name = "otype" id = "otype" required="false" disabled="" >
                                                <c:forEach items = "${requestScope.otype}" var = "i">

                                                    <option value = "${i}" >${i}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </c:if>
                              
                                </div>
                                <div class = "prt">
                                    <div class = "blk">5. Điều kiện và điều khoản:</div>
                                    <div class = "plI">
                                        <div>- Áp dụng tại các rạp:
                                        </div>
                                        <div class ="imGe">
                                            <img onclick ="createNewCin()" src ="images/plusIcon.png"/>
                                        </div>
                                    </div>

                                    <c:forEach items = "${requestScope.e.getCin()}" var = "i">
                                        <div class = "m1">
                                            <select name = "cin">
                                                <c:forEach items = "${requestScope.cin}" var = "k">
                                                    <option value = "${k.getCinID()}" ${k.getCinID() == i.getCinID()?"selected":""}>${k.getCinName()}</option>
                                                    ${k.getCinID()}
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </c:forEach>
                                    <div class ="m1">
                                        <select hidden id ="slt1" name ="cin">
                                            <c:forEach items = "${requestScope.cin}" var = "k">
                                                <option value = "${k.getCinID()}">${k.getCinName()}</option>
                                            </c:forEach>
                                        </select><!-- comment -->
                                        <select hidden id ="slt2" name ="cin">
                                            <c:forEach items = "${requestScope.cin}" var = "k">
                                                <option value = "${k.getCinID()}">${k.getCinName()}</option>
                                            </c:forEach>
                                        </select>
                                        <select hidden id ="slt3" name ="cin">
                                            <c:forEach items = "${requestScope.cin}" var = "k">
                                                <option value = "${k.getCinID()}">${k.getCinName()}</option>
                                            </c:forEach>
                                        </select>
                                        <select hidden id ="slt4" name ="cin">
                                            <c:forEach items = "${requestScope.cin}" var = "k">
                                                <option value = "${k.getCinID()}">${k.getCinName()}</option>
                                            </c:forEach>
                                        </select>
                                        <select hidden id ="slt5" name ="cin">
                                            <c:forEach items = "${requestScope.cin}" var = "k">
                                                <option value = "${k.getCinID()}">${k.getCinName()}</option>
                                            </c:forEach>
                                        </select>
                                        <select hidden id ="slt6" name ="cin">
                                            <c:forEach items = "${requestScope.cin}" var = "k">
                                                <option value = "${k.getCinID()}">${k.getCinName()}</option>
                                            </c:forEach>
                                        </select>
                                        <select hidden id ="slt7" name ="cin">
                                            <c:forEach items = "${requestScope.cin}" var = "k">
                                                <option value = "${k.getCinID()}">${k.getCinName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div id = "newcin" class = "m1">

                                    </div>
                                    <input hidden type ="text" id ="gN" name ="gN" value ="0"/>
                                    <input type ="text" hidden id ="size" value ="${requestScope.e.getCin().size()}"/>


                              
                                </div>
                                <div class = "prt">
                                    <div class = "blk">6. Ảnh:<input type = "text" required name = "img" value = "${requestScope.e.getImg()}"/></div> 

                                </div>

                                <div class = "prt">
                                    <div class = "blk">7. Áp dụng với ưu đãi khác:
                                        <select name ="appO">
                                            <c:forEach items = "${requestScope.appO}" var = "k">
                                                <c:if test = "${requestScope.e.getApplyO() == 1}">
                                                    <option ${k.equals("Có")?"selected":""}>${k}</option>
                                                </c:if>
                                                <c:if test = "${requestScope.e.getApplyO() == 0}">
                                                    <option ${k.equals("Không")?"selected":""}>${k}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div> 

                                </div>
                                <div class = "prt">
                                    <div class = "blk">8. Tiếp tục ưu đãi này: 
                                        <select name ="discontinued">
                                            <c:forEach items = "${requestScope.discontinued}" var = "k">
                                                <c:if test = "${requestScope.e.getDiscontinued() == 0}">
                                                    <option ${k.equals("Tiếp tục")?"selected":""}>${k}</option>
                                                </c:if>
                                                <c:if test = "${requestScope.e.getDiscontinued() == 1}">
                                                    <option ${k.equals("Không")?"selected":""}>${k}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div> 
                                </div>
                                <c:if test = "${sessionScope.account != null && sessionScope.account.getRole() == 3}">
                                    <div class = "m1">
                                        <div class = "btS">
                                            <button type = "submit">LƯU</button>
                                        </div>
                                    </div>
                                </c:if>

                            </div>

                        </div>
                    </form>
                </div>

            </c:if>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function dt(id) {
                window.location = "edt?id=" + id;
            }
            var size = document.getElementById("size").value;
            var b = 0;
            document.getElementById("gN").value = Number(b) + Number(size);
            function createNewCin() {
                if (Number(b) + Number(size) < 7) {
                    b++;
                    document.getElementById("gN").value = Number(b) + Number(size);
                    console.log(b);
                    document.getElementById("slt" + b).style.display = 'block';
                } else {
                    alert("Tối đa là 7");
                }

            }

            function sbmitDcType() {
                var e = document.getElementById("dctype");
                var value = e.value;
                var text = e.options[e.selectedIndex].text;
                if (String(text) === "Giảm giá") {
                    document.getElementById("dc").disabled = false;
                    document.getElementById("dc").required = true;
                    document.getElementById("fCode").disabled = true;

                    document.getElementById("dte").disabled = true;

                } else if (String(text) === "Tặng đồ") {
                    document.getElementById("dc").disabled = true;
                    document.getElementById("fCode").disabled = false;
                    document.getElementById("fCode").required = true;
                    document.getElementById("dte").disabled = false;
                    document.getElementById("dte").required = true;
                }

            }

            function sbmitApplyType() {
                var e = document.getElementById("applytype");
                var value = e.value;
                var text = e.options[e.selectedIndex].text;
                if (String(text) === "Phim") {
                    document.getElementById("price").disabled = true;
                    document.getElementById("otype").disabled = true;
                    document.getElementById("mov").disabled = false;
                    document.getElementById("mov").required = true;
                } else if (String(text) === "Hóa Đơn") {
                    document.getElementById("price").disabled = false;
                    document.getElementById("price").required = true;
                    document.getElementById("otype").disabled = false;
                    document.getElementById("otype").required = true;
                    document.getElementById("mov").disabled = true;

                }
            }
        </script>
    </body>
</html>
