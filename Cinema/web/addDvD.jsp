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
                THÊM THÔNG TIN
            </div>
            <div class = "in4">
                <c:if test = "${requestScope.check != null}">
                    <div class = "imgE">
                        <img src ="${requestScope.device.getImg()}"/>
                    </div>
                </c:if>
                <div class = "oIN4">
                    <form id ="frm" action = "adddvd" method = "post">
                        <div class = "insideoIN4">
                            <c:if test = "${requestScope.check != null}">
                                <div class = "ms">
                                    ${requestScope.ms}
                                </div>
                                <div>
                                    Mã Thiết Bị: ${requestScope.device.getDeviceCode()}

                                </div>
                                <div>
                                    Rạp: ${requestScope.device.getCinName()}
                                </div>
                                <div>
                                    Phòng: ${requestScope.device.getRoomID()}
                                </div>
                                <div>
                                    Ngày phân bố: ${requestScope.device.getDte()}
                                </div><!-- comment -->
                                <div>
                                    Thời gian phân bố:  ${requestScope.device.getTme()}
                                </div>
                                <div>
                                    Barcode:  ${requestScope.device.getDeviceBarCode()}
                                </div>
                                <div>
                                    Ảnh: ${requestScope.img}
                                </div>
                            </div>
                        </c:if>
                        <c:if test = "${requestScope.check == null}">
                            <c:if test = "${requestScope.ms != null}">
                                <div class = "ms">
                                    ${requestScope.ms}
                                </div>
                            </c:if>
                            <input type ="text" name ="check" id ="check" hidden value = "0"/>
                            <div>
                                Mã Thiết Bị: 
                                <select name ="code">
                                    <c:forEach items = "${requestScope.code}" var = "i">
                                        <option value ="${i}" >${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                Rạp:
                                <select name ="cin" onchange="cngeCin()">
                                    <c:forEach items = "${requestScope.cin}" var = "i">
                                        <option value ="${i.getCinID()}" ${cinID == i.getCinID()?"selected":""}>${i.getCinName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                Phòng:
                                <select name ="room">
                                    <c:forEach items = "${requestScope.room}" var = "i">
                                        <option value ="${i.getRoomID()}" ${requestScope.roomID == i.getRoomID()?"selected":""}>${i.getRoomID()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                Ngày phân bố: <input type ="date" required name ="dte" />
                            </div>
                            <div>
                                Thời gian phân bố: <input type ="time" required name ="tme" />
                            </div>
                            <div>
                                Barcode: <input type ="text" name ="bar" required />
                            </div>
                            
                        </c:if>

                        <c:if test = "${requestScope.check == null}">
                            <div class = "btS">
                                <button type = "submit">THÊM</button>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>

        </div>

    </div>
    <div id = "footer">
        <%@include file = "footer.jsp" %>
    </div>
    <script>
        function cngeCin() {
            document.getElementById("check").value = 1;
            document.getElementById("frm").submit();
        }
    </script>
</body>
</html>
