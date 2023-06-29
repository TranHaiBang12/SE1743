<%-- 
    Document   : mvRp
    Created on : Jun 28, 2023, 4:10:11 PM
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
                text-align: center;
                padding-top: 20px;
                font-size: 27px;
                font-weight: bold;
                margin-bottom: 40px;
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

            .ms{
                text-align: center;
                font-size: 20px;
                color: red;
            }

            .msT{
                text-align: center;
                font-size: 20px;
                color: red;
                margin-top: 20px;
            }

            .mvIN4{
                display: flex;
                margin-top: 20px;
                margin-left: 40px;
                background-color: white;
                padding-bottom: 20px;
                border-bottom: 3px solid black;
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

            .dteS{
                display: flex;
                justify-content: center;
                font-size: 19px;
            }

            .dteS div{
                margin-right: 15px;
            }

        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">THỐNG KÊ VỀ PHIM</div>
            <form action = "rpm" method = "post">
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
            <c:if test = "${requestScope.ms != null}">
                <div class = "ms">
                    ${requestScope.ms}
                </div>
            </c:if>
            <c:if test = "${requestScope.listM != null}">
                <div class = "dteS">
                    <div>Ngày: ${requestScope.start}</div>
                    <div>
                        -
                    </div>
                    <div>Ngày: ${requestScope.end}</div>
                </div>
                <c:if test = "${requestScope.msT != null}">
                    <div class = "msT">
                        ${requestScope.msT}
                    </div>
                </c:if>
                <c:if test = "${requestScope.msT == null}">
                    <div>
                        <c:forEach items = "${requestScope.listM}" var = "i">
                            <div class = "mvIN4">
                                <div class = "imGE"><img src = "${i.getImg()}"></div>
                                <div class = "in4">
                                    <div class = "mName">
                                        <div>${i.getMovName()}</div>
                                    </div>
                                    <div. class = "oIn4">
                                        <div>
                                            <div>Ngày khởi chiếu: <span class = "blk">${i.getStartDate()}</span></div>
                                            <div>Ngày dừng chiếu: <span class = "blk">${i.getEndDate()}</span></div>
                                        </div>
                                        <div>
                                            <div>Tổng đánh giá: <span class = "blk">${i.getSumRate()}</span></div><!-- <div></div> -->
                                            <div>Số lượng đánh giá: <span class = "blk">${i.getNoRate()}</span></div>
                                            <div>Trung bình: <span class = "blk">${i.getAvrRate()}</span></div>
                                        </div>
                                        <div>
                                            <div><span class = "rte">Số lượt đánh giá 5 sao: <span class = "blk">${i.getNoRate5()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${i.getpRate5()}%</span></span></div>
                                            <div><span class = "rte">Số lượt đánh giá 4 sao: <span class = "blk">${i.getNoRate4()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${i.getpRate5()}%</span></span></div>
                                            <div><span class = "rte">Số lượt đánh giá 3 sao: <span class = "blk">${i.getNoRate3()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${i.getpRate5()}%</span></span></div><!-- <div></div> -->
                                            <div><span class = "rte">Số lượt đánh giá 2 sao: <span class = "blk">${i.getNoRate2()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${i.getpRate5()}%</span></span></div>
                                            <div><span class = "rte">Số lượt đánh giá 1 sao: <span class = "blk">${i.getNoRate1()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${i.getpRate5()}%</span></span></div>

                                        </div>
                                        <div class = "btn">
                                            <a href = "rpmd?id=${i.getMovID()}&start=${requestScope.start}&end=${requestScope.end}"><input type ="button" value ="XEM CHI TIẾT"/></a>
                                        </div>
                                    </div.>
                                </div>

                            </div>
                        </div>

                    </c:forEach>
                </div>
            </c:if>
        </c:if>
    </div>
    <div id = "footer">
        <%@include file = "footer.jsp" %>
    </div>
</body>
</html>
