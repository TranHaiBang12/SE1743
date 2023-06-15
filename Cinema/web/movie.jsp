<%-- 
    Document   : movie
    Created on : Jun 15, 2023, 3:50:39 PM
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
                padding-bottom: 10px;
                border-bottom: 3px dashed red;
            }
            
            
            .containMV{
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                margin-top: 20px;
            }

            .main-slider-box {
                width: 400px;
                height: 600px;
                margin-bottom: 15px;
                margin-right: 15px;
            }
            
            .main-slider-overlay{
                
            }

            .main-slider-img{
                width:100%;
                height:100%;

            }

            .main-slider-img img{
                width: 100%;
                height: 100%;
                object-fit: cover;
                object-position: center;
            }

            .breaker {
                flex-basis: 100%;
                height: 0;
            }

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

            .main-slider-box{
                cursor: pointer;
            }

            .ttle{
                font-size: 25px;
                font-weight: bold;
                margin-left: 20px;
                padding-top: 20px;
                padding-bottom: 10px;
                border-bottom: 3px solid black;
            }




        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ns">
                <div class = "ttle">PHIM ĐANG CHIẾU</div>
                <div class = "containMV">
                    <c:forEach items = "${requestScope.mvNS}" var = "i">

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
                                    <c:if test="${sessionScope.account.role==3}">
                                        <button type = "submit" value = "UPDATE" onclick = "upd('${i.movID}')">UPDATE</button>
                                        <button type = "submit" value = "DELETE">DELETE</button>
                                    </c:if>
                                </div>

                            </div>
                            <div class="main-slider-img">
                                <img src="${i.getImg()}" alt="Poster" />
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
                        <div class = "${((i.getID()+1) % 3 == 0)?"breaker":""}">

                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class = "ns">
                <div class = "ttle">PHIM SẮP CHIẾU</div>
                <div class = "containMV">
                    <c:forEach items = "${requestScope.mvCS}" var = "i">

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
                                    <button type = "submit" value = "XEM CHI TIẾT" onclick = "detail('${i.getMovID()}')">XEM CHI TIẾT</button>
                                    <button type = "submit" value = "MUA VÉ" onclick = "booking('${i.getMovID()}')">MUA VÉ</button>
                                </div>

                                <div class = "bt2">
                                    <c:if test="${sessionScope.account.role==3}">
                                        <button type = "submit" value = "UPDATE" onclick = "upd('${i.movID}')">UPDATE</button>
                                        <button type = "submit" value = "DELETE">DELETE</button>
                                    </c:if>
                                </div>

                            </div>
                            <div class="main-slider-img">
                                <img src="${i.getImg()}" alt="Poster" />
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
                        <div class = "${((i.getID()+1) % 3 == 0)?"breaker":""}">

                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
     
        <script>
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
