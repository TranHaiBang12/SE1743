<%-- 
    Document   : home
    Created on : May 24, 2023, 1:44:16 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>The Best Website</title>

        <link rel="shortcut icon" href="images/cinemaFavIcon.png" />

        <link rel="stylesheet" href="style.css">
        <script src="https://kit.fontawesome.com/8157308f93.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="swiper-bundle.min.css">
    </head>
    <body>

        <div>
            <ul class="menu_ttinBenLe">
                <li><a href="#">TUYỂN DỤNG</a></li>
                <li><a href="#">TIN MỚI & ƯU ĐÃI</a></li>
                <li><a href="#">VÉ CỦA TÔI</a></li>
                <li><a href="#">ĐĂNG NHẬP/ĐĂNG KÝ</a></li>
                <li><a href="#">CSKH</a></li>
                <li><a href="#">EN</a></li>
            </ul>

            <div class="wrapper_menu">
                <img class="cinemaLogo" src="images/logoCinema.png" />
                <ul class="menu">
                    <li>
                        <div class="dropdown">
                            <div class="dropbtn">
                                <img class="movieIcon" src="images/movieIcon.jpg" />
                                <a href="#">PHIM</a>
                            </div>
                            <div class="dropdown-content">
                                <div class=insidedropdown-content>
                                    <a href="#">Phim Đang Chiếu</a>
                                    <a href="#">Phim Sắp Chiếu</a>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <div class="dropbtn">
                                <img class="cinemaIcon" src="images/cinemaIcon.png" />
                                <a href="#">RẠP</a>
                            </div>
                            <div class="dropdown-content">
                                <div class=insidedropdown-content>
                                    <a href="#">Tất Cả Các Rạp</a>
                                    <a href="#">Rạp Đặc Biệt</a>
                                    <a href="#">Rạp 3D</a>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <div class="dropbtn">
                                <img class="memberIcon" src="images/memberIcon.avif" />
                                <a href="#">THÀNH VIÊN</a>
                            </div>
                            <div class="dropdown-content">
                                <div class=insidedropdown-content>
                                    <a href="#">Tài Khoản</a>
                                    <a href="#">Quyền Lợi</a>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <div class="dropbtn">
                                <img class="culturePlexIcon" src="images/culturePlexIcon.png" />
                                <a href="#">CULTUREPLEX</a>
                            </div>
                            <div class="dropdown-content">
                                <div class=insidedropdown-content>
                                    <a href="#">Nội Quy</a>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
                <a href="#"><img class="storeLogo" src="images/storeiCON.png" /></a>

            </div>

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



                    </div>
                    <div class="swiper-pagination"></div>
                </div>

                <!--box-->

            </section>

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
