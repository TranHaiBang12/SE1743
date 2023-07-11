<%-- 
    Document   : icR
    Created on : Jul 4, 2023, 7:25:31 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <link rel="stylesheet" href="style.css"/>
        <style>
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
            }

            .insider1{
                font-size: 20px;
                margin-left: 40px;
                padding-bottom: 10px;
            }

            .insider1 div{
                margin-top: 10px;
                padding-bottom: 10px;
            }

            .ttle{
                text-align: center;
                padding-top: 40px;
                font-size: 27px;
                font-weight: bold;
                margin-bottom: 40px;
            }
            
            .body{
                padding-left: 40px;
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


            .SSttle {
                font-size: 20px;
                margin-top: 10px;
                font-weight: bold;
                color: magenta;
            }

            .RATE{
                padding-top: 20px;
                margin-left: 40px;
                background-color: white;
                padding-bottom: 20px;
                border-bottom: 3px solid black;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">THỐNG KÊ VỀ LỢI NHUẬN</div>
            <c:if test = "${requestScope.check == null}">
                <form action = "itr" method = "post">
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
                </div><!-- comment -->
                <div class = "RATE">
                    <div class = "Sttle">1. DOANH THU </div>
                    <div class = "SSttle">a. Doanh Thu Tổng(Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "insider1">
                        <div>
                            <div>Doanh thu tổng: <span class = "rd">${requestScope.numIAT + requestScope.numIAF}đ</span>
                            </div>

                        </div>

                    </div>
                    <div class = "SSttle">b. Doanh Thu Vé(Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "insider1">
                        <div>

                            <div>Doanh thu tổng: <span class = "rd">${requestScope.numIAT}đ</span>, chiếm <span class = "rd">${requestScope.PCTAll}%</span>
                            </div>
                            <div>Doanh thu bán online: <span class = "rd">${requestScope.numOnlT}đ</span>, chiếm <span class = "rd">${requestScope.PCnumONLT}%</span> doanh thu vé
                            </div><!-- comment -->
                            <div>Doanh thu bán offline: <span class = "rd">${requestScope.numOffT}đ</span>, chiếm <span class = "rd">${requestScope.PCnumOFFT}%</span> doanh thu vé
                            </div>

                        </div>

                    </div>
                    <div class = "SSttle">c. Doanh Thu Đồ Ăn(Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "insider1">
                        <div>
                            <div>Doanh thu tổng: <span class = "rd">${requestScope.numIAF}đ</span>, chiếm <span class = "rd">${requestScope.PCFAll}%</span>
                            </div>
                            <div>Doanh thu bán online: <span class = "rd">${requestScope.numOnlF}đ</span>, chiếm <span class = "rd">${requestScope.PCnumONLF}%</span> doanh thu đồ ăn
                            </div><!-- comment -->
                            <div>Doanh thu bán offline: <span class = "rd">${requestScope.numOffF}đ</span>, chiếm <span class = "rd">${requestScope.PCnumOFFF}%</span> doanh thu đồ ăn
                            </div>

                        </div>

                    </div>
                </div>
                <div class = "RATE">
                    <div class = "Sttle">2. CHI PHÍ </div>
                    <div class = "insider1">
                        <div>
                            <div>Chi phí tổng: <span class = "rd">${costBuy + costFixed}đ</span>
                            </div>

                        </div>

                    </div>
                    <div class = "insider1">
                        <div>
                            <div>Chi phí mua thiết bị: <span class = "rd">${costBuy}đ</span>, chiếm <span class = "rd">${requestScope.PCBuy}%</span>
                            </div>

                        </div>

                    </div>
                    <div class = "insider1">
                        <div>

                            <div>Chi phí sửa chữa thiết bị: <span class = "rd">${requestScope.costFixed}đ</span>, chiếm <span class = "rd">${requestScope.PCFixed}%</span>
                            </div>


                        </div>

                    </div>

                </div>
                <div class = "RATE">
                    <div class = "Sttle">3. LỢI NHUẬN </div>
                    <div class = "insider1">
                        <div>
                            <c:if test = "${requestScope.numIAT + requestScope.numIAF - requestScope.costBuy - requestScope.costFixed >= 0}">
                            <div>Lợi nhuận: <span class = "rd">Lời ${requestScope.numIAT + requestScope.numIAF - requestScope.costBuy - requestScope.costFixed}đ</span>
                            </div>
                            </c:if>
                            <c:if test = "${requestScope.numIAT + requestScope.numIAF - requestScope.costBuy - requestScope.costFixed < 0}">
                                <div>Lợi nhuận: <span class = "rd">Lỗ ${requestScope.costBuy + requestScope.costFixed - requestScope.numIAT - requestScope.numIAF}đ</span>
                            </div>
                            </c:if>

                        </div>

                    </div>
          
                </div>
            </c:if>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
