<%-- 
    Document   : icR
    Created on : Jul 4, 2023, 7:25:31 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <link rel="stylesheet" href="style.css?version=1"/>
        <style>
            .dteS{
                display: flex;
                justify-content: center;
                font-size: 19px;
            }

            .dteS div{
                margin-right: 15px;
            }

            .rd{
                color: red;
            }

            .Sttle{
                font-weight: bold;
                font-size: 20px;
            }

            .insider1{
                font-size: 20px;
                margin-left: 40px;
                padding-bottom: 10px;
            }

            .insider1 div{
                margin-top: 10px;
                padding-bottom: 10px;
            }

            .ttle{
                text-align: center;
                padding-top: 20px;
                font-size: 27px;
                font-weight: bold;
                margin-bottom: 40px;
            }

            .search{
                display: flex;
                justify-content: space-around;
                font-size: 18px;
            }

            .search input {
                font-size: 18px;
            }

            .srchBtn{
                text-align: center;
                margin-top: 20px;
                padding-bottom: 30px;
            }

            .srchBtn input{
                font-size: 18px;
                padding-top: 5px;
                padding-bottom: 5px;
                padding-right: 12px;
                padding-left: 12px;
                cursor: pointer;
            }


            .SSttle {
                font-size: 20px;
                margin-top: 10px;
                font-weight: bold;
                color: magenta;
            }

            .RATE{
                padding-top: 20px;
                margin-left: 40px;
                background-color: white;
                padding-bottom: 20px;
                border-bottom: 3px solid black;
            }

            .btS button{
                font-size: 18px;
                padding: 5px;
                background-color: red;
                color: white;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div>
        <div class = "body">
            <div class = "ttle">THỐNG KÊ TỔNG</div>

            <div class = "RATE">
                <div class = "Sttle">1. THỐNG KÊ VỀ NHÂN VIÊN </div>
                <div class = "insider1">
                    <div>
                        <div class = "btS">
                            <a href = "#"><button>XEM CHI TIẾT</button></a>
                        </div>
                    </div>

                </div>
            </div>
            <div class = "RATE">
                <div class = "Sttle">2. THỐNG KÊ VỀ PHIM </div>
                <div class = "insider1">
                    <div>
                        <div class = "btS">
                            <a href = "rpm"><button>XEM CHI TIẾT</button></a>
                        </div>
                    </div>

                </div>
            </div>
            <div class = "RATE">
                <div class = "Sttle">3. THỐNG KÊ VỀ VÉ </div>
                <div class = "insider1">
                    <div>
                        <div class = "btS">
                            <a href = "rpp?type=TK"><button>XEM CHI TIẾT</button></a>
                        </div>
                    </div>

                </div>
            </div>
            <div class = "RATE">
                <div class = "Sttle">4. THỐNG KÊ VỀ ĐỒ ĂN </div>
                <div class = "insider1">
                    <div>
                        <div class = "btS">
                            <a href = "rpp?type=FD"><button>XEM CHI TIẾT</button></a>
                        </div>
                    </div>

                </div>
            </div>
            <div class = "RATE">
                <div class = "Sttle">5. THỐNG KÊ VỀ HÓA ĐƠN </div>
                <div class = "insider1">
                    <div>
                        <div class = "btS">
                            <a href = "orl"><button>XEM CHI TIẾT</button></a>
                        </div>
                    </div>

                </div>
            </div>
            <div class = "RATE">
                <div class = "Sttle">6. THỐNG KÊ VỀ CƠ SỞ VẬT CHẤT </div>
                <div class = "insider1">
                    <div>
                        <div class = "btS">
                            <a href = "#"><button>XEM CHI TIẾT</button></a>
                        </div>
                    </div>

                </div>
            </div>
            <div class = "RATE">
                <div class = "Sttle">7. THỐNG KÊ VỀ DOANH THU </div>
                <div class = "insider1">
                    <div>
                        <div class = "btS">
                            <a href = "icr"><button>XEM CHI TIẾT</button></a>
                        </div>
                    </div>

                </div>
            </div>
            <div class = "RATE">
                <div class = "Sttle">8. THỐNG KÊ VỀ LỢI NHUẬN </div>
                <div class = "insider1">
                    <div>
                        <div class = "btS">
                            <a href = "itr"><button>XEM CHI TIẾT</button></a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
