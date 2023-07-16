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

            .cinemaLogo{
                border-right: 1px solid black;
            }
            .menu_ttinBenLe{
                padding: 10px;
            }

            .menu{

                border-top: 3px solid black;
                padding-top: 20px;
                padding-bottom: 20px;
                border-bottom: 3px solid black;
                padding-left: 0px;
                margin-left: 0px;
            }

            body{
                border-bottom: 3px solid black;
            }

            .wrapper_menu{
                padding-left: 50px;
                padding-right: 85px;
            }

            .menu{
                justify-content: space-between;
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
                        <c:if test = "${sessionScope.account != null && (sessionScope.account.getRole() == 3 || sessionScope.account.getRole() == 1)}">
                            <a href="addev">Thêm Ưu Đãi</a>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="dropdown">
                <li><a href="cart">GIỎ HÀNG CỦA TÔI</a></li>
            </div>
            <c:if test="${sessionScope.account==null}">
                <div class="dropdown">
                    <li><a href="login">ĐĂNG NHẬP/ĐĂNG KÝ</a></li>
                </div>
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
                <div class="dropdown">
                    <li>

                        <a class ="lnk" href="transact">LỊCH SỬ GIAO DỊCH</a>

                    </li>
                </div>
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
                            <div class="insidedropdown-content">
                                <a class ="lnk" href="nowshowing">Phim Đang Chiếu</a>
                                <a class ="lnk" href="comingsoon">Phim Sắp Chiếu</a>
                                <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                                    <a class ="lnk" href="listMV">Danh Sách Phim</a>
                                </c:if>
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
                            <a class ="lnk" href="movie">LỊCH CHIẾU & VÉ</a>
                        </div>
                        <div class="dropdown-content">
                            <div class="insidedropdown-content">
      
                                <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                                    <a class ="lnk" href="allsche">Danh Sách Lịch Chiếu</a>
                                </c:if>
                                <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                                    <a class ="lnk" href="allt">Danh Sách Vé</a>
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
                                <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                                    <a class ="lnk" href="listcin">Danh Sách Các Rạp</a>
                                </c:if>
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
                                <a class ="lnk" href="acc">Tài Khoản</a>
                                <c:if test="${sessionScope.account!=null && (sessionScope.account.getRole() == 1 || sessionScope.account.getRole() == 3)}">
                                    <a class ="lnk" href="emprp?acc=${sessionScope.account.getUserName()}">Xem Báo Cáo</a>
                                </c:if>
                                <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                                    <a class ="lnk" href="arp">Danh Sách Khách Hàng</a>
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
                                    <c:if test="${sessionScope.account!=null && sessionScope.account.getRole() == 3}">
                                    </c:if>
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
                                    <a class ="lnk" href="listfd">Danh Sách Đồ Ăn</a>
                                    <a class ="lnk" href="fooo">Danh Sách Đồ Ăn Hết Hàng</a>
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
                <li>
                    <div class="dropdown">
                        <div class="dropbtn">
                            <img class="memberIcon" src="images/storeiCON.png" />
                            <a class ="lnk" href="store">CỬA HÀNG</a>
                        </div>

                    </div>
                </li>
            </ul>


        </div>
    </body>
</html>
