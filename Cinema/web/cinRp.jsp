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
        <link rel="stylesheet" href="style.css?version=1"/>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <style>
            .body{
                padding-left: 40px;
            }

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
                flex-direction: column;
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


            .oDate{
                font-size: 25px;
                font-weight: bold;
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



            #tt{
                display: none;
            }

            .ms{
                text-align: center;
                margin-top: 40px;
                padding-bottom: 40px;
                font-size: 30px;
                color: red;
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

            .rd{
                color: red;
            }

            table{

                width: 100%;
                margin-top: 20px;
                border: 1px solid black;
                margin-bottom: 20px;
                margin: 0 auto;
                margin-left: 20px;
                margin-right: 50px;
            }

            body{
                overflow-x: hidden;
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

            td img{
                width: 100px;
            }

            .ttle{
                text-align: center;
                font-weight: bold;
                margin-bottom: 20px;
                font-size: 25px;
                padding-top: 40px;
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

            td a{
                text-decoration: none;
                color: black;
            }

            .mn {
                font-size: 20px;
                text-align: center;
            }

            .mn select {
                font-size: 20px;
            }

            .SSttle {
                font-size: 20px;
                margin-top: 10px;
                font-weight: bold;
                margin-left: 40px;
                color: deeppink;
            }

            .m1{
                margin-left: 40px;
            }

            .m2{
                margin-left: 60px;
            }

            .m3{
                margin-left: 100px;
            }

            .hl1{
                font-weight: bold;
                font-size: 22px;
            }

            .m3{
                width: 800px;
            }
            .dp{
                display: flex;
                align-items: center;
            }

            .btS button{
                font-size: 18px;
                padding: 5px;
                background-color: red;
                color: white;
                cursor: pointer;
            }

            .m12{
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">THỐNG KÊ VỀ RẠP</div>
            <c:if test = "${requestScope.check == null}">
                <form action = "cnrp" method = "post">
                    <div class = "search">
                        <div>
                            Ngày bắt đầu: <input type ="date" required name ="start"/>
                        </div>

                        <div>
                            Ngày kết thúc: <input type ="date" required name ="end"/>
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
                </div>
                <div class = "TKET">
                    <div class = "Sttle">1. LỢI NHUẬN (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "SSttle">a. Doanh thu</div>
                    <div class = "mvIN4">
                        <div class = "in4">
                            <div class = "oIn4">
                                <div>
                                    <div><span class = "hl1">Doanh thu tổng:</span> <span class = "rd">${requestScope.TIDonl.getNo() + requestScope.TIDonl.getNo()}đ</span>
                                    </div>
                                    <div class = "m1">
                                        <div>
                                            <span class = "hl1">+ ${requestScope.TIDonl.getdS()}:</span> <span class = "rd">${requestScope.TIDonl.getNo()}đ</span>, chiếm <span class = "rd">${requestScope.TIDonl.getPc()}% </span> doanh thu tổng
                                        </div>
                                        <c:forEach items = "${requestScope.listTID1}" var = "i">
                                            <div class = "m2">
                                                <span class = "hl1">+ ${i.getdS()}:</span> <span class = "rd">${i.getNo()}đ</span>, chiếm <span class = "rd">${i.getPc()}% </span> doanh thu website
                                            </div>

                                            <c:forEach items = "${i.getTkd()}" var = "k">
                                                <div class = "dp">
                                                    <div class = "m3">
                                                        ${k.getdS()}: <span class = "rd">${k.getNo()}đ</span>, chiếm <span class = "rd">${k.getPc()}% </span> doanh thu ${i.getdS()}
                                                    </div>
                                                    <div class = "btS">
                                                        <a href = "rpp?type=${k.getType()}&start=${requestScope.startR}&end=${requestScope.endR}&id=${k.getID()}"><button>XEM CHI TIẾT</button></a>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:forEach>


                                        <div>
                                            <span class = "hl1">+ ${requestScope.TIDoff.getdS()}:</span> <span class = "rd">${requestScope.TIDoff.getNo()}đ</span>, chiếm <span class = "rd">${requestScope.TIDoff.getPc()}% </span> doanh thu tổng
                                        </div>
                                        <c:forEach items = "${requestScope.listTID2}" var = "i">
                                            <div class = "m2">
                                                <span class = "hl1">+ ${i.getdS()}:</span> <span class = "rd">${i.getNo()}đ</span>, chiếm <span class = "rd">${i.getPc()}% </span> doanh thu trực tiếp

                                            </div>

                                            <c:forEach items = "${i.getTkd()}" var = "k">
                                                <div class = "dp">
                                                    <div class = "m3">
                                                        ${k.getdS()}: <span class = "rd">${k.getNo()}đ</span>, chiếm <span class = "rd">${k.getPc()}% </span> doanh thu ${i.getdS()}
                                                    </div>
                                                    <div class = "btS">
                                                        <a href = "rpp?type=${k.getType()}&start=${requestScope.startR}&end=${requestScope.endR}&id=${k.getID()}"><button>XEM CHI TIẾT</button></a>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:forEach>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class = "SSttle">b. Chi phí</div> 
                    <div class = "mvIN4">
                        <div class = "in4">
                            <div class = "oIn4">
                                <div>
                                    <div><span class = "hl1">Chi phí tổng: </span><span class = "rd">${requestScope.costImpD + requestScope.costImpE}đ</span>
                                    </div>
                                    <div class = "m1">
                                        <div><span class = "m1"><span class = "hl1">+ Số thiết bị nhập vào: </span></span><span class = "rd">${requestScope.numD}</span>
                                        </div>
                                        <div class = "m3">
                                            <div class = "btS">
                                                <a href = "dvd"><button>XEM CHI TIẾT</button></a>
                                            </div>
                                        </div>
                                        <c:forEach items = "${requestScope.listTID6}" var = "k">
                                            <div class = "dp">
                                                <div class = "m3">
                                                    ${k.getdS()}: <span class = "rd">${k.getNo()}</span>, chiếm <span class = "rd">${k.getPc()}% </span> doanh thu chi phí nhập thiết bị
                                                </div>
                                                <div class = "btS">
                                                    <a href = "dvd?cin=${k.getID()}"><button>XEM CHI TIẾT</button></a>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <div><span class = "m1"><span class = "hl1">+ Chi phí nhập thiết bị: </span></span><span class = "rd">${requestScope.costImpD}đ</span>
                                        </div>
                                        <div class = "m3">
                                            <div class = "btS">
                                                <a href = "dvd"><button>XEM CHI TIẾT</button></a>
                                            </div>
                                        </div>
                                        <c:forEach items = "${requestScope.listTID4}" var = "k">
                                            <div class = "dp">
                                                <div class = "m3">
                                                    ${k.getdS()}: <span class = "rd">${k.getNo()}đ</span>, chiếm <span class = "rd">${k.getPc()}% </span> doanh thu chi phí nhập thiết bị
                                                </div>
                                                <div class = "btS">
                                                    <a href = "dvd?cin=${k.getID()}"><button>XEM CHI TIẾT</button></a>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <div>
                                            <div><span class = "m2"><span class = "hl1">+ Số thiết bị sửa chữa: </span></span><span class = "rd">${requestScope.numE}</span>
                                            </div>
                                            <div class = "m3">
                                                <div class = "btS">
                                                    <a href = "dvs"><button>XEM CHI TIẾT</button></a>
                                                </div>
                                            </div>
                                            <c:forEach items = "${requestScope.listTID7}" var = "k">
                                                <div class = "dp">
                                                    <div class = "m3">
                                                        ${k.getdS()}: <span class = "rd">${k.getNo()}</span>, chiếm <span class = "rd">${k.getPc()}% </span> doanh thu chi phí nhập thiết bị
                                                    </div>
                                                    <div class = "btS">
                                                        <a href = "dvs"><button>XEM CHI TIẾT</button></a>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                            <div><span class = "m2"><span class = "hl1">+ Chi phí sửa chữa thiết bị </span></span><span class = "rd">${requestScope.costImpE}đ</span>
                                            </div>
                                            <div class = "m3">
                                                <div class = "btS">
                                                    <a href = "dvs"><button>XEM CHI TIẾT</button></a>
                                                </div>
                                            </div>
                                            <c:forEach items = "${requestScope.listTID5}" var = "k">
                                                <div class = "dp">
                                                    <div class = "m3">
                                                        ${k.getdS()}: <span class = "rd">${k.getNo()}đ</span>, chiếm <span class = "rd">${k.getPc()}% </span> doanh thu chi phí sửa chữa thiết bị
                                                    </div>
                                                    <div class = "btS">
                                                        <a href = "dvs"><button>XEM CHI TIẾT</button></a>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class = "SSttle">c. Lợi nhuận</div> 
                    <div class = "mvIN4">
                        <div class = "in4">
                            <div class = "oIn4">
                                <div>
                                    <div><span class = "hl1">Chi phí tổng: </span><span class = "rd">
                                            <c:if test = "${requestScope.TIDonl.getNo() + requestScope.TIDonl.getNo() - requestScope.costImpD - requestScope.costImpE >= 0}">
                                                Lời: ${requestScope.TIDonl.getNo() + requestScope.TIDonl.getNo() - requestScope.costImpD - requestScope.costImpE}đ
                                            </c:if>
                                            <c:if test = "${requestScope.costImpD + requestScope.costImpE - requestScope.TIDonl.getNo() - requestScope.TIDonl.getNo() >= 0}">
                                                Lỗ: ${requestScope.costImpD + requestScope.costImpE - requestScope.TIDonl.getNo() - requestScope.TIDonl.getNo()}đ
                                            </c:if>
                                        </span>
                                    </div>
                                    <div class = "m1">
                                        <div class = "m3">
                                            <div class = "btS">
                                                <a href = "itr"><button>XEM CHI TIẾT</button></a>
                                            </div>
                                        </div>
                                        <c:forEach items = "${requestScope.listTID8}" var = "k">
                                            <div class = "dp">
                                                <div class = "m3">
                                                    ${k.getdS()}: <span class = "rd">${k.getNo()}</span>, chiếm <span class = "rd">${k.getPc()}% </span>
                                                </div>
                                                <div class = "btS">
                                                    <a href = "itr?start=${requestScope.startR}&end=${requestScope.endR}&cin=${k.getID()}"><button>XEM CHI TIẾT</button></a>
                                                </div>
                                            </div>
                                        </c:forEach>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class = "Sttle">2. NHÂN VIÊN (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "mvIN4">
                        <div class = "in4">
                            <div class = "oIn4">
                                <div class = "m1">
                                    <div class = "m3">
                                        <div class = "btS">
                                            <a href = "liste"><button>XEM CHI TIẾT</button></a>
                                        </div>
                                    </div>
                                    <c:forEach items = "${requestScope.listTID9}" var = "k">
                                        <div class = "dp">
                                            <div class = "m3">
                                                ${k.getdS()}: <span class = "rd">${k.getNo()}</span>, chiếm <span class = "rd">${k.getPc()}% </span>
                                            </div>
                                            <div class = "btS">
                                                <a href = "liste?start=${requestScope.startR}&end=${requestScope.endR}&cin=${k.getID()}"><button>XEM CHI TIẾT</button></a>
                                            </div>
                                        </div>
                                    </c:forEach>


                                </div>
                            </div>
                        </div>
                    </div>

                    <div class = "Sttle">3. PHIM (Ngày ${requestScope.start} - Ngày ${requestScope.end})</div>
                    <div class = "mvIN4">
                        <div class = "in4">
                            <div class = "oIn4">
                                <div class = "m1">
                                    <div class = "m3">
                                        <div class = "btS">
                                            <a href = "rpm"><button>XEM CHI TIẾT</button></a>
                                        </div>
                                    </div>
                                    <c:forEach items = "${requestScope.listTID10}" var = "k">
                                        <div class = "dp">
                                            <div class = "m3">
                                                ${k.getdS()}: <span class = "rd">${k.getNo()}</span>, chiếm <span class = "rd">${k.getPc()}% </span>
                                            </div>
                                            <div class = "btS">
                                                <a href = "rpm?start=${requestScope.startR}&end=${requestScope.endR}&cin=${k.getID()}"><button>XEM CHI TIẾT</button></a>
                                            </div>
                                        </div>
                                    </c:forEach>


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

    </body>
</html>
