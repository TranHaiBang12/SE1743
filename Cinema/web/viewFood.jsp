<%-- 
    Document   : viewFood
    Created on : Jun 25, 2023, 9:56:00 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <<link rel="stylesheet" href="style.css?version=1"/>
        <style>
            .body{
            }

            h2{
                padding-top: 20px;
                font-size: 30px;
                margin-bottom: 30px;
            }

            .fIn4 img{
                width: 800px;
                height: 600px;
            }

            .morefIn4{
                padding-bottom: 20px;
            }

            .morefIn4 div{
                margin-top: 20px;
                font-size: 20px;
                font-weight: bold;
            }

            .morefIn4 input{
                width: 50%;
                height: 30px;
                font-size: 20px;
                cursor: pointer;
                color: white;
                background-color: red;
            }

            .rd{
                color: red;
            }

            .bodyTitle{
                color: black;
                font-size: 40px;
                padding-top: 40px;
                padding-left: 45px;
            }


            .bodyContent{
                display: flex;
                padding-top: 30px;
                padding-bottom: 50px;
                justify-content: flex-start;
                margin-left: 50px;
            }

            .bodyContent .content{
                color: black;
                font-size: 20px;

            }

            .bodyContent .content span{
                font-weight: bold;
            }


            .img{
                width: 30%;
                height: 450px;
                margin-right: 50px;
            }

            .img img{
                width: 100%;
                height:100%;
            }

            .name{
                font-size: 40px;
                font-weight: bold;
                padding-bottom: 5px;
                margin-bottom: 30px;
            }

            .oInfo{
                line-height: 35px;
            }
            
            .body{
                padding-left: 40px;
            }

            .bodyContent .content button{
                margin-top: 30px;
                padding-top:10px;
                padding-bottom:10px;
                padding-left:25px;
                padding-right:25px;
                border-radius: 15px;
                background-color: red;
                color:white;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div><!-- comment -->
        <div class = "body">
            <div class = "bodyTitle">
                CHI TIẾT
            </div>

            <div class ="bodyContent">
                <div class = "img">  
                    <img src="${requestScope.f.getImg()}" alt="alt"/>
                </div>
                <div class = "content">
                    <div class = "name">${requestScope.data.getMovName()}</div>
                    <div class = "oInfo">
                        <span>Mã: </span>${requestScope.f.getProductCode()}
                        <br/>
                        <span>Tên: </span>${requestScope.f.getFoodDescript()}
                        <br/>
                        <span>Thể loại:  </span>${requestScope.f.getFoodType()}
                        <br/>
                        <span>Giá:  </span>${requestScope.f.getPrice()}
                        <br/>
                        <span>Khuyến Mại:  </span>${requestScope.f.getDiscount()}
                        <br/>
                        <span>Tình trạng:  </span>${requestScope.f.getStatus()}
                        <br/>
                        <span>Kinh Doanh:  </span>${requestScope.dc}
                        <br/>
                        <a href="updf?id=${requestScope.id}"><button type ="submit" value ="UPDATE">UPDATE</button></a>
                        <br/>
                        <button type ="button" value ="DELETE" onclick ="delF('${requestScope.id}')">DELETE</button>
                    </div>
                </div>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
        <script type = "text/javascript">
            function delF(id) {
                if (confirm("Bạn có chắc muốn xóa sản phẩm với id = " + id)) {
                    window.location = "delf?id=" + id;
                }
            }
        </script><!-- comment -->
    </body>
</html>
