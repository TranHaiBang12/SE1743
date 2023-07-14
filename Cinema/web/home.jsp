<%-- 
    Document   : home
    Created on : May 24, 2023, 1:44:16 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cinema</title>

        <link rel="shortcut icon" href="images/cinemaFavIcon.png" />

        <link rel="stylesheet" href="style.css">
        <script src="https://kit.fontawesome.com/8157308f93.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="swiper-bundle.min.css">

        <style>
            .bt2 button{
                background-color: red;
                color: white;
                border: 1px solid red;
                border-radius: 5px;
                font-size: 15px;
                padding:5px;
                cursor: pointer;
            }

            .bt2{
                margin-top: 15px;
            }

            .movieContent{
                padding-right: 0px;
            }
            .title{
                font-size: 45px;
                padding-top: 10px;
                padding-bottom: 10px;
            }
            
            .movieContent{
                margin-top: 100px;
            }
            
            .title{
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>

        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div id = "wrapper">


            <div class = "movieContent">
                <div class = "title">
                    Phim Đang Chiếu
                </div>
                <div class = "nowShowing">
                    <div class="swiper mySwiper">
                        <div class="swiper-wrapper">
                            <c:forEach items = "${requestScope.mvNS}" var = "i">
                                <div class="swiper-slide">
                                    <div class="main-slider-box">

                                        <!--overlayer-->
                                        <div class = "main-slider-overlay">
                                            <div class="movie-name">
                                                <strong>${i.movName}</strong>

                                            </div>
                                            <div class = "playBtn">
                                                <a href="#">
                                                    <i class="fa fa-play"></i>
                                                </a>
                                            </div>
                                            <div class = "btn">
                                                <button type = "submit" value = "XEM CHI TIẾT" onclick = "detail('${i.movID}')">XEM CHI TIẾT</button>
                                                <button type = "submit" value = "MUA VÉ" onclick = "booking('${i.movID}')">MUA VÉ</button>
                                            </div>

                                            <div class = "bt2">
                                                <c:if test="${sessionScope.account.role==3 || sessionScope.account.role==1}">
                                                    <button type = "submit" value = "UPDATE" onclick = "upd('${i.movID}')">UPDATE</button>
                                                    <a href = "listMV"><button type = "submit" value = "LIST">LIST</button></a>
                                                    <button type = "submit" value = "DELETE" onclick = "dlt('${i.movID}', '${i.getMovName()}')">DELETE</button>
                                                </c:if>
                                            </div>
                                            <div class = "bt2">
                                                <c:if test="${sessionScope.account.role==3 || sessionScope.account.role==1}">
                                                    <button type = "submit" value = "ADD SCHEDULE" onclick = "addSche('${i.movID}')">ADD SCHE</button>
                                                    <button type = "submit" value = "VIEW SCHEDULE" onclick = "viewSche('${i.movID}')">VIEW SCHE</button>
                                                </c:if>
                                            </div>


                                        </div>
                                        <div class="main-slider-img">
                                            <img src="${i.img}" alt="Poster" />
                                        </div>

                                        <div class="main-slider-text">
                                            <div class="insidemain-slider-text">
                                                <div class="bottom-text">
                                                    <!--name-->




                                                    <!--Category-and-rating-->
                                                    <div class="category-rating">
                                                        <!--category-->


                                                    </div>


                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </div>
            <c:if test = "${requestScope.ms != null}">
                <input type ="text" id ="ms" value ="${requestScope.ms}"/>
            </c:if>

            <div class ="movieContent">
                <div class = "title">
                    Phim Sắp Chiếu
                </div>
                <div class = "notShowing">
                    <div class="swiper mySwiper">
                        <div class="swiper-wrapper">
                            <c:forEach items = "${requestScope.mvNSY}" var = "i">
                                <div class="swiper-slide">
                                    <div class="main-slider-box">

                                        <!--overlayer-->
                                        <div class = "main-slider-overlay">
                                            <div class="movie-name">
                                                <strong>${i.movName}</strong>

                                            </div>
                                            <div class = "playBtn">
                                                <a href="#">
                                                    <i class="fa fa-play"></i>
                                                </a>
                                            </div>
                                            <div class = "btn">
                                                <button type = "submit" value = "XEM CHI TIẾT" onclick = "detail('${i.movID}')">XEM CHI TIẾT</button>
                                                <button type = "submit" value = "MUA VÉ" onclick = "booking('${i.movID}')">MUA VÉ</button>
                                            </div>
                                            <div class = "bt2">
                                                <c:if test="${sessionScope.account.role==3 || sessionScope.account.role==1}">
                                                    <button type = "submit" value = "UPDATE"  onclick = "upd('${i.movID}')">UPDATE</button>
                                                    <button type = "submit" value = "DELETE" onclick = "dlt('${i.movID}', '${i.getMovName()}')">DELETE</button>
                                                </c:if>
                                            </div>
                                            <div class = "bt2">
                                                <c:if test="${sessionScope.account.role==3 || sessionScope.account.role==1}">
                                                    <button type = "submit" value = "ADD SCHEDULE" onclick = "addSche('${i.movID}')">ADD SCHE</button>
                                                    <button type = "submit" value = "VIEW SCHEDULE" onclick = "viewSche('${i.movID}')">VIEW SCHE</button>
                                                </c:if>
                                            </div>

                                        </div>

                                        <div class="main-slider-img">
                                            <img src="${i.img}" alt="Poster" />
                                        </div>

                                        <div class="main-slider-text">
                                            <div class="insidemain-slider-text">
                                                <div class="bottom-text">
                                                    <!--name-->
                                                    <div class="movie-name">
                                                        <strong>${i.movName}</strong>

                                                    </div>

                                                    <!--Category-and-rating-->
                                                    <div class="category-rating">
                                                        <!--category-->

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </div>





        </div>

        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>



        <script src="index.js"></script>
        <script src="swiper-bundle.min.js"></script>
        <script>
                                                        var swiper = new Swiper(".mySwiper", {
                                                            slidesPerView: 1,
                                                            spaceBetween: 10,
                                                            pagination: {
                                                                el: ".swiper-pagination",
                                                                clickable: true,
                                                            },
                                                            breakpoints: {
                                                                640: {
                                                                    slidesPerView: 2,
                                                                    spaceBetween: 20,
                                                                },
                                                                768: {
                                                                    slidesPerView: 4,
                                                                    spaceBetween: 40,
                                                                },
                                                                1024: {
                                                                    slidesPerView: 3,
                                                                    spaceBetween: 50,
                                                                },
                                                            },
                                                        });

                                                        var swiper = new Swiper(".mySwiperEvent", {
                                                            slidesPerView: 1,
                                                            spaceBetween: 10,
                                                            pagination: {
                                                                el: ".swiper-pagination",
                                                                clickable: true,
                                                            },
                                                            breakpoints: {
                                                                640: {
                                                                    slidesPerView: 2,
                                                                    spaceBetween: 20,
                                                                },
                                                                768: {
                                                                    slidesPerView: 4,
                                                                    spaceBetween: 40,
                                                                },
                                                                1024: {
                                                                    slidesPerView: 3,
                                                                    spaceBetween: 50,
                                                                },
                                                            },
                                                        });


        </script>
        <script>
            if(document.getElementById("ms") !== null) {
                alert(document.getElementById("ms").value);
            }
            function addSche(id) {
                window.location = "addsche?id=" + id;
            }

            function dlt(id, name) {
                if (confirm("Bạn có chắc muốn xóa bộ phim " + name + ". Việc này sẽ xóa cả vé cũng như toàn bộ lịch chiếu của bộ phim này")) {
                    window.location = "dltmv?id=" + id;
                }
            }

            function viewSche(id) {
                window.location = "viewsche?id=" + id;
            }

            function updSche(id) {
                window.location = "updsche?id=" + id;
            }

            function detail(id) {
                window.location = "detail?id=" + id;
            }

            function upd(id) {
                window.location = "update?id=" + id;
            }

            function booking(id) {
                window.location = "booking?id=" + id;
            }
        </script>
    </body>
</html>
