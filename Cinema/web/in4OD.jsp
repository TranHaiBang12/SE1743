<%-- 
    Document   : in4OD
    Created on : Jun 14, 2023, 10:14:05 AM
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

            table{
                margin-left: 40px;
                margin-right: 40px;
                margin-top: 20px;
                border: 1px solid black;
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

            .ttle{
                margin-top: 20px;
                margin-left: 40px;
                margin-right: 40px;
                font-size: 25px;
            }

            td img{
                width: 150px;
                height: 120px;
            }

            .ttAmF {
                margin-left: 40px;
                margin-top: 20px;
                font-size: 20px;
                font-weight: bold;
            }

            .ttAmT {
                margin-left: 40px;
                margin-top: 20px;
                font-size: 20px;
                font-weight: bold;
            }

            .doan {
                margin-left: 100px;
            }

            .ve{
                margin-left: 100px;
            }

            #amA{
                font-weight: bold;
            }

            .code{
                font-weight: bold;
                color: red;
            }

            .note{
                font-weight: bold;
                font-size: 25px;
                color: blue;
                margin-left: 130px;
            }

            .note div{
                margin-top: 15px;
            }
            
            #amM{
                font-weight: bold;
            }

        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "uName">Tài Khoản: <span class = "rd">${sessionScope.account.getUserName()}<span></div>
                        <div class = "uName">Mã Hóa Đơn: <span class = "rd">${requestScope.orderID}<span></div>
                                    <c:if test = "${requestScope.type != null}">
                                        <div class = "in4O">
                                            <div class = "ttle">
                                                1. Thông Tin Khách Hàng
                                            </div>
                                            <table>
                                                <tr>                                
                                                    <th>HỌ</th>
                                                    <th>TÊN</th>
                                                    <th>SỐ ĐIỆN THOẠI</th>
                                                    <th>EMAIL</th>
                                                    <th>QUỐC GIA</th>
                                                    <th>SỐ NHÀ</th>
                                                    <th>QUẬN</th><!-- <th></th> -->
                                                    <th>THÀNH PHỐ</th>
                                                    <th>KIỂU THANH TOÁN</th><!-- <th></th> -->
                                                    <th>NGÀY THANH TOÁN</th>
                                                    <th>NƠI THANH TOÁN</th>
                                                </tr>

                                                <c:forEach items = "${requestScope.listO}" var = "i">
                                                    <tr>
                                                        <td>${i.getFirstName()}</td>
                                                        <td>${i.getLastName()}</td>
                                                        <td>${i.getPhone()}</td>
                                                        <td>${i.getEmail()}</td>
                                                        <td>${i.getCountry()}</td>
                                                        <td>${i.getStreet()}</td>
                                                        <td>${i.getDistrict()}</td><!-- <th></th> -->
                                                        <td>${i.getCity()}</td>
                                                        <td>${i.getPaymentType()}</td><!-- <th></th> -->
                                                        <td>${i.getPaymentDate()}</td>
                                                        <td>${i.getPaymentTime()}</td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                        <div><!-- comment -->
                                            <div class = "ttle">2. Sản Phẩm</div>
                                            <div class = "doan">
                                                <c:if test = "${ms1 == null}">
                                                    <div class = "ttle">a. Đồ Ăn</div>

                                                    <table>
                                                        <tr>
                                                            <th>TÊN</th>
                                                            <th>LOẠI SẢN PHẨM</th><!-- comment -->
                                                            <th>KHUYẾN MẠI</th><!-- comment -->
                                                            <th>GIÁ</th><!-- comment -->
                                                            <th>ẢNH</th>
                                                            <th>SỐ LƯỢNG</th>
                                                            <th>TỔNG</th>
                                                        </tr>
                                                        <c:set var="cnt" value="1"/>

                                                        <c:forEach items = "${requestScope.listOD}" var = "j">
                                                            <tr>
                                                                <td>${j.getF().getFoodDescript()}</td>
                                                                <td>${j.getF().getFoodType()}</td>
                                                                <td>${j.getDiscount()}%</td>
                                                                <td>${j.getPrice()}đ</td><!-- comment -->
                                                                <td><img src="${j.getF().getImg()}"/></td>
                                                                <td>${j.getQuantity()}</td>

                                                                <td><span class = "rd"><label id = "f${cnt}">${j.getPrice() * j.getQuantity()}</label></span></td>
                                                                        <c:set var="cnt" value="${cnt+ 1}"/>
                                                            </tr>
                                                        </c:forEach>
                                                        <input type ="text" hidden value ="${cnt}" id = "numF"/>

                                                    </table>
                                                    <div class = "ttAmF">
                                                        TỔNG GIÁ: <span class = "rd"><label id = "amF"></label></span>
                                                    </div>
                                                </c:if>

                                            </div>

                                            <div class = "ve">
                                                <c:if test = "${ms2 == null}">
                                                    <div class = "ttle">b. Vé</div>
                                                    <table>
                                                        <tr>
                                                            <th>PHIM</th>
                                                            <th>DẠNG</th>
                                                            <th>RẠP</th>
                                                            <th>PHÒNG</th>
                                                            <th>CỘT</th>
                                                            <th>HÀNG</th><!-- comment -->
                                                            <th>LOẠI GHẾ</th>
                                                            <th>KHUYẾN MẠI</th>
                                                            <th>GIÁ</th>                    

                                                            <th>NGÀY</th>
                                                            <th>GIỜ</th>
                                                            <th>ẢNH</th>
                                                        </tr>
                                                        <c:set var="cntTK" value="1"/>
                                                        <c:forEach items = "${requestScope.listOTD}" var = "k">
                                                            <tr>
                                                                <td>${k.getMovName()}</td>
                                                                <td>${k.getFormName()}</td><!-- comment -->
                                                                <td>${k.getCinName()}</td><!-- comment -->
                                                                <td>${k.getRoomID()}</td>
                                                                <td>${k.getSeatType()}</td>
                                                                <td>${k.getSeatNumber()}</td>
                                                                <td>${k.getType()}</td>
                                                                <td>${k.getDiscount()}%</td>
                                                                <td><span class = "rd"><label id = "t${cntTK}">${k.getPrice()}</label>đ</span></td>
                                                                <td>${k.getStartDate()}</td>
                                                                <td>${k.getStartTime()}</td>
                                                                <td><img src = "images/cinemaTicket.jpg"></td>
                                                                    <c:set var="cntTK" value="${cntTK+ 1}"/>
                                                            </tr>
                                                        </c:forEach>
                                                        <input type ="text" hidden value ="${cntTK}" id = "numT"/>
                                                    </table>
                                                    <div class = "ttAmT">
                                                        TỔNG GIÁ: <span class = "rd"><label id = "amT"></label></span>
                                                    </div>
                                                </c:if>

                                            </div>

                                            <div class = "ttIen">
                                                <div class = "ttle">3. Tổng Hóa Đơn: <span class = "rd"><label id = "amA"></label></span></div>
                                            </div>
                                            
                                            <div class = "ttIen">
                                                <div class = "ttle">4. Số Điểm Sử Dụng: <span class = "rd"><label id = "pu">${requestScope.point}</label></span></div>
                                            </div>
                                            
                                            <div class = "ttIen">
                                                <div class = "ttle">5. Số Tiền Phải Trả: <span class = "rd"><label id = "amM"></label></span></div>
                                            </div>
                                            
                                            <div class = "ttIen">
                                                <div class = "ttle">6. Số Điểm Đạt Được: <span class = "rd">${requestScope.pointAchieve}</span></div>
                                            </div>

                                            <div class = "ttIen">
                                                <div class = "ttle">7. Mã Đổi Sản Phẩm</div>
                                                <div class = "note">
                                                    <div>- Sử dụng mã trong thời gian có hiệu lực để đổi lấy sản phẩm</div>

                                                    <div>  - Mã vé để đổi các loại vé trong hóa đơn và mã đồ ăn để đổi các loại đồ ăn trong hóa đơn</div>
                                                    <div>    - Khi hết thời gian hiệu lực, mã sẽ không còn giá trị</div>
                                                </div>
                                                <div class = "doan">
                                                    <c:if test = "${ms1 == null}">
                                                        <div class = "ttle">a. Đồ Ăn</div>
                                                        <table>
                                                            <tr>
                                                                <th class = "code">MÃ</th>
                                                                <th>RẠP</th>
                                                                <th>NGÀY CÓ HIỆU LỰC</th>
                                                                <th>THỜI GIAN CÓ HIỆU LỰC</th>
                                                                <th>NGÀY HẾT HIỆU LỰC</th>
                                                                <th>THỜI GIAN HẾT HIỆU LỰC</th>
                                                            </tr>
                                                            <c:forEach items = "${requestScope.listTCF}" var = "m">
                                                                <tr>
                                                                    <td class = "code">${m.getCode()}</td>
                                                                    <td>${m.getCinName()}</td>
                                                                    <td>${m.getDateStart()}</td><!-- comment -->
                                                                    <td>${m.getTimeStart()}</td>
                                                                    <td>${m.getDateEnd()}</td>
                                                                    <td>${m.getTimeEnd()}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </c:if>

                                                </div>
                                                <div class = "ve">
                                                    <c:if test = "${ms2 == null}">
                                                        <div class = "ttle">b. Vé</div>
                                                        <table>
                                                            <tr>
                                                                <th class = "code">MÃ</th>
                                                                <th>RẠP</th>
                                                                <th>NGÀY CÓ HIỆU LỰC</th>
                                                                <th>THỜI GIAN CÓ HIỆU LỰC</th>
                                                                <th>NGÀY HẾT HIỆU LỰC</th>
                                                                <th>THỜI GIAN HẾT HIỆU LỰC</th>
                                                            </tr>
                                                            <c:forEach items = "${requestScope.listTCT}" var = "n">
                                                                <tr>
                                                                    <td class = "code">${n.getCode()}</td>
                                                                    <td>${n.getCinName()}</td>
                                                                    <td>${n.getDateStart()}</td><!-- comment -->
                                                                    <td>${n.getTimeStart()}</td>
                                                                    <td>${n.getDateEnd()}</td>
                                                                    <td>${n.getTimeEnd()}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </c:if>

                                                </div>
                                            </div>

                                        </div>
                                    </c:if>

                                    <c:if test = "${requestScope.type == null}">
                                        <div class = "in4O">
                                            <div class = "ttle">
                                                1. Thông Tin Khách Hàng
                                            </div>
                                            <table>
                                                <tr>                                
                                                    <th>TÀI KHOẢN</th>
                                                    <th>TÊN</th>
                                                    <th>SỐ ĐIỆN THOẠI</th>
                                                    <th>NHÂN VIÊN</th>
                                                    <th>RẠP</th>
                                                    <th>KIỂU THANH TOÁN</th><!-- <th></th> -->
                                                    <th>NGÀY THANH TOÁN</th>
                                                    <th>NƠI THANH TOÁN</th>
                                                </tr>

                                                <c:forEach items = "${requestScope.listO}" var = "i">
                                                    <tr>
                                                        <td>${i.getUserName()}</td>
                                                        <td>${i.getName()}</td>
                                                        <td>${i.getPhone()}</td>
                                                        <td>${i.getEmpName()}</td>
                                                        <td>${i.getCinName()}</td>
                                    
                                                        <td>${i.getPaymentType()}</td><!-- <th></th> -->
                                                        <td>${i.getPaymentDate()}</td>
                                                        <td>${i.getPaymentTime()}</td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                        <div><!-- comment -->
                                            <div class = "ttle">2. Sản Phẩm</div>
                                            <div class = "doan">
                                                <c:if test = "${ms1 == null}">
                                                    <div class = "ttle">a. Đồ Ăn</div>

                                                    <table>
                                                        <tr>
                                                            <th>TÊN</th>
                                                            <th>LOẠI SẢN PHẨM</th><!-- comment -->
                                                            <th>KHUYẾN MẠI</th><!-- comment -->
                                                            <th>GIÁ</th><!-- comment -->
                                                            <th>ẢNH</th>
                                                            <th>SỐ LƯỢNG</th>
                                                            <th>TỔNG</th>
                                                        </tr>
                                                        <c:set var="cnt" value="1"/>

                                                        <c:forEach items = "${requestScope.listOD}" var = "j">
                                                            <tr>
                                                                <td>${j.getF().getFoodDescript()}</td>
                                                                <td>${j.getF().getFoodType()}</td>
                                                                <td>${j.getDiscount()}%</td>
                                                                <td>${j.getPrice()}đ</td><!-- comment -->
                                                                <td><img src="${j.getF().getImg()}"/></td>
                                                                <td>${j.getQuantity()}</td>

                                                                <td><span class = "rd"><label id = "f${cnt}">${j.getPrice() * j.getQuantity()}</label></span></td>
                                                                        <c:set var="cnt" value="${cnt+ 1}"/>
                                                            </tr>
                                                        </c:forEach>
                                                        <input type ="text" hidden value ="${cnt}" id = "numF"/>

                                                    </table>
                                                    <div class = "ttAmF">
                                                        TỔNG GIÁ: <span class = "rd"><label id = "amF"></label></span>
                                                    </div>
                                                </c:if>

                                            </div>

                                            <div class = "ve">
                                                <c:if test = "${ms2 == null}">
                                                    <div class = "ttle">b. Vé</div>
                                                    <table>
                                                        <tr>
                                                            <th>PHIM</th>
                                                            <th>DẠNG</th>
                                                            <th>RẠP</th>
                                                            <th>PHÒNG</th>
                                                            <th>CỘT</th>
                                                            <th>HÀNG</th><!-- comment -->
                                                            <th>LOẠI GHẾ</th>
                                                            <th>KHUYẾN MẠI</th>
                                                            <th>GIÁ</th>                    

                                                            <th>NGÀY</th>
                                                            <th>GIỜ</th>
                                                            <th>ẢNH</th>
                                                        </tr>
                                                        <c:set var="cntTK" value="1"/>
                                                        <c:forEach items = "${requestScope.listOTD}" var = "k">
                                                            <tr>
                                                                <td>${k.getMovName()}</td>
                                                                <td>${k.getFormName()}</td><!-- comment -->
                                                                <td>${k.getCinName()}</td><!-- comment -->
                                                                <td>${k.getRoomID()}</td>
                                                                <td>${k.getSeatType()}</td>
                                                                <td>${k.getSeatNumber()}</td>
                                                                <td>${k.getType()}</td>
                                                                <td>${k.getDiscount()}%</td>
                                                                <td><span class = "rd"><label id = "t${cntTK}">${k.getPrice()}</label>đ</span></td>
                                                                <td>${k.getStartDate()}</td>
                                                                <td>${k.getStartTime()}</td>
                                                                <td><img src = "images/cinemaTicket.jpg"></td>
                                                                    <c:set var="cntTK" value="${cntTK+ 1}"/>
                                                            </tr>
                                                        </c:forEach>
                                                        <input type ="text" hidden value ="${cntTK}" id = "numT"/>
                                                    </table>
                                                    <div class = "ttAmT">
                                                        TỔNG GIÁ: <span class = "rd"><label id = "amT"></label></span>
                                                    </div>
                                                </c:if>

                                            </div>

                                            <div class = "ttIen">
                                                <div class = "ttle">3. Tổng Hóa Đơn: <span class = "rd"><label id = "amA"></label></span></div>
                                            </div>
                                            
                                            <div class = "ttIen">
                                                <div class = "ttle">4. Số Điểm Sử Dụng: <span class = "rd"><label id = "pu">${requestScope.point}</label></span></div>
                                            </div>
                                            
                                            <div class = "ttIen">
                                                <div class = "ttle">5. Số Tiền Phải Trả: <span class = "rd"><label id = "amM"></label></span></div>
                                            </div>
                                            
                                            <div class = "ttIen">
                                                <div class = "ttle">6. Số Điểm Đạt Được: <span class = "rd">${requestScope.pointAchieve}</span></div>
                                            </div>


                                        </div>
                                    </c:if>
                                    <div id = "footer">
                                        <%@include file = "footer.jsp" %>
                                    </div>
                                    <script type = "text/javascript">
                                        var t = 0;
                                        if ((document.getElementById("numF")) !== null)
                                            t = Number(document.getElementById("numF").value);
                                        var value = 0;
                                        for (var i = 1; i < t; i++) {
                                            if (Number(document.getElementById("f" + i).innerHTML) !== null)
                                                value += Number(document.getElementById("f" + i).innerHTML);
                                        }
                                        if (document.getElementById("amF") !== null)
                                            document.getElementById("amF").innerHTML = value + "đ";

                                        if ((document.getElementById("numT")) !== null)
                                            t = Number(document.getElementById("numT").value);
                                        var valueT = 0;
                                        for (var i = 1; i < t; i++) {
                                            if (document.getElementById("t" + i) !== null)
                                                valueT += Number(document.getElementById("t" + i).innerHTML);

                                        }
                                        if (document.getElementById("amT") !== null)
                                            document.getElementById("amT").innerHTML = valueT + "đ";


                                        document.getElementById("amA").innerHTML = value + valueT + "đ";
                                        console.log(document.getElementById("pu").innerHTML);
                                        document.getElementById("amM").innerHTML = value + valueT  - Number(document.getElementById("pu").innerHTML) * 1000;
                                        document.getElementById("amM").innerHTML = document.getElementById("amM").innerHTML + "đ";
                                    </script>
                                    </body>
                                    </html>
