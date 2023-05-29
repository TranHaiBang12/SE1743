<%-- 
    Document   : header
    Created on : May 29, 2023, 9:14:12 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
    </body>
</html>
