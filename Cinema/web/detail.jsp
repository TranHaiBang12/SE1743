<%-- 
    Document   : detail
    Created on : May 30, 2023, 8:42:22 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Locale"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nội Dung Phim</title>
        <link rel="stylesheet" href="style.css"/>
        <style>
            .body{
                background-color: white;
                padding-left: 100px;
                padding-right: 100px;
                border-bottom: 2px dashed red;
            }

            .bodyTitle{
                color: black;
                font-size: 40px;
                border-bottom: 1px solid black;
                padding: 15px;
            }


            .bodyContent{
                display: flex;
                padding-top: 50px;
                padding-bottom: 50px;
                justify-content: flex-start;
            }

            .bodyContent .content{
                color: black;
                font-size: 20px;

            }

            .bodyContent .content span{
                font-weight: bold;
            }


            .img{
                width: 30%;
                margin-right: 50px;
            }

            .img img{
                width: 100%;
                height:90%;
            }

            .name{
                font-size: 40px;
                font-weight: bold;
                padding-bottom: 5px;
                margin-bottom: 30px;
            }

            .oInfo{
                line-height: 35px;
            }

            .bodyContent .content button{
                margin-top: 30px;
                padding-top:10px;
                padding-bottom:10px;
                padding-left:25px;
                padding-right:25px;
                border-radius: 15px;
                background-color: red;
                color:white;
            }

            button{
                cursor: pointer;
            }

            .rate{
                border: 1px solid black;
                padding-left: 20px;
                padding-top: 10px;
                padding-bottom: 10px;
                display: flex;
                flex-direction: column;
                border-radius: 10px;
                margin-bottom: 20px;
            }

            .ttle{
                font-size: 22px;
                font-weight: bold;
                margin-bottom: 10px;
            }

            .rateDetail{
                display: flex;
                align-items: center;
                font-size: 18px;
            }

            .userRate {
                margin-right: 80px;
            }


            .rating-box{
                display: flex;
                margin-bottom: 10px;
            }

            .rating-box div{
                width: 15px;
                margin-right: 10px;
            }

            .rating-box img{
                width: 100%;
            }

            .t{
                margin-left: 55px;
                margin-bottom: 10px;
            }

            .t2{
                margin-left: 25px;
            }

            .btn{
                margin-left: 400px;
                width: 400px;
                height: 50px;
            }

            .btn input{
                height: 100%;
                width: 100%;
                border-radius: 10px;
                cursor: pointer;
                font-size: 20px;
                color: #a83256;
                background-color: white;

            }

            .b{
                cursor: pointer;
                filter: invert(75%) sepia(9%) saturate(35%) hue-rotate(31deg) brightness(97%) contrast(94%);
            }

            .myR{
                border: 1px solid black;
                padding-left: 20px;
                padding-top: 10px;
                padding-bottom: 10px;
                display: flex;
                flex-direction: column;
                border-radius: 10px;
                margin-bottom: 20px;
                text-align: center;
                display: none;
            }

            #m{
                margin-left: 580px;
            }

            #nme{
                width: 400px;
                height: 40px;
            }

            .name1{
                font-size: 15px;
                width: 100%;
                height: 100%;
                border-radius: 10px;
                padding-left: 10px;
            }

            .eName{
                display: flex;
                align-items: center;
                justify-content: space-between;
            }

            .anoRate{
                display: flex;
                align-items: center;
            }

            .bl{
                font-size: 18px;
                font-weight: bold;
                margin-right: 10px;
            }

            .anoRate{
                margin-right: 20px;
            }

            .cmt textArea{
                border-radius: 10px;
                width: 100%;
                font-size: 15px;
                padding-left: 10px;
                height: 500px;
                padding-top: 10px;

            }
            .cmt{
                margin-top: 20px;
                margin-right: 20px;
            }

            .send{
                text-align: center;
                height: 30px;
                width: 40%;
                margin: 0 auto;
                margin-top: 7px;
            }

            .send input{
                width: 100%;
                height: 100%;
                color: white;
                background-color: red;
                cursor: pointer;
                font-size: 18px;
            }


        </style>

    </head>
    <body>
        <div class = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "bodyTitle">
                Nội Dung Phim:
            </div>

            <div class ="bodyContent">
                <div class = "img">  
                    <img src="${requestScope.data.getImg()}" alt="alt"/>
                </div>
                <%
//                    String pattern = "dd-MM-yyyy";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        
//        if(request.getAttribute("data") != null)
//        Date date = Date.valueOf(request.getAttribute("data").getStartDate());
//
//        String date1 = simpleDateFormat.format(date);
//        System.out.println(request.getAttribute("data").getStartDate());
%>
                <div class = "content">
                    <div class = "name">${requestScope.data.getMovName()}</div>
                    <div class = "oInfo">
                        <span>Đạo diễn:</span>${requestScope.dir}
                        <br/>
                        <span>Diễn viên:  </span>${requestScope.star}
                        <br/>
                        <span>Thể loại:  </span>${requestScope.genre}
                        <br/>
                        <span>Khởi chiếu:  </span>${requestScope.data.getStartDate()}
                        <br/>
                        <span>Thời lượng:  </span>${requestScope.data.getTime()} phút
                        <br/>
                        <span>Ngôn ngữ:  </span>${requestScope.data.getLanguage()}
                        <br/>
                        <span>Xuất xứ:  </span>${requestScope.data.getOrigin()}
                        <br/>
                        <span>Tình trạng:  </span>${requestScope.data.getStatus()}
                        <br/>
                        <button type ="submit" value ="MUA VÉ" onclick = "detail('${requestScope.id}')">MUA VÉ</button>
                    </div>
                </div>


            </div><!-- comment -->
            <div class = "rate">
                <div class = "ms">${requestScope.ms}</div>
                <div class = "ttle">Đánh giá phim</div>
                <div class = "rateDetail">
                    <div class = "userRate">
                        <div class = "t"><span>0</span>/5</div>
                        <div class = "rating-box">
                            <div><image class ="a" src ="images/star-icon.svg"/></div>
                            <div><image class ="a" src ="images/star-icon.svg"/></div>
                            <div><image class ="a" src ="images/star-icon.svg"/></div>
                            <div><image class ="a" src ="images/star-icon.svg"/></div><!-- comment -->
                            <div><image class ="a" src ="images/star-icon.svg"/></div>
                        </div>
                        <div class = "t2"><span>0</span> đánh giá</div>
                    </div>
                    <div>
                        <div>5 sao:  &nbsp;&nbsp;<span>0%</span></div>
                        <div>4 sao:  &nbsp;&nbsp;<span>0%</span></div><!-- comment -->
                        <div>3 sao:  &nbsp;&nbsp;<span>0%</span></div><!-- comment -->
                        <div>2 sao:  &nbsp;&nbsp;<span>0%</span></div>
                        <div>1 sao:  &nbsp;&nbsp;<span>0%</span></div>
                    </div>
                    <div class="btn"><input type = "button" onclick ="display('${sessionScope.account.getUserName()}')" value = "Viết đánh giá"/></div>
                </div>
            </div>
            <input type ="text" id ="stat" hidden value ="${requestScope.stat}"/>
            <div id ="myR" class = "myR">
                <form action = "detail" method = "post">

                    <div class = "ttle">Viết đánh giá phim</div>
                    <div class = "rating-box" id = "m">
                        <div><image class ="b" id ="1" src ="images/star-icon.svg" onclick = "rate('1')"/></div>
                        <div><image class ="b" id ="2" src ="images/star-icon.svg" onclick = "rate('2')"/></div>
                        <div><image class ="b" id ="3" src ="images/star-icon.svg" onclick = "rate('3')"/></div>
                        <div><image class ="b" id ="4" src ="images/star-icon.svg" onclick = "rate('4')"/></div><!-- comment -->
                        <div><image class ="b" id ="5" src ="images/star-icon.svg" onclick = "rate('5')"/></div>
                    </div>
                    <div class = "eName">
                        <div id = "nme"><input type = "text" class ="name1" id ="name1" name = "name1" required placeholder = "Nhập tên sẽ hiển thị khi đánh giá"/></div>
                        <div class = "anoRate">
                            <span class="bl">Đánh giá ẩn danh</span>
                            <input type ="checkbox" name ="anoy" id ="anoy" onclick = "ckedBtn()"/>
                        </div>
                        <input type ="text" hidden id ="star" name ="star"/>
                        <input type ="text" hidden id ="movID" name ="movID" value = "${requestScope.id}"/>

                    </div>
                    <div class = "cmt">
                        <textarea name ="cmt" id ="cmt" required placeholder = "Nhập tên sẽ hiển thị khi đánh giá"></textarea>
                    </div>
                    <div class = "send">
                        <input type ="submit" value ="Gửi đánh giá"/> 
                    </div>
                </form>
            </div>
        </div>



        <div class = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type="text/javascript">


            function ckedBtn() {
                if (document.getElementById("anoy").checked === true) {
                    document.getElementById("name1").required = false;
                    console.log("1");
                } else {
                    document.getElementById("name1").required = true;
                    console.log("2");
                }
                console.log(document.getElementById("anoy").checked);
            }

            function detail(id) {
                window.location = "booking?id=" + id;
            }

            function rate(num) {
                console.log(num);
                for (var i = 1; i <= Number(num); i++) {
                    document.getElementById(i).style.filter = 'invert(82%) sepia(44%) saturate(769%) hue-rotate(11deg) brightness(113%) contrast(86%)';
                    document.getElementById("star").value = num;
                }
                for (var i = Number(num) + 1; i <= 5; i++) {
                    document.getElementById(i).style.filter = 'invert(75%) sepia(9%) saturate(35%) hue-rotate(31deg) brightness(97%) contrast(94%)';

                }

            }

            function display(user) {
                if(user === null || user === "" || user === undefined) {
                    window.location = "login";
                }
                console.log(user);
                if (Number(document.getElementById("stat").value) === 0) {
                    if (String(document.getElementById("myR").style.display) === 'none') {
                        document.getElementById("myR").style.display = 'block';

                    } else if (String(document.getElementById("myR").style.display) === 'block') {
                        document.getElementById("myR").style.display = 'none';

                    } else {
                        document.getElementById("myR").style.display = 'block';

                    }
                }
                else if (Number(document.getElementById("stat").value) === 1){
                    alert("Bạn đã có đánh giá về bộ phim này rồi");
                }
            }
        </script>
    </body>
</html>
