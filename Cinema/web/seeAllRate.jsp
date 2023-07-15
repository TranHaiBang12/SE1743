<%-- 
    Document   : seeAllRate
    Created on : Jul 4, 2023, 8:59:06 AM
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

            .RATE{
                padding-top: 20px;
                margin-left: 40px;
                background-color: white;
                padding-bottom: 20px;
                border-bottom: 3px solid black;
            }

            .Sttle{
                font-weight: bold;
                font-size: 20px;
                padding-top: 20px;
                padding-bottom: 20px;
                color: brown;
                text-shadow: 10px 10px 5px #666666;
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

            .cmtIN4 {
                margin-bottom: 15px;
            }
            .cmtIN4 div{
                margin-bottom: 15px;
            }

            .rd{
                color: red;
            }

            button{
                padding: 5px;
                cursor: pointer;
                font-weight: bold;
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
            
            .ms{
                font-size: 25px;
                margin-left: 20px;
                margin-top: 20px;
                padding-bottom: 20px;
                color: red;
            }
            
            .body{
                padding-left: 50px;
                padding-right: 100px;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "RATE">
                <div class = "Sttle">ĐÁNH GIÁ </div>

                <c:set var="cnt" value="0"/>
                <div class = "insider2">
                    <input type ="text" hidden id ="type" value ="${requestScope.type}"/>
                    <span class = "blk" hidden id = "nm">${requestScope.mr.getSum()}</span> 
                    <input type ="text" id ="num" hidden value ="${requestScope.mr.getSum()}"/>

                    <div id ="comment" class = "comment">
                        <div class = "choice">
                            <div id = "active" class = "al" onclick = "bActive('active')">Tất cả bình luận</div>
                            <div id ="tkEtChoice" class = "tkEtChoice" onclick = "bActive('tkEtChoice')">Bình luận chưa duyệt</div>
                            <div id ="foodChoi" class = "foodChoi" onclick = "bActive('foodChoi')">Bình luận được duyệt</div>

                        </div>
                        <c:if test = "${requestScope.ms == null}">
                            <c:forEach items = "${requestScope.listPerPage}" var = "t">
                                <div class ="insidecmt">
                                    <div class = "avt">
                                        <img src ="images/avatarIcon.png" name ="avt"/>
                                    </div>
                                    <div class = "cmtIN4">
                                        <div class = "displayName">
                                            <div>
                                                Tài khoản: <span class = "rd">${t.getUserName()}</span> (Tên hiển thị: ${t.getDisplayName()}, ngày: ${t.getDate()}, tình trạng: <span class = "rd">${t.getStatus()}</span>) <c:if test = "${t != null && t.getStatus() != null && t.getStatus() == 'Chờ duyệt'}"><button onclick = "check('${t.getUserName()}', '${t.getMovID()}')">DUYỆT</button></c:if> <button onclick = "dlt('${t.getUserName()}', '${t.getMovID()}')">XÓA</button>
                                                </div>
                                                <div>
                                                    Phim: ${t.getMovName()}
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
                                <a href ="sr?page=${(page - 1) < 1?(1):(page-1)}&type=${requestScope.type}"><</a>
                                <c:forEach begin = "${1}" end = "${totalPage}" var = "i">
                                    <a class ="${i == page ? "active":"noActive"}" href ="sr?page=${i}&type=${requestScope.type}">${i}</a>
                                </c:forEach>
                                <a href ="sr?page=${(page + 1) > totalPage?(1):(page+1)}&type=${requestScope.type}">></a>
                            </div>
                        </c:if>
                        <c:if test = "${requestScope.ms != null}">
                            <div class="ms">
                                ${requestScope.ms}
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            var a = document.getElementById("nm").innerHTML;
            console.log(${cnt});
            for (var i = 0; i < ${cnt}; i++) {
                var star = document.getElementById("n" + i).value;
                for (var j = 1; j <= star; j++) {
                    console.log(star);
                    document.getElementById(String(i) + String(j)).style.filter = 'invert(82%) sepia(44%) saturate(769%) hue-rotate(11deg) brightness(113%) contrast(86%)';
                }
            }

            function dlt(user, mov) {
                if (confirm("Bạn có chắc chắn muốn xóa bình luận của tài khoản " + user + " ở bộ phim " + mov)) {
                    window.location = "dlr?user=" + user + "&mov=" + mov;
                }
            }

            function check(user, mov) {
                if (confirm("Bạn có chắc chắn muốn duyêt bình luận của tài khoản " + user + " ở bộ phim " + mov)) {
                    window.location = "updr?user=" + user + "&mov=" + mov;
                }
            }
            if (String(document.getElementById("type").value) === "1") {
                console.log(1);
                document.getElementById("active").style.color = 'white';
                document.getElementById("active").style.backgroundColor = 'black';
                document.getElementById("foodChoi").style.color = 'black';
                document.getElementById("foodChoi").style.backgroundColor = 'white';
                document.getElementById("tkEtChoice").style.color = 'black';
                document.getElementById("tkEtChoice").style.backgroundColor = 'white';
            } else if (String(document.getElementById("type").value) === "2") {
                console.log(2);
                document.getElementById("foodChoi").style.color = 'white';
                document.getElementById("foodChoi").style.backgroundColor = 'black';
                document.getElementById("active").style.color = 'black';
                document.getElementById("active").style.backgroundColor = 'white';
                document.getElementById("tkEtChoice").style.color = 'black';
                document.getElementById("tkEtChoice").style.backgroundColor = 'white';
            } else if (String(document.getElementById("type").value) === "3") {
                console.log(3);
                document.getElementById("tkEtChoice").style.color = 'white';
                document.getElementById("tkEtChoice").style.backgroundColor = 'black';
                document.getElementById("active").style.color = 'black';
                document.getElementById("active").style.backgroundColor = 'white';
                document.getElementById("foodChoi").style.color = 'black';
                document.getElementById("foodChoi").style.backgroundColor = 'white';
            }


            function bActive(id) {

                if (id === "tkEtChoice") {
                    document.getElementById(id).style.color = 'white';
                    document.getElementById(id).style.backgroundColor = 'black';
                    document.getElementById("active").style.color = 'black';
                    document.getElementById("active").style.backgroundColor = 'white';
                    document.getElementById("foodChoi").style.color = 'black';
                    document.getElementById("foodChoi").style.backgroundColor = 'white';
                    window.location = "sr?type=3";

                } else if (id === "foodChoi") {
                    document.getElementById(id).style.color = 'white';
                    document.getElementById(id).style.backgroundColor = 'black';
                    document.getElementById("active").style.color = 'black';
                    document.getElementById("active").style.backgroundColor = 'white';
                    document.getElementById("tkEtChoice").style.color = 'black';
                    document.getElementById("tkEtChoice").style.backgroundColor = 'white';
                    window.location = "sr?type=2";

                } else {
                    document.getElementById(id).style.color = 'white';
                    document.getElementById(id).style.backgroundColor = 'black';
                    document.getElementById("foodChoi").style.color = 'black';
                    document.getElementById("foodChoi").style.backgroundColor = 'white';
                    document.getElementById("tkEtChoice").style.color = 'black';
                    document.getElementById("tkEtChoice").style.backgroundColor = 'white';
                    window.location = "sr?type=1";
                }
            }

        </script>
    </body>
</html>
