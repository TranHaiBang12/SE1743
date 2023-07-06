<%-- 
    Document   : updDevice
    Created on : Jul 5, 2023, 7:10:20 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css"/>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <style>
            .in4{
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .imgE img{
                width: 100%;
                height: 100%;
            }

            .imgE{
                margin-right: 50px;
                width: 400px;
            }

            .insideoIN4{
                font-size: 20px;
            }
            .insideoIN4 div{
                margin-bottom: 20px;
            }

            .insideoIN4 div input{
                font-size: 20px;

            }

            .insideoIN4 div select{
                font-size: 20px;

            }

            .btS{
            }

            .btS button{
                font-size: 18px;
                padding: 5px;
                background-color: red;
                color: white;
                cursor: pointer;
            }

            .ttle{
                text-align: center;
                font-weight: bold;
                margin-bottom: 20px;
                font-size: 25px;
                padding-top: 20px;
            }



            .ms{
                color: red;
            }
        </style>
    </head>
    <body>
        <div id = "header">
            <%@include file = "header.jsp" %>
        </div><!-- comment -->
        <div class = "body">
            <div class = "ttle">
                SỬA THÔNG TIN
            </div>
            <div class = "in4">
                <div class = "imgE">
                    <img src ="${requestScope.dd.getImg()}"/>
                </div>
                <div class = "oIN4">
                    <form action = "upddv" method = "post">
                        <div class = "insideoIN4">
                            <c:if test = "${requestScope.check != null}">
                                <div class = "ms">
                                    ${requestScope.ms}
                                </div>
                                <div>
                                    Mã Thiết Bị: ${requestScope.device.getDeviceCode()}
                                </div>
                                <div>
                                    Loại Thiết Bị: ${requestScope.device.getTypeName()}
                                </div>
                                <div>
                                    Giá Thiết Bị: ${requestScope.device.getPrice()}
                                </div>
                                <div>
                                    Mô tả: ${requestScope.device.getDescript()}
                                </div>
                                <div>
                                    Ảnh: ${requestScope.dd.getImg()}
                                </div>
                            </div>
                        </c:if>
                        <c:if test = "${requestScope.check == null}">
                            <c:if test = "${requestScope.ms != null}">
                                <div class = "ms">
                                    ${requestScope.ms}
                                </div>
                            </c:if>
                            <input type ="text" name ="ocode" hidden value ="${requestScope.dd.getDeviceCode()}"/>
                            <div>
                                Mã Thiết Bị: <input type = "text" name ="code" value = "${requestScope.dd.getDeviceCode()}"/>
                            </div>
                            <div>
                                Loại Thiết Bị: 
                                <select name ="type">
                                    <c:forEach items = "${requestScope.type}" var = "i">
                                        <option value ="${i.getIdType()}" ${requestScope.dd.getIdType().equals(i.getIdType())?"selected":""}>${i.getTypeName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                Giá Thiết Bị: <input type ="text" name ="price" value ="${requestScope.dd.getPrice()}"/>
                            </div>
                            <div>
                                Mô tả: <input type ="text" name ="descript" value ="${requestScope.dd.getDescript()}"/>
                            </div>
                            <div>
                                Ngày phân bố: <input type ="text" name ="descript" value ="${requestScope.dd.getDte()}"/>
                            </div>
                            <div>
                                Thời gian phân bố: <input type ="text" name ="descript" value ="${requestScope.dd.getTme()}"/>
                            </div>
                            <div>
                                Barcode: <input type ="text" name ="descript" value ="${requestScope.dd.getDeviceBarCode()}"/>
                            </div>
                            <div>
                                Tình trạng: 
                                <select name ="stat" id = "stat" onchange="cnge()">
                                    <c:forEach items = "${requestScope.stat}" var = "i">
                                        <option value ="${i}" ${requestScope.dd.getStatus().equals(i)?"selected":""}>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
      
                            <div>
                                Ảnh: <input type ="text" name ="img" value ="${requestScope.dd.getImg()}"/> 
                            </div>
                        </c:if>
                </div>
                <c:if test = "${requestScope.check == null}">
                    <div class = "btS">
                        <button type = "submit">LƯU</button>
                    </div>
                </c:if>
                </form>
            </div>

        </div>

    </div>
    <div id = "footer">
        <%@include file = "footer.jsp" %>
    </div>
    <script type = "text/javascript">
        function cnge() {
            var e = document.getElementById("stat");
            var value = e.value;
            var text = e.options[e.selectedIndex].text;
            console.log(value + " " + text);
        }
    </script>
</body>
</html>
