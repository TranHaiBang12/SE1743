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
        <<link rel="stylesheet" href="style.css"/>
        <style>
            .body{
                text-align: center;
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
            }

            .morefIn4 select {
                width: 50%;
                height: 30px;
                font-size: 20px;
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
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div><!-- comment -->
        <div class = "body">
            <div class = "fIn4">
                <div>
                    <h2><span  class = "rd">${requestScope.f.getFoodDescript()}</span></h2>
                </div>
                <div>
                    <img src ="${requestScope.f.getImg()}"/>
                </div>
            </div>
            <div class = "ms">
                ${requestScope.ms}
            </div>
            <div class = "morefIn4">
                <form action = "updf" method = "post">
                    <div>
                        <label for = "img">Ảnh:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <input type ="text" id ="img" required name ="img" placeholder ="${requestScope.f.getImg()}"/>
                    </div>
                    <div>
                        <label for = "fd">Tên:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <input type ="text" id ="fd" required name ="fd" placeholder ="${requestScope.f.getFoodDescript()}"/>
                    </div>

                    <div>
                        <label for = "tl">Thể Loại:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <select name = "tl">
                            <c:forEach items = "${requestScope.type}" var = "i">
                                <option value = "${i.getFoodType()}" ${(requestScope.f.getFoodType() != null && requestScope.f.getFoodType().equals(i.getFoodType()))?"selected":""}
                                        >${i.getTypeName()}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <label for = "dc">Khuyến Mại(%):</label>
                        <input type="number"  class="tk_in4" min ="0" step="0.01" max ="1" required id ="dc" name = "dc" placeholder = "${requestScope.f.getDiscount()}">
                    </div>
                    <div>
                        <label for = "price">Giá:(.000đ):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><!-- comment -->
                        <input type ="number"  class="tk_in4" required  min="0" max ="1000" id ="price" name ="price" placeholder = "${requestScope.f.getPrice()}"/>
                    </div>
                    <div>
                        <label for = "stt">Tình Trạng:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <select name = "stt">
                            <c:forEach items = "${requestScope.status}" var = "i">
                                <option value = "${i}" ${(requestScope.f.getStatus() != null && requestScope.f.getStatus().equals(i))?"selected":""}>${i}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label for = "kd">Kinh Doanh:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <select name ="kd">
                            <c:forEach items = "${requestScope.discontinued}" var = "i">
                                <option value = "${i}" ${(requestScope.f.getDiscontinued() != null && requestScope.f.getDiscontinued().equals(dc))?"selected":""}>${i}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type ="text" hidden name ="id" value ="${requestScope.id}"/>
                    <div>
                        <input class ="m" type = "submit" value = "UPDATE"/>
                    </div>
                </form>
            </div>
        </div>
        <div id = "footer">
            <%@include file = "footer.jsp" %>
        </div>
    </body>
</html>
