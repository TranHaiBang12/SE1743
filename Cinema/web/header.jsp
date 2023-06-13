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
                color: white;
                cursor: pointer;
            }


        </style>
    </head>
    <body>
        <ul class="menu_ttinBenLe">
            <li><a href="#">TUYỂN DỤNG</a></li>
            <li><a href="#">TIN MỚI & ƯU ĐÃI</a></li>
            <li><a href="cart">GIỎ HÀNG CỦA TÔI</a></li>

            <c:if test="${sessionScope.account==null}">
                <li><a href="login">ĐĂNG NHẬP/ĐĂNG KÝ</a></li>
                </c:if>
            <li><a href="#">CSKH</a></li>
            <li><a href="#">EN</a></li>
                <c:if test = "${sessionScope.account!=null}">
                <li><a href="transact">LỊCH SỬ GIAO DỊCH</a></li>
                <div class="dropdown">
                    <li id = "userN">${sessionScope.account.getUserName()}</li>
                    <div class="dropdown-content">
                        <div class=insidedropdown-content>
                            <a href="#">Thông Tin Tài Khoản</a>
                            <a href="logout">Đăng Xuất</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </ul>

        <div class="wrapper_menu">

            <ul class="menu">
                <li><a href = "home"><img class="cinemaLogo" src="images/logoCinema.png" /></a></li>
                <li>
                    <div class="dropdown">
                        <div class="dropbtn">
                            <img class="movieIcon" src="images/movieIcon.jpg" />
                            <a class ="lnk" href="#">PHIM</a>
                        </div>
                        <div class="dropdown-content">
                            <div class=insidedropdown-content>
                                <a class ="lnk" href="#">Phim Đang Chiếu</a>
                                <a class ="lnk" href="#">Phim Sắp Chiếu</a>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="dropdown">
                        <div class="dropbtn">
                            <img class="cinemaIcon" src="images/cinemaIcon.png" />
                            <a class ="lnk" href="#">RẠP</a>
                        </div>
                        <div class="dropdown-content">
                            <div class=insidedropdown-content>
                                <a class ="lnk" href="#">Tất Cả Các Rạp</a>
                                <a class ="lnk" href="#">Rạp Đặc Biệt</a>
                                <a class ="lnk" href="#">Rạp 3D</a>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="dropdown">
                        <div class="dropbtn">
                            <img class="memberIcon" src="images/memberIcon.jpg" />
                            <a class ="lnk" href="#">THÀNH VIÊN</a>
                        </div>
                        <div class="dropdown-content">
                            <div class=insidedropdown-content>
                                <a class ="lnk" href="#">Tài Khoản</a>
                                <a class ="lnk" href="#">Quyền Lợi</a>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="dropdown">
                        <div class="dropbtn">
                            <img class="culturePlexIcon" src="images/culturePlexIcon.png" />
                            <a class ="lnk" href="#">CULTUREPLEX</a>
                        </div>
                        <div class="dropdown-content">
                            <div class=insidedropdown-content>
                                <a class ="lnk" href="#">Nội Quy</a>
                            </div>
                        </div>
                    </div>
                </li>
                <li><a href="store"><img class="storeLogo" src="images/storeiCON.png" /></a></li>
            </ul>


        </div>
    </body>
</html>
