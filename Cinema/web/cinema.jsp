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

            .main-slider-img img{
                width:100%;
            }

            .main-slider-img{
                width: 100%;
            }

            .bigttle{
                text-align: center;
                padding-bottom: 10px;
                font-size: 30px;
                margin-top: 20px;
                margin-bottom: 20px;
                color: brown;
                text-shadow: 10px 10px 5px #666666;
            }

            .sperTtle{
                padding-top: 20px;
                
                margin-bottom: 20px;
                font-size: 30px;
                font-weight: bold;
            }

            .schedule{
                margin-top: 15px;
                display: flex;
                flex-wrap: wrap;
                border-bottom: 3px solid black;
            }

            .insideSchedule{
                display: flex;
                width: 80px;
                height: 40px;
                margin-left: 15px;
                margin-right: 0px;
                margin-bottom: 10px;
                justify-content: center;
                align-items: center;
                cursor: pointer;
            }

            .insideScheduleActive{
                display: flex;
                width: 80px;
                height: 40px;
                margin-left: 15px;
                margin-right: 0px;
                margin-bottom: 10px;
                justify-content: center;
                align-items: center;
                cursor: pointer;
                border: 1px solid black;
                border-radius: 15px;
                padding-top: 10px;
                padding-bottom: 10px;
                background-color: black;
                color: white;
            }
            .notDate{
                display: flex;
                flex-direction: column;
                margin-right: 8px;
                margin-bottom: 5px;
            }
            .isDate{
                font-size: 30px;
            }
            .insideSchedule:hover{
                border: 1px solid black;
                border-radius: 15px;
                padding-top: 10px;
                padding-bottom: 10px;
            }

            .ms{
                margin-top: 20px;
                margin-left: 30px;
                margin-right: 0px;
                margin-bottom: 20px;
            }

            .mImg img{
                width: 200px;
                height: 250px;
            }

            .mOvSCHE{
                padding-bottom: 15px;
                border-bottom: 2px solid black;
            }

            .mName{
                font-size: 22px;
                margin-bottom: 15px;
                margin-top: 15px;
                font-weight: bold;
            }

            .mIn4{
                display: flex;
            }

            .insidemNotImg{
                display: flex;
                flex-direction: column;
                flex-wrap: wrap;
            }

            .mTme{
                margin-bottom: 10px;
                display: flex;
                flex-wrap: wrap;
                cursor: pointer;
            }

            .insidemTme{
                margin-right: 10px;
                border: 1px solid black;
                padding: 10px;
            }

            .mFrm{
                margin-bottom: 10px;
                font-weight: bold;
            }

            .mNotImg{
                margin-left: 20px;
                font-size: 20px;
            }
            
            .ms{
                margin-bottom: 0px;
                padding-bottom: 20px;
            }
            
            .sperTtle{
                padding-top: 40px;
                text-shadow: 10px 10px 5px #666666;
                color: brown
            }
            
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "sperTtle">CHỌN RẠP CHIẾU PHIM</div>
            <div class = "loc">
                <div class = "bigttle">Địa Điểm</div>
                <div class = "insideloc">
                    <c:forEach items = "${requestScope.listLoc}" var = "i">
                        <div class = "${i==requestScope.loc?"insideinsideLocActive":"insideinsideLoc"}" onclick = "pckLoc('${i}', '${requestScope.type}')">${i}</div>
                    </c:forEach>
                </div>
            </div>
            <c:if test = "${requestScope.listM != null}">
                <div class = "loc">
                    <div class = "bigttle">Rạp Chiếu Phim</div>
                    <div class = "insideloc">
                        <c:forEach items = "${requestScope.listM}" var = "k">
                            <div class = "${k.getCinID()==requestScope.cinID?"insideinsideLocActive":"insideinsideLoc"}" onclick = "pckCin('${k.getCinID()}', '${requestScope.loc}', '${requestScope.type}')">${k.getCinName()}</div>
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
            <c:if test = "${requestScope.m != null}">
                <div class = "ttle">Lịch Chiếu</div>
                <div class = "schedule">
                    <c:forEach items = "${requestScope.dte}" var = "j">
                        <div class="${requestScope.sche==j.getId()?"insideScheduleActive":"insideSchedule"}" onclick = "pckSche('${j.getId()}', '${requestScope.cinID}', '${requestScope.loc}', '${requestScope.type}')">

                            <div class = "notDate">
                                <span>${j.getMonth()}</span><!-- comment -->
                                <span>${j.getDay()}</span>
                            </div>
                            <div class = "isDate">${j.getDate()}</div>
                        </div>
                    </c:forEach>
                </div>

                <div class="mov">
                    <c:if test = "${requestScope.ms == null}">
                        <c:forEach items = "${requestScope.mvt}" var = "j">
                            <div class="mOvSCHE">
                                <div class = "mName">${j.getName()}</div>
                                <div class = "mIn4">
                                    <div class = "mImg"><img src = "${j.getS().getMovImg()}"/></div>
                                    <div class = "mNotImg">
                                        <c:forEach items = "${j.getFt()}" var = "q">
                                            <div class = "insidemNotImg">
                                                <div class = "mFrm">${q.getType()}</div>
                                                <div class = "mTme">
                                                    <c:forEach items = "${q.getTime()}" var = "n">
                                                        <div class = "insidemTme" onclick = "pckMovSche('${n.getScheNo()}')">${n.getStart()}</div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test = "${requestScope.ms != null}">
                        <h3 class = "ms">${requestScope.ms}</h3>
                    </c:if>
                </div>
            </c:if> 
        </div>

        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
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
        <script type = "text/javascript">
            function pckLoc(loc, id) {
                window.location = "cin?loc=" + loc + "&id=" + id;
            }

            function pckCin(cinID, loc, id) {
                window.location = "cin?loc=" + loc + "&id=" + id + "&cinID=" + cinID;
            }

            function pckSche(sche, cinID, loc, id) {
                window.location = "cin?sche=" + sche + "&loc=" + loc + "&id=" + id + "&cinID=" + cinID;
            }
            
            function pckMovSche(id) {
                window.location = "seat?id=" + id;
            }
        </script>
    </body>
</html>
