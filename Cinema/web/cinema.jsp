<%-- 
    Document   : cinema
    Created on : Jun 15, 2023, 4:42:57 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css"/>
        <script src="https://kit.fontawesome.com/8157308f93.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="swiper-bundle.min.css">
        <style>
            .body{
                padding-left: 80px;
            }

            .insideloc{
                display: flex;
                flex-wrap: wrap;
            }

            .ttle{
                text-align: center;
                font-size: 30px;
            }

            .insideinsideLoc{
                font-size: 20px;
                border: 1px solid black;
                padding: 10px;
                margin-right: 30px;
                border-radius: 10px;
                cursor: pointer;
            }

            .ttle{
                margin-bottom: 20px;
                padding-top: 20px;
            }

            .insideinsideLocActive{
                font-size: 20px;
                border: 1px solid black;
                padding: 10px;
                margin-right: 30px;
                border-radius: 10px;
                cursor: pointer;
                background-color: black;
                color: white;
            }


        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "loc">
                <div class = "ttle">Địa Điểm</div>
                <div class = "insideloc">
                    <c:forEach items = "${requestScope.listLoc}" var = "i">
                        <div class = "${i==requestScope.loc?"insideinsideLocActive":"insideinsideLoc"}" onclick = "pckLoc('${i}', '${requestScope.type}')">${i}</div>
                    </c:forEach>
                </div>
            </div>
            <c:if test = "${requestScope.listM != null}">
                <div class = "loc">
                    <div class = "ttle">Rạp Chiếu Phim</div>
                    <div class = "insideloc">
                        <c:forEach items = "${requestScope.listM}" var = "k">
                            <div class = "insideinsideLoc" onclick = "pckCin('${k.getCinID()}', '${requestScope.loc}', '${requestScope.type}')">${k.getCinName()}</div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
            <c:if test = "${requestScope.m != null}">
                <div class = "bigttle">THEATER</div>
                <div class = "iMaGE">
                    <section id="main-slider">
                        <div class="swiper mySwiper">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <div class="main-slider-box">
                                        <div class="main-slider-img">
                                            <img src="images/cinema.jpg" alt="Poster" />
                                        </div>
                                    </div>
                                </div>
                                <div class="swiper-slide">
                                    <div class="main-slider-box">
                                        <div class="main-slider-img">
                                            <img src="images/cinema2.jpg" alt="Poster" />
                                        </div>
                                    </div>
                                </div>
                                <div class="swiper-slide">
                                    <div class="main-slider-box">
                                        <div class="main-slider-img">
                                            <img src="images/cinema3.jpg" alt="Poster" />
                                        </div>
                                    </div>
                                </div>
                                <div class="swiper-slide">
                                    <div class="main-slider-box">
                                        <div class="main-slider-img">
                                            <img src="images/cinema4.jpg" alt="Poster" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="swiper-pagination"></div>
                        </div>
                    </section>
                </div>
            </c:if>

        </div>

        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
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
        <script type = "text/javascript">
            function pckLoc(loc, id) {
                window.location = "cin?loc=" + loc + "&id=" + id;
            }

            function pckCin(cinID, loc, id) {
                window.location = "cin?loc=" + loc + "&id=" + id + "&cinID=" + cinID;
            }
        </script>
    </body>
</html>
