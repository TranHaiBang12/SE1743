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

            .imge img{
                width: 100%;
                height: 100%;
            }

            .img{
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
            .m1{
                margin-left: 40px;
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
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">ƯU ĐÃI</div>
            <c:if test = "${requestScope.ms != null}">
                <div class = "ms">
                    ${requestScope.ms}
                </div>
            </c:if>
            <c:if test = "${requestScope.e != null}">
                <div class = "detail">
                    <div class = "Sttle">${requestScope.e.getEventName()}</div>
                    <div class = "event">
                        <div class = "imge">
                            <img src ="${requestScope.e.getImg()}"/>
                        </div>
                        <div class = "in4">
                            <div class = "prt">
                                <span class = "blk">1. Thời gian áp dụng</span> Từ ${requestScope.e.getStartS()} đến hết ${requestScope.e.getEndS()}
                            </div>
                            <div class = "prt">
                                <div class = "blk">2. Nội dung ưu đãi</div>
                                <div>${requestScope.e.getEventContent()}</div>
                            </div>
                            <div class = "prt">
                                <div class = "blk">3. Điều kiện và điều khoản</div>
                                <div>- Áp dụng khi mua online hoặc offline trực tiếp tại rạp.</div>
                                <div>- Áp dụng tại các rạp:</div>

                                <c:forEach items = "${requestScope.e.getCin()}" var = "i">
                                    <div class = "m1">
                                        + Rạp ${i.getCinName()}
                                    </div>
                                </c:forEach>
                                <div>
                                    - Không áp dụng tách hoặc gộp giá trị hóa đơn dưới mọi hình thức để tham gia chương trình.
                                </div>
                                <c:if test = "${requestScope.e.getNumDateEx() != 0}">
                                    <div>
                                        - Mua sản phẩm ở rạp nào thì nhận quà ở rạp đó, quà có giá trị quy đổi trong ${requestScope.e.getNumDateEx()} ngày.
                                    </div>
                                </c:if>
                                <c:if test = "${requestScope.e.getNumDateEx() == 0}">
                                    <div>
                                        - Mua sản phẩm ở rạp nào thì nhận quà ở rạp đó, quà có giá trị quy đổi ngay khi tạo hóa đơn.
                                    </div>
                                </c:if>
                                <c:if test = "${requestScope.e.getApplyO() == 1}">
                                    <div>
                                        - Có thể kết hợp với các ưu đãi khác
                                    </div>
                                </c:if>
                                <c:if test = "${requestScope.e.getApplyO() == 0}">
                                    <div>
                                        - Không thể kết hợp với các ưu đãi khác
                                    </div>
                                </c:if>
                                <div class = "btS">
                                    <a href = "store"><button>XEM CHI TIẾT</button></a>
                                </div>
                            </div>
                        </div>
                    </div>
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
        </script>
    </body>
</html>
