<%-- 
    Document   : fdRp
    Created on : Jul 2, 2023, 2:19:10 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css"/>
        <style>
            .ttle{
                text-align: center;
                padding-top: 20px;
                font-size: 27px;
                font-weight: bold;
                margin-bottom: 40px;
            }

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
                margin-left: 40px;
                margin-top: 20px;
            }

            .mvIN4{
                display: flex;
                margin-top: 20px;
                margin-left: 40px;
                background-color: white;
                padding-bottom: 20px;
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

            .btS button{
                font-size: 18px;
                padding: 5px;
                background-color: red;
                color: white;
                cursor: pointer;
            }

            .insider1{
                font-size: 20px;
                margin-left: 80px;
                margin-bottom: 10px
            }

            .insider1 div{
                margin-top: 10px;
                margin-bottom: 10px;
            }

            .TKET{
                margin-top: 20px;
                margin-left: 40px;
                background-color: white;
                padding-bottom: 20px;
                border-bottom: 3px solid black;
            }

            .SSttle {
                font-size: 20px;
                margin-top: 10px;
                font-weight: bold;
                margin-left: 40px;
                color: deeppink;
            }

            .ttype2{
                margin-left: 120px;
            }
            .pagination{
                text-align: center;
                padding-bottom: 15px;
                padding-top: 20px;
            }

            .pagination a{
                text-decoration: none;

            }

            .noActive{
                text-decoration: none;
                color: black;
                border: 1px solid black;
                padding: 5px;
                border-radius: 18px;
                cursor: pointer;
            }

            .active{
                text-decoration: none;
                border: 1px solid black;
                padding: 5px;
                color: white;
                border-radius: 18px;
                background-color: black;
                cursor: pointer;
            }
            
            .k{
                cursor: pointer;
            }

        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div><!-- comment -->
        <div class = "body">
            <div class = "ttle">THỐNG KÊ VỀ ĐỒ ĂN</div>
            <div class = "dteS">
                <div>Ngày: <span class = "rd">${requestScope.start}</span></div>
                <div>
                    -
                </div>
                <div>Ngày: <span class = "rd">${requestScope.end}</span></div>
            </div><!-- comment -->
            <div class = "TKET">
                <div class = "Sttle">1. TỔNG QUÁT (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                <div class = "mvIN4">
                    <div class = "imGE"><img src = "images/foodIcon.png"></div>
                    <div class = "in4">

                        <div. class = "oIn4">

                            <div>
                                <div>Tổng số món ăn được bán: <span class = "blk">${requestScope.numFood}</span>, chiếm <span  class = "blk">${requestScope.pcF}%</span> tổng số món ăn được bán từ trước đến nay</div><!-- <div></div> -->
                            </div>
                            <div class = "btS">
                                <a href  = "store"><button type ="submit" value ="XEM CHI TIẾT">XEM TẤT CẢ ĐỒ ĂN </button></a>
                            </div>

                        </div.>
                    </div>
                </div>

            </div>

            <div class = "TKET">
                <div class = "Sttle">2. CHI TIẾT (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>

                <div class = "SSttle">a. Tỉ lệ các loại đồ ăn:</div>
                <div class = "insider1">
                    <c:forEach items = "${requestScope.listTID}" var = "t">
                        <div>
                            + ${t.getdS()}: <span class = "blk">${t.getNo()}</span> suất đồ ăn được bán ra, chiếm <span class = "blk">${t.getPc()}%</span> suất đồ ăn được bán ra trong khoảng thời gian này 

                        </div>
                    </c:forEach>
                </div>

                <div class = "SSttle">b. Tỉ lệ suất đồ ăn bán giữa các rạp:</div>
                <div class = "insider1">

                    <c:forEach items = "${requestScope.listTID1}" var = "t">
                        <div>
                            + <span class = "blk">${t.getdS()}</span>: <span class = "blk">${t.getNo()}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${t.getPc()}%</span></span>
                        </div>
                    </c:forEach>
                </div>

                <div class = "SSttle">c. Tỉ lệ đồ ăn bán online và bán trực tiếp:</div>
                <div class = "insider1">
                    <div>
                        + <span class = "blk">${requestScope.tONL.getdS()}</span>: <span class = "blk">${requestScope.tONL.getNo()}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.tONL.getPc()}%</span></span>
                    </div>
                    <div>
                        + <span class = "blk">${requestScope.tOFF.getdS()}</span>: <span class = "blk">${requestScope.tOFF.getNo()}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.tOFF.getPc()}%</span></span>
                    </div>
                </div>
            </div>
            <div class = "TKET">
                <div class = "Sttle">3. CHI TIẾT CÁC NGÀY BÁN ĐỒ ĂN (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                <div class = "insider1">
                    <c:forEach items = "${requestScope.listTID2}" var = "t">
                        <div>
                            + Ngày ${t.getdS()}: <span class = "blk">${t.getNo()}</span> suất đồ ăn được bán ra, chiếm <span class = "blk">${t.getPc()}%</span> suất đồ ăn được bán ra trong khoảng thời gian này <span class = "det"><a href = "ordr?id=0&date=${t.getdS()}&t=1">XEM CHI TIẾT</a></span>

                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class = "TKET">
                <div class = "Sttle">4. CHI TIẾT ĐỒ ĂN (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                <div class = "insider1">
                    <div>
                        <c:forEach items = "${requestScope.listPerPage}" var = "i">
                            <div class = "mvIN4">
                                <div class = "imGE"><img src = "${i.getImg()}"></div>
                                <div class = "in4">
                                    <div class = "mName">
                                        <div>${i.getFoodDescript()}</div>
                                    </div>
                                    <div class = "oIn4">
                                        <div>
                                            <div>Thể loại: <span class = "blk">${i.getTypeName()}</span></div>
                                        </div>

                                        <div class = "btn">
                                            <a href = "viewf?id=${i.getProductCode()}"><input type ="button" value ="XEM CHI TIẾT"/></a>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </c:forEach>
                        <div class = "pagination">
                            <form id ="frm" action = "rpp" method = "post">
                                <input type ="text" hidden name ="start" value ="${requestScope.startR}"/>
                                <input type ="text" hidden name ="end" value ="${requestScope.endR}"/>
                                <input type ="text" hidden name ="type" value ="${requestScope.type}"/>
                                <input type ="text" hidden name ="page" id ="page" value ="${requestScope.page}"/>
                                <a class ="k" onclick = "dpA('${(page - 1) < 1?(1):(page-1)}')"><</a>
                                <c:forEach begin = "${1}" end = "${totalPage}" var = "i">
                                    <a class ="${i == page ? "active":"noActive"}" onclick = "dpA('${i}')">${i}</a>
                                </c:forEach>
                                <a class ="k" onclick = "dpA('${(page + 1) > totalPage?(1):(page+1)}')">></a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type="text/javascript">
            function dpA(id) {
                document.getElementById("page").value = id;
                document.getElementById("frm").submit();
            }
        </script>
    </body>
</html>
