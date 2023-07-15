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

            input{
                width: 50%;
                height: 30px;
                font-size: 20px;
                margin-bottom: 10px;
            }

            select {
                width: 50%;
                height: 30px;
                font-size: 20px;
                margin-bottom: 10px;
            }

            .m{
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

            .morefIn4 label{
                margin-right: 10px;
            }

            .ms{
                font-size: 20px;
                color: red;
                margin-top: 20px;
            }

            .bodyTitle{
                color: black;
                font-size: 40px;
                padding-top: 40px;
                padding-left: 45px;
            }

            .body{
                padding-left: 40px;
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
            
            .m{
                margin-top: 30px;
                padding-bottom:10px;
                padding-left:25px;
                padding-right:25px;
                border-radius: 15px;
                background-color: red;
                color:white;
                cursor: pointer;
            }
            
            .blk{
                font-weight: bold;
                color: red;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div><!-- comment -->
        <div class = "body">
            <div class = "bodyTitle">
                SỬA:
            </div>

            <div class ="bodyContent">
                <div class = "img">  
                    <img src="${requestScope.f.getImg()}" alt="alt"/>
                </div>
                <div class = "content">
                    <div class = "name">${requestScope.data.getMovName()}</div>
                    <div class = "oInfo">
                        <form action = "updf" method = "post">
                            <div class = "blk">${requestScope.ms}</div>
                            <span>Mã:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            <input type ="text" id ="img" readonly name ="code" value ="${requestScope.f.getProductCode()}"/>
                            <br/>
                            <span>Ảnh:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            <input type ="text" id ="img" required name ="img" placeholder ="${requestScope.f.getImg()}"/>
                            <br/>
                            <span>Tên: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            <input type ="text" id ="img" required name ="fd" placeholder ="${requestScope.f.getFoodDescript()}"/>
                            <br/>
                            <span>Thể loại:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            <select name = "tl">
                                <c:forEach items = "${requestScope.type}" var = "i">
                                    <option value = "${i.getFoodType()}" ${(requestScope.f.getFoodType() != null && requestScope.f.getFoodType().equals(i.getFoodType()))?"selected":""}
                                            >${i.getTypeName()}</option>
                                </c:forEach>
                            </select>
                            <br/>
                            <span>Giá: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            <input type ="number" id ="img" min ="0" max ="1000" required name ="price" placeholder ="${requestScope.f.getPrice()}"/>
                            <br/>
                            <span>Khuyến Mại:  </span>
                            <input type ="number" id ="img" min ="0" step ="0.01" max ="1" required name ="dc" placeholder ="${requestScope.f.getDiscount()}"/>
                            <br/>
                            <span>Tình trạng: &nbsp;&nbsp;</span>
                            <select name = "stt">
                                <c:forEach items = "${requestScope.status}" var = "i">
                                    <option value = "${i}" ${(requestScope.f.getStatus() != null && requestScope.f.getStatus().equals(i))?"selected":""}>${i}</option>
                                </c:forEach>
                            </select>
                            <br/>
                            <span>Kinh Doanh:&nbsp;</span>
                            <select name ="kd">
                                <c:forEach items = "${requestScope.discontinued}" var = "i">
                                    <option value = "${i}" ${(requestScope.f.getDiscontinued() != null && requestScope.f.getDiscontinued().equals(dc))?"selected":""}>${i}</option>
                                </c:forEach>
                            </select>
                            <br/>
                            <input type ="text" hidden name ="id" value ="${requestScope.id}"/>
                            <input class ="m" type ="submit" value ="UPDATE">
                            <br/>
                        </form>
                    </div>
                </div>
            </div>
 
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
