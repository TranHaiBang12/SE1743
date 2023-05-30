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
        </style>
    </head>
    <body>

        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div id = "wrapper">
            <div class = "movieContent">
                <div class = "title">
                    PHIM HOT
                </div>

                <!--slider-->
                <section id="main-slider">

                    <!-- Swiper -->
                    <div class="swiper mySwiper">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide">
                                <div class="main-slider-box">

                                    <!--overlayer-->
                                    <a href="#" class="main-slider-overlay">
                                        <i class="fa fa-play"></i>
                                    </a>

                                    <div class="main-slider-img">
                                        <img src="images/Avengers_Endgame_bia_teaser.jpg" alt="Poster" />
                                    </div>

                                    <div class="main-slider-text">
                                        <div class="insidemain-slider-text">
                                            <div class="bottom-text">
                                                <!--name-->
                                                <div class="movie-name">
                                                    <strong>Avengers: End Game</strong>

                                                </div>

                                                <!--Category-and-rating-->
                                                <div class="category-rating">
                                                    <!--category-->
                                                    <div class="category">
                                                        <a href="#">Fiction</a>, <a href="#">Action</a>
                                                    </div>

                                                    <!--rating-->
                                                    <div class="rating">
                                                        5.2 <!-- img hinh nut like-->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide">
                                <div class="main-slider-box">

                                    <!--overlayer-->
                                    <a href="#" class="main-slider-overlay">
                                        <i class="fa fa-play"></i>
                                    </a>

                                    <div class="main-slider-img">
                                        <img src="images/avatar.jpg" alt="Poster" />
                                    </div>

                                    <div class="main-slider-text">
                                        <div class="insidemain-slider-text">
                                            <div class="bottom-text">
                                                <!--name-->
                                                <div class="movie-name">
                                                    <strong>Avengers: End Game</strong>

                                                </div>

                                                <!--Category-and-rating-->
                                                <div class="category-rating">
                                                    <!--category-->
                                                    <div class="category">
                                                        <a href="#">Fiction</a>, <a href="#">Action</a>
                                                    </div>

                                                    <!--rating-->
                                                    <div class="rating">
                                                        5.2 <!-- img hinh nut like-->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide">
                                <div class="main-slider-box">

                                    <!--overlayer-->
                                    <a href="#" class="main-slider-overlay">
                                        <i class="fa fa-play"></i>
                                    </a>

                                    <div class="main-slider-img">
                                        <img src="images/Chua_te_Godzilla_bia_poster.jpg" alt="Poster" />
                                    </div>

                                    <div class="main-slider-text">
                                        <div class="insidemain-slider-text">
                                            <div class="bottom-text">
                                                <!--name-->
                                                <div class="movie-name">
                                                    <strong>Avengers: End Game</strong>

                                                </div>

                                                <!--Category-and-rating-->
                                                <div class="category-rating">
                                                    <!--category-->
                                                    <div class="category">
                                                        <a href="#">Fiction</a>, <a href="#">Action</a>
                                                    </div>

                                                    <!--rating-->
                                                    <div class="rating">
                                                        5.2 <!-- img hinh nut like-->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide">
                                <div class="main-slider-box">

                                    <!--overlayer-->
                                    <a href="#" class="main-slider-overlay">
                                        <i class="fa fa-play"></i>
                                    </a>

                                    <div class="main-slider-img">
                                        <img src="images/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_.jpg" alt="Poster" />
                                    </div>

                                    <div class="main-slider-text">
                                        <div class="insidemain-slider-text">
                                            <div class="bottom-text">
                                                <!--name-->
                                                <div class="movie-name">
                                                    <strong>Avengers: End Game</strong>

                                                </div>

                                                <!--Category-and-rating-->
                                                <div class="category-rating">
                                                    <!--category-->
                                                    <div class="category">
                                                        <a href="#">Fiction</a>, <a href="#">Action</a>
                                                    </div>

                                                    <!--rating-->
                                                    <div class="rating">
                                                        5.2 <!-- img hinh nut like-->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide">
                                <div class="main-slider-box">

                                    <!--overlayer-->
                                    <a href="#" class="main-slider-overlay">
                                        <i class="fa fa-play"></i>
                                    </a>

                                    <div class="main-slider-img">
                                        <img src="images/GodzilaKong.jpg" alt="Poster" />
                                    </div>

                                    <div class="main-slider-text">
                                        <div class="insidemain-slider-text">
                                            <div class="bottom-text">
                                                <!--name-->
                                                <div class="movie-name">
                                                    <strong>Avengers: End Game</strong>

                                                </div>

                                                <!--Category-and-rating-->
                                                <div class="category-rating">
                                                    <!--category-->
                                                    <div class="category">
                                                        <a href="#">Fiction</a>, <a href="#">Action</a>
                                                    </div>

                                                    <!--rating-->
                                                    <div class="rating">
                                                        5.2 <!-- img hinh nut like-->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide">
                                <div class="main-slider-box">

                                    <!--overlayer-->
                                    <a href="#" class="main-slider-overlay">
                                        <i class="fa fa-play"></i>
                                    </a>

                                    <div class="main-slider-img">
                                        <img src="images/fastAndFurious.jpg" alt="Poster" />
                                    </div>

                                    <div class="main-slider-text">
                                        <div class="insidemain-slider-text">
                                            <div class="bottom-text">
                                                <!--name-->
                                                <div class="movie-name">
                                                    <strong>Avengers: End Game</strong>

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



                        </div>
                        <div class="swiper-pagination"></div>
                    </div>

                    <!--box-->

                </section>
            </div>

            <div class = "movieContent">
                <div class = "title">
                    PHIM ĐANG CHIẾU
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
                                                <button type = "submit" value = "XEM CHI TIẾT">XEM CHI TIẾT</button>
                                                <button type = "submit" value = "MUA VÉ">MUA VÉ</button>
                                            </div>
                                                
                                            <div class = "bt2">
                                                <c:if test="${sessionScope.account.role==3}">
                                                <button type = "submit" value = "UPDATE">UPDATE</button>
                                                <button type = "submit" value = "DELETE">DELETE</button>
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

            <div class ="movieContent">
                <div class = "title">
                    PHIM SẮP CHIẾU
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
                                                <button type = "submit" value = "XEM CHI TIẾT">XEM CHI TIẾT</button>
                                                <button type = "submit" value = "MUA VÉ">MUA VÉ</button>
                                            </div>
                                            <div class = "bt2">
                                                <c:if test="${sessionScope.account.role==3}">
                                                <button type = "submit" value = "UPDATE">UPDATE</button>
                                                <button type = "submit" value = "DELETE">DELETE</button>
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

            <div class ="movieContent">
                <div class = "title">
                    EVENT
                </div>
                <div class = "event">
                    <div class="swiper mySwiperEvent">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide"><img class ="event1" src="images/event1.png" alt="Event Poster" /></div>
                            <div class="swiper-slide"><img class ="event" src="images/event2.png" alt="Event Poster" /></div>
                            <div class="swiper-slide"><img class ="event" src="images/event3.png" alt="Event Poster" /></div>
                            <div class="swiper-slide"><img class ="event" src="images/event4.png" alt="Event Poster" /></div>
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
    </body>
</html>
