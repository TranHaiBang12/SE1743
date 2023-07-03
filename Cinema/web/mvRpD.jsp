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
                margin-bottom: 10px
            }

            .insider1 div{
                margin-top: 10px;
                margin-bottom: 10px;
            }

            .insider2{
                font-size: 20px;
                margin-left: 40px;
            }

            .insider2 div{
                margin-top: 6px;
                padding-bottom: 10px;
            }


            .SSttle {
                font-size: 20px;
                margin-top: 10px;
                font-weight: bold;
            }

            .comment{
                border: 1px solid black;
                padding-left: 20px;
                padding-top: 10px;
                padding-bottom: 10px;
                display: flex;
                flex-direction: column;
                border-radius: 10px;
                margin-bottom: 20px;

            }

            .avt{
            }

            .avt img{
                margin-top:10px;
                width: 20px;
            }

            .insidecmt {
                display: flex;
            }

            .cmtIN4{
                display: flex;
                flex-direction: column;
                margin-left: 20px;
                font-size: 18px;

            }

            .cmtIN4 div{
            }

            .insidecmt{
                margin-top: 20px;
                border-bottom: 2px solid black;
                display: flex;
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
            }

            .active{
                text-decoration: none;
                border: 1px solid black;
                padding: 5px;
                color: white;
                border-radius: 18px;
                background-color: black;
            }

            .rating-box{
                display: flex;
            }

            .rating-box div{
                width: 15px;
                margin-right: 10px;
            }

            .rating-box img{
                width: 100%;
            }

            .b{
                filter: invert(75%) sepia(9%) saturate(35%) hue-rotate(31deg) brightness(97%) contrast(94%);
            }

            .ttype{
                margin-left: 40px;
            }

            .det{
                margin-left: 100px;
            }

            .btS button{
                font-size: 18px;
                padding: 5px;
                background-color: red;
                color: white;
                cursor: pointer;
            }

            .btR button{
                font-size: 18px;
                padding: 5px;
                background-color: red;
                color: white;
                cursor: pointer;
            }

            .btR{
                text-align: center;
                margin-top: 20px;
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
                            <div class = "btS">
                                <a href  = "detail?id=${requestScope.id}"><button type ="submit" value ="XEM CHI TIẾT">XEM CHI TIẾT</button></a>
                            </div>
                        </div.>
                    </div>
                </div>

                <div class = "RATE">
                    <div class = "Sttle">1. ĐÁNH GIÁ (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "SSttle">a. Đánh giá</div>
                    <div class = "insider1">

                        <div>
                            <div>Trong khoảng thời gian này có: <span class = "blk">${requestScope.mr.getSum()}</span>, chiếm <span  class = "blk">${requestScope.pcARate}%</span> tổng số đánh giá</div><!-- <div></div> -->
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
                    <c:set var="cnt" value="0"/>
                    <div class = "insider2">
                        <div>Trong khoảng thời gian này có <span class = "blk" id = "nm">${requestScope.mr.getSum()}</span> bình luận về bộ phim này, chiếm <span  class = "blk">${requestScope.pcARate}%</span> tổng số bình luận</div>
                        <input type ="text" id ="num" hidden value ="${requestScope.mr.getSum()}"/>
                        <div id ="comment" class = "comment">

                            <c:forEach items = "${requestScope.listPerPage}" var = "t">
                                <div class ="insidecmt">
                                    <div class = "avt">
                                        <img src ="images/avatarIcon.png" name ="avt"/>
                                    </div>
                                    <div class = "cmtIN4">
                                        <div class = "displayName">
                                            <div>
                                                Tài khoản: <span class = "rd">${t.getUserName()}</span> (Tên hiển thị: ${t.getDisplayName()}, ngày: ${t.getDate()}) 
                                            </div>
                                            <input type ="text" hidden id ="n${cnt}" value ="${t.getRate()}"/>
                                            <div class = "rating-box" id = "${cnt}">
                                                <div><image class ="b" id ="${cnt}1" src ="images/star-icon.svg"/></div>
                                                <div><image class ="b" id ="${cnt}2" src ="images/star-icon.svg"/></div>
                                                <div><image class ="b" id ="${cnt}3" src ="images/star-icon.svg"/></div>
                                                <div><image class ="b" id ="${cnt}4" src ="images/star-icon.svg"/></div><!-- comment -->
                                                <div><image class ="b" id ="${cnt}5" src ="images/star-icon.svg"/></div>
                                            </div>
                                            <c:set var="cnt" value="${cnt+1}"/>
                                        </div>
                                        <div class = "CMT">
                                            ${t.getComments()}
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class = "pagination">
                                <a href ="rpmd?page=${(page - 1) < 1?(1):(page-1)}&id=${requestScope.id}&start=${requestScope.start}&end=${requestScope.end}"><</a>
                                <c:forEach begin = "${1}" end = "${totalPage}" var = "i">
                                    <a class ="${i == page ? "active":"noActive"}" href ="rpmd?page=${i}&id=${requestScope.id}&start=${requestScope.start}&end=${requestScope.end}">${i}</a>
                                </c:forEach>
                                <a href ="rpmd?page=${(page + 1) > totalPage?(1):(page+1)}&id=${requestScope.id}&start=${requestScope.start}&end=${requestScope.end}">></a>
                            </div>
                        </div>
                    </div>
                </div>


                <div class = "TKET">
                    <div class = "Sttle">2. VÉ (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "insider1">
                        <div>
                            Trong khoảng thời gian này đã bán được <span class = "blk">${requestScope.numTick}</span> vé của bộ phim này, chiếm <span  class = "blk">${requestScope.pcATick}%</span> tổng số vé
                        </div>
                        <div>
                            Có <span class = "blk">${requestScope.listTT.size()}</span> loại vé:
                            <c:forEach items = "${requestScope.listTT}" var = "t">
                                <div class = "ttype">
                                    <c:if test = "${t == 'Thường'}">
                                        + Vé ${t}: <span class = "blk">${requestScope.mt.nm}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.mt.pcnm}%</span></span>
                                    </c:if>
                                    <c:if test = "${t == 'VIP'}">
                                        + Vé ${t}: <span class = "blk">${requestScope.mt.vp}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.mt.pcvp}%</span></span>
                                    </c:if>
                                    <c:if test = "${t == 'Đôi'}">
                                        + Vé ${t}: <span class = "blk">${requestScope.mt.vt}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${requestScope.mt.pcvt}%</span></span>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </div>
                        <div>
                            Có <span class = "blk">${requestScope.listMF.size()}</span> thể loại:
                            <c:forEach items = "${requestScope.listMF}" var = "t">
                                <div class = "ttype">
                                    + Thể loại ${t.getFormName()}: <span class = "blk">${t.getNo()}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${t.getPc()}%</span></span>
                                </div>
                            </c:forEach>
                        </div>
                        <c:forEach items = "${requestScope.listTID}" var = "t">
                            <div>
                                Ngày ${t.getdS()}: <span class = "blk">${t.getNo()}</span> vé được bán ra, chiếm <span class = "blk">${t.getPc()}%</span> vé được bán ra trong khoảng thời gian này <span class = "det"><a href = "ordr?id=${requestScope.id}&date=${t.getdS()}">XEM CHI TIẾT</a></span>
                                <c:forEach items = "${t.getTkd()}" var = "p">
                                    <div class = "ttype">
                                        + Vé ${p.getdS()}: <span class = "blk">${p.getNo()}</span>. <span class = "rte">Tỉ lệ: <span class = "blk">${p.getPc()}%</span></span>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class = "btR">
                <a href  = "rpm"><button type ="submit" value ="VỀ TRANG THỐNG KÊ PHIM">VỀ TRANG THỐNG KÊ PHIM</button></a>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type="text/javascript">
            var a = document.getElementById("nm").innerHTML;
            console.log(${cnt});
            for (var i = 0; i < ${cnt}; i++) {
                var star = document.getElementById("n" + i).value;
                for (var j = 1; j <= star; j++) {
                    console.log(star);
                    document.getElementById(String(i) + String(j)).style.filter = 'invert(82%) sepia(44%) saturate(769%) hue-rotate(11deg) brightness(113%) contrast(86%)';
                }
            }
            console.log(document.getElementById("n1").value);
//            for (var i = 0; i < Number(a); i++) {
//                console.log(document.getElementById("n" + a).value);
//            }
//            for (var i = 0; i < Number(a); i++) {
//                for (var j = 0; j < Number(a); j++) {
//                    var t = j + a;
//                    console.log(t);
//                    document.getElementById(t).style.filter = 'invert(82%) sepia(44%) saturate(769%) hue-rotate(11deg) brightness(113%) contrast(86%)';
//                }
//            }

        </script>
    </body>
</html>
