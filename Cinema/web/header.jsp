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
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <style>
            #userN{
                font-size:20px;
                text-decoration: none;
                color: white;
                cursor: pointer;
                margin-right: 20px;
            }


        </style>
    </head>
    <body>
        <ul class="menu_ttinBenLe">
            <div class="dropdown">
                <li id = "userN">ƯU ĐÃI</li>
                <div class="dropdown-content">
                    <div class=insidedropdown-content>
                        <a href="event">Xem Ưu Đãi</a>
                        <c:if test = "${sessionScope.account != null && sessionScope.account.getRole() == 3}">
                            <a href="updev">Thống Kê Lợi Nhuận</a>
                        </c:if>
                    </div>
                </div>
            </div>
            <li><a href="cart">GIỎ HÀNG CỦA TÔI</a></li>

            <c:if test="${sessionScope.account==null}">
                <li><a href="login">ĐĂNG NHẬP/ĐĂNG KÝ</a></li>
                </c:if>

            <c:if test = "${sessionScope.account != null && sessionScope.account.getRole() == 3}">
                <div class="dropdown">
                    <li id = "userN">THỐNG KÊ</li>
                    <div class="dropdown-content">
                        <div class=insidedropdown-content>
                            <a href="alr">Thống Kê Tổng</a>
                            <a href="icr">Thống Kê Doanh Thu</a>
                            <a href="itr">Thống Kê Lợi Nhuận</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test = "${sessionScope.account != null && sessionScope.account.getRole() == 3}">
                <div class="dropdown">
                    <li id = "userN">CƠ SỞ VẬT CHẤT</li>
                    <div class="dropdown-content">
                        <div class=insidedropdown-content>
                            <a href="dv">Thiết Bị </a>
                            <a href="dvd">Phân Bố Thiết Bị </a>
                            <a href="dvs">Danh Sách Lỗi</a>
                            <a href="dvrp">Thống Kê Về Thiết Bị </a>
                            <a href="cnrp">Thống Kê Rạp</a>
                            <a href="rmrp">Danh Sách Phòng</a><!-- <a href="#">Thống Kê Rạp</a> -->
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test = "${sessionScope.account!=null}">
                <li><a href="transact">LỊCH SỬ GIAO DỊCH</a></li>
                <div class="dropdown">
                    <li id = "userN">${sessionScope.account.getUserName()}</li>
                    <div class="dropdown-content">
                        <div class=insidedropdown-content>
                            <a href="acc">Thông Tin Tài Khoản</a>
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
                            <a class ="lnk" href="movie">PHIM</a>
                        </div>
                        <div class="dropdown-content">
                            <div class=insidedropdown-content>
                                <a class ="lnk" href="nowshowing">Phim Đang Chiếu</a>
                                <a class ="lnk" href="comingsoon">Phim Sắp Chiếu</a>
                                <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                                    <a class ="lnk" href="addmov">Thêm Phim Mới</a>
                                </c:if>
                                <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                                    <a class ="lnk" href="rpm">Thống Kê Phim</a>
                                </c:if>


                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="dropdown">
                        <div class="dropbtn">
                            <img class="cinemaIcon" src="images/cinemaIcon.png" />
                            <a class ="lnk" href="cin">RẠP</a>
                        </div>
                        <div class="dropdown-content">
                            <div class=insidedropdown-content>
                                <a class ="lnk" href="cin">Tất Cả Các Rạp</a>
                                <a class ="lnk" href="cin?id=1">Rạp Đặc Biệt</a>
                                <a class ="lnk" href="cin?id=2">Rạp 2D</a>
                                <a class ="lnk" href="cin?id=3">Rạp 3D</a>
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
                                <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                                    <a class ="lnk" href="arp">Danh Sách</a>
                                    <a class ="lnk" href="ordr?p=1">Danh Sách Hóa Đơn</a>
                                    <a class ="lnk" href="orl">Thống Kê Hóa Đơn</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </li>

                <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                    <li>
                        <div class="dropdown">
                            <div class="dropbtn">
                                <img class="memberIcon" src="images/memberIcon.jpg" />
                                <a class ="lnk" href="#">NHÂN VIÊN</a>
                            </div>
                            <div class="dropdown-content">
                                <div class=insidedropdown-content>
                                    <a class ="lnk" href="liste">Danh Sách</a>
                                    <a class ="lnk" href="#">Báo cáo</a>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:if>

                <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                    <li>
                        <div class="dropdown">
                            <div class="dropbtn">
                                <img class="memberIcon" src="images/productIcon.png" />
                                <a class ="lnk" href="#">SẢN PHẨM</a>
                            </div>
                            <div class="dropdown-content">
                                <div class=insidedropdown-content>
                                    <a class ="lnk" href="rpp?type=TK">Vé</a>
                                    <a class ="lnk" href="rpp?type=FD">Đồ Ăn</a>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:if>
                <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                    <li>
                        <div class="dropdown">
                            <div class="dropbtn">
                                <img class="memberIcon" src="images/rateIcon.png" />
                                <a class ="lnk" href="#">ĐÁNH GIÁ</a>
                            </div>
                            <div class="dropdown-content">
                                <div class=insidedropdown-content>
                                    <a class ="lnk" href="sr">Xem Đánh Giá</a>
                                    <a class ="lnk" href="rrp">Thống Kê</a>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:if>
                <li><a href="store"><img class="storeLogo" src="images/storeiCON.png" /></a></li>
            </ul>


        </div>
    </body>
</html>
