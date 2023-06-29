<%-- 
    Document   : mvRpD
    Created on : Jun 29, 2023, 12:33:24 PM
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

            .RATE{
                margin-top: 20px;
                margin-left: 40px;
                background-color: white;
                padding-bottom: 20px;
                border-bottom: 3px solid black;
            }

            .TKET{
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
            }

            .insider1 div{
                margin-top: 10px;
                margin-bottom: 10px;
            }

            .SSttle {
                font-size: 20px;
                margin-top: 10px;
                font-weight: bold;
            }


        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">THỐNG KÊ VỀ PHIM</div>
            <div class = "dteS">
                <div>Ngày: <span class = "rd">${requestScope.start}</span></div>
                <div>
                    -
                </div>
                <div>Ngày: <span class = "rd">${requestScope.end}</span></div>
            </div><!-- comment -->
            <div>
                <div class = "mvIN4">
                    <div class = "imGE"><img src = "${requestScope.m.getImg()}"></div>
                    <div class = "in4">
                        <div class = "mName">
                            <div>${requestScope.m.getMovName()}</div>
                        </div>
                        <div. class = "oIn4">
                            <div>
                                <div>Ngày khởi chiếu: <span class = "blk">${requestScope.m.getStartDate()}</span></div>
                                <div>Ngày dừng chiếu: <span class = "blk">${requestScope.m.getEndDate()}</span></div>
                            </div>
                            <div>
                                <div>Tổng đánh giá: <span class = "blk">${requestScope.m.getSumRate()}</span></div><!-- <div></div> -->
                                <div>Số lượng đánh giá: <span class = "blk">${requestScope.m.getNoRate()}</span></div>
                                <div>Trung bình: <span class = "blk">${requestScope.m.getAvrRate()}</span></div>
                            </div>
                            <div>
                                <div><span class = "rte">Số lượt đánh giá 5 sao: <span class = "blk">${requestScope.m.getNoRate5()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.m.getpRate5()}%</span></span></div>
                                <div><span class = "rte">Số lượt đánh giá 4 sao: <span class = "blk">${requestScope.m.getNoRate4()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.m.getpRate4()}%</span></span></div>
                                <div><span class = "rte">Số lượt đánh giá 3 sao: <span class = "blk">${requestScope.m.getNoRate3()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.m.getpRate3()}%</span></span></div><!-- <div></div> -->
                                <div><span class = "rte">Số lượt đánh giá 2 sao: <span class = "blk">${requestScope.m.getNoRate2()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.m.getpRate2()}%</span></span></div>
                                <div><span class = "rte">Số lượt đánh giá 1 sao: <span class = "blk">${requestScope.m.getNoRate1()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.m.getpRate1()}%</span></span></div>

                            </div>
                        </div.>
                    </div>
                </div>

                <div class = "RATE">
                    <div class = "Sttle">1. ĐÁNH GIÁ (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "SSttle">a. Đánh giá</div>
                    <div class = "insider1">

                        <div>
                            <div>Tổng đánh giá: <span class = "blk">${requestScope.mr.getSum()}</span></div><!-- <div></div> -->
                            <div>Số lượng đánh giá: <span class = "blk">${requestScope.mr.getNoD()}</span></div>
                            <div>Trung bình: <span class = "blk">${requestScope.mr.getAvr()}</span></div>
                        </div>
                        <div>
                            <div><span class = "rte">Số lượt đánh giá 5 sao: <span class = "blk">${requestScope.mr.getNo5()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.mr.getPc5()}%</span></span></div>
                            <div><span class = "rte">Số lượt đánh giá 4 sao: <span class = "blk">${requestScope.mr.getNo4()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.mr.getPc4()}%</span></span></div>
                            <div><span class = "rte">Số lượt đánh giá 3 sao: <span class = "blk">${requestScope.mr.getNo3()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.mr.getPc3()}%</span></span></div><!-- <div></div> -->
                            <div><span class = "rte">Số lượt đánh giá 2 sao: <span class = "blk">${requestScope.mr.getNo2()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.mr.getPc2()}%</span></span></div>
                            <div><span class = "rte">Số lượt đánh giá 1 sao: <span class = "blk">${requestScope.mr.getNo1()}</span></span><span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.mr.getPc1()}%</span></span></div>

                        </div>
                    </div>
                    <div class = "SSttle">b. Bình luận</div>
                </div>


                <div class = "TKET">
                    <div class = "Sttle">2. VÉ (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
