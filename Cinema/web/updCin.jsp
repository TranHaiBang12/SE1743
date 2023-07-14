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
                padding-top: 40px;
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

                <div class = "oIN4">
                    <form action = "updcin" method = "post">
                        <div class = "insideoIN4">
                            <c:if test = "${requestScope.check != null}">
                                <div class = "ms">
                                    ${requestScope.ms}
                                </div>
                                <div>
                                    Mã: ${requestScope.cin.getCinID()}
                                </div>
                                <div>
                                    Tên: ${requestScope.cin.getCinName()}
                                </div>
                                <div>
                                    Loại: ${requestScope.cin.getCtypeName()}
                                </div>
                                <div>
                                    Thành phố: ${requestScope.cin.getCity()}
                                </div>
                                <div>
                                    Phố: ${requestScope.cin.getStreet()}
                                </div>
                                <div>
                                    Địa chỉ: ${requestScope.cin.getAddress()}
                                </div>
                                <div>
                                    Số phòng: ${requestScope.cin.getNoRoom()}
                                </div>
                                <div>
                                    Fax: ${requestScope.cin.getFax()}
                                </div>
                                <div>
                                    Hotline: ${requestScope.cin.getHotline()}
                                </div>
                                <div class = "btS">
                                    <a href = "updcin?id=${requestScope.cin.getCinID()}"><button type = "button">SỬA</button></a>
                                </div>
                            </div>
                        </c:if>
                        <c:if test = "${requestScope.check == null}">
                            <c:if test = "${requestScope.ms != null}">
                                <div class = "ms">
                                    ${requestScope.ms}
                                </div>
                            </c:if>
                            <input type ="text" name ="code" hidden value ="${requestScope.cin.getCinID()}"/>
                            <div>
                                Mã: ${requestScope.cin.getCinID()}
                            </div>
                            <div>
                                Tên: <input type = "text" name ="name" value = "${requestScope.cin.getCinName()}"/>
                            </div>
                            <div>
                                Loại: 
                                <select name ="type">
                                    <c:forEach items = "${requestScope.type}" var = "i">
                                        <option value ="${i.getCinID()}" ${requestScope.cin.getCinType().equals(i.getCinID())?"selected":""}>${i.getCinName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                Thành phố: <input type = "text" name ="city" value = "${requestScope.cin.getCity()}"/>
                            </div>
                            <div>
                                Phố: <input type = "text" name ="street" value = "${requestScope.cin.getStreet()}"/>
                            </div>
                            <div>
                                Địa chỉ: <input type = "text" name ="address" value = "${requestScope.cin.getAddress()}"/>
                            </div>
                            <div>
                                Số phòng: <input type = "text" name ="noroom" value = "${requestScope.cin.getNoRoom()}"/>
                            </div>
                            <div>
                                Fax: <input type = "text" name ="fax" value = "${requestScope.cin.getFax()}"/>
                            </div>
                            <div>
                                Hotline: <input type = "text" name ="hotline" value = "${requestScope.cin.getHotline()}"/>
                            </div>
                            <c:if test = "${requestScope.check == null}">
                                <div class = "btS">
                                    <button type = "submit">LƯU</button>
                                </div>
                            </c:if>
                        </form>
                    </c:if>
                </div>

            </div>

        </div>

    </div>
    <div id = "footer">
        <%@include file = "footer.jsp" %>
    </div>
</body>
</html>
