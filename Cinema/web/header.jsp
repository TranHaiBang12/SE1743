<%-- 
    Document   : header
    Created on : May 29, 2023, 9:14:12 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #userN{
                font-size:20px;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <ul class="menu_ttinBenLe">
            <li><a href="#">TUYỂN DỤNG</a></li>
            <li><a href="#">TIN MỚI & ƯU ĐÃI</a></li>
            <li><a href="#">VÉ CỦA TÔI</a></li>

            <c:if test="${sessionScope.account==null}">
                <li><a href="login">ĐĂNG NHẬP/ĐĂNG KÝ</a></li>
                </c:if>
            <li><a href="#">CSKH</a></li>
            <li><a href="#">EN</a></li>
                <c:if test = "${sessionScope.account!=null}">
                <span id ="userN" style="color:red">${sessionScope.account.getUserName()}</a>
                </c:if>
        </ul>

        <div class="wrapper_menu">

            <ul class="menu">
                <li><img class="cinemaLogo" src="images/logoCinema.png" /></li>
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
                            <img class="memberIcon" src="images/memberIcon.jpg" />
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
                <li><a href="#"><img class="storeLogo" src="images/storeiCON.png" /></a></li>
            </ul>


        </div>
    </body>
</html>
